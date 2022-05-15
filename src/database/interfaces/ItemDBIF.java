package database.interfaces;

import java.sql.SQLException;
import java.util.List;

import exceptions.NotEnoughInStockException;
import exceptions.NotFoundException;
import model.Department;
import model.Item;
import model.Product;

public interface ItemDBIF {
    public List<Item> findAllPerProduct(Product product) throws SQLException, NotFoundException;
    public Item findById(int id) throws SQLException, NotFoundException;
    public void createItem(Item item) throws SQLException;
    public void deleteItem(Item item) throws SQLException;
    public List<Item> selectItems(int amount, Product product, Department department) throws SQLException, NotFoundException, NotEnoughInStockException;
}
