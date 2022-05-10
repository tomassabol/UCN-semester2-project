package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Product;
import model.SupplyOrder;

public interface SupplyOrderDBIF {
	public List<SupplyOrder> findAll() throws SQLException, NotFoundException;
	public List<SupplyOrder> findAllPerProduct(Product product) throws SQLException, NotFoundException;
	public SupplyOrder findById(int id) throws SQLException, NotFoundException;
	public void createSupplyOrder(SupplyOrder supplyOrder) throws SQLException;
	public void updateSupplyOrder(SupplyOrder supplyOrder) throws SQLException;
	public void disableSupplyOrder(SupplyOrder supplyOrder) throws SQLException;
}
