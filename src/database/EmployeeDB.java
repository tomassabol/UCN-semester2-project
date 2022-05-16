package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.CityController;
import controller.DepartmentController;
import controller.EmployeeTypeController;
import database.interfaces.EmployeeDBIF;
import exceptions.NotFoundException;
import model.City;
import model.Department;
import model.Employee;
import model.Employee.EmployeeType;

public class EmployeeDB implements EmployeeDBIF {

    // PreparedStatements for the EmployeeDB class
    private static final String FIND_ALL = "select * from Employees where Enabled = 1";
    private static final String FIND_BY_ID = "select * from Employees where Id = ?";
    private static final String FIND_BY_EMAIL = "select * from Employees where Email = ? and Password = ?";
    private static final String CREATE_EMPLOYEE = "insert into Employees values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_EMPLOYEE = "update Employees set Name = ?, Email = ?, Phone = ?, ZIP = ?, Address = ?, EmployeeTypeId = ?, Password = ?, CPR = ?, DepartmentId = ? where Id = ?";
    private static final String DELETE_EMPLOYEE = "update Employees set Enabled = 0 where Id = ?";

    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement findByEmail;
    private PreparedStatement createEmployee;
    private PreparedStatement updateEmployee;
    private PreparedStatement deleteEmployee;

    CityController cityCtrl = new CityController();
    EmployeeTypeController employeeTypeCtrl = new EmployeeTypeController();
    DepartmentController departmentCtrl = new DepartmentController();

    /**
     * Constructor for the EmployeeDB class
     * @throws SQLException
     */
    public EmployeeDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
        findByEmail = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_EMAIL);
        createEmployee = DBConnection.getInstance().getConnection().prepareStatement(CREATE_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
        updateEmployee = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_EMPLOYEE);
        deleteEmployee = DBConnection.getInstance().getConnection().prepareStatement(DELETE_EMPLOYEE);
    }

    /**
     * @return a list of all Employees in a DB
     * @throws SQLException
     * @throws NotFoundException
     */
    @Override
    public List<Employee> findAll() throws SQLException, NotFoundException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Employee> employees = buildObjects(rs);
        return employees;
    }

    /**
     * @return object of the Employee class with a desired ID
     * @throws SQLException
     * @throws NotFoundException
     */
    @Override
    public Employee findById(int id) throws SQLException, NotFoundException {
        Employee employee = null;
        ResultSet rs;
        findById.setInt(1, id);
        rs = findById.executeQuery();
        while(rs.next()) {
            employee = buildObject(rs);
        }

        if (employee == null) { throw new NotFoundException("Employee", id); }

        return employee;
    }

    /**
     * @return object of the Employee class with the provided credentials
     * @throws SQLException
     * @throws NotFoundException if provided credentials are incorrect
     */
    @Override
    public Employee findByEmail(String email, String password) throws SQLException, NotFoundException {
        Employee employee = null;
        ResultSet rs;
        findByEmail.setString(1, email);
        findByEmail.setString(2, password);
        rs = findByEmail.executeQuery();
        while(rs.next()) {
            employee = buildObject(rs);
        }

        if (employee == null) { throw new NotFoundException("Employee", email); }

        return employee;
    }

    /**
     * inserts a new Employee into DB
     * @throws SQLException
     */
    @Override
    public void createEmployee(Employee employee) throws SQLException {
        createEmployee.setString(1, employee.getName());
        createEmployee.setString(2, employee.getEmail());
        createEmployee.setString(3, employee.getPhone());
        createEmployee.setString(4, employee.getZipCode().getZipCode());
        createEmployee.setString(5, employee.getAddress());
        createEmployee.setInt(6, employee.getEmployeeType().getId());
        createEmployee.setString(7, employee.getPassword());
        createEmployee.setString(8, employee.getCPR());
        createEmployee.setInt(9, employee.getDepartment().getId());
        createEmployee.setBoolean(10, true);
        employee.setId(DBConnection.getInstance().executeSqlInsertWithIdentity(createEmployee));
    }

    /**
     * Updates employee details in DB
     * @throws SQLException
     */
    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        updateEmployee.setString(1, employee.getName());
        updateEmployee.setString(2, employee.getEmail());
        updateEmployee.setString(3, employee.getPhone());
        updateEmployee.setString(4, employee.getZipCode().getZipCode());
        updateEmployee.setString(5, employee.getAddress());
        updateEmployee.setInt(6, employee.getEmployeeType().getId());
        updateEmployee.setString(7, employee.getPassword());
        updateEmployee.setString(8, employee.getCPR());
        updateEmployee.setInt(9, employee.getDepartment().getId());
        updateEmployee.setInt(10, employee.getId());
        updateEmployee.executeUpdate();
    }

    /**
     * Deletes employee from DB
     * @throws SQLException
     */
    @Override
    public void deleteEmployee(Employee employee) throws SQLException {
        deleteEmployee.setInt(1, employee.getId());
        deleteEmployee.executeUpdate();
    }

    // local methods
    
    private Employee buildObject(ResultSet rs) throws SQLException, NotFoundException {
        // TODO: Change this to call Controllers
        City zipCode = cityCtrl.findByZip(rs.getString("ZIP"));
        EmployeeType employeeType = employeeTypeCtrl.findById(rs.getInt("EmployeeTypeId"));
        Department department = departmentCtrl.findById(rs.getInt("DepartmentId"));
        Employee employee = new Employee(rs.getString("Name"), rs.getString("Email"), rs.getString("Phone"), zipCode, rs.getString("Address"), employeeType, rs.getString("Password"), rs.getString("CPR"), department);
        employee.setId(rs.getInt("Id"));
        return employee;
    }

    private List<Employee> buildObjects(ResultSet rs) throws SQLException, NotFoundException {
        List<Employee> employees = new ArrayList<>();
        while(rs.next()) {
            employees.add(buildObject(rs));
        }

        return employees;
    }

}
