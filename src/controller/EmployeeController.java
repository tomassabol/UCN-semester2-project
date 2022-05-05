package controller;

import java.sql.SQLException;
import java.util.List;

import database.EmployeeDB;
import database.interfaces.EmployeeDBIF;
import exceptions.NotFoundException;
import model.City;
import model.Department;
import model.Employee;
import model.Employee.EmployeeType;

public class EmployeeController {
    
    private EmployeeDBIF employeeDBIF;

    /**
     * Constructor for the EmployeeController class
     * @throws SQLException
     */
    public EmployeeController() throws SQLException {
        employeeDBIF = new EmployeeDB();
    }

    /**
     * Finds all employees 
     * @return a list of all Employees
     * @throws SQLException
     * @throws NotFoundException
     */
    public List<Employee> findAll() throws SQLException, NotFoundException {
        List<Employee> employees = employeeDBIF.findAll();
        return employees;
    }

    /**
     * Finds the employee with the desired ID
     * @param id - desired id
     * @return employee with the ID
     * @throws SQLException
     * @throws NotFoundException
     */
    public Employee findById(int id) throws SQLException, NotFoundException {
        Employee employee = employeeDBIF.findById(id);
        return employee;
    }

    /**
     * Finds employee by Email
     * Provided email and password must be correct in order to return Employee object
     * @param email - employee email
     * @param password - employee password
     * @return employee
     * @throws SQLException
     * @throws NotFoundException
     */
    public Employee findByEmail(String email, String password) throws SQLException, NotFoundException {
        Employee employee = employeeDBIF.findByEmail(email, password);
        return employee;
    }

    /**
     * creates a new employee
     * @param name - employee's name
     * @param email - employee's email
     * @param phone - employee's phone
     * @param zipCode - employee's zip code
     * @param address - employee's address
     * @param employeeType - employee's Type
     * @param password - employee's password
     * @param CPR - employee's CPR
     * @param department - employee's department
     * @throws SQLException
     */
    public Employee createEmployee(String name, String email, String phone, City zipCode, String address, EmployeeType employeeType, String password, String CPR, Department department) throws SQLException {
        Employee employee = new Employee(name, email, phone, zipCode, address, employeeType, password, CPR, department);
        employeeDBIF.createEmployee(employee);
        return employee;
    }

    /**
     * Updates employee details
     * @param name - updated name
     * @param email - updated email
     * @param phone - updated phone
     * @param zipCode - updated zip code
     * @param address - updated address
     * @param employeeType - updated Type
     * @param password - updated password
     * @param department - updated department
     * @throws SQLException
     */
    public void updateEmployee(Employee employee, String name, String email, String phone, City zipCode, String address, EmployeeType employeeType, String password, Department department) throws SQLException {
        employee.setName(name);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setPhone(phone);
        employee.setZipCode(zipCode);
        employee.setAddress(address);
        employee.setEmployeeType(employeeType);
        employee.setPassword(password);
        employee.setDepartment(department);
        employeeDBIF.updateEmployee(employee);
    }

    /**
     * deletes employee
     * @param employee - employee to be deleted
     * @throws SQLException
     */
    public void deleteEmployee(Employee employee) throws SQLException {
        employeeDBIF.deleteEmployee(employee);
    }

}
