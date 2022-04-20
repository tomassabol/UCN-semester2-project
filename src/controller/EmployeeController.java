package controller;

import java.sql.SQLException;
import java.util.List;

import database.EmployeeDB;
import database.EmployeeTypeDB;
import database.interfaces.EmployeeDBIF;
import database.interfaces.EmployeeTypeDBIF;
import exceptions.NotFoundException;
import model.City;
import model.Department;
import model.Employee;
import model.Employee.EmployeeType;

public class EmployeeController {
    
    private EmployeeDBIF employeeDBIF;
    private EmployeeTypeDBIF employeeTypeDBIF;
    private DepartmentController departmentCtrl;
    private CityController cityCtrl;

    public EmployeeController() throws SQLException {
        employeeDBIF = new EmployeeDB();
        employeeTypeDBIF = new EmployeeTypeDB();
        departmentCtrl = new DepartmentController();
        cityCtrl = new CityController();
    }

    public List<Employee> findAll() throws SQLException, NotFoundException {
        List<Employee> employees = employeeDBIF.findAll();
        return employees;
    }

    public Employee findById(int id) throws SQLException, NotFoundException {
        Employee employee = employeeDBIF.findById(id);
        return employee;
    }

    public Employee findByEmail(String email, String password) throws SQLException, NotFoundException {
        Employee employee = employeeDBIF.findByEmail(email, password);
        return employee;
    }

    public void createEmployee(String name, String email, String phone, City zipCode, String address, EmployeeType employeeType, String password, String CPR, Department department) throws SQLException {
        Employee employee = new Employee(name, email, phone, zipCode, address, employeeType, password, CPR, department);
        employeeDBIF.createEmployee(employee);
    }

    public void updateEmployee() throws SQLException {
        employeeDBIF.updateEmployee(employee);
    }

}
