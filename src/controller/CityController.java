package controller;

import java.sql.SQLException;
import java.util.List;

import database.CityDB;
import database.interfaces.CityDBIF;
import exceptions.NotFoundException;
import model.City;

public class CityController {
    
    private CityDBIF cityDBIF;

    /**
     * Constructor for the CityController class
     * @throws SQLException
     */
    public CityController() throws SQLException {
        cityDBIF = new CityDB();
    }

    /**
     * finds a city with the desired id
     * @param id - desired ID
     * @return a city with the desired id
     * @throws SQLException
     * @throws NotFoundException
     */
    public City findById(int id) throws SQLException, NotFoundException {
        City city = cityDBIF.findById(id);
        return city;
    }

    /**
     * finds a city with the desired ZIP
     * @param zip - desired zip code
     * @return a city with the desired zip
     * @throws SQLException
     * @throws NotFoundException
     */
    public City findByZip(String zip) throws SQLException, NotFoundException {
        City city = cityDBIF.findByZip(zip);
        return city;
    }

    /**
     * finds all cities
     * @return list of all cities
     * @throws SQLException
     */
    public List<City> findAll() throws SQLException {
        List<City> cities = cityDBIF.findAll();
        return cities;
    }

    /**
     * creates a new city
     * @param zipCode - zip code of the city
     * @param name - name of the city
     * @throws SQLException
     */
    public void createCity(String zipCode, String name) throws SQLException {
        City city = new City(zipCode, name);
        cityDBIF.createCity(city);
    }

    /**
     * updates the city details
     * @param city - city to be updated
     * @param zipCode - new zip code
     * @param name - new name
     * @throws SQLException
     */
    public void updateCity(City city, String zipCode, String name) throws SQLException {
        city.setName(name);
        city.setZipCode(zipCode);
        cityDBIF.updateCity(city);
    }

    /**
     * deletes the city
     * @param city - city to be deleted
     * @throws SQLException
     */
    public void deleteCity(City city) throws SQLException {
        cityDBIF.deleteCity(city);
    }

}
