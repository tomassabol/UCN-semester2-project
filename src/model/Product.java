package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private String description;
    private ProductType productType;
    private BigDecimal price;
    private int discount;
    private boolean active;
    private List<SupplyOrder> supplyOrders;

    public enum ProductType {
        BIKE,
        EBIKE,
        ACCESSORIES,
        ELECTRONICS,
        PARTS,
        TOOLS,
        CLOTHING,
        SHOES,
        NUTRITION;

        private int id;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }
    }

    /**
     * Constructor for the class Product
     * @param name - product name
     * @param description - product description
     * @param productType - product type
     * @param price - product price
     * @param discount - discount for the product
     * @param active - is product active
     */
    public Product(String name, String description, ProductType productType, BigDecimal price, int discount, boolean active) {
        this.name = name;
        this.description = description;
        this.productType = productType;
        this.price = price;
        this.discount = discount;
        this.active = active;
        this.supplyOrders = new ArrayList<>();
    }

    /**
     * calculate price after the discount
     * @return price if there is no discount applied
     * @return price with discount % applied
     */
    public BigDecimal getPriceAfterDiscount() {
        // return 0 if product has no discount
        if (discount == 0) { return price; }

        // convert discount into bigdecimal and substract it by 100
        BigDecimal decimaldiscount = BigDecimal.valueOf(discount).divide(BigDecimal.valueOf(100));
        // multiply discount with price and substract it from product price
        BigDecimal priceAfterDiscount = price.subtract(decimaldiscount.multiply(price));
        // return price with discount applied
        return priceAfterDiscount;
    }

    public void addSupplyOrder(SupplyOrder supplyOrder) {
        supplyOrders.add(supplyOrder);
    }

    // getters & setters
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductType getProductType() {
        return this.productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDiscount() {
        return this.discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<SupplyOrder> getSupplyOrders() {
        return this.supplyOrders;
    }

}
