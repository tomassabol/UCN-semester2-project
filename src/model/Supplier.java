package model;

public class Supplier extends Person {

    /**
     * Constructor for the Supplier class
     * @param name
     * @param email
     * @param phone
     * @param zipCode
     * @param address
     */
    public Supplier(String name, String email, String phone, City zipCode, String address) {
        super(name, email, phone, zipCode, address);
    }
}
