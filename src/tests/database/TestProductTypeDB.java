package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import database.ProductTypeDB;
import database.DBConnection;
import database.interfaces.ProductTypeDBIF;
import exceptions.NotFoundException;
import model.Product.ProductType;

public class TestProductTypeDB {
    ProductTypeDBIF productTypeDBIF;
    ProductType productType;

    @BeforeEach
    public void setUp() throws SQLException {
        productTypeDBIF = new ProductTypeDB();
    }

    public void findById4() throws SQLException, NotFoundException {
        productType = productTypeDBIF.findById(4);
    }

    @Test
    public void testFindById1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        ProductType productType;
        // Act
        productType = productTypeDBIF.findById(id);
        // Assert
        assertNotNull(productType);
    }

    @Test
    public void testFindById4() throws SQLException, NotFoundException {
        // Assert
        assertThrows(NotFoundException.class, () -> findById4());
    }

    @Test
    public void testFindAll() throws SQLException {
        // Arrange
        int size = 9;
        List<ProductType> productTypes = new ArrayList<>();
        // Act
        productTypes = productTypeDBIF.findAll();
        // Assert
        assertEquals(size, productTypes.size());
    }
    

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        productTypeDBIF = null;
    }
    
}
