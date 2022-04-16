package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.interfaces.EmployeeTypeDBIF;
import exceptions.NotFoundException;
import model.Employee.EmployeeType;

public class EmployeeTypeDB implements EmployeeTypeDBIF {

    // PreparedStatements for the EmployeeTypeDB class
    private static final String FIND_ALL = "select * from EmployeeTypes";
    private static final String FIND_BY_ID = "select * from EmployeeTypes where Id = ?";

    private PreparedStatement findAll;
    private PreparedStatement findById;

    /**
     * Constructor for the EmployeeTypeDB class
     * @throws SQLException
     */
    public EmployeeTypeDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
    }

    @Override
    public List<EmployeeType> findAll() throws SQLException, NotFoundException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<EmployeeType> employeeTypes = buildObjects(rs);
        return employeeTypes;
    }

    @Override
    public EmployeeType findById(int id) throws SQLException, NotFoundException {
        EmployeeType employeeType = null;
        ResultSet rs;
        findById.setInt(1, id);
        rs = findById.executeQuery();
        while(rs.next()) {
            employeeType = buildObject(rs);
        }

        if(employeeType == null) { throw new NotFoundException("EmployeeType", id); }

        return employeeType;
    }

    // local methods
    private EmployeeType buildObject(ResultSet rs) throws SQLException {
        EmployeeType employeeType = EmployeeType.valueOf(rs.getString("Name"));
        employeeType.setId(rs.getInt("Id"));
        return employeeType;
    }

    private List<EmployeeType> buildObjects(ResultSet rs) throws SQLException {
        List<EmployeeType> employeeTypes = new ArrayList<>();
        while(rs.next()) {
            employeeTypes.add(buildObject(rs));
        }
        return employeeTypes;
    }
    
}
