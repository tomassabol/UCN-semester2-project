package controller;

import java.sql.SQLException;
import java.util.List;

import database.OrderDB;
import database.OrderLineDB;
import database.OrderDetailsDB;
import database.interfaces.OrderDBIF;
import database.interfaces.OrderLineDBIF;
import database.interfaces.OrderDetailsDBIF;
import exceptions.NotFoundException;
import model.Order;
import model.OrderLine;
import model.Employee;
import model.Customer;

public class OrderController {
	
	private OrderDBIF orderDBIF;
	private OrderLineDBIF orderLineDBIF;
	private CustomerController customerCtrl;
	private EmployeeController employeeCtrl;
	
	public OrderController() throws SQLException {
		orderDBIF = new OrderDB();
		orderLineDBIF = new OrderLineDB();
		customerCtrl = new CustomerController();
		employeeCtrl = new EmployeeController();
		
	}
	
	public List<Order> findAll() throws SQLException, NotFoundException {
		List<Order> orders = orderDBIF.findAll();
				return orders;
	}
	
	public Order findById(int id) throws SQLException, NotFoundException {
		Order order = orderDBIF.findById(id);
		return order;
	}
	
	public Order createOrder(Employee employee,Customer customer) throws SQLException, NotFoundException {
		Order order = new Order(employee,customer);
		orderDBIF.createOrder(order);
	}
	
	public Order deleteOrder(Order order) throws SQLException {
		orderDBIF.deleteOrder(order);
	}
	
	
	
	
}
