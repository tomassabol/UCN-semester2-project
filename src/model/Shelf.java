package model;

public class Shelf {
    
    private int id;
    private String name;
    private Product product;
    private int productQuantity;
    private Department department;

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
        this.productQuantity = 0;
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

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int quantity) {
        this.productQuantity = quantity;
    }

}
