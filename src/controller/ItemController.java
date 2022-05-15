package controller;

import java.sql.SQLException;
import java.util.List;

import database.ItemDB;
import database.interfaces.ItemDBIF;
import exceptions.NotEnoughInStockException;
import exceptions.NotFoundException;
import model.Department;
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
     * @return 
     * @throws SQLException
     */
    public Item createItem(Product product, Department department) throws SQLException {
        Item item = new Item(product, department);
        item.setSold(false);
        itemDBIF.createItem(item);
        return item;
    }

    /**
     * deletes specific item from DB
     * @param item
     * @throws SQLException
     */
    public void deleteItem(Item item) throws SQLException {
        itemDBIF.deleteItem(item);
    }

    /**
     * Selects specific amount of items per product that were not yet sold
     * @param amount - amount of items to be selected
     * @param product - product to get items for
     * @return list of the items
     * @throws SQLException
     * @throws NotFoundException
     * @throws NotEnoughInStockException 
     */
    public List<Item> selectItems(int amount, Product product, Department department) throws SQLException, NotFoundException, NotEnoughInStockException {
        List<Item> items = itemDBIF.selectItems(amount, product, department);
        return items;
    }

}
