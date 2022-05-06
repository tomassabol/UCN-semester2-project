package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {
    
    private int id;
    private Employee employee;
    private Customer customer;
    private LocalDate orderDate;
    private List<OrderLine> orderLines;
    private BigDecimal price;

    /**
     * Constructor for the class Order
     * @param employee
     * @param customer
     */
    public Order(Employee employee, Customer customer) {
        this.employee = employee;
        this.customer = customer;
        this.orderLines = new ArrayList<>();
    }

    /**
     * Add the OrderLine to the Order Set
     * @param orderLine - OrderLine to be added
     */
    public void addOrderLine(OrderLine orderLine) {
        orderLines.add(orderLine);
    }

    /**
     * Remove the OrderLine from the Order Set
     * @param orderLine - OrderLine to be remoed
     */
    public void removeOrderLine(OrderLine orderLine) {
        orderLines.remove(orderLine);
    }

    /**
     * Checks if the product is already present in the order
     * @param product - the product to search for
     * @return true if product is already presents
     */
    public boolean isProductPresent(Product product) {
        boolean found = false;

        for(OrderLine orderLine : orderLines) {
            if(orderLine.getProduct().getId() == product.getId()) {
                found = true;
            } else{ found = false; }
        }

        return found;
    }

    public OrderLine getOrderLinebyProduct(Product product) {
        OrderLine returnOrderLine = null;

        for(OrderLine orderLine : orderLines) {
            if(orderLine.getProduct().getId() == product.getId()) {
                returnOrderLine = orderLine;
            }
        }
        
        return returnOrderLine;
    }

    /**
     * Checks if the order is empty
     * @return true if order has no orderlines - is empty
     */
    public boolean isEmpty() {
        boolean returnValue = false;
        if(orderLines.size() == 0) {
            returnValue = true;
        }

        return returnValue;
    }

    // getters and setters

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderLines() {
        return this.orderLines;
    }

    // 

    public BigDecimal getOrderPrice() {
        price = BigDecimal.valueOf(0);
        orderLines.forEach(orderLine -> {
            price.add(orderLine.getPrice());
        });
        return price;
    }

    public BigDecimal getOrderPriceAfterDiscount() {
        price = BigDecimal.valueOf(0);
        orderLines.forEach(orderLine -> {
            price.add(orderLine.getPriceAfterDiscount());
        });
        return price;
    }

}
