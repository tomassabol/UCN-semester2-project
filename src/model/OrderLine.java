package model;

import java.util.HashSet;
import java.util.Set;

public class OrderLine {
    
    private Product product;
    private int quantity;
    private Set<Item> items;

    /**
     * Constructor for the OrderLine class
     * @param product - product
     * @param quantity - desired quantity
     */
    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.items = new HashSet<>();
    }

    // add and remove methods for items Set<Item>

    /**
     * Add the item to the Set
     * @param item - item to be added
     */
    public void addItems(Item item) {
        items.add(item);
    }

    /**
     * Remove the item from the Set
     * @param item - item to be removed
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    // getters and setters

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<Item> getItems() {
        return items;
    }

}
