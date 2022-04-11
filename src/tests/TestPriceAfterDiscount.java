package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import model.*;
import model.Product.ProductType;

public class TestPriceAfterDiscount {
    public Product product;
    public Product product1;

    @Test
    public void price1000ApplyDiscount10percent() {

        // Arrange
        product = new Product("product1", "description", ProductType.BIKE, BigDecimal.valueOf(1000), 10, true);

        // Act
        BigDecimal result = product.getPriceAfterDiscount();
        System.out.println(result);

        // Assert
        assertEquals(900, result.intValue());

    }

    @Test
    public void price0ApplyDiscount10percent() {
        // Arrange
        product1 = new Product("product2", "description", ProductType.BIKE, BigDecimal.valueOf(0), 10, true);

        // Act
        BigDecimal result = product1.getPriceAfterDiscount();

        // Assert
        assertEquals(product1.getPrice().intValue(), result.intValue());
    }

    @AfterEach
    public void cleanUp() {
        product = null;
        product1 = null;
    }

}
