package database;

import exceptions.NotFoundException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.CustomerController;
import controller.EmployeeController;
import database.interfaces.OrderDBIF;
import model.Customer;
import model.Employee;
import model.Order;

public class OrderDB implements OrderDBIF {

    //PreparedStatments
    private static final String FIND_ALL = "select * from Orders";
    private static final String FIND_BY_ID = "select * from Orders where id = ?";
    private static final String FIND_BY_CUSTOMER = "select * from Orders where CustomerId = ?";
    private static final String CREATE_ORDER = "insert into Orders values(?, ?, ?)";
    private static final String DELETE_ORDER = "delete from Orders where id = ?";

    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement findByCustomer;
    private PreparedStatement createOrder;
    private PreparedStatement deleteOrder;

    EmployeeController employeeCtrl = new EmployeeController();
    CustomerController customerCtrl = new CustomerController();
  
    public OrderDB()throws SQLException{
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findById= DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
        findByCustomer = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_CUSTOMER);
        createOrder= DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
        deleteOrder = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ORDER);
    }

    @Override
    public List<Order> findAll() throws SQLException,NotFoundException{
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Order> orders = buildObjects(rs);
        return orders;
    }

    @Override    
    public Order findById(int id) throws SQLException, NotFoundException{
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
    public List<Order> findByCustomer(Customer customer) throws SQLException, NotFoundException {
    	ResultSet rs;
    	findByCustomer.setInt(1, customer.getId());
    	rs = findByCustomer.executeQuery();
    	List<Order> orders = buildObjects(rs);
    	return orders;
    }

    @Override
    public void createOrder(Order order) throws SQLException{
        createOrder.setInt(1, order.getCustomer().getId());
        createOrder.setInt(2, order.getEmployee().getId());
        createOrder.setDate(3, Date.valueOf(order.getOrderDate()));
        order.setId(DBConnection.getInstance().executeSqlInsertWithIdentity(createOrder));
    }

    @Override
    public void deleteOrder(Order order) throws SQLException{
        deleteOrder.setInt(1, order.getId());
        deleteOrder.executeUpdate();
    }

    private Order buildObject(ResultSet rs)throws SQLException,NotFoundException{
        Employee employee = employeeCtrl.findById(rs.getInt("EmployeeId"));
        Customer customer = customerCtrl.findById(rs.getInt("CustomerId"));
        Order order = new Order(employee, customer);
        order.setId(rs.getInt("Id"));
        order.setOrderDate(rs.getDate("Date").toLocalDate());
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
