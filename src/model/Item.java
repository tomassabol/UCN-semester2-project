package model;

public class Item {
    
    private int id;
    private Product product;

    /**
     * Constructor for the class Item
     * @param product
     */
    public Item(Product product) {
        this.product = product;
    }

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
