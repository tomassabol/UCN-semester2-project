package controller;

import java.sql.SQLException;
import java.util.List;

import database.ItemDB;
import database.interfaces.ItemDBIF;
import exceptions.NotFoundException;
import model.Item;
import model.Product;

public class ItemController {

    private ItemDBIF itemDBIF;
    
    /**
     * Constructor for the ItemController class
     * @throws SQLException
     */
    public ItemController() throws SQLException {
        itemDBIF = new ItemDB();
    }

    /**
     * Find all Items per product
     * @param product - product to find all items
     * @return List of all items per product
     * @throws SQLException
     * @throws NotFoundException
     */
    public List<Item> findAllPerProduct(Product product) throws SQLException, NotFoundException {
        List<Item> items = itemDBIF.findAllPerProduct(product);
        return items;
    }

    /**
     * Finds an Item with the desired ID
     * @param id
     * @return Item with the ID
     * @throws SQLException
     * @throws NotFoundException
     */
    public Item findById(int id) throws SQLException, NotFoundException {
        Item item = itemDBIF.findById(id);
        return item;
    }

    /**
     * Creates an Item
     * @param product - product for which the Item will be created
     * @throws SQLException
     */
    public void createItem(Product product) throws SQLException {
        Item item = new Item(product);
        itemDBIF.createItem(item);
    }

    public void deleteItem(Item item) throws SQLException {
        itemDBIF.deleteItem(item);
    }

    public List<Item> selectItems(int amount, Product product) throws SQLException, NotFoundException {
        List<Item> items = itemDBIF.selectItems(amount, product);
        return items;
    }

}
