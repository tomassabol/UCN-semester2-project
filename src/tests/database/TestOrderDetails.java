package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.*;

import controller.OrderController;
import controller.OrderDetailsController;
import controller.OrderLineController;
import database.DBConnection;
import exceptions.NotFoundException;
import model.Order;
import model.OrderLine;

public class TestOrderDetails {
    private OrderLineController orderLineCtrl;
    private OrderController orderCtrl;
    private OrderDetailsController orderDetailsCtrl;

    @BeforeEach
    public void setUp() throws SQLException {
        orderCtrl = new OrderController();
        orderLineCtrl = new OrderLineController();
        orderDetailsCtrl = new OrderDetailsController();
    }

    @Test
    public void testFindByOrderId1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        List<OrderLine> orderLines;
        Order order;
        // Act
        order = orderCtrl.findById(id);
        orderLines = orderDetailsCtrl.findByOrderId(order.getId());

        // Assert
        System.out.println(orderLines.size());
        assertNotNull(orderLines);
    }

    @Test
    public void testCreateOrderLineDetails() throws SQLException, NotFoundException {
        // Arrange
        OrderLine orderLine = orderLineCtrl.findById(2);
        Order order = orderCtrl.findById(1);
        List<OrderLine> orderLines;
        // Act
        orderDetailsCtrl.createOrderDetails(order, orderLine);
        orderLines = orderDetailsCtrl.findByOrderId(4);
        // Assert
        assertNotNull(orderLines);
        assertEquals(2, orderLines.size());
    }

    @Test
    public void testDeleteOrderLineDetails() throws SQLException, NotFoundException {
        // Arrange
        Order order;
        List<OrderLine> orderLines;
        // Act
        order = orderCtrl.findById(1);
        orderDetailsCtrl.deleteAllOrderDetails(order);
        orderLines = orderDetailsCtrl.findByOrderId(order.getId());
        // Assert
        assertEquals(0, orderLines.size());
    }
    

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        orderLineCtrl = null;
        orderCtrl = null;
        orderDetailsCtrl = null;
    }
    
}
