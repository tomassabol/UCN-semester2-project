package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Product;

public interface ProductDBIF {
    public List<Product> findAll() throws SQLException, NotFoundException;
    public List<Product> findAllActive() throws SQLException, NotFoundException;
    public Product findById(int id) throws SQLException, NotFoundException;
    public void createProduct(Product product) throws SQLException;
    public void updateProduct(Product product) throws SQLException;
    public void deleteProduct(Product product) throws SQLException;
}
