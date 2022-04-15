package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.interfaces.ProductDBIF;
import database.interfaces.ProductTypeDBIF;
import exceptions.NotFoundException;
import model.Product;
import model.Product.ProductType;

public class ProductDB implements ProductDBIF {
    // PreparedStatements for the ProductDB class
    private static final String FIND_ALL = "select * from Products";
    private static final String FIND_ALL_ACTIVE = "select * from Products where Active = 1";
    private static final String FIND_BY_ID = "select * from Products where Id = ?";
    private static final String CREATE_PRODUCT = "insert into Products values(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCTS = "update Products set Name = ?, Description = ?, ProductTypeId = ?, Price = ?, Discount = ?, Active = ? from Products where Id = ?";
    private static final String DELETE_PRODUCTS = "delete from Products where Id = ?";

    private PreparedStatement findAll;
    private PreparedStatement findAllActive;
    private PreparedStatement findById;
    private PreparedStatement createProduct;
    private PreparedStatement updateProduct;
    private PreparedStatement deleteProduct;

    ProductTypeDBIF productTypeDBIF = new ProductTypeDB();

    /**
     * Constructor for the ProductDB class
     * @throws SQLException
     */
    public ProductDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findAllActive = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL_ACTIVE);
        findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
        createProduct = DBConnection.getInstance().getConnection().prepareStatement(CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
        updateProduct = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_PRODUCTS);
        deleteProduct = DBConnection.getInstance().getConnection().prepareStatement(DELETE_PRODUCTS);
    }

    /**
     * @return list of all Products in database
     * @throws SQLException
     * @throws NotFoundException
     */
    @Override
    public List<Product> findAll() throws SQLException, NotFoundException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Product> products = buildObjects(rs);
        return products;
    }

    /**
     * @return list of all active Products in database
     * @throws SQLException
     * @throws NotFoundException
     */
    @Override
    public List<Product> findAllActive() throws SQLException, NotFoundException {
        ResultSet rs;
        rs = findAllActive.executeQuery();
        List<Product> products = buildObjects(rs);
        return products;
    }

    /**
     * @param id - id of the desired product
     * @return product with the desired id
     * @throws SQLException
     * @throws NotFoundException
     */
    @Override
    public Product findById(int id) throws SQLException, NotFoundException {
        Product product = null;
        ResultSet rs;
        findById.setInt(1, id);
        rs = findById.executeQuery();
        while(rs.next()) {
            product = buildObject(rs);
        }

        if (product == null) { throw new NotFoundException("Product", id); }

        return product;
    }

    /**
     * inserts new Product into DB
     * @throws SQLException
     */
    @Override
    public void createProduct(Product product) throws SQLException {
        createProduct.setString(1, product.getName());
        createProduct.setString(2, product.getDescription());
        createProduct.setString(3, String.valueOf(product.getProductType().getId()));
        createProduct.setBigDecimal(4, product.getPrice());
        createProduct.setInt(5, product.getDiscount());
        createProduct.setBoolean(6, product.getActive());
        product.setId(DBConnection.getInstance().executeSqlInsertWithIdentity(createProduct));
    }

    /**
     * updates Product object in DB
     * @throws SQLException
     */
    @Override
    public void updateProduct(Product product) throws SQLException {
        updateProduct.setString(1, product.getName());
        updateProduct.setString(2, product.getDescription());
        updateProduct.setString(3, String.valueOf(product.getProductType().getId()));
        updateProduct.setBigDecimal(4, product.getPrice());
        updateProduct.setInt(5, product.getDiscount());
        updateProduct.setBoolean(6, product.getActive());
        updateProduct.setInt(7, product.getId());
        updateProduct.executeUpdate();
    }

    /**
     * deletes Product from the database
     * @throws SQLException
     */
    @Override
    public void deleteProduct(Product product) throws SQLException {
        deleteProduct.setInt(1, product.getId());
        deleteProduct.executeUpdate();
    }

    // local methods

    /**
     * Builds an object of the Product class from the DB columns
     * @param rs - ResultSet
     * @return object of the Product class
     * @throws SQLException
     * @throws NotFoundException
     */
    private Product buildObject(ResultSet rs) throws SQLException, NotFoundException {
        // TODO: change this to request ProductTypeController
        int id = rs.getInt("ProductTypeId");
        ProductType productType = productTypeDBIF.findById(id);
        Product product = new Product(rs.getString("Name"), rs.getString("Description"), productType, rs.getBigDecimal("Price"), rs.getInt("Discount"), rs.getBoolean("Active"));
        product.setId(rs.getInt("Id"));
        return product;
    }

    /**
     * Builds a list of all the objects of the Product class from the DB columns
     * @param rs - ResultSet
     * @return a list of all Products in DB
     * @throws SQLException
     * @throws NotFoundException
     */
    private List<Product> buildObjects(ResultSet rs) throws SQLException, NotFoundException {
        List<Product> products = new ArrayList<>();
        while(rs.next()) {
            products.add(buildObject(rs));
        }
        return products;
    }
    
}
