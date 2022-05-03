package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.interfaces.CustomerTypeDBIF;
import exceptions.NotFoundException;
import model.Customer.CustomerType;

public class CustomerTypeDB implements CustomerTypeDBIF {
    // PreparedStatements for the CustomerTypeDB class
    private static final String FIND_ALL = "select * from CustomerTypes";
    private static final String FIND_BY_ID = "select * from CustomerTypes where Id = ?";

    private PreparedStatement findAll;
    private PreparedStatement findById;

    /**
     * Constructor for the CustomerTypeDB class
     * @throws SQLException
     */
    public CustomerTypeDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
    }

    /**
     * @return a list of all ProductTypes in the DB
     */
    @Override
    public List<CustomerType> findAll() throws SQLException, NotFoundException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<CustomerType> customerTypes = buildObjects(rs);
        return customerTypes;
    }

    /**
     * @return finds a specific CustomerType in the DB by the desired ID
     * @throws SQLException
     * @throws NotFoundException
     */
    @Override
    public CustomerType findById(int id) throws SQLException, NotFoundException {
        CustomerType customerType = null;
        ResultSet rs;
        findById.setInt(1, id);
        rs = findById.executeQuery();
        while(rs.next()) {
            customerType = buildObject(rs);
        }

        if(customerType == null) { throw new NotFoundException("CustomerType", id); }

        return customerType;
    }

    // local methods

    private CustomerType buildObject(ResultSet rs) throws SQLException {
        CustomerType customerType = CustomerType.valueOf(rs.getString("Name"));
        customerType.setId(rs.getInt("Id"));
        return customerType;
    }

    private List<CustomerType> buildObjects(ResultSet rs) throws SQLException {
        List<CustomerType> customerTypes = new ArrayList<>();
        while(rs.next()) {
            customerTypes.add(buildObject(rs));
        }
        return customerTypes;
    }
    
}
