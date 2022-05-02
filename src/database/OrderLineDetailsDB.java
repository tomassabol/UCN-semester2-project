package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.ItemController;
import database.interfaces.OrderLineDetailsDBIF;
import exceptions.NotFoundException;
import model.Item;
import model.OrderLine;

public class OrderLineDetailsDB implements OrderLineDetailsDBIF {

    //Prepared statments
    private static final String FIND_ALL = "select * from OrderLineDetails where OrderLineId = ?";
    private static final String CREATE_ORDER_LINE_DETAILS = "insert into OrderLineDetails values (?, ?)";
    private static final String DELETE_ORDER_LINE_DETAILS = "delete from OrderLineDetails where OrderLineId = ?, ItemId = ?";
    private static final String DELETE_ALL_ORDER_LINE_DETAILS = "delete from OrderLineDetails where OrderLineId = ?";

    private PreparedStatement findAll;
    private PreparedStatement createOrderLineDetails;
    private PreparedStatement deleteOrderLineDetails;
    private PreparedStatement deleteAllOrderLineDetails;

    ItemController itemCtrl = new ItemController();

    public OrderLineDetailsDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        createOrderLineDetails = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDER_LINE_DETAILS, Statement.RETURN_GENERATED_KEYS);
        deleteOrderLineDetails = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ORDER_LINE_DETAILS);
        deleteAllOrderLineDetails = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ALL_ORDER_LINE_DETAILS);
    }

    @Override
    public List<Item> findByOrderLine(int orderLineId) throws SQLException, NotFoundException {
        ResultSet rs;
        findAll.setInt(1, orderLineId);
        rs = findAll.executeQuery();
        List<Item> items = buildObjects(rs);
        return items;
    }

    @Override
    public void createOrderLineDetails(OrderLine orderLine, Item item) throws SQLException {
        createOrderLineDetails.setInt(1, orderLine.getId());
        createOrderLineDetails.setInt(2, item.getId());
        createOrderLineDetails.execute();
    }

    @Override
    public void deleteOrderLineDetails(OrderLine orderLine, Item item) throws SQLException {
        deleteOrderLineDetails.setInt(1, orderLine.getId());
        deleteOrderLineDetails.executeUpdate();
    }

    @Override
    public void deleteAllOrderLineDetails(OrderLine orderLine) throws SQLException {
        deleteAllOrderLineDetails.setInt(1, orderLine.getId());
        deleteAllOrderLineDetails.executeUpdate();
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
