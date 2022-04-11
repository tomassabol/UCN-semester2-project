package tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.*;
import model.Employee.EmployeeType;
import model.Product.ProductType;

public class TestAddAndRemoveOrderLine {
    
    public Order order;
    public OrderLine orderLine;
    public City zipCode;
    public Employee employee;
    public Customer customer;
    public Department department;
    public Product product;

    @BeforeEach
    public void setUp() {
        department = new Department("Department 1", zipCode, "address 1");
        zipCode = new City("DK-9000", "Aalborg");
        employee = new Employee("name", "name@mail.com", "+45", zipCode, "address 1", EmployeeType.CEO , "password", "010402-****", department);
        product = new Product("name", "description", ProductType.BIKE, BigDecimal.valueOf(1000), 0, true);
        orderLine = new OrderLine(product, 1);
        order = new Order(employee, customer);
    }

    @Test
    public void testAddOrderLine() {

        // Act
        order.addOrderLine(orderLine);

        // Assert
        assertEquals(1, order.getOrderLines().size());
    }

    @Test
    public void testAddAndRemoveOrderLine() {

        // Act
        order.removeOrderLine(orderLine);

        // Assert
        assertEquals(0, order.getOrderLines().size());
    }

    @AfterEach
    public void clearUp() {
        department = null;
        zipCode = null;
        employee = null;
        product = null;
        orderLine = null;
        order = null;
    }

}
