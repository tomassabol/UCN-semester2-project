package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Order;
import model.OrderLine;

public interface OrderDetailsDBIF {
    public List<OrderLine> findByOrderId(int id) throws SQLException, NotFoundException;
    public void createOrderDetails(Order order, OrderLine orderLine) throws SQLException;
    public void deleteOrderDetails(Order order, OrderLine orderLine) throws SQLException;
}
