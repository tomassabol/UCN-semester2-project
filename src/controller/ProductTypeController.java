package controller;

import java.sql.SQLException;
import java.util.List;

import database.ProductTypeDB;
import database.interfaces.ProductTypeDBIF;
import exceptions.NotFoundException;
import model.Product.ProductType;

public class ProductTypeController {
    
    private ProductTypeDBIF productTypeDBIF;

    /**
     * Constructor for the ProductTyoeController class
     * @throws SQLException
     */
    public ProductTypeController() throws SQLException {
        productTypeDBIF = new ProductTypeDB();
    }

    /**
     * Find all ProductTypes in the DB
     * @return list of all Product types in DB
     * @throws SQLException
     */
    public List<ProductType> findAll() throws SQLException {
        List<ProductType> productTypes = productTypeDBIF.findAll();
        return productTypes;
    }

    /**
     * Finds a ProductType with the desired id
     * @param id - desired id
     * @return object of ProductType with the ID
     * @throws SQLException
     * @throws NotFoundException
     */
    public ProductType findById(int id) throws SQLException, NotFoundException {
        ProductType productType = productTypeDBIF.findById(id);
        return productType;
    }
}
