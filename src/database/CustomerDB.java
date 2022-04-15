package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.interfaces.CityDBIF;
import database.interfaces.CustomerDBIF;
import database.interfaces.CustomerTypeDBIF;
import exceptions.NotFoundException;
import model.City;
import model.Customer;
import model.Customer.CustomerType;

public class CustomerDB implements CustomerDBIF {

    // PreparedStatements for the CustomerDB class
    private static final String FIND_ALL = "select * from Customers";
    private static final String FIND_BY_ID = "select * from Customers where Id = ?";
    private static final String CREATE_CUSTOMER = "insert into Customers values(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CUSTOMER = "update Customers set Name = ?, Email = ?, Phone = ?, Zip = ?, Address = ?, CustomerTypeId = ? from Customers where Id = ?";
    private static final String DELETE_CUSTOMER = "delete from Customers where Id = ?";

    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement createCustomer;
    private PreparedStatement updateCustomer;
    private PreparedStatement deleteCustomer;

    CustomerTypeDBIF customerTypeDBIF = new CustomerTypeDB();
    CityDBIF cityDBIF = new CityDB();

    /**
     * Constructor for the CustomerDB class
     * @throws SQLException
     */
    public CustomerDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
        createCustomer = DBConnection.getInstance().getConnection().prepareStatement(CREATE_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
        updateCustomer = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_CUSTOMER);
        deleteCustomer = DBConnection.getInstance().getConnection().prepareStatement(DELETE_CUSTOMER);
    }

    @Override
    public List<Customer> findAll() throws SQLException, NotFoundException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Customer> customers = buildObjects(rs);
        return customers;
    }

    @Override
    public Customer findById(int id) throws SQLException, NotFoundException {
        Customer customer = null;
        ResultSet rs;
        findById.setInt(1, id);
        rs = findById.executeQuery();
        while(rs.next()) {
            customer = buildObject(rs);
        }

        if (customer == null) { throw new NotFoundException("Customer", id); }

        return customer;
    }

    @Override
    public void createCustomer(Customer customer) throws SQLException {
        createCustomer.setString(1, customer.getName());
        createCustomer.setString(2, customer.getEmail());
        createCustomer.setString(3, customer.getPhone());
        createCustomer.setString(4, customer.getZipCode().getZipCode());
        createCustomer.setString(5, customer.getAddress());
        createCustomer.setInt(6, customer.getCustomerType().getId());
        customer.setId(DBConnection.getInstance().executeSqlInsertWithIdentity(createCustomer));
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        updateCustomer.setString(1, customer.getName());
        updateCustomer.setString(2, customer.getEmail());
        updateCustomer.setString(3, customer.getPhone());
        updateCustomer.setString(4, customer.getZipCode().getZipCode());
        updateCustomer.setString(5, customer.getAddress());
        updateCustomer.setInt(6, customer.getCustomerType().getId());
        updateCustomer.setInt(7, customer.getId());
        updateCustomer.executeUpdate();
    }

    @Override
    public void deleteCustomer(Customer customer) throws SQLException {
        deleteCustomer.setInt(1, customer.getId());
        deleteCustomer.executeUpdate();
    }

    // local methods

    private Customer buildObject(ResultSet rs) throws SQLException, NotFoundException {
        // TODO: change this to call CityController and CustomerTypeController
        City zipCode = cityDBIF.findByZip(rs.getString("ZIP"));
        CustomerType customerType = customerTypeDBIF.findById(rs.getInt("CustomerTypeId"));
        Customer customer = new Customer(rs.getString("Name"), rs.getString("Email"), rs.getString("Phone"), zipCode, rs.getString("Address"), customerType);
        customer.setId(rs.getInt("Id"));
        return customer;
    }

    private List<Customer> buildObjects(ResultSet rs) throws SQLException, NotFoundException {
        List<Customer> customers = new ArrayList<>();
        while(rs.next()) {
            customers.add(buildObject(rs));
        }
        return customers;
    }
    
}
