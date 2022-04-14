package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Product.ProductType;

public interface ProductTypeDBIF {
    public List<ProductType> findAll() throws SQLException;
    public ProductType findById(int id) throws SQLException, NotFoundException;
}
