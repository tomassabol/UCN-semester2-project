package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Supplier;

public interface SupplierDBIF {
	public List<Supplier> findAll() throws SQLException, NotFoundException;
	public Supplier findById(int id) throws SQLException, NotFoundException;
	public void createSupplier(Supplier supplier) throws SQLException;
	public void updateSupplier(Supplier supplier) throws SQLException;
	public void disableSupplier(Supplier supplier) throws SQLException;
}
