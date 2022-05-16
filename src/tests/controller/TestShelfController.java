package tests.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.*;

import controller.OrderLineDetailsController;
import controller.ShelfController;
import controller.ShelfDetailsController;
import database.DBConnection;
import exceptions.NotEnoughInStockException;
import exceptions.NotFoundException;
import model.Item;

public class TestShelfController {

    private OrderLineDetailsController orderLineDetailsCtrl;
    private ShelfController shelfCtrl;
    private ShelfDetailsController shelfDetailsCtrl;
    
    @BeforeEach
    public void setUp() throws SQLException, NotFoundException {
        orderLineDetailsCtrl = new OrderLineDetailsController();
        shelfCtrl = new ShelfController();
        shelfDetailsCtrl = new ShelfDetailsController();
    }

    @Test
    public void testRemoveFromStock() throws SQLException, NotFoundException, NotEnoughInStockException {
        // Arrange
        List<Item> items;

        // Act
        items = orderLineDetailsCtrl.findByOrderLine(1);
        shelfCtrl.removeFromStock(items);

        // Assert
        System.out.println(orderLineDetailsCtrl.findByOrderLine(1));
        assertEquals(0, shelfDetailsCtrl.findByItemId(1));
    }

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        shelfCtrl = null;
        orderLineDetailsCtrl = null;
        shelfDetailsCtrl = null;
    }
}
