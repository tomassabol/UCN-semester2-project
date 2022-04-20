/**
 * 
 */
package controller;

import java.sql.SQLException;
import java.util.List;

import database.CustomerDB;
import database.CustomerTypeDB;
import database.interfaces.CustomerDBIF;
import database.interfaces.CustomerTypeDBIF;
import exceptions.NotFoundException;
import model.City;
import model.Customer;
import model.Customer.CustomerType;

/**
 * @author ttomy
 * @version 20 Apr 2022
 */
public class CustomerController {
	
	// Fields for class CustomerController
	private CustomerDBIF customerDBIF;
	private CustomerTypeDBIF customerTypeDBIF;
	private CityController cityCtrl;
	
	/*
	 * Constructor for class CustomerController
	 */
	public CustomerController() throws SQLException {
		customerDBIF = new CustomerDB();
		customerTypeDBIF = new CustomerTypeDB();
		cityCtrl = new CityController();
	}
	
	/**
	 * Returns all the customers in the system
	 * @return all the customers
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public List<Customer> findAll() throws SQLException, NotFoundException {
		List<Customer> customers = customerDBIF.findAll();
		return customers;
	}
	
	/**
	 * Finds a customer by its id 
	 * @param id The id of the customer
	 * @return the customer with the id
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public Customer findById(int id) throws SQLException, NotFoundException {
		Customer customer = customerDBIF.findById(id);
		return customer;
	}
	
	/**
	 * Creates a customer and adds it to the database
	 * @param name The name of the customer
	 * @param email The email of the customer
	 * @param phone The phone number of the customer
	 * @param zipCode The zip code of the city the customer lives in
	 * @param address The address of the customer
	 * @param customerType The type of the Customer
	 * @throws SQLException
	 */
	public void createCustomer(String name, String email, String phone, City zipCode, String address, CustomerType customerType) throws SQLException {
		Customer customer = new Customer(name, email, phone, zipCode, address, customerType);
		customerDBIF.createCustomer(customer);
	}
	
	/**
	 * Updates a customer in the database
	 * @param customer The customer that will be updated
	 * @throws SQLException
	 */
	public void updateCustomer(Customer customer) throws SQLException {
		customerDBIF.updateCustomer(customer);
	}
	
	/**
	 * Hard deletes the customer from the database
	 * @param customer
	 * @throws SQLException
	 */
	public void deleteCustomer(Customer customer) throws SQLException {
		customerDBIF.deleteCustomer(customer);
	}
}
