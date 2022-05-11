package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Customer;
import model.Order;

public interface OrderDBIF {
    public List<Order> findAll() throws SQLException, NotFoundException;
    public Order findById(int id) throws SQLException, NotFoundException;
    public List<Order> findByCustomer(Customer customer) throws SQLException, NotFoundException;
    public void createOrder(Order order) throws SQLException;
    public void deleteOrder(Order order) throws SQLException;
}
