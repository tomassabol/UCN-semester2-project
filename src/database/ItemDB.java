package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.ProductController;
import database.interfaces.ItemDBIF;
import exceptions.NotFoundException;
import model.Item;
import model.Product;

public class ItemDB implements ItemDBIF {
    
    // PreparedStatements for the ItemDB class
    private static final String FIND_ALL = "select * from Items where ProductId = ?";
    private static final String FIND_BY_ID = "select * from Items where Id = ?";
    private static final String CREATE_ITEM = "insert into Items values(?, ?)";
    private static final String DELETE_ITEM = "delete from Items where Id = ?";
    private static final String SELECT_ITEMS = "select top (?) * from Items where ProductId = ? and Sold = 0";

    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement createItem;
    private PreparedStatement deleteItem;
    private PreparedStatement selectItems;

    private ProductController productController = new ProductController();

    /**
     * Constructor for the ItemDB class
     * @throws SQLException
     */
    public ItemDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
        createItem = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ITEM, Statement.RETURN_GENERATED_KEYS);
        deleteItem = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ITEM);
        selectItems = DBConnection.getInstance().getConnection().prepareStatement(SELECT_ITEMS);
    }

    /**
     * @return list of all Items per product in database
     * @throws SQLException
     * @throws NotFoundException
     */
    @Override
    public List<Item> findAllPerProduct(Product product) throws SQLException, NotFoundException {
        ResultSet rs;
        findAll.setInt(1, product.getId());
        rs = findAll.executeQuery();
        List<Item> items = buildObjects(rs);

        if(items.size() == 0) {
            throw new NotFoundException("Items for a product", product.getId());
        }
        return items;
    }

    /**
     * @param id - id of the desired item
     * @return item with the desired id
     * @throws SQLException
     * @throws NotFoundException
     */
    @Override
    public Item findById(int id) throws SQLException, NotFoundException {
        Item item = null;
        ResultSet rs;
        findById.setInt(1, id);
        rs = findById.executeQuery();
        while(rs.next()) {
            item = buildObject(rs);
        }

        if (item == null) { throw new NotFoundException("Item", id); }

        return item;
    }

    /**
     * inserts new Item into DB
     * @throws SQLException
     */
    @Override
    public void createItem(Item item) throws SQLException {
        createItem.setInt(1, item.getProduct().getId());
        createItem.setBoolean(2, item.isSold());
        item.setId(DBConnection.getInstance().executeSqlInsertWithIdentity(createItem));
    }

    /**
     * deletes Item from the database
     * @throws SQLException
     */
    @Override
    public void deleteItem(Item item) throws SQLException {
        deleteItem.setInt(1, item.getId());
        deleteItem.executeUpdate();
    }

    @Override
    public List<Item> selectItems(int amount, Product product) throws SQLException, NotFoundException {
        ResultSet rs;
        selectItems.setInt(1, amount);
        selectItems.setInt(2, product.getId());
        rs = selectItems.executeQuery();
        List<Item> items = buildObjects(rs);
        if(items.size() == 0) {
            throw new NotFoundException("Items for a product", product.getId());
        }

        return items;
    }

    // local methods

    /**
     * Builds an object of the Item class from the DB columns
     * @param rs - ResultSet
     * @return object of the Item class
     * @throws SQLException
     * @throws NotFoundException
     */
    private Item buildObject(ResultSet rs) throws SQLException, NotFoundException {
        Product product = productController.findById(rs.getInt("ProductId"));
        Item item = new Item(product);
        item.setId(rs.getInt("Id"));
        return item;
    }

    /**
     * Builds a list of all the objects of the Item class from the DB columns
     * @param rs - ResultSet
     * @return a list of all Items in DB
     * @throws SQLException
     * @throws NotFoundException
     */
    private List<Item> buildObjects(ResultSet rs) throws SQLException, NotFoundException {
        List<Item> items = new ArrayList<>();
        while(rs.next()) {
            items.add(buildObject(rs));
        }
        return items;
    }

}
