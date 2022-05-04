package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import database.CityDB;
import database.CustomerDB;
import database.CustomerTypeDB;
import database.DBConnection;
import database.interfaces.CityDBIF;
import database.interfaces.CustomerDBIF;
import database.interfaces.CustomerTypeDBIF;
import exceptions.NotFoundException;
import model.City;
import model.Customer;
import model.Customer.CustomerType;

public class TestCustomerDB {
    private CustomerDBIF customerDBIF;
    private CustomerTypeDBIF customerTypeDBIF;
    private CityDBIF cityDBIF;
    Customer customer;

    @BeforeEach
    public void setUp() throws SQLException {
        customerDBIF = new CustomerDB();
        customerTypeDBIF = new CustomerTypeDB();
        cityDBIF = new CityDB();
    }

    public void findById4() throws SQLException, NotFoundException {
        customer = customerDBIF.findById(4);
    }

    @Test
    public void testFindById1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        Customer customer = null;
        // Act
        customer = customerDBIF.findById(id);
        // Assert
        assertNotNull(customer);
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
        List<Customer> customers = new ArrayList<>();
        // Act
        customers = customerDBIF.findAll();
        // Assert
        assertEquals(size, customers.size());
    }
    
    @Test
    public void testCreateCustomer() throws SQLException, NotFoundException {
        // Arrange
        CustomerType customerType = customerTypeDBIF.findById(1);
        City zipCode = cityDBIF.findById(1);
        customer = new Customer("testName", "testMail", "testPhone", zipCode, "testAddress", customerType);
        Customer returnCustomer = null;
        // Act
        customerDBIF.createCustomer(customer);
        returnCustomer= customerDBIF.findById(4);
        // Assert
        assertNotNull(returnCustomer);
    }

    @Test
    public void testUpdateCustomer() throws SQLException, NotFoundException {
        // Arrange
        customer = customerDBIF.findById(4);
        CustomerType customerType2 = customerTypeDBIF.findById(2);
        City city = cityDBIF.findById(1);
        Customer returnCustomer = null;
        // Act
        customer.setName("testUpdate");
        customer.setEmail("testUpdate");
        customer.setPhone("testUpdate");
        customer.setZipCode(city);
        customer.setAddress("testUpdate");
        customer.setCustomerType(customerType2);
        customerDBIF.updateCustomer(customer);
        returnCustomer = customerDBIF.findById(4);
        // Act
        assertNotNull(returnCustomer);
        assertEquals("testUpdate", returnCustomer.getName());
    }

    @Test
    public void testDeleteCustomer() throws SQLException, NotFoundException {
        // Arrange
        List<Customer> customers = null;
        // Act
        customer = customerDBIF.findById(4);
        customerDBIF.deleteCustomer(customer);
        customers = customerDBIF.findAll();
        // Assert
        assertEquals(3, customers.size());
    }
    

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        customerDBIF = null;
        customerTypeDBIF = null;
    }
    
}
