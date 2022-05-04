package model;

public class City {
    
    private int id;
    private String zipCode;
    private String name;

    /**
     * Constructor for the City class
     * @param zipCode - city zip code
     * @param name - city name
     */
    public City(String zipCode, String name) {
        this.zipCode = zipCode;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
 
    }


