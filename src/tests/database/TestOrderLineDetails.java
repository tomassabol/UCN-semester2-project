package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import controller.ItemController;
import controller.OrderLineDetailsController;
import database.DBConnection;
import database.OrderLineDB;
import database.interfaces.OrderLineDBIF;
import exceptions.NotFoundException;
import model.Item;
import model.OrderLine;

public class TestOrderLineDetails {
    private OrderLineDBIF orderLineDBIF;
    private OrderLineDetailsController orderLineDetailsCtrl;
    private ItemController itemCtrl;
    OrderLine orderLine;

    @BeforeEach
    public void setUp() throws SQLException {
        orderLineDBIF = new OrderLineDB();
        orderLineDetailsCtrl = new OrderLineDetailsController();
        itemCtrl = new ItemController();
    }

    @Test
    public void testFindById1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        OrderLine orderLine = null;
        List<Item> items;
        // Act
        orderLine = orderLineDBIF.findById(id);
        items = orderLineDetailsCtrl.findByOrderLine(orderLine);

        // Assert
        assertNotNull(items);
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
    public void testCreateOrderLineDetails() throws SQLException, NotFoundException {
        // Arrange
        OrderLine orderLine = orderLineDBIF.findById(1);
        Item item = itemCtrl.findById(1);
        List<Item> items;
        // Act
        orderLineDetailsCtrl.createOrderLineDetails(orderLine, item);
        items = orderLineDetailsCtrl.findByOrderLine(orderLine);
        // Assert
        assertNotNull(items);
        assertEquals(2, items.size());
    }

    @Test
    public void testDeleteOrderLineDetails() throws SQLException, NotFoundException {
        // Arrange
        List<Item> items;
        OrderLine orderLine;
        // Act
        orderLine = orderLineDBIF.findById(1);
        orderLineDetailsCtrl.deleteAllOrderLineDetails(orderLine);
        items = orderLineDetailsCtrl.findByOrderLine(orderLine);
        // Assert
        assertEquals(0, items.size());
    }
    

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        orderLineDBIF = null;
    }
    
}
