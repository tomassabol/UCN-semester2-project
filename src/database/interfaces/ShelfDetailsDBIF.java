package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Item;
import model.Shelf;

public interface ShelfDetailsDBIF {
    public List<Item> findByShelf(Shelf shelfw) throws SQLException, NotFoundException;
    public Shelf findByItemId(int id) throws SQLException, NotFoundException;
    public Item findByShelfId(int id) throws SQLException, NotFoundException;
    public void createShelfDetails(Shelf shelf, Item item) throws SQLException;
    public void deleteShelfDetails(Shelf shelf, Item item) throws SQLException;
    public void deleteAllShelfDetails(Shelf shelf) throws SQLException;
    public void removeItemFromStock(Item item) throws SQLException;
}
