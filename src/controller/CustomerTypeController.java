/**
 * 
 */
package controller;

import java.sql.SQLException;
import java.util.List;

import database.CustomerTypeDB;
import database.interfaces.CustomerTypeDBIF;
import exceptions.NotFoundException;
import model.Customer.CustomerType;

/**
 * @author ttomy
 * @version 20 Apr 2022
 */
public class CustomerTypeController {

	//Fields for class CustomerTypeController
	private CustomerTypeDBIF customerTypeDBIF;
	
	/**
	 * Constructor for class CustomerTypeController
	 * @throws SQLException
	 */
	public CustomerTypeController() throws SQLException {
		customerTypeDBIF = new CustomerTypeDB();
	}
	
	/**
	 * Finds all the customerTypes
	 * @return a list of all the customerTypes
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public List<CustomerType> findAll() throws SQLException, NotFoundException {
		List<CustomerType> customerTypes = customerTypeDBIF.findAll();
		return customerTypes;
	}
	
	/**
	 * Finds a customerType in the database by its id
	 * @param id The id of the custoemType
	 * @return The customerType with the id
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public CustomerType findById(int id) throws SQLException, NotFoundException {
		CustomerType customerType = customerTypeDBIF.findById(id);
		return customerType;
	}
}
