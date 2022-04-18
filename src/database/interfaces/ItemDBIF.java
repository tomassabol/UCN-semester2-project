package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Item;

public interface ItemDBIF {
    public List<Item> findAllPerProduct(int productId) throws SQLException, NotFoundException;
    public Item findById(int id) throws SQLException, NotFoundException;
    public void createItem(Item item) throws SQLException;
    public void deleteItem(Item item) throws SQLException;
}
