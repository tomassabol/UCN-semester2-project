package tests.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.*;

import controller.CustomerController;
import controller.EmployeeController;
import controller.OrderController;
import controller.OrderDetailsController;
import controller.ProductController;
import database.DBConnection;
import exceptions.NotEnoughInStockException;
import exceptions.NotFoundException;
import model.Customer;
import model.Employee;
import model.Order;
import model.OrderLine;
import model.Product;
import model.Product.ProductType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestOrderController {

    private OrderController orderCtrl;
    private EmployeeController employeeCtrl;
    private CustomerController customerCtrl;
    private ProductController productCtrl;
    private OrderDetailsController orderDetailsCtrl;
    
    @BeforeAll
    public void setUp() throws SQLException, NotFoundException {
        orderCtrl = new OrderController();
        employeeCtrl = new EmployeeController();
        customerCtrl = new CustomerController();
        productCtrl = new ProductController();
        orderDetailsCtrl = new OrderDetailsController();
    }

    @Test
    public void testCreateOrder() throws SQLException, NotFoundException, NotEnoughInStockException {
        // Arrange
        Order order = null;
        Employee employee = null;
        Customer customer = null;
        Order returnOrder = null;
        Product product = null;
        int quantity = 1;
        // Act
        employee = employeeCtrl.findById(1);
        customer = customerCtrl.findById(1);
        order = orderCtrl.createOrder(employee, customer);
        product = productCtrl.findById(1);
        orderCtrl.addProduct(order, product, quantity);
        orderCtrl.finishOrder(order, employee.getDepartment());

        returnOrder = orderCtrl.findById(4);
        List<OrderLine> orderLines = orderDetailsCtrl.findByOrderId(returnOrder.getId());
        // Assert
        assertNotNull(returnOrder);
        assertEquals(1, orderLines.size());
    }
    
    @Test
    public void testGetOrderPrice() throws SQLException, NotFoundException {
    	BigDecimal price = BigDecimal.valueOf(0);
    	Order order = orderCtrl.findById(1);
    	price = orderCtrl.getOrderPrice(order, false);
    	
    	assertNotNull(price);
    	assertEquals(2000.00, price);
    }

    @AfterAll
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        orderCtrl = null;
        employeeCtrl = null;
        customerCtrl = null;
        productCtrl = null;
        orderDetailsCtrl = null;
    }
}
