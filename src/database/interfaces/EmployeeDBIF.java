package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Employee;

public interface EmployeeDBIF {
    public List<Employee> findAll() throws SQLException, NotFoundException;
    public Employee findById(int id) throws SQLException, NotFoundException;
    public Employee findByEmail(String email, String password) throws SQLException, NotFoundException;
    public Employee findOnlyByEmail(String email) throws SQLException, NotFoundException; // for CRUD employee
    public void createEmployee(Employee employee) throws SQLException;
    public void updateEmployee(Employee employee) throws SQLException;
    public void deleteEmployee(Employee employee) throws SQLException;
}
