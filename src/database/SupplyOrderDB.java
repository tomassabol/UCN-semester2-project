package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.ProductController;
import database.interfaces.SupplierDBIF;
import database.interfaces.SupplyOrderDBIF;
import exceptions.NotFoundException;
import model.Product;
import model.Supplier;
import model.SupplyOrder;

public class SupplyOrderDB implements SupplyOrderDBIF {
	// PreparedStatements for the SupplyOrderDB class
	private static final String FIND_ALL = "select * from SupplyOrders";
	private static final String FIND_ALL_PER_PRODUCT = "select * from SupplyOrders where ProductId = ?";
	private static final String FIND_BY_ID = "select * from SupplyOrders where Id = ?";
	private static final String CREATE_SUPPLYORDER = "insert into SupplyOrders values(?, ?, ?, ?, ?)";
	private static final String UPDATE_SUPPLYORDER = "update SupplyOrders set Quantity = ? from SupplyOrders where Id = ?";
	private static final String DISABLE_SUPPLYORDER = "delete from SupplyOrders where Id = ?";
	private static final String SET_DELIVERED = "update SupplyOrders set Delivered = ? where Id = ?";
	
	private PreparedStatement findAll;
	private PreparedStatement findAllPerProduct;
	private PreparedStatement findById;
	private PreparedStatement createSupplyOrder;
	private PreparedStatement updateSupplyOrder;
	private PreparedStatement disableSupplyOrder;
	private PreparedStatement setDelivered;

	ProductController productCtrl = new ProductController();
	SupplierDBIF supplierDBIF = new SupplierDB();
	
	/**
	 * Constructor for the SupplyOrderDB class
	 * @throws SQLException 
	 */
	
	public SupplyOrderDB() throws SQLException {
		findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
		findAllPerProduct = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_PER_PRODUCT);
		findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
		createSupplyOrder = DBConnection.getInstance().getConnection().prepareStatement(CREATE_SUPPLYORDER, Statement.RETURN_GENERATED_KEYS);
		updateSupplyOrder = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_SUPPLYORDER);
		disableSupplyOrder = DBConnection.getInstance().getConnection().prepareStatement(DISABLE_SUPPLYORDER);
		setDelivered = DBConnection.getInstance().getConnection().prepareStatement(SET_DELIVERED);
	}
	
	/**
	 * @return a list of all SupplyOrders in a DB
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	@Override
	public List<SupplyOrder> findAll() throws SQLException, NotFoundException {
		ResultSet rs;
		rs = findAll.executeQuery();
		List<SupplyOrder> supplyOrders = buildObjects(rs);
		return supplyOrders;
	}
	
	@Override
	public List<SupplyOrder> findAllPerProduct(Product product) throws SQLException, NotFoundException {
		ResultSet rs;
		findAllPerProduct.setInt(1, product.getId());
		rs = findAllPerProduct.executeQuery();
		List<SupplyOrder> supplyOrders = buildObjects(rs);
		return supplyOrders;
	}
	
	@Override
	public SupplyOrder findById(int id) throws SQLException, NotFoundException {
		SupplyOrder supplyOrder = null;
		ResultSet rs;
		findById.setInt(1, id);
		rs = findById.executeQuery();
		while(rs.next()) {
			supplyOrder = buildObject(rs);
		}
		if (supplyOrder == null) { throw new NotFoundException("SupplyOrder", id); }
		
		return supplyOrder;
	}
	
	/**
	 * inserts a new Supply Order into DB
	 * @throws SQLException
	 */
	@Override
	public void createSupplyOrder(SupplyOrder supplyOrder) throws SQLException {
		createSupplyOrder.setInt(1, supplyOrder.getProduct().getId());
		createSupplyOrder.setInt(2, supplyOrder.getQuantity());
		createSupplyOrder.setDate(3, Date.valueOf(supplyOrder.getOrderDate()));
		createSupplyOrder.setInt(4, supplyOrder.getSupplier().getId());
		createSupplyOrder.setBoolean(5, false);
		supplyOrder.setId(DBConnection.getInstance().executeSqlInsertWithIdentity(createSupplyOrder));
	}

	@Override
	public void updateSupplyOrder(SupplyOrder supplyOrder) throws SQLException {
		updateSupplyOrder.setInt(1, supplyOrder.getQuantity());
		updateSupplyOrder.setInt(2, supplyOrder.getId());
		updateSupplyOrder.executeUpdate();
	}
	
	/**
	 * disables a Supply Order from DB
	 * @throws SQLException
	 */
	@Override
	public void disableSupplyOrder(SupplyOrder supplyOrder) throws SQLException {
		disableSupplyOrder.setInt(1, supplyOrder.getId());
		disableSupplyOrder.execute();
	}

	/**
	 * sets delivered to true
	 * @throws SQLException
	 */
	@Override
	public void setDelivered(SupplyOrder supplyOrder) throws SQLException {
		setDelivered.setBoolean(1, true);
		setDelivered.setInt(2, supplyOrder.getId());
		setDelivered.executeUpdate();
	}
	
	// local methods
	
	private SupplyOrder buildObject(ResultSet rs) throws SQLException, NotFoundException {
		Product product = productCtrl.findById(rs.getInt("ProductId"));
		Supplier supplier = supplierDBIF.findById(rs.getInt("SupplierId"));
		SupplyOrder supplyOrder = new SupplyOrder(product, rs.getInt("Quantity"), rs.getDate("OrderDate").toLocalDate(),supplier);
		supplyOrder.setId(rs.getInt("Id"));
		return supplyOrder;
	}
	
	private List<SupplyOrder> buildObjects(ResultSet rs) throws SQLException, NotFoundException {
		List<SupplyOrder> supplyOrder = new ArrayList<>();
		while(rs.next()) {
			supplyOrder.add(buildObject(rs));
		}
		return supplyOrder;
	}	
}
