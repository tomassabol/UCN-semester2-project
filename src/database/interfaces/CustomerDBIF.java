package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Customer;

public interface CustomerDBIF {
    public List<Customer> findAll() throws SQLException, NotFoundException;
    public Customer findById(int id) throws SQLException, NotFoundException;
    public void createCustomer(Customer customer) throws SQLException;
    public void updateCustomer(Customer customer) throws SQLException;
    public void deleteCustomer(Customer customer) throws SQLException;
}
