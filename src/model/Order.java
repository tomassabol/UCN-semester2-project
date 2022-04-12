package model;

import java.util.HashSet;
import java.util.Set;

public class Order {
    
    private Employee employee;
    private Customer customer;
    private Set<OrderLine> orderLines;

    /**
     * Constructor for the class Order
     * @param employee
     * @param customer
     */
    public Order(Employee employee, Customer customer) {
        this.employee = employee;
        this.customer = customer;
        this.orderLines = new HashSet<>();
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

    // getters and setters

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

    public Set<OrderLine> getOrderLines() {
        return this.orderLines;
    }

}
