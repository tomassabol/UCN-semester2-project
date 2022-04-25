package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import database.OrderDB;
import database.interfaces.OrderDBIF;
import exceptions.NotEnoughInStockException;
import exceptions.NotFoundException;
import model.Order;
import model.OrderLine;
import model.Product;
import model.Employee;
import model.Customer;

public class OrderController {
	
	private OrderDBIF orderDBIF;
	private ItemController itemCtrl;
	private OrderLineController orderLineCtrl;
	private OrderDetailsController orderDetailsCtrl;
	
	/**
	 * Constructor for the OrderController class
	 * @throws SQLException
	 */	
	public OrderController() throws SQLException {
		orderDBIF = new OrderDB();
		itemCtrl = new ItemController();
		orderLineCtrl = new OrderLineController();
		orderDetailsCtrl = new OrderDetailsController();
	}
	/**
	 * Finds all orders
	 * @return a list of all Orders
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public List<Order> findAll() throws SQLException, NotFoundException {
		List<Order> orders = orderDBIF.findAll();
				return orders;
	}
	/**
	 * Finds the Orders by ID
	 * @param id - with the required ID
	 * @return the order with the required ID
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	
	public Order findById(int id) throws SQLException, NotFoundException {
		Order order = orderDBIF.findById(id);
		return order;
	}
	/**
	 * Creates a new object of the Order class
	 * @param employee - the employee that assigns the order
	 * @param customer - the customer that made the order
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	
	public Order createOrder(Employee employee,Customer customer) throws SQLException, NotFoundException {
		Order order = new Order(employee,customer);
		return order;
	}
	/**
	 * Deletes an Order
	 * @param order - the order that is requested to be deleted
	 * @throws SQLException
	 */
	
	public void deleteOrder(Order order) throws SQLException {
		orderDBIF.deleteOrder(order);
	}

	/**
	 * Adds the desired product and its quantity to the order
	 * Creates new orderline object and adds it to the order if everything is fine
	 * @param order - current order
	 * @param product - product to be added
	 * @param quantity
	 * @return true if all is good
	 * @throws SQLException
	 * @throws NotFoundException
	 * @throws NotEnoughInStockException
	 */
	public boolean addProduct(Order order, Product product, int quantity) throws SQLException, NotFoundException, NotEnoughInStockException {
		boolean returnValue = false;
		if(order.isProductPresent(product)) {
			quantity += order.getOrderLinebyProduct(product).getQuantity();
			// basic stock check 
			// TODO: update once the stock UC is implemented
			if(itemCtrl.findAllPerProduct(product).size() < quantity) {
				int currentStock = itemCtrl.findAllPerProduct(product).size();
				throw new NotEnoughInStockException(quantity, currentStock);
			}
		}

		OrderLine orderLine = new OrderLine(product, quantity);
		order.addOrderLine(orderLine);
		returnValue = true;

		return returnValue;
	}

	/**
	 * 
	 * @param order
	 * @return
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public boolean finishOrder(Order order) throws SQLException, NotFoundException {
		boolean returnValue = false;
		// sets the date to the order
		order.setOrderDate(LocalDate.now());
		// inserts all orderlines in the order into DB
		for(OrderLine orderLine : order.getOrderLines()) {
			orderLineCtrl.createOrderLine(orderLine.getProduct(), orderLine.getQuantity());
		}
		// insert order into DB
		orderDBIF.createOrder(order);

		// create order details to know what orderLines are in the order
		// TODO: test if it is possible to move this to previous foreach loop, if order was inserted into DB before the loop
		// We need OrderId to create OrderDetails. OrderId is assigned when order is inserted
		for(OrderLine orderLine : order.getOrderLines()) {
			orderDetailsCtrl.createOrderDetails(order, orderLine);
		}
		returnValue = true;
		return returnValue;
	}
	
}
