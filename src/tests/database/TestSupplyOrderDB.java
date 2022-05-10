package tests.database;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import exceptions.NotFoundException;
import model.Product;
import model.Supplier;
import model.SupplyOrder;
import database.DBConnection;
import database.ProductDB;
import database.SupplyOrderDB;
import database.interfaces.ProductDBIF;
import database.interfaces.SupplierDBIF;
import database.interfaces.SupplyOrderDBIF;

public class TestSupplyOrderDB {
    ProductDBIF productDBIF;
    SupplierDBIF supplierDBIF;
    private SupplyOrderDBIF supplyOrderDBIF;
    private SupplyOrder supplyOrder;
    SupplyOrder supplyOrder1;  
    private SupplyOrder newSupplyOrder;
    
    @BeforeEach
    public void setup() throws SQLException{
        supplyOrderDBIF= new SupplyOrderDB();
        productDBIF = new ProductDB();
    }

    public void findById10() throws SQLException, NotFoundException {
        supplyOrder1 = supplyOrderDBIF.findById(10);
    }

    @Test
    public void testGetSupplyOrderById1() throws SQLException, NotFoundException {
    	// Arrange
        int id = 1;
        // Act
        supplyOrder = supplyOrderDBIF.findById(id);
        // Assert
        assertNotNull(supplyOrder);
    }

    @Test
    public void testGetSupplyOrderId10() throws SQLException, NotFoundException {
        // Assert
        assertThrows(NotFoundException.class, () -> findById10());
    }

    @Test
    public void testFindAllReturnListSize() throws SQLException,NotFoundException {
    	// Arrange
    	int size;
        // Act
        size = supplyOrderDBIF.findAll().size();
        // Assert
        assertNotNull(size);
    }

    @Test
    public void testCreateSupplyOrder(int quantity,LocalDate orderdate) throws SQLException, NotFoundException {
    	//Product product, int quantity, LocalDate orderdate, Supplier supplier
    	//Arrange
        Product product = productDBIF.findById(1);
        Supplier supplier = supplierDBIF.findById(1);
        newSupplyOrder = new SupplyOrder(product,quantity,orderdate,supplier);
        // Act
        int size = supplyOrderDBIF.findAll().size();
        supplyOrderDBIF.createSupplyOrder(newSupplyOrder);
        // Assert
        assertEquals(newSupplyOrder.getId(), size+1);
    }

    public void testUpdateSupplyOrder() throws SQLException, NotFoundException {
        // Arrange
        supplyOrder = supplyOrderDBIF.findById(1);
        // Act
        supplyOrder.setQuantity(5);
        supplyOrderDBIF.updateSupplyOrder(supplyOrder);
        // Assert
        assertEquals(supplyOrder.getQuantity(), supplyOrderDBIF.findById(1).getQuantity());
    }

    @Test
    public void testDisableSupplyOrder() throws SQLException, NotFoundException {
    	// Act
        int size = supplyOrderDBIF.findAll().size();
        supplyOrder = supplyOrderDBIF.findById(1);
        supplyOrderDBIF.disableSupplyOrder(supplyOrder);
        // Assert
        assertEquals(size, supplyOrderDBIF.findAll().size()+1);
    }

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        supplyOrder = null;
        supplyOrder1 = null;
    }
}




