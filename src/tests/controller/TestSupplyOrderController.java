package tests.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import controller.AuthenticationController;
import controller.ProductController;
import controller.ShelfController;
import controller.SupplierController;
import controller.SupplyOrderController;

import java.sql.SQLException;
import java.time.LocalDate;

import exceptions.NotFoundException;
import model.Shelf;
import model.SupplyOrder;
import database.DBConnection;

public class TestSupplyOrderController {

    private SupplyOrderController supplyOrderCtrl;
    private ShelfController shelfCtrl;
    private AuthenticationController auth;
    private ProductController productCtrl;
    private SupplierController supplierCtrl;

    private SupplyOrder supplyOrder;
    private Shelf shelf;
    
    @BeforeEach
    public void setup() throws SQLException, NotFoundException {
    	shelfCtrl = new ShelfController();
        supplyOrderCtrl = new SupplyOrderController();
        auth = new AuthenticationController();
        productCtrl = new ProductController();
        supplierCtrl = new SupplierController();
        auth.logIn("admin", "admin");
    }

    // test again with update shelf
    @Test
    public void testAddToStock() throws SQLException, NotFoundException {
    	shelf = shelfCtrl.createShelf("test", auth.getLoggedInUser().getDepartment());
        supplyOrder = supplyOrderCtrl.createSupplyOrder(productCtrl.findById(1), 3, LocalDate.now(), supplierCtrl.findById(1));
        
        // Act
    	shelf = shelfCtrl.findById(4);
        supplyOrder = supplyOrderCtrl.findById(4);
        		
        // Arrange
        int newQuantity;
        Shelf newShelf;
        Shelf returnShelf;
        // Act
        newShelf = shelfCtrl.findById(shelf.getId());
        supplyOrderCtrl.addToStock(supplyOrder, newShelf);
        returnShelf = shelfCtrl.findById(shelf.getId());
        newQuantity = returnShelf.getProductQuantity();
        // Assert
        assertNotNull(shelf.getProduct());
        assertNotNull(shelf.getProductQuantity());
        assertEquals(supplyOrder.getQuantity(), newQuantity);
    }

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        supplyOrderCtrl = null;
        shelfCtrl = null;
    }
}




