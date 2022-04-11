package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.*;
import model.Product.ProductType;

public class TestAddAndRemoveItem {
    
    public Item item;
    public Item item1;
    public Product product;
    public OrderLine orderLine;

    @BeforeEach
    public void setUp() {
        product = new Product("name", "description", ProductType.BIKE, BigDecimal.valueOf(1000), 0, true);
        orderLine = new OrderLine(product, 1);
        item = new Item(product);
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
