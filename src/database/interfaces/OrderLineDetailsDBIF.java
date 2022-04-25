package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Item;
import model.OrderLine;

public interface OrderLineDetailsDBIF {
    public List<Item> findByOrderLine(int orderLineId) throws SQLException, NotFoundException;
    public void createOrderLineDetails(OrderLine orderLine, Item item) throws SQLException;
    public void deleteOrderLineDetails(OrderLine orderLine, Item item) throws SQLException;
    public void deleteAllOrderLineDetails(OrderLine orderLine) throws SQLException;
}
