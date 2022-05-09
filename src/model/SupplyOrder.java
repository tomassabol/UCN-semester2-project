package model;

import java.time.LocalDate;

public class SupplyOrder {
    
    private int id;
    private Product product;
    private int quantity;
    private LocalDate orderDate;
    private Supplier supplier;
    private boolean isDelivered;

    /**
     * Constructor for the SupplyOrder class
     * @param product
     * @param orderdate
     * @param supplier
     */
    public SupplyOrder(Product product, int quantity, LocalDate orderdate, Supplier supplier) {
        this.product = product;
        this.quantity = quantity;
        this.orderDate = orderdate;
        this.supplier = supplier;
        this.isDelivered = false;
    }

    // getters and setters 

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Supplier getSupplier() {
        return this.supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public boolean isIsDelivered() {
        return this.isDelivered;
    }

    public boolean getIsDelivered() {
        return this.isDelivered;
    }

    public void setIsDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

}
