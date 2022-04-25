package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import database.ProductDB;
import database.DBConnection;
import database.ItemDB;
import database.interfaces.ItemDBIF;
import database.interfaces.ProductDBIF;
import exceptions.NotFoundException;
import model.Item;
import model.Product;

public class TestItemDB {
    private ItemDBIF itemDBIF;
    private ProductDBIF productDBIF;
    Item item;

    @BeforeEach
    public void setUp() throws SQLException {
        productDBIF = new ProductDB();
        itemDBIF = new ItemDB();
    }

    public void findById5() throws SQLException, NotFoundException {
        item = itemDBIF.findById(5);
    }

    @Test
    public void testFindById1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        Item item = null;
        // Act
        item = itemDBIF.findById(id);
        // Assert
        assertNotNull(item);
    }

    @Test
    public void testFindById5() throws SQLException, NotFoundException {
        // Assert
        assertThrows(NotFoundException.class, () -> findById5());
    }

    @Test
    public void testFindAll() throws SQLException, NotFoundException {
        // Arrange
        int size = 1;
        Product product = productDBIF.findById(1);
        List<Item> items = new ArrayList<>();
        // Act
        items = itemDBIF.findAllPerProduct(product);
        // Assert
        assertEquals(size, items.size());
    }

    @Test
    public void testCreateItem() throws SQLException, NotFoundException {
        // Arrange
        Product product = productDBIF.findById(1);
        Item item = new Item(product);
        Item returnItem;
        // Act
        itemDBIF.createItem(item);
        returnItem = itemDBIF.findById(5);
        // Assert
        assertNotNull(returnItem);
    }

    @Test
    public void testDeleteItem() throws SQLException, NotFoundException {
        // Arrange
        List<Item> items = null;
        Product product;
        // Act
        item = itemDBIF.findById(5);
        itemDBIF.deleteItem(item);
        product = item.getProduct();
        items = itemDBIF.findAllPerProduct(product);
        // Assert
        assertEquals(1, items.size());
    }
    

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        itemDBIF = null;
        productDBIF = null;
    }
    
}
