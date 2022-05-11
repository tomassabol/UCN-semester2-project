package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.*;

import controller.ItemController;
import database.ShelfDB;
import database.ShelfDetailsDB;
import database.DBConnection;
import database.interfaces.ShelfDBIF;
import exceptions.NotFoundException;
import model.Item;
import model.Shelf;

public class TestShelfDetailsDB {
    private ItemController itemCtrl;
    private ShelfDBIF shelfDBIF;
    private ShelfDetailsDB shelfDetailsDBIF;

    @BeforeEach
    public void setUp() throws SQLException {
        itemCtrl = new ItemController();
        shelfDBIF = new ShelfDB();
        shelfDetailsDBIF = new ShelfDetailsDB();
    }

    public void findByShelfId10() throws SQLException, NotFoundException {
        shelfDetailsDBIF.findByShelf(shelfDBIF.findById(10));
    }

    @Test
    public void testFindByShelfId2() throws SQLException, NotFoundException {
        // Arrange
        int id = 2;
        Shelf shelf;
        // Act
        shelf = shelfDBIF.findById(id);
        List<Item> items = shelfDetailsDBIF.findByShelf(shelf);
        // Assert
        assertNotNull(items);
        assertEquals(1, items.size());
    }

    @Test
    public void testFindByShelfId10() throws SQLException, NotFoundException {
        // Assert
        assertThrows(NotFoundException.class, () -> findByShelfId10());
    }

    @Test
    public void testCreateShelfDetails() throws SQLException, NotFoundException {
        // Arrange
        Shelf shelf;
        Item item;
        List<Item> items;
        // Act
        shelf = shelfDBIF.findById(4);
        item = itemCtrl.findById(4);

        shelfDetailsDBIF.createShelfDetails(shelf, item);
        items = shelfDetailsDBIF.findByShelf(shelf);
        // Assert
        assertNotNull(items);
        assertEquals(1, items.size());
    }

    @Test
    public void testDeleteShelfDetails() throws SQLException, NotFoundException {
        // Arrange
        Shelf shelf;
        Item item;
        int id = 4;
        // Act
        item = itemCtrl.findById(id);
        shelf = shelfDBIF.findById(id);

        shelfDetailsDBIF.deleteShelfDetails(shelf, item);
        List<Item> items = shelfDetailsDBIF.findByShelf(shelf);
        // Assert
        assertEquals(0, items.size());
    }

    @Test
    public void testDeleteAllShelfDetails() throws SQLException, NotFoundException {
        // Arrange
        Shelf shelf;
        Item item;
        Item item2;
        int id = 4;
        // Act
        shelf = shelfDBIF.findById(4);
        item = itemCtrl.findById(id);
        item2 = itemCtrl.findById(3);
        shelfDetailsDBIF.createShelfDetails(shelf, item);
        shelfDetailsDBIF.createShelfDetails(shelf, item2);

        shelfDetailsDBIF.deleteAllShelfDetails(shelf);
        List<Item> items = shelfDetailsDBIF.findByShelf(shelf);
        // Assert
        assertEquals(0, items.size());
    }

    @Test
    public void testRemoveItem() throws SQLException, NotFoundException {
        // Arrange
        Item item;
        Shelf shelf;
        int id = 4;
        // Act
        item = itemCtrl.findById(id);
        shelf = shelfDBIF.findById(id);

        shelfDetailsDBIF.removeItemFromStock(item);
        List<Item> items = shelfDetailsDBIF.findByShelf(shelf);
        // Assert
        assertEquals(0, items.size());
    }
    

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        itemCtrl = null;
        shelfDBIF = null;
        shelfDetailsDBIF = null;
    }
    
}
