package controller;

import java.sql.SQLException;
import java.util.List;

import database.OrderDetailsDB;
import database.interfaces.OrderDetailsDBIF;
import exceptions.NotFoundException;
import model.Order;
import model.OrderLine;

public class OrderDetailsController {
    
    private OrderDetailsDBIF orderDetailsDBIF;

    /**
     * Constructor for the OrderDetailsController class
     * @throws SQLException
     */
    public OrderDetailsController() throws SQLException {
        orderDetailsDBIF = new OrderDetailsDB();
    }

    /**
     * Finds all OrderLines per Order
     * @param id - order ID
     * @return list of all OrderLines in the Order
     * @throws SQLException
     * @throws NotFoundException
     */
    public List<OrderLine> findByOrderId(int id) throws SQLException, NotFoundException {
        List<OrderLine> orderLines = orderDetailsDBIF.findByOrderId(id);
        return orderLines;
    }

    /**
     * Insert into OrderLineDetails
     * @param order - order to get OrderId
     * @param orderLine - orderLine to get OrderLineId
     * @throws SQLException
     */
    public void createOrderDetails(Order order, OrderLine orderLine) throws SQLException {
        orderDetailsDBIF.createOrderDetails(order, orderLine);
    }

    /**
     * delete order details from DB
     * @param order - order to get OrderId
     * @param orderLine - orderLine to get OrderLineId
     * @throws SQLException
     */
    public void deleteOrderDetails(Order order, OrderLine orderLine) throws SQLException {
        orderDetailsDBIF.deleteOrderDetails(order, orderLine);
    }
}
