package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.*;

import database.CityDB;
import database.DBConnection;
import database.interfaces.CityDBIF;
import exceptions.NotFoundException;
import model.City;

public class TestCity {

    private CityDBIF cityDBIF;
    private City city;
    City city1;
    private City createCity;
    private City returnCity = null;
    
    @BeforeEach
    public void setUp() throws SQLException, NotFoundException {
        cityDBIF = new CityDB();
        city = null;
        city1 = null;
        createCity = null;
        returnCity = null;
    }

    public void findById4() throws SQLException, NotFoundException {
        city1 = cityDBIF.findById(4);
    }

    @Test
    public void testGetCityById1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        // Act
        city = cityDBIF.findById(id);
        // Assert
        assertNotNull(city);
    }

    @Test
    public void testGetCityById4() throws SQLException, NotFoundException {
        // Assert
        assertThrows(NotFoundException.class, () -> findById4());
    }

    @Test
    public void testFindAllReturnListSize3() throws SQLException {
    	// Arrange
    	int size;
        // Act
        size = cityDBIF.findAll().size();

        // Assert
        assertEquals(3, size);
    }

    @Test
    public void testCreateCity() throws SQLException, NotFoundException {
        // Arrange
        createCity = new City("DK-9400", "Nørresundby");
        // Act
        cityDBIF.createCity(createCity);
        returnCity = cityDBIF.findById(4);
        // Assert
        assertEquals(createCity.getZipCode(), returnCity.getZipCode());
    }

    @Test
    public void testUpdateCity() throws SQLException, NotFoundException {
        // Arrange
        city = cityDBIF.findById(4);
        // Act
        city.setName("changedName");
        cityDBIF.updateCity(city);
        // Assert
        assertEquals(city.getName(), cityDBIF.findById(4).getName());
    }

    @Test
    public void testDeleteCity() throws SQLException, NotFoundException {
        // Act
        city = cityDBIF.findById(4);
        cityDBIF.deleteCity(city);
        // Assert
        assertEquals(3, cityDBIF.findAll().size());
    }

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        city = null;
        city1 = null;
    }
}
