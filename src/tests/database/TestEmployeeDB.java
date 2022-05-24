package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import database.CityDB;
import database.DBConnection;
import database.DepartmentDB;
import database.EmployeeDB;
import database.EmployeeTypeDB;
import database.interfaces.CityDBIF;
import database.interfaces.DepartmentDBIF;
import database.interfaces.EmployeeDBIF;
import database.interfaces.EmployeeTypeDBIF;
import exceptions.NotFoundException;
import model.City;
import model.Department;
import model.Employee;
import model.Employee.EmployeeType;

public class TestEmployeeDB {
    private EmployeeDBIF employeeDBIF;
    private EmployeeTypeDBIF employeeTypeDBIF;
    private CityDBIF cityDBIF;
    private DepartmentDBIF departmentDBIF;
    Employee employee;

    @BeforeEach
    public void setUp() throws SQLException {
        employeeDBIF = new EmployeeDB();
        employeeTypeDBIF = new EmployeeTypeDB();
        cityDBIF = new CityDB();
        departmentDBIF = new DepartmentDB();
    }

    public void findById4() throws SQLException, NotFoundException {
        employee = employeeDBIF.findById(4);
    }

    public void findByEmailIncorrect() throws SQLException, NotFoundException {
        employee = employeeDBIF.findByEmail("employee1@email.com");
    }

    @Test
    public void testFindById1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        Employee employee = null;
        // Act
        employee = employeeDBIF.findById(id);
        // Assert
        assertNotNull(employee);
    }

    @Test
    public void testFindById4() throws SQLException, NotFoundException {
        // Assert
        assertThrows(NotFoundException.class, () -> findById4());
    }

    @Test
    public void testFindAll() throws SQLException, NotFoundException {
        // Arrange
        int size = 3;
        List<Employee> employees = new ArrayList<>();
        // Act
        employees = employeeDBIF.findAll();
        // Assert
        assertEquals(size, employees.size());
    }

    @Test
    public void testFindByEmail() throws SQLException, NotFoundException {
        // Arrange
        String email = "employee1@email.com";
        // Act
        employee = employeeDBIF.findByEmail(email);
        // Assert
        assertNotNull(email);
    }

    @Test
    public void testFindByEmailIncorrectCredentials() throws SQLException, NotFoundException {
        // Assert
        assertThrows(NotFoundException.class, () -> findByEmailIncorrect());
    }

    @Test
    public void testCreateEmployee() throws SQLException, NotFoundException {
        // Arrange
        EmployeeType employeeType = employeeTypeDBIF.findById(1);
        City zipCode = cityDBIF.findById(1);
        Department department = departmentDBIF.findById(1);
        employee = new Employee("testName", "testMail", "testPhone", zipCode, "testAddress", employeeType, "testPassword", "0000000000", department);
        Employee returnEmployee = null;
        // Act
        employeeDBIF.createEmployee(employee);
        returnEmployee= employeeDBIF.findById(4);
        // Assert
        assertNotNull(returnEmployee);
    }

    @Test
    public void testUpdateEmployee() throws SQLException, NotFoundException {
        // Arrange
        employee = employeeDBIF.findById(4);
        EmployeeType employeeType2 = employeeTypeDBIF.findById(2);
        City city = cityDBIF.findById(1);
        Department department = departmentDBIF.findById(2);
        Employee returnEmployee = null;
        // Act
        employee.setName("testUpdate");
        employee.setEmail("testUpdate");
        employee.setPhone("testUpdate");
        employee.setZipCode(city);
        employee.setAddress("testUpdate");
        employee.setEmployeeType(employeeType2);
        employee.setDepartment(department);
        employeeDBIF.updateEmployee(employee);
        returnEmployee = employeeDBIF.findById(4);
        // Act
        assertNotNull(returnEmployee);
        assertEquals("testUpdate", returnEmployee.getName());
    }

    @Test
    public void testDeleteEmployee() throws SQLException, NotFoundException {
        // Arrange
        List<Employee> employees = null;
        // Act
        employee = employeeDBIF.findById(4);
        employeeDBIF.deleteEmployee(employee);
        employees = employeeDBIF.findAll();
        // Assert
        assertEquals(3, employees.size());
    }
    

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        employeeDBIF = null;
        employeeTypeDBIF = null;
    }
    
}
