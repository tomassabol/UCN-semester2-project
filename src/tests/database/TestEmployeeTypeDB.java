package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import database.DBConnection;
import database.EmployeeTypeDB;
import database.interfaces.EmployeeTypeDBIF;
import exceptions.NotFoundException;
import model.Employee.EmployeeType;;

public class TestEmployeeTypeDB {
    EmployeeTypeDBIF employeeTypeDBIF;
    EmployeeType employeeType;

    @BeforeEach
    public void setUp() throws SQLException {
        employeeTypeDBIF = new EmployeeTypeDB();
    }

    public void findById4() throws SQLException, NotFoundException {
        employeeType = employeeTypeDBIF.findById(4);
    }

    @Test
    public void testFindById1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        // Act
        employeeType = employeeTypeDBIF.findById(id);
        // Assert
        assertNotNull(employeeType);
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
        List<EmployeeType> employeeTypes = new ArrayList<>();
        // Act
        employeeTypes = employeeTypeDBIF.findAll();
        // Assert
        assertEquals(size, employeeTypes.size());
    }
    

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        employeeType = null;
        employeeTypeDBIF = null;
    }
    
}
