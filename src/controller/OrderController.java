package controller;

import java.sql.SQLException;
import java.util.List;

import database.OrderDB;
import database.interfaces.OrderDBIF;
import exceptions.NotFoundException;
import model.Order;
import model.Employee;
import model.Customer;

public class OrderController {
	
	private OrderDBIF orderDBIF;
	
	/**
	 * Constructor for the OrderController class
	 * @throws SQLException
	 */	
	public OrderController() throws SQLException {
		orderDBIF = new OrderDB();
		
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
	 * Creates a new Order
	 * @param employee - the employee that assigns the order
	 * @param customer - the customer that made the order
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	
	public void createOrder(Employee employee,Customer customer) throws SQLException, NotFoundException {
		Order order = new Order(employee,customer);
		orderDBIF.createOrder(order);
	}
	/**
	 * Deletes an Order
	 * @param order - the order that is requested to be deleted
	 * @throws SQLException
	 */
	
	public void  deleteOrder(Order order) throws SQLException {
		orderDBIF.deleteOrder(order);
	}
	
}
