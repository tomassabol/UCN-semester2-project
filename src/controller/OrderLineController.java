/**
 * 
 */
package controller;

import java.sql.SQLException;
import java.util.List;

import database.OrderLineDB;
import database.interfaces.OrderLineDBIF;
import exceptions.NotEnoughInStockException;
import exceptions.NotFoundException;
import model.Department;
import model.Item;
import model.Order;
import model.OrderLine;
import model.Product;

/**
 * @author ttomy
 * @version 22 Apr 2022
 */
public class OrderLineController {

	/**
	 * Fields for class OrderLineController
	 */
	private OrderLineDBIF orderLineDBIF;
	private OrderLineDetailsController orderLineDetailsCtrl;
	private ItemController itemCtrl;
	
	/**
	 * Constructor for class OrderLineController
	 * @throws SQLException
	 */
	public OrderLineController() throws SQLException {
		orderLineDBIF = new OrderLineDB();
		orderLineDetailsCtrl = new OrderLineDetailsController();
		itemCtrl = new ItemController();
	}
	
	/**
	 * Finds all the OrderLines
	 * @return A list of the OrderLines
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public List<OrderLine> findAll() throws SQLException, NotFoundException {
		List<OrderLine> orderLines = orderLineDBIF.findAll();
		return orderLines;
	}
	
	/**
	 * Finds an orderLine by its id
	 * @param id The id of the orderLine
	 * @return The orderLien with the id
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public OrderLine findById(int id) throws SQLException, NotFoundException {
		OrderLine orderLine = orderLineDBIF.findById(id);
		return orderLine;
	}
	
	/**
	 * Finds an orderLine by the order and the product
	 * @param order - the order that the orderLine is in
	 * @param product - the product the orderLine has
	 * @return The orderLine within the order and with the product
	 */
	public OrderLine findByOrderAndProduct(Order order, Product product) {
		OrderLine orderLine = order.getOrderLinebyProduct(product);
		return orderLine;
		
	}
	
	/**
	 * Creates an order line
	 * @param product
	 * @param id
	 * @throws SQLException
	 * @throws NotFoundException
	 * @throws NotEnoughInStockException 
	 */
	public OrderLine createOrderLine(Product product, int quantity, Department department) throws SQLException, NotFoundException, NotEnoughInStockException {
		// create new OrderLine object
		OrderLine orderLine = new OrderLine(product, quantity);
		// insert OrderLine into DB
		orderLineDBIF.createOrderLine(orderLine);
		// select
		List<Item> items = itemCtrl.selectItems(quantity, product, department);
		// create order line details
		for(int i = 0; i < quantity; i++) {
			Item item = items.get(i);
			orderLineDetailsCtrl.createOrderLineDetails(orderLine, item);
			item.setSold(true);
		}
		return orderLine;
	}
	
	/**
	 * Updates an orderLine in the database
	 * @param orderLine
	 * @throws SQLException
	 */
	public void updateOrderLine(OrderLine orderLine) throws SQLException {
		orderLineDBIF.updateOrderLine(orderLine);
	}
	
	/**
	 * Deletes an orderLine from the database
	 * @param orderLine
	 * @throws SQLException
	 */
	public void deleteOrderLine(OrderLine orderLine) throws SQLException {
		orderLineDetailsCtrl.deleteAllOrderLineDetails(orderLine);
		orderLineDBIF.deleteOrderLine(orderLine);
	}
	
}
