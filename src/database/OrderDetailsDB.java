package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.OrderLineController;
import database.interfaces.OrderDetailsDBIF;
import database.interfaces.OrderLineDBIF;
import exceptions.NotFoundException;
import model.Order;
import model.OrderLine;

public class OrderDetailsDB implements OrderDetailsDBIF {

    //Prepared statements
    private static final String FIND_ALL = "select * from OrderDetails where OrderId = ?";
    private static final String CREATE_ORDER_DETAILS = "insert into OrderDetails values (?, ?)";
    private static final String DELETE_ORDER_DETAILS = "delete from OrderDetails where OrderId = ?, OrderLineId = ?";
    private static final String DELETE_ALL_ORDER_DETAILS = "delete from OrderDetails where OrderId = ?";

    private PreparedStatement findAll;
    private PreparedStatement createOrderDetails;
    private PreparedStatement deleteOrderDetails;
    private PreparedStatement deleteAllOrderDetails;

    OrderLineDBIF orderLineDBIF = new OrderLineDB();

    public OrderDetailsDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        createOrderDetails = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDER_DETAILS, Statement.RETURN_GENERATED_KEYS);
        deleteOrderDetails = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ORDER_DETAILS);
        deleteAllOrderDetails = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ALL_ORDER_DETAILS);
    }
      

    @Override
    public List<OrderLine> findByOrderId(int id) throws SQLException, NotFoundException {
        ResultSet rs;
        findAll.setInt(1, id);
        rs = findAll.executeQuery();
        List<OrderLine> orderLines = buildObjects(rs);
        return orderLines;
    }

    @Override
    public void createOrderDetails(Order order, OrderLine orderLine) throws SQLException {
        createOrderDetails.setInt(1, order.getId());
        createOrderDetails.setInt(2, orderLine.getId());
        createOrderDetails.execute();
    }

    @Override
    public void deleteOrderDetails(Order order, OrderLine orderLine) throws SQLException {
        deleteOrderDetails.setInt(1, order.getId());
        deleteOrderDetails.setInt(2, orderLine.getId());
        deleteOrderDetails.executeUpdate();
    }

    @Override
    public void deleteAllOrderDetails(Order order) throws SQLException {
        deleteAllOrderDetails.setInt(1, order.getId());
        deleteAllOrderDetails.executeUpdate();
    }

    // local methods

    private OrderLine buildObject(ResultSet rs) throws SQLException, NotFoundException {
        OrderLine orderLine = orderLineDBIF.findById(rs.getInt("OrderLineId"));
        return orderLine;
    }

    private List<OrderLine> buildObjects(ResultSet rs) throws SQLException, NotFoundException {
        List<OrderLine> orderLines = new ArrayList<>();
        while(rs.next()) {
            orderLines.add(buildObject(rs));
        }
        return orderLines;
    }
    
}
