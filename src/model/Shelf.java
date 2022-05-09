package model;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
    
    private int id;
    private String name;
    private Product product;
    private List<Item> items;
    private Department department;
    private List<OrderLine> orderLines;

    /**
     * Constructor for the Shelf class
     * @param name
     * @param product
     * @param department
     */
    public Shelf(String name, Product product, Department department) {
        this.name = name;
        this.product = product;
        this.department = department;
        this.items = new ArrayList<>();
        this.orderLines = new ArrayList<>();
    }

    // getters and setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<OrderLine> getOrderLines() {
        return this.orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

}
