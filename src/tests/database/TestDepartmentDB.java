package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import database.DepartmentDB;
import database.CityDB;
import database.DBConnection;
import database.interfaces.CityDBIF;
import database.interfaces.DepartmentDBIF;
import exceptions.NotFoundException;
import model.City;
import model.Department;

public class TestDepartmentDB {
    DepartmentDBIF departmentDBIF;
    CityDBIF cityDBIF;

    @BeforeEach
    public void setUp() throws SQLException {
        departmentDBIF = new DepartmentDB();
        cityDBIF = new CityDB();
    }

    @Test
    public void testFindById1() throws SQLException, NotFoundException {
        // Arrange
        int id = 1;
        Department department;
        // Act
        department = departmentDBIF.findById(id);
        // Assert
        assertEquals("Department1", department.getName());
    }
    
    @Test
    public void testFindAll() throws SQLException, NotFoundException {
        // Arrange
        int size = 3;
        List<Department> departments = new ArrayList<>();
        // Act
        departments = departmentDBIF.findAll();
        // Assert
        assertEquals(size, departments.size());
    }

    @Test
    public void testCreateDepartment() throws SQLException, NotFoundException {
        // Arrange
        City city = cityDBIF.findById(1);
        Department createDepartment = new Department("testDepartment", city, "testAddress");
        Department returnDepartment;
        // Act
        departmentDBIF.createDepartment(createDepartment);
        returnDepartment = departmentDBIF.findById(4);
        // Assert
        assertEquals("testAddress", returnDepartment.getAddress());
    }


    @Test
    public void testUpdateDepartment() throws SQLException, NotFoundException {
        // Arrange
        Department department = departmentDBIF.findById(4);
        // Act
        department.setAddress("changedAddress");
        department.setName("changedName");

        departmentDBIF.updateDepartment(department);
        // Assert
        assertEquals("changedName", departmentDBIF.findById(4).getName());
        assertEquals("changedAddress", departmentDBIF.findById(4).getAddress());
    }
    
    @Test
    public void testDeleteDepartment() throws SQLException, NotFoundException {
        // Arrange
        Department department;
        List<Department> departments = new ArrayList<>();
        // Act
        department = departmentDBIF.findById(4);
        departmentDBIF.deleteDepartment(department);
        departments = departmentDBIF.findAll();
        // Assert
        assertEquals(3, departments.size());
    }
    

    @AfterEach
    public void clearUp() throws SQLException {
        DBConnection.getInstance().disconnect();
        departmentDBIF = null;
    }
    
}
