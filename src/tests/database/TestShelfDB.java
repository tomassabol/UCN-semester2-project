package tests.database;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import exceptions.NotFoundException;
import model.Department;
import model.Product;
import model.Shelf;
import database.DBConnection;
import database.DepartmentDB;
import database.ProductDB;
import database.ShelfDB;
import database.interfaces.DepartmentDBIF;
import database.interfaces.ProductDBIF;
import database.interfaces.ShelfDBIF;

public class TestShelfDB {
    DepartmentDBIF departmentDBIF;
    ProductDBIF productDBIF;
    private ShelfDBIF shelfDBIF;
    private Shelf shelf;
    Shelf shelf1;  
    private Shelf newShelf;
    
    @BeforeEach
    public void setup() throws SQLException{
        shelfDBIF= new ShelfDB();
        departmentDBIF = new DepartmentDB();
        productDBIF = new ProductDB();
    }

    public void findById40() throws SQLException, NotFoundException {
        shelf1 = shelfDBIF.findById(40);
    }

    @Test
    public void testGetShelfById1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        // Act
        shelf = shelfDBIF.findById(id);
        // Assert
        assertNotNull(shelf);
    }

    @Test
    public void testGetShelfyById40() throws SQLException, NotFoundException {
        // Assert
        assertThrows(NotFoundException.class, () -> findById40());
    }

    @Test
    public void testFindAllReturnListSize() throws SQLException,NotFoundException {
    	// Arrange
    	int size;
    	Department department;
        // Act
    	department = departmentDBIF.findById(1);
        size = shelfDBIF.findAll(department).size();

        // Assert
        assertNotNull(size);
    }

    @Test
    public void testCreateShelf() throws SQLException, NotFoundException {
        //String name, Product product, Department department
        // Arrange
        Department department = departmentDBIF.findById(1);
        newShelf = new Shelf("TestShelf",department);
        // Act
        int size = shelfDBIF.findAll(department).size();
        shelfDBIF.createShelf(newShelf);
        // Assert
        assertEquals(newShelf.getId(), size+1);
    }

    @Test
    public void testUpdateShelf() throws SQLException, NotFoundException {
        // Arrange
        shelf = shelfDBIF.findById(5);
        Product product;
        // Act
        shelf.setName("test");
        product = productDBIF.findById(1);
        shelf.setProduct(product);
        shelfDBIF.updateShelf(shelf);
        // Assert
        assertEquals(shelf.getName(), shelfDBIF.findById(5).getName());
        assertEquals(1, shelfDBIF.findById(5).getProduct().getId());
    }

    @Test
    public void testDeleteShelf() throws SQLException, NotFoundException {
        // Act
    	Department department = departmentDBIF.findById(1);
        int size = shelfDBIF.findAll(department).size();
        shelf = shelfDBIF.findById(1);
        shelfDBIF.deleteShelf(shelf);
        // Assert
        assertEquals(size, shelfDBIF.findAll(department).size()+1);
    }

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        shelf = null;
        shelf1 = null;
    }
}




