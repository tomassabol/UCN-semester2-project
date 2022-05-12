package controller;

import java.sql.SQLException;
import java.util.List;

import database.SupplierDB;
import database.interfaces.SupplierDBIF;
import exceptions.NotFoundException;
import model.City;
import model.Supplier;

public class SupplierController {
	
	private SupplierDBIF supplierDBIF;
	
	public SupplierController() throws SQLException {
		supplierDBIF = new SupplierDB();
	}
	
	public List<Supplier> findAll() throws SQLException, NotFoundException {
		List<Supplier> suppliers = supplierDBIF.findAll();
		return suppliers;
	}
	
	public Supplier findById(int id) throws SQLException, NotFoundException {
		Supplier supplier = supplierDBIF.findById(id);
		return supplier;
	}
	
	public Supplier createSupplier(String name, String email, String phone, City zipCode, String address) throws SQLException {
		Supplier supplier = new Supplier(name, email, phone, zipCode, address);
		supplierDBIF.createSupplier(supplier);
		return supplier;
	}
	
	public void updateSupplier(Supplier supplier, String name, String email, String phone, City zipCode, String address) throws SQLException {
		supplier.setName(name);
		supplier.setEmail(email);
		supplier.setPhone(phone);
		supplier.setZipCode(zipCode);
		supplier.setAddress(address);
		supplierDBIF.updateSupplier(supplier);
	}
	
	public void deleteSupplier(Supplier supplier) throws SQLException {
		supplierDBIF.deleteSupplier(supplier);
	}
}
