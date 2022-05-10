package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.ItemController;
import database.interfaces.ShelfDetailsDBIF;
import exceptions.NotFoundException;
import model.Item;
import model.Shelf;

public class ShelfDetailsDB implements ShelfDetailsDBIF {
    
    // PreparedStatements for the ItemDB class
    private static final String FIND_BY_SHELF = "select * from ShelfDetails where ShelfId = ?";
    private static final String CREATE_SHELFDETAILS = "insert into ShelfDetails values(?, ?)";
    private static final String DELETE_SHELFDETAILS = "delete from ShelfDetails where ShelfId = ? and ItemId = ?";
    private static final String DELETE_ALL_SHELFDETAILS = "delete from ShelfDetails where ShelfId = ?";
    private static final String REMOVE_ITEM = "delete from ShelfDetails where ItemId = ?";

    private PreparedStatement findByShelf;
    private PreparedStatement createShelfDetails;
    private PreparedStatement deleteShelfDetails;
    private PreparedStatement deleteAllShelfDetails;
    private PreparedStatement removeItem;

    private ItemController itemCtrl = new ItemController();

    /**
     * Constructor for the ShelfDetailsDB class
     * @throws SQLException
     */
    public ShelfDetailsDB() throws SQLException {
        findByShelf = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_SHELF);
        createShelfDetails = DBConnection.getInstance().getConnection().prepareStatement(CREATE_SHELFDETAILS, Statement.RETURN_GENERATED_KEYS);
        deleteShelfDetails = DBConnection.getInstance().getConnection().prepareStatement(DELETE_SHELFDETAILS);
        deleteAllShelfDetails = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ALL_SHELFDETAILS);
        removeItem = DBConnection.getInstance().getConnection().prepareStatement(REMOVE_ITEM);
    }

    @Override
    public List<Item> findByShelf(Shelf shelf) throws SQLException, NotFoundException {
        ResultSet rs;
        findByShelf.setInt(1, shelf.getId());
        rs = findByShelf.executeQuery();
        List<Item> items = buildObjects(rs);
        return items;
    }

    @Override
    public void createShelfDetails(Shelf shelf, Item item) throws SQLException {
        createShelfDetails.setInt(1, shelf.getId());
        createShelfDetails.setInt(2, item.getId());
        createShelfDetails.execute();
    }

    @Override
    public void deleteShelfDetails(Shelf shelf, Item item) throws SQLException {
        deleteShelfDetails.setInt(1, shelf.getId());
        deleteShelfDetails.setInt(2, item.getId());
        deleteShelfDetails.execute();
    }

    @Override
    public void deleteAllShelfDetails(Shelf shelf) throws SQLException {
        deleteAllShelfDetails.setInt(1, shelf.getId());
        deleteAllShelfDetails.execute();
    }

    @Override
    public void removeItemFromStock(Item item) throws SQLException {
        removeItem.setInt(1, item.getId());
        removeItem.execute();
    }

    // local methods

    private Item buildObject(ResultSet rs) throws SQLException, NotFoundException {
        Item item = itemCtrl.findById(rs.getInt("ItemId"));
        return item;
    }

    private List<Item> buildObjects(ResultSet rs) throws SQLException, NotFoundException {
        List<Item> items = new ArrayList<>();
        while(rs.next()) {
            items.add(buildObject(rs));
        }
        return items;
    }

}
