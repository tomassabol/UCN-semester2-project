package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.ItemController;
import database.interfaces.ShelfDBIF;
import database.interfaces.ShelfDetailsDBIF;
import exceptions.NotFoundException;
import model.Item;
import model.Shelf;

public class ShelfDetailsDB implements ShelfDetailsDBIF {
    
    // PreparedStatements for the ItemDB class
    private static final String FIND_BY_SHELF = "select * from ShelfDetails where ShelfId = ?";
    private static final String FIND_BY_ITEM_ID = "select * from ShelfDetails where ItemId = ?";
    private static final String CREATE_SHELFDETAILS = "insert into ShelfDetails values(?, ?, ?)";
    private static final String DELETE_SHELFDETAILS = "update ShelfDetails set Disabled = ? where ItemId = ?";
    private static final String DELETE_ALL_SHELFDETAILS = "delete from ShelfDetails where ShelfId = ?";
    private static final String REMOVE_ITEM = "update ShelfDetails set Disabled = 1 where ItemId = ?";

    private PreparedStatement findByShelf;
    private PreparedStatement findByItemid;
    private PreparedStatement createShelfDetails;
    private PreparedStatement deleteShelfDetails;
    private PreparedStatement deleteAllShelfDetails;
    private PreparedStatement removeItem;

    private ItemController itemCtrl = new ItemController();
    private ShelfDBIF shelfDBIF = new ShelfDB();

    /**
     * Constructor for the ShelfDetailsDB class
     * @throws SQLException
     */
    public ShelfDetailsDB() throws SQLException {
        findByShelf = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_SHELF);
        findByItemid = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ITEM_ID);
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
    public Item findByItemId(int id) throws SQLException, NotFoundException {
        Item item = null;
        ResultSet rs;
        findByItemid.setInt(1, id);
        rs = findByItemid.executeQuery();
        while (rs.next()) {
            item = buildObject(rs);
        }

        if (item == null) { throw new NotFoundException("Item", id); }

        return item;
    }

    @Override
    public Shelf findByShelfId(int id) throws SQLException, NotFoundException {
        Shelf shelf = null;
        ResultSet rs;
        findByItemid.setInt(1, id);
        rs = findByItemid.executeQuery();
        while (rs.next()) {
            shelf = builShelfdObject(rs);
        }

        if (shelf == null) { throw new NotFoundException("Shelf", id); }

        return shelf;
    }

    @Override
    public void createShelfDetails(Shelf shelf, Item item) throws SQLException {
        createShelfDetails.setInt(1, shelf.getId());
        createShelfDetails.setInt(2, item.getId());
        createShelfDetails.setBoolean(3, false);
        createShelfDetails.execute();
    }

    @Override
    public void deleteShelfDetails(Shelf shelf, Item item) throws SQLException {
        deleteShelfDetails.setInt(1, shelf.getId());
        deleteShelfDetails.setInt(2, item.getId());
        deleteAllShelfDetails.setBoolean(3, true);
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

    private Shelf builShelfdObject(ResultSet rs) throws SQLException, NotFoundException {
        Shelf shelf = shelfDBIF.findById(rs.getInt("ShelfId"));
        return shelf;
    }

    private List<Item> buildObjects(ResultSet rs) throws SQLException, NotFoundException {
        List<Item> items = new ArrayList<>();
        while(rs.next()) {
            items.add(buildObject(rs));
        }
        return items;
    }

}
