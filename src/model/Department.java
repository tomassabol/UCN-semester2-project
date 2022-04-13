package model;

public class Department {
    private int id;
    private String name;
    private City zipCode;
    private String address;

    /**
     * Constructor for the class Department
     * @param name
     * @param zipCode
     * @param address
     */
    public Department(String name, City zipCode, String address) {
        this.name = name;
        this.zipCode = zipCode;
        this.address = address;
    }

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
