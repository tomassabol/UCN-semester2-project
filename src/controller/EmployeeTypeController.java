package controller;

import java.sql.SQLException;
import java.util.List;

import database.EmployeeTypeDB;
import database.interfaces.EmployeeTypeDBIF;
import exceptions.NotFoundException;
import model.Employee.EmployeeType;

public class EmployeeTypeController {
    
    private EmployeeTypeDBIF employeeTypeDBIF;

    /**
     * Constructor for the EmployeeController class
     * @throws SQLException
     */
    public EmployeeTypeController() throws SQLException {
        employeeTypeDBIF = new EmployeeTypeDB();
    }

    /**
     * Finds all employeeTypes
     * @return a list of all EmployeeTypes
     * @throws SQLException
     * @throws NotFoundException
     */
    public List<EmployeeType> findAll() throws SQLException, NotFoundException {
        List<EmployeeType> employeeTypes = employeeTypeDBIF.findAll();
        return employeeTypes;
    }

    /**
     * Finds the employeeType with the desired ID
     * @param id - desired id
     * @return employeeType with the ID
     * @throws SQLException
     * @throws NotFoundException
     */
    public EmployeeType findById(int id) throws SQLException, NotFoundException {
        EmployeeType employeeType = employeeTypeDBIF.findById(id);
        return employeeType;
    }

}
