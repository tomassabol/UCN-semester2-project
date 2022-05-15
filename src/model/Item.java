package model;

public class Item {
    
    private int id;
    private Product product;
    private Department department;
    private boolean sold;

    /**
     * Constructor for the class Item
     * @param product
     */
    public Item(Product product, Department department) {
        this.product = product;
        this.department = department;
        this.sold = false;
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

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean isSold() {
        return this.sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

}
