package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Customer.CustomerType;;

public interface CustomerTypeDBIF {
    public List<CustomerType> findAll() throws SQLException, NotFoundException;
    public CustomerType findById(int id) throws SQLException, NotFoundException;
}
