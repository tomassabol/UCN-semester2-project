package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import database.CustomerTypeDB;
import database.DBConnection;
import database.interfaces.CustomerTypeDBIF;
import exceptions.NotFoundException;
import model.Customer.CustomerType;;

public class TestCustomerTypeDB {
    CustomerTypeDBIF customerTypeDBIF;
    CustomerType customerType;

    @BeforeEach
    public void setUp() throws SQLException {
        customerTypeDBIF = new CustomerTypeDB();
    }

    public void findById4() throws SQLException, NotFoundException {
        customerType = customerTypeDBIF.findById(4);
    }

    @Test
    public void testFindById1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        // Act
        customerType = customerTypeDBIF.findById(id);
        // Assert
        assertNotNull(customerType);
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
        List<CustomerType> customerTypes = new ArrayList<>();
        // Act
        customerTypes = customerTypeDBIF.findAll();
        // Assert
        assertEquals(size, customerTypes.size());
    }
    

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        customerType = null;
    }
    
}
