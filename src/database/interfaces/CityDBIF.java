package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.City;

public interface CityDBIF {
    public List<City> findAll() throws SQLException;
    public City findById(int id) throws SQLException, NotFoundException;
    public City findByZip(String zip) throws SQLException;
    public void createCity(City city) throws SQLException;
    public void updateCity(City city) throws SQLException;
    public void deleteCity(City city) throws SQLException;
}
