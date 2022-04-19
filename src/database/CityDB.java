package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.interfaces.CityDBIF;
import exceptions.NotFoundException;
import model.City;

public class CityDB implements CityDBIF {

    // PreparedStatements for CityDB class
    private static final String FIND_ALL = "select * FROM City";
    private static final String FIND_BY_ID = "select * FROM City where Id = ?";
    private static final String FIND_BY_ZIP = "select * FROM City where ZIP = ?";
    private static final String CREATE_CITY = "insert into City values(?, ?)";
    private static final String UPDATE_CITY = "update City set Name = ? from City where Id = ?";
    private static final String DELETE_CITY = "delete from City where Id = ?";

    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement findByZip;
    private PreparedStatement createCity;
    private PreparedStatement updateCity;
    private PreparedStatement deleteCity;

    /**
     * Constructor for the CityDB class
     * @throws SQLException
     */
    public CityDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
        findByZip = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ZIP);
        createCity = DBConnection.getInstance().getConnection().prepareStatement(CREATE_CITY, Statement.RETURN_GENERATED_KEYS);
        updateCity = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_CITY);
        deleteCity = DBConnection.getInstance().getConnection().prepareStatement(DELETE_CITY);
    }

    /**
     * @return - list of all the cities in the database
     */
    @Override
    public List<City> findAll() throws SQLException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<City> cities = buildObjects(rs);
        return cities;
    }

    /**
     * @param id - id to be found
     * @return city with the desired id
     */
    @Override
    public City findById(int id) throws SQLException, NotFoundException {
        City city = null;
        ResultSet rs;
        findById.setInt(1, id);
        rs = findById.executeQuery();
        while (rs.next()) {
            city = buildObject(rs);
        }

        if (city == null) { throw new NotFoundException("City", id); }

        return city;
    }

    public City findByZip(String zip) throws SQLException, NotFoundException {
        City city = null;
        ResultSet rs;
        findByZip.setString(1, zip);
        rs = findByZip.executeQuery();
        while (rs.next()) {
            city = buildObject(rs);
        }

        if(city == null) { throw new NotFoundException("City", zip); }

        return city;
    }

    /**
     * @param city - the city to be inserted
     * Insert the City into the database
     */
    @Override
    public void createCity(City city) throws SQLException {
        createCity.setString(1, city.getZipCode());
        createCity.setString(2, city.getName());
        city.setId(DBConnection.getInstance().executeSqlInsertWithIdentity(createCity));
    }

    /**
     * @param city - the city to be updated
     * update the name of the cuty
     */
    @Override
    public void updateCity(City city) throws SQLException {
        updateCity.setString(1, city.getName());
        updateCity.setInt(2, city.getId());
        updateCity.executeUpdate();
    }

    /**
     * Removes a city from the database
     * @param city - the city to be removed 
     */
    @Override
    public void deleteCity(City city) throws SQLException {
        deleteCity.setInt(1, city.getId());
        deleteCity.executeUpdate();
    }

    // local methods
    /**
     * Builds an object of a City class from the City table rows
     * @param rs - resultset
     * @return object of the City class built from the columns of City table
     * @throws SQLException
     */
    private City buildObject(ResultSet rs) throws SQLException {
        City city = new City(rs.getString("ZIP"), rs.getString("Name"));
        city.setId(rs.getInt("Id"));
        return city;
    }

    /**
     * Builds all objects from the table City
     * @param rs - resultset
     * @return a list of all the City objects
     * @throws SQLException
     */
    private List<City> buildObjects(ResultSet rs) throws SQLException{
        List<City> cities = new ArrayList<>();
        while(rs.next()) {
            cities.add(buildObject(rs));
        }
        return cities;
    }

}
