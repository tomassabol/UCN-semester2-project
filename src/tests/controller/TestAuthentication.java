package tests.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.*;

import controller.AuthenticationController;
import controller.EmployeeController;
import database.DBConnection;
import exceptions.NotFoundException;
import model.Employee;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAuthentication {

    private AuthenticationController authenticationCtrl;
    private EmployeeController employeeCtrl;
    
    @BeforeAll
    public void setUp() throws SQLException, NotFoundException {
        authenticationCtrl = new AuthenticationController();
        employeeCtrl = new EmployeeController();
    }

    public void testIncorrectCredentials(String email, String password) throws SQLException, NotFoundException {
        authenticationCtrl.logIn(email, password);
    }

    @Test
    public void testLogIn() throws SQLException, NotFoundException {
        // Arrange
        Employee employee = employeeCtrl.findById(1);
        String email = employee.getEmail();
        String password = employee.getPassword();
        boolean login;
        // Act
        login = authenticationCtrl.logIn(email, password);
        // Assert
        assertTrue(login);
    }

    @Test
    public void testFailLogIn() throws SQLException, NotFoundException {
        // Arrange
        Employee employee = employeeCtrl.findById(1);
        String email = employee.getEmail();
        String password = "password2";
        // Assert
        assertThrows(NotFoundException.class, () -> testIncorrectCredentials(email, password));
    }

    @Test
    public void testGetLoggedInUser() throws SQLException, NotFoundException {
        // Arrange
        Employee employee = null;
        // Act
        employee = authenticationCtrl.getLoggedInUser();
        // Assert
        assertNotNull(employee);
        assertEquals(1, employee.getId());
    }

    @Test
    public void testLogOut() throws SQLException, NotFoundException {
        // Arrange
        Employee employee = null;
        // Act
        authenticationCtrl.logout();
        employee = authenticationCtrl.getLoggedInUser();
        // Arrange
        assertNull(employee);
    }

    @AfterAll
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        authenticationCtrl = null;
        employeeCtrl = null;
    }
}
