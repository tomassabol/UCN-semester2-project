package tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DepartmentController;
import exceptions.NotFoundException;
import model.*;
import model.Product.ProductType;

public class TestAddAndRemoveItem {
    
    private Item item;
    private Item item1;
    private Product product;
    private OrderLine orderLine;
    private DepartmentController departmentCtrl;
    private Department department;

    @BeforeEach
    public void setUp() throws SQLException, NotFoundException {
        product = new Product("name", "description", ProductType.BIKE, BigDecimal.valueOf(1000), 0, true);
        orderLine = new OrderLine(product, 1);
        department = departmentCtrl.findById(1);
        item = new Item(product, department);
    }

    @Test
    public void testAddItem() {

        // Act
        orderLine.addItems(item);

        // Assert
        assertEquals(1, orderLine.getItems().size());
    }

    @Test
    public void testRemoveItem() {

        // Act
        orderLine.removeItem(item);

        // Assert
        assertEquals(0, orderLine.getItems().size());
    }

    @AfterEach
    public void clearUp() {
        product = null;
        orderLine = null;
        item = null;
    }

}
