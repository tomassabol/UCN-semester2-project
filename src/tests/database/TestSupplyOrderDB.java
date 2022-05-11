package tests.database;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import exceptions.NotFoundException;
import model.Product;
import model.Supplier;
import model.SupplyOrder;
import database.DBConnection;
import database.ProductDB;
import database.SupplierDB;
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
    
    @BeforeEach
    public void setup() throws SQLException{
        supplyOrderDBIF= new SupplyOrderDB();
        productDBIF = new ProductDB();
        supplierDBIF = new SupplierDB();
    }

    public void findById10() throws SQLException, NotFoundException {
        supplyOrder1 = supplyOrderDBIF.findById(10);
    }

    @Test
    public void testGetSupplyOrderById1() throws SQLException, NotFoundException {
    	// Arrange
        int id = 1;
        SupplyOrder supplyOrder;
        // Act
        supplyOrder = supplyOrderDBIF.findById(id);
        // Assert
        assertNotNull(supplyOrder);
        assertEquals(1, supplyOrder.getId());
    }

    @Test
    public void testGetSupplyOrderId10() throws SQLException, NotFoundException {
        // Assert
        assertThrows(NotFoundException.class, () -> findById10());
    }

    @Test
    public void testFindAll() throws SQLException,NotFoundException {
        // Act
        List<SupplyOrder> items = supplyOrderDBIF.findAll();
        // Assert
        assertNotNull(items);
        assertEquals(3, items.size());
    }

    @Test
    public void testCreateSupplyOrder() throws SQLException, NotFoundException {
    	//Arrange
        Product product = productDBIF.findById(1);
        int quantity = 3;
        LocalDate orderdate = LocalDate.now();
        Supplier supplier = supplierDBIF.findById(1);
        SupplyOrder supplyOrder;
        // Act
        supplyOrderDBIF.createSupplyOrder(new SupplyOrder(product, quantity, orderdate, supplier));
        supplyOrder = supplyOrderDBIF.findById(4);
        // Assert
        assertNotNull(supplyOrder);
        assertEquals(4, supplyOrderDBIF.findAll().size());
    }

    @Test
    public void testUpdateSupplyOrder() throws SQLException, NotFoundException {
        // Arrange
        int quantity = 10;
        SupplyOrder supplyOrder;
        SupplyOrder returnSupplyOrder;
        // Act
        supplyOrder = supplyOrderDBIF.findById(4);
        supplyOrder.setQuantity(quantity);
        supplyOrderDBIF.updateSupplyOrder(supplyOrder);

        returnSupplyOrder = supplyOrderDBIF.findById(4);
        // Assert
        assertNotNull(returnSupplyOrder);
        assertEquals(10, returnSupplyOrder.getQuantity());
    }

    @Test
    public void testDisableSupplyOrder() throws SQLException, NotFoundException {
    	// Act
        supplyOrder = supplyOrderDBIF.findById(4);
        supplyOrderDBIF.disableSupplyOrder(supplyOrder);
        // Assert
        assertEquals(3, supplyOrderDBIF.findAll().size());
    }

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        supplyOrder = null;
        supplyOrder1 = null;
    }
}




