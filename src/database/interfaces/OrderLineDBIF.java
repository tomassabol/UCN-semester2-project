package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.OrderLine;

public interface OrderLineDBIF {
    public List<OrderLine> findAll() throws SQLException, NotFoundException;
    public OrderLine findById(int id) throws SQLException, NotFoundException;
    public void createOrderLine(OrderLine orderLine) throws SQLException;
    public void updateOrderLine(OrderLine orderLine) throws SQLException;
    public void deleteOrderLine(OrderLine orderLine) throws SQLException;
}
