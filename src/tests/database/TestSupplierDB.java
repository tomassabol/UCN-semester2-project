package tests.database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.CityDB;
import database.DBConnection;
import database.SupplierDB;
import database.interfaces.CityDBIF;
import database.interfaces.SupplierDBIF;
import exceptions.NotFoundException;
import model.City;
import model.Supplier;

class TestSupplierDB {
	private SupplierDBIF supplierDBIF;
	private CityDBIF cityDBIF;
	Supplier supplier;
	
	@BeforeEach
	public void setUp() throws SQLException {
		supplierDBIF = new SupplierDB();
		cityDBIF = new CityDB();
	}
	
	public void findById4() throws SQLException, NotFoundException {
		supplier = supplierDBIF.findById(4);
	}
	
	@Test
	public void testFindById1() throws SQLException, NotFoundException {
		// Arrange
		int id = 1;
		Supplier supplier = null;
		// Act
		supplier = supplierDBIF.findById(id);
		// Assert
		assertNotNull(supplier);
	}
	
	@Test
	public void testFindById4() throws SQLException, NotFoundException {
		// Assert 
		assertThrows(NotFoundException.class, () -> findById4());
	}
	
	@Test
	public void testFindAll() throws SQLException, NotFoundException {
		// Arrange
		int size = 4;
		List<Supplier> suppliers = new ArrayList<>();
		// Act
		suppliers = supplierDBIF.findAll();
		// Assert 
		assertEquals(size, suppliers.size());
	}
	
	@Test
	public void testCreateSupplier() throws SQLException, NotFoundException {
		// Arrange
		City zipCode = cityDBIF.findById(1);
		supplier = new Supplier("testName", "testMail", "testPhone", zipCode, "testAddress");
		Supplier returnSupplier = null;
		// Act
		supplierDBIF.createSupplier(supplier);
		returnSupplier = supplierDBIF.findById(4);
		// Assert
		assertNotNull(returnSupplier);
	}
	
	@Test
	public void testUpdateSupplier() throws SQLException, NotFoundException {
		// Arrange
		supplier = supplierDBIF.findById(1);
		City city = cityDBIF.findById(1);
		Supplier returnSupplier = null;
		// Act
		supplier.setName("testUpdate");
		supplier.setEmail("testUpdate");
		supplier.setPhone("testUpdate");
		supplier.setZipCode(city);
		supplier.setAddress("testUpdate");
		supplierDBIF.updateSupplier(supplier);
		returnSupplier = supplierDBIF.findById(1);
		// Act
		assertNotNull(returnSupplier);
		assertEquals("testUpdate", returnSupplier.getName());
	}
	
	@Test 
	public void testDeleteSupplier() throws SQLException, NotFoundException {
		// Arrange
		List<Supplier> suppliers = null;
		// Act
		supplier = supplierDBIF.findById(3);
		supplierDBIF.deleteSupplier(supplier);
		suppliers = supplierDBIF.findAll();
		// Assert
		assertEquals(2, suppliers.size());
	}
	
	@AfterEach
	public void clearUp() throws SQLException {
		DBConnection.getInstance().disconnect();
		supplierDBIF = null;
		cityDBIF = null;
	}
}
