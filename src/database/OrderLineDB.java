package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.ProductController;
import database.interfaces.OrderLineDBIF;
import exceptions.NotFoundException;
import model.OrderLine;
import model.Product;


public class OrderLineDB implements OrderLineDBIF{
    //Prepared statments
    private static final String FIND_ALL = "select * from OrderLines where Enabled = 1";
    private static final String FIND_BY_ID = "select * from OrderLines where id = ?";
    private static final String CREATE_ORDER_LINE = "insert into OrderLines values (?, ?, ?)";
    private static final String UPDATE_ORDER_LINE = "update OrderLines set Quantity = ?";
    private static final String DELETE_ORDER_LINE = "update OrderLines set Enabled = 1 where Id = ?";

    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement createOrderLine;
    private PreparedStatement updateOrderLine;
    private PreparedStatement deleteOrderLine;

    /**
     * Consturctor for the orderLineDB class
     * @throws sqlException
     */
    ProductController productCtrl = new ProductController();

    public OrderLineDB() throws SQLException{
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
        createOrderLine = DBConnection.getInstance().getConnection().prepareStatement(CREATE_ORDER_LINE, Statement.RETURN_GENERATED_KEYS);
        updateOrderLine = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_ORDER_LINE);
        deleteOrderLine = DBConnection.getInstance().getConnection().prepareStatement(DELETE_ORDER_LINE);
    }
    
    @Override
    public List<OrderLine> findAll() throws SQLException,NotFoundException{
        ResultSet rs;
        rs = findAll.executeQuery();
        List<OrderLine> orderLines = buildObjects(rs);
        return orderLines;    
    }

    @Override
    public OrderLine findById(int id) throws SQLException,NotFoundException{
        OrderLine orderLine = null;
        ResultSet rs;
        findById.setInt(1,id);
        rs = findById.executeQuery();
        while(rs.next()){
            orderLine = buildObject(rs);
        }

        if( orderLine == null){throw new NotFoundException("OrderLine", id); }
        return orderLine;
    }

    @Override
    public void createOrderLine(OrderLine orderLine)  throws SQLException {
        createOrderLine.setInt(1,orderLine.getProduct().getId());
        createOrderLine.setInt(2,orderLine.getQuantity());
        createOrderLine.setBoolean(3, true);
        orderLine.setId(DBConnection.getInstance().executeSqlInsertWithIdentity(createOrderLine));
    }

    @Override
    public void updateOrderLine(OrderLine orderLine) throws SQLException {
        updateOrderLine.setInt(1, orderLine.getQuantity());
        updateOrderLine.executeUpdate();
    }

    @Override
    public void deleteOrderLine(OrderLine orderLine) throws SQLException {
        deleteOrderLine.setInt(1, orderLine.getId());
        deleteOrderLine.executeUpdate();
    }

    private OrderLine buildObject(ResultSet rs)throws SQLException, NotFoundException {
        Product product = productCtrl.findById(rs.getInt("ProductId")); 
        OrderLine orderLine = new OrderLine(product, rs.getInt("Quantity"));
        orderLine.setId(rs.getInt("Id"));
        return orderLine;
    }
    private List<OrderLine> buildObjects (ResultSet rs)throws SQLException, NotFoundException {
        List<OrderLine> orderLines = new ArrayList<>();
        while(rs.next()){
            orderLines.add(buildObject(rs));
        }
        return orderLines;
    }
}
