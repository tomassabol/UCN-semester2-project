package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import database.SupplyOrderDB;
import database.interfaces.SupplyOrderDBIF;
import exceptions.NotFoundException;
import model.Item;
import model.Product;
import model.Shelf;
import model.Supplier;
import model.SupplyOrder;

public class SupplyOrderController {

	private SupplyOrderDBIF supplyOrderDBIF;
	private ItemController itemCtrl;
	private ShelfDetailsController shelfDetailsCtrl;
	private ShelfController shelfCtrl;
	
	/**
	 * Constructor for the SupplyOrder class
	 * @throws SQLException
	 */
	public SupplyOrderController() throws SQLException {
		supplyOrderDBIF = new SupplyOrderDB();
		itemCtrl = new ItemController();
		shelfDetailsCtrl = new ShelfDetailsController();
		shelfCtrl = new ShelfController();
	}
	
	/**
	 * Finds all supply orders existing in the database
	 * @return list of all supply orders in the database
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public List<SupplyOrder> findAll() throws SQLException, NotFoundException {
		List<SupplyOrder> supplyOrders = supplyOrderDBIF.findAll();
		return supplyOrders;
	}
	
	/**
	 * Find supply order with id given by the user
	 * @param id
	 * @return supply order with given id 
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public SupplyOrder findById(int id) throws SQLException, NotFoundException {
		SupplyOrder supplyOrder = supplyOrderDBIF.findById(id);
		return supplyOrder;
	}
	
	/**
	 * Creates new supply order and add it to the database
	 * @param product
	 * @param quantity
	 * @param orderdate
	 * @param supplier
	 * @throws SQLException
	 */
	public SupplyOrder createSupplyOrder(Product product, int quantity, LocalDate orderdate, Supplier supplier) throws SQLException {
		SupplyOrder supplyOrder = new SupplyOrder(product,quantity,orderdate,supplier);
		supplyOrderDBIF.createSupplyOrder(supplyOrder);
		return supplyOrder;
	}
	
	/**
	 * Update existing supply order in the database
	 * @param supplyorder
	 * @throws SQLException
	 */
	public void updateSupplyOrder(SupplyOrder supplyOrder, Product product, int quantity, LocalDate orderDate, Supplier supplier) throws SQLException {
		supplyOrder.setProduct(product);
		supplyOrder.setQuantity(quantity);
		supplyOrder.setOrderDate(orderDate);
		supplyOrder.setSupplier(supplier);
		supplyOrderDBIF.updateSupplyOrder(supplyOrder);
	}
	
	/**
	 * Disable the supply order
	 * @param supplyorder
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public void disableSupplyOrder(SupplyOrder supplyOrder) throws SQLException {
		supplyOrderDBIF.disableSupplyOrder(supplyOrder);
	}

	/**
	 * set delivered to true
	 * use when supplyOrder is put into stock - put into shelf
	 * @param supplyOrder
	 * @throws SQLException
	 */
	public void setDelivered(SupplyOrder supplyOrder) throws SQLException {
		supplyOrder.setIsDelivered(true);
		supplyOrderDBIF.setDelivered(supplyOrder);
	}

	public void addToStock(SupplyOrder supplyOrder, Shelf shelf) throws SQLException {
		Product product = supplyOrder.getProduct();
		// create new items from supply order
		for (int i = 0; i < supplyOrder.getQuantity(); i++) {
			Item item = itemCtrl.createItem(product, shelf.getDepartment());
			shelfDetailsCtrl.createShelfDetails(shelf, item);
		}

		// if shelf doesn't have a product assigned, set product
		if (shelf.getProduct() == null) { shelf.setProduct(product); }
		// update product quantity on she shelf
		int newQuantity = shelf.getProductQuantity() + supplyOrder.getQuantity();

		// update shelf in db
		shelfCtrl.updateShelf(shelf, shelf.getName(), product, newQuantity, shelf.getDepartment());
		// update supply order - mark delivered
		setDelivered(supplyOrder);
	}
	
}
