package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.interfaces.ProductTypeDBIF;
import exceptions.NotFoundException;
import model.Product.ProductType;

public class ProductTypeDB implements ProductTypeDBIF {
    
    // PreparedStatements for the ProductTypeDB class
    private static final String FIND_ALL = "select * from ProductTypes";
    private static final String FIND_BY_ID = "select * from ProductTypes where Id = ?";

    private PreparedStatement findAll;
    private PreparedStatement findById;

    public ProductTypeDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
    }
    
    @Override
    public List<ProductType> findAll() throws SQLException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<ProductType> productTypes = buildObjects(rs);
        return productTypes;
    }

    @Override
    public ProductType findById(int id) throws SQLException, NotFoundException {
        ProductType productType = null;
        ResultSet rs;
        findById.setInt(1, id);
        rs = findById.executeQuery();
        while(rs.next()) {
            productType = buildObject(rs);
        }

        if(productType == null) { throw new NotFoundException("ProductType", id); }
        return productType;
    }

    // local methods

    private ProductType buildObject(ResultSet rs) throws SQLException {
        ProductType productType = ProductType.valueOf(rs.getString("Name"));
        return productType;
    }

    private List<ProductType> buildObjects(ResultSet rs) throws SQLException {
        List<ProductType> productTypes = new ArrayList<>();
        while(rs.next()) {
            productTypes.add(buildObject(rs));
        }
        return productTypes;
    }
    
}
