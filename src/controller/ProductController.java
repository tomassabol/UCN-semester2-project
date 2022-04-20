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
	
	public ProductController() throws SQLException {
		productDBIF = new ProductDB();
		productTypeDBIF = new ProductTypeDB();
	}
	
	public List<Product> findAll() throws SQLException, NotFoundException {
		List<Product> products = productDBIF.findAll();
		return products;
	}
	
	public Product findById(int id) throws SQLException, NotFoundException {
		Product product = productDBIF.findById(id);
		return product;
	}
	
	public void createProduct(String name, String description, ProductType productType, BigDecimal price, int discount, boolean active) throws SQLException {
		Product product = new Product(name, description, productType, price, discount, active);
		productDBIF.createProduct(product);
	}
	
	public void updateProduct(Product product) throws SQLException {
		productDBIF.updateProduct(product);
	}
	
	public void disableProduct(int id) throws SQLException, NotFoundException {
		Product product = productDBIF.findById(id);
		product.setActive(false);
	}
	
	public void enableProduct(int id) throws SQLException, NotFoundException {
		Product product = productDBIF.findById(id);
		product.setActive(true);
	}
}
