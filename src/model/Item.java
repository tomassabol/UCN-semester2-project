package model;

public class Item {
    
    private Product product;

    /**
     * Constructor for the class Item
     * @param product
     */
    public Item(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
