/**
 * 
 */
package controller;

import java.sql.SQLException;
import java.util.List;

import database.OrderLineDetailsDB;
import database.interfaces.OrderLineDetailsDBIF;
import exceptions.NotFoundException;
import model.Item;
import model.OrderLine;

/**
 * @author ttomy
 * @version 22 Apr 2022
 */
public class OrderLineDetailsController {

	/**
	 * Fields for class OrderLineDetailsController
	 */
	private OrderLineDetailsDBIF orderLineDetailsDBIF;
	
	/**
	 * Constructor for class OrderLineDetailsController
	 * @throws SQLException
	 */
	public OrderLineDetailsController() throws SQLException {
		orderLineDetailsDBIF = new OrderLineDetailsDB();
	}
	
	/**
	 * Lists all the items for a specific orderLine
	 * @param id The id of the orderLine
	 * @return A list of the items in an orderLine
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public List<Item> findByOrderLine(int id) throws SQLException, NotFoundException {
		List<Item> items = orderLineDetailsDBIF.findByOrderLine(id);
		return items;
	}
	
	/**
	 * It inserts the details of an orderLine into the database / orderLine id and item id
	 * @param orderLine The orderLine thats details are getting inserted into the database
	 * @param item The item that is associated with the orderLine
	 * @throws SQLException
	 */
	public void createOrderLineDetails(OrderLine orderLine, Item item) throws SQLException {
		orderLineDetailsDBIF.createOrderLineDetails(orderLine, item);
	}

	public void deleteOrderLineDetails(OrderLine orderLine, Item item) throws SQLException {
		orderLineDetailsDBIF.deleteOrderLineDetails(orderLine, item);
	}

	public void deleteAllOrderLineDetails(OrderLine orderLine) throws SQLException {
		orderLineDetailsDBIF.deleteAllOrderLineDetails(orderLine);
	}

}
