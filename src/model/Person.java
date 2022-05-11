package model;

abstract class Person {
    
    private String name;
    private String email;
    private String phone;
    private City zipCode;
    private String address;

    /**
     * Constructor for the Person class
     * @param name - name of the person
     * @param email - email of the person
     * @param phone - phone number of the person
     * @param zipCode - City zipCode of the person's address
     * @param address - person's address
     */
    public Person(String name, String email, String phone, City zipCode, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.zipCode = zipCode;
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public City getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(City zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
