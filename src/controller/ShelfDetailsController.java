package controller;

import java.sql.SQLException;
import java.util.List;

import database.ShelfDetailsDB;
import database.interfaces.ShelfDetailsDBIF;
import exceptions.NotFoundException;
import model.Item;
import model.Shelf;

public class ShelfDetailsController {
    
    private ShelfDetailsDBIF shelfDetailsDBIF;

    public ShelfDetailsController() throws SQLException {
        shelfDetailsDBIF = new ShelfDetailsDB();
    }

    public List<Item> findbyShelf(Shelf shelf) throws SQLException, NotFoundException {
        List<Item> items = shelfDetailsDBIF.findByShelf(shelf);
        return items;
    }

    public Shelf findByItemId(int id) throws SQLException, NotFoundException {
        Shelf shelf = shelfDetailsDBIF.findByItemId(id);
        return shelf;
    }

    public Item findByShelfId(int id) throws SQLException, NotFoundException {
    	Item item = shelfDetailsDBIF.findByShelfId(id);
        return item;
    }

    public void createShelfDetails(Shelf shelf, Item item) throws SQLException {
        shelfDetailsDBIF.createShelfDetails(shelf, item);
    }

    public void deleteShelfDetails(Shelf shelf, Item item) throws SQLException {
        shelfDetailsDBIF.deleteShelfDetails(shelf, item);
    }

    public void deleteAllShelfDetails(Shelf shelf) throws SQLException {
        shelfDetailsDBIF.deleteAllShelfDetails(shelf);
    }

    public void removeItemFromStock(Item item) throws SQLException {
        shelfDetailsDBIF.removeItemFromStock(item);
    }

}
