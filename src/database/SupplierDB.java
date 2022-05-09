package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.CityController;
import database.interfaces.SupplierDBIF;
import exceptions.NotFoundException;
import model.City;
import model.Supplier;

public class SupplierDB implements SupplierDBIF {
	
	// PreparedStatements for the EmployeeDB class
	private static final String FIND_ALL = "select * from Suppliers";
	private static final String FIND_ALL_PER_PRODUCT = ""; // fix it
	private static final String FIND_BY_ID = "select * from Suppliers where Id = ?";
	private static final String CREATE_SUPPLIER = "insert into Suppliers values(?, ?, ?, ?, ?)";
	private static final String UPDATE_SUPPLIER = "update Suppliers set Name = ?, Email = ?, Phone = ?, Zipcode = ?, Adress = ? from Suppliers where Id = ?";
	private static final String DELETE_SUPPLIER = "delete from Suppliers where Id = ?";
	
	private PreparedStatement findAll;
	private PreparedStatement findAllPerProduct;
	private PreparedStatement findById;
	private PreparedStatement createSupplier;
	private PreparedStatement updateSupplier;
	private PreparedStatement deleteSupplier;
	
	CityController cityCtrl = new CityController();
	
	/**
	 * Constructor for the SupplierDB class
	 * @throws SQLException 
	 */
	public SupplierDB() throws SQLException {
		findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
		findAllPerProduct = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_PER_PRODUCT);
		findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
		createSupplier = DBConnection.getInstance().getConnection().prepareStatement(CREATE_SUPPLIER);
		updateSupplier = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_SUPPLIER);
		deleteSupplier = DBConnection.getInstance().getConnection().prepareStatement(DELETE_SUPPLIER);
	}
	
	@Override
	public List<Supplier> findAll() throws SQLException, NotFoundException {
		ResultSet rs;
		rs = findAll.executeQuery();
		List<Supplier> suppliers = buildObjects(rs);
		return suppliers;
	}
	
	@Override
	public List<Supplier> findAllPerProduct() {
		List<Supplier> suppliers = new ArrayList<>();
		return suppliers;
	}
	
	@Override
	public Supplier findById(int id) throws SQLException, NotFoundException {
		Supplier supplier = null;
		ResultSet rs;
		findById.setInt(1, id);
		rs = findById.executeQuery();
		while(rs.next()) {
			supplier = buildObject(rs);
		}
		if (supplier == null) { throw new NotFoundException("Supplier", id); }
		
		return supplier;
	}
	
	@Override
	public void createSupplier(Supplier supplier) throws SQLException {
		createSupplier.setString(1, supplier.getName());
		createSupplier.setString(2, supplier.getEmail());
		createSupplier.setString(3, supplier.getPhone());
		createSupplier.setString(4, supplier.getZipCode().getZipCode());
		createSupplier.setString(5, supplier.getAddress());
		supplier.setId(DBConnection.getInstance().executeSqlInsertWithIdentity(createSupplier));
	}
	
	@Override
	public void updateSupplier(Supplier supplier) throws SQLException {
		updateSupplier.setString(1, supplier.getName());
		updateSupplier.setString(2, supplier.getEmail());
		updateSupplier.setString(3, supplier.getPhone());
		updateSupplier.setString(4, supplier.getZipCode().getZipCode());
		updateSupplier.setString(5, supplier.getAddress());
		updateSupplier.setInt(6, supplier.getId());
		updateSupplier.executeQuery();
	}
	
	@Override
	public void disableSupplier(Supplier supplier) throws SQLException {
		deleteSupplier.setInt(1, supplier.getId());
		deleteSupplier.executeQuery();
	}
	
	private Supplier buildObject(ResultSet rs) throws SQLException, NotFoundException {
		City zipCode = cityCtrl.findByZip(rs.getString("ZIP"));
		Supplier supplier = new Supplier(rs.getString("Name"), rs.getString("Email"), rs.getString("Phone"), zipCode, rs.getString("Address"));
		supplier.setId(rs.getInt("Id"));
		return supplier;
	}
	
	private List<Supplier> buildObjects(ResultSet rs) throws SQLException, NotFoundException {
		List<Supplier> suppliers = new ArrayList<>();
		while(rs.next()) {
			suppliers.add(buildObject(rs));
		}
		return suppliers;
	}
}
