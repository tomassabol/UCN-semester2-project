package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import database.ProductDB;
import database.ProductTypeDB;
import database.DBConnection;
import database.interfaces.ProductDBIF;
import database.interfaces.ProductTypeDBIF;
import exceptions.NotFoundException;
import model.Product;
import model.Product.ProductType;

public class TestProductDB {
    private ProductDBIF productDBIF;
    private ProductTypeDBIF productTypeDBIF;
    Product product;

    @BeforeEach
    public void setUp() throws SQLException {
        productDBIF = new ProductDB();
        productTypeDBIF = new ProductTypeDB();
    }

    public void findById4() throws SQLException, NotFoundException {
        product = productDBIF.findById(4);
    }

    @Test
    public void testFindById1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        Product product = null;
        // Act
        product = productDBIF.findById(id);
        // Assert
        assertNotNull(product);
    }

    @Test
    public void testFindById4() throws SQLException, NotFoundException {
        // Assert
        assertThrows(NotFoundException.class, () -> findById4());
    }

    @Test
    public void testFindAll() throws SQLException, NotFoundException {
        // Arrange
        int size = 3;
        List<Product> products = new ArrayList<>();
        // Act
        products = productDBIF.findAll();
        // Assert
        assertEquals(size, products.size());
    }

    @Test
    public void testCreateProduct() throws SQLException, NotFoundException {
        // Arrange
        ProductType productType = productTypeDBIF.findById(1);
        product = new Product("createTest", "createTest", productType, BigDecimal.valueOf(1000), 0, true);
        Product returnProduct = null;
        // Act
        productDBIF.createProduct(product);
        returnProduct = productDBIF.findById(4);
        // Assert
        assertNotNull(returnProduct);
    }

    @Test
    public void testUpdateProduct() throws SQLException, NotFoundException {
        // Arrange
        product = productDBIF.findById(4);
        ProductType productType2 = productTypeDBIF.findById(2);
        Product returnProduct = null;
        // Act
        product.setName("testUpdate");
        product.setDescription("testDescription");
        product.setProductType(productType2);
        product.setPrice(BigDecimal.valueOf(9));
        product.setDiscount(1);
        product.setActive(false);
        productDBIF.updateProduct(product);
        returnProduct = productDBIF.findById(4);
        // Act
        assertNotNull(returnProduct);
        assertEquals("testUpdate", returnProduct.getName());
    }

    @Test
    public void testDeleteProduct() throws SQLException, NotFoundException {
        // Arrange
        List<Product> products = null;
        // Act
        product = productDBIF.findById(4);
        productDBIF.deleteProduct(product);
        products = productDBIF.findAll();
        // Assert
        assertEquals(3, products.size());
    }
    

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        productDBIF = null;
    }
    
}
