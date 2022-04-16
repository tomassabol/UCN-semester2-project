package database;

import exceptions.NotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.interfaces.CustomerDBIF;
import database.interfaces.EmployeeDBIF;
import database.interfaces.OrderDBIF;
import model.Customer;
import model.Employee;
import model.Order;

public class OrderDB implements OrderDBIF {

    //PreparedStatments
    private static final String FIND_ALL = "select * from Orders";
    private static final String FIND_BY_ID = "select * from Orders where id = ?";
    private static final String CREATE_ORDER = "insert into Orders values( ?,  ?)";
    private static final String DELETE_ORDER = "delete from Orders where id = ?";

    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement createOrder;
    private PreparedStatement deleteOrder;

/*    
    private Employee employee;
    private Customer customer;
    private Set<OrderLine> orderLines;
*/
    EmployeeDBIF employeeDBIF = new EmployeeDB();
    CustomerDBIF customerDBIF = new CustomerDB();
  
    OrderDB()throws SQLException{
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findById= DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
        createOrder= DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDER);
        deleteOrder = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ORDER);
    }

    @Override
    public List<Order> findAll()throws SQLException,NotFoundException{
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Order> orders = buildObjects(rs);
        return orders;
    }

    @Override    
    public Order findByID(int id)throws SQLException, NotFoundException{
        Order order = null;
        ResultSet rs;
        findById.setInt(1, id);
        rs = findById.executeQuery();
        while(rs.next()){
            order = buildObject(rs);
        }

        if(order == null){throw new NotFoundException("Order",id);}
        return order;
    }

    @Override
    public void createOrder(Order order)throws SQLException{
        createOrder.setInt(1, order.getCustomer().getId());
        createOrder.setInt(2, order.getEmployee().getId());
        order.setId(DBConnection.getInstance().executeSqlInsertWithIdentity(createOrder));
    }

    @Override
    public void deleteOrder(Order order)throws SQLException{
        deleteOrder.setInt(1, order.getId());
        deleteOrder.executeUpdate();
    }

    private Order buildObject(ResultSet rs)throws SQLException,NotFoundException{
        Employee employee = employeeDBIF.findById(rs.getInt("Id"));
        Customer customer = customerDBIF.findById(rs.getInt("Id"));
         Order order = new Order(employee, customer);
        return order;
    }

    private List<Order> buildObjects(ResultSet rs)throws SQLException, NotFoundException{
        List<Order> orders = new ArrayList<>();
        while(rs.next()){
            orders.add(buildObject(rs));
        }
        return orders;
    }
}
