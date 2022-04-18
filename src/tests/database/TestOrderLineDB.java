package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import database.ProductDB;
import database.DBConnection;
import database.OrderLineDB;
import database.interfaces.OrderLineDBIF;
import database.interfaces.ProductDBIF;
import exceptions.NotFoundException;
import model.OrderLine;
import model.Product;

public class TestOrderLineDB {
    private ProductDBIF productDBIF;
    private OrderLineDBIF orderLineDBIF;
    OrderLine orderLine;

    @BeforeEach
    public void setUp() throws SQLException {
        productDBIF = new ProductDB();
        orderLineDBIF = new OrderLineDB();
    }

    public void findById5() throws SQLException, NotFoundException {
        orderLine = orderLineDBIF.findById(5);
    }

    @Test
    public void testFindById1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        OrderLine orderLine = null;
        // Act
        orderLine = orderLineDBIF.findById(id);
        // Assert
        assertNotNull(orderLine);
    }

    @Test
    public void testFindById5() throws SQLException, NotFoundException {
        // Assert
        assertThrows(NotFoundException.class, () -> findById5());
    }

    @Test
    public void testFindAll() throws SQLException, NotFoundException {
        // Arrange
        int size = 3;
        List<OrderLine> orderLines = new ArrayList<>();
        // Act
        orderLines = orderLineDBIF.findAll();
        // Assert
        assertEquals(size, orderLines.size());
    }

    @Test
    public void testCreateOrderLine() throws SQLException, NotFoundException {
        // Arrange
        Product product = productDBIF.findById(1);
        orderLine = new OrderLine(product, 1);
        OrderLine returnOrderLine;
        // Act
        orderLineDBIF.createOrderLine(orderLine);
        returnOrderLine = orderLineDBIF.findById(4);
        // Assert
        assertNotNull(returnOrderLine);
    }

    @Test
    public void testUpdateOrderLine() throws SQLException, NotFoundException {
        // Arrange
        orderLine = orderLineDBIF.findById(4);
        OrderLine returnOrderLine;
        // Act
        orderLine.setQuantity(2);
        orderLineDBIF.updateOrderLine(orderLine);
        returnOrderLine = orderLineDBIF.findById(4);
        // Act
        assertNotNull(returnOrderLine);
        assertEquals(2, returnOrderLine.getQuantity());
    }

    @Test
    public void testDeleteOrderLine() throws SQLException, NotFoundException {
        // Arrange
        List<OrderLine> orderLines = null;
        // Act
        orderLine = orderLineDBIF.findById(4);
        orderLineDBIF.deleteOrderLine(orderLine);
        orderLines = orderLineDBIF.findAll();
        // Assert
        assertEquals(3, orderLines.size());
    }
    

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        orderLineDBIF = null;
        productDBIF = null;
    }
    
}
