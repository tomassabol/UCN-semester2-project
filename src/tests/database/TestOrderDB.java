package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import database.CustomerDB;
import database.DBConnection;
import database.EmployeeDB;
import database.OrderDB;
import database.interfaces.CustomerDBIF;
import database.interfaces.EmployeeDBIF;
import database.interfaces.OrderDBIF;
import exceptions.NotFoundException;
import model.Customer;
import model.Employee;
import model.Order;

public class TestOrderDB {
    private OrderDBIF orderDBIF;
    private CustomerDBIF customerDBIF;
    private EmployeeDBIF employeeDBIF;
    Order order;

    @BeforeEach
    public void setUp() throws SQLException {
        orderDBIF = new OrderDB();
        customerDBIF = new CustomerDB();
        employeeDBIF = new EmployeeDB();
    }

    public void findById4() throws SQLException, NotFoundException {
        order = orderDBIF.findById(4);
    }

    @Test
    public void testFindById1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        order = null;
        // Act
        order = orderDBIF.findById(id);
        // Assert
        assertNotNull(order);
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
        List<Order> orders = new ArrayList<>();
        // Act
        orders = orderDBIF.findAll();
        // Assert
        assertEquals(size, orders.size());
    }

    @Test
    public void testCreateOrder() throws SQLException, NotFoundException {
        // Arrange
        Customer customer = customerDBIF.findById(1);
        Employee employee = employeeDBIF.findById(1);
        order = new Order(employee, customer);
        Order returnOrder;
        // Act
        orderDBIF.createOrder(order);
        returnOrder = orderDBIF.findById(4);
        // Assert
        assertNotNull(returnOrder);
    }

    @Test
    public void testDeleteOrder() throws SQLException, NotFoundException {
        // Arrange
        List<Order> orders = null;
        // Act
        order = orderDBIF.findById(4);
        orderDBIF.deleteOrder(order);
        orders = orderDBIF.findAll();
        // Assert
        assertEquals(3, orders.size());
    }
    

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        orderDBIF = null;
        customerDBIF = null;
        employeeDBIF = null;
    }
    
}
