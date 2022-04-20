package controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import database.ProductDB;
import database.ProductTypeDB;
import database.interfaces.ProductDBIF;
import database.interfaces.ProductTypeDBIF;
import exceptions.NotFoundException;
import model.Product;
import model.Product.ProductType;

public class ProductController {

	private ProductDBIF productDBIF;
	private ProductTypeDBIF productTypeDBIF;
	
	/**
	 * Constructor for the ProductController class
	 * @throws SQLException
	 */
	public ProductController() throws SQLException {
		productDBIF = new ProductDB();
		productTypeDBIF = new ProductTypeDB();
	}
	
	/**
	 * Find all products exisiting in the database
	 * @return list of all products in the database
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public List<Product> findAll() throws SQLException, NotFoundException {
		List<Product> products = productDBIF.findAll();
		return products;
	}
	
	/**
	 * Find product with id given by the user
	 * @param id
	 * @return product with given id 
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public Product findById(int id) throws SQLException, NotFoundException {
		Product product = productDBIF.findById(id);
		return product;
	}
	
	/**
	 * Creates new product and add it to the database
	 * @param name
	 * @param description
	 * @param productType
	 * @param price
	 * @param discount
	 * @param active
	 * @throws SQLException
	 */
	public void createProduct(String name, String description, ProductType productType, BigDecimal price, int discount, boolean active) throws SQLException {
		Product product = new Product(name, description, productType, price, discount, active);
		productDBIF.createProduct(product);
	}
	
	/**
	 * Update existing product in the database
	 * @param product
	 * @throws SQLException
	 */
	public void updateProduct(Product product) throws SQLException {
		productDBIF.updateProduct(product);
	}
	
	/**
	 * Disable the product
	 * @param id
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public void disableProduct(int id) throws SQLException, NotFoundException {
		Product product = productDBIF.findById(id);
		productDBIF.deleteProduct(product);
	}
	
	/**
	 * Enable the product55
	 * @param id
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public void enableProduct(int id) throws SQLException, NotFoundException {
		Product product = productDBIF.findById(id);
		productDBIF.updateProduct(product);
	}
}
