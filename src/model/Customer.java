package model;

public class Customer extends Person {

    private CustomerType customerType;
    
    public enum CustomerType {
        PRIVATE,
        BUSINESS,
        STUDENT
    }

    /**
     * Constructor for the class Customer
     * @param name
     * @param email
     * @param phone
     * @param zipCode
     * @param address
     * @param customerType
     */
    public Customer(String name, String email, String phone, City zipCode, String address, CustomerType customerType) {
        super(name, email, phone, zipCode, address);
        this.customerType = customerType;
    }

    public CustomerType getCustomerType() {
        return this.customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

}
