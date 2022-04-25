/**
 * 
 */
package view.tableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.AuthenticationController;
import controller.OrderController;
import controller.OrderDetailsController;
import controller.OrderLineController;
import exceptions.NotEnoughInStockException;
import exceptions.NotFoundException;
import model.Customer;
import model.Order;
import model.OrderLine;
import model.Product;

/**
 * @author ttomy
 * @version 22 Apr 2022
 */
public class CreateOrderTableModel extends AbstractTableModel{

	private static final String[] columnNames = {"ID", "Product", "Quantity"}; //TODO: Add price per orderLine
	
	private List<OrderLine> orderLines;
	private OrderController orderCtrl;
	private OrderLineController orderLineCtrl;
	private AuthenticationController auth;
	private Order order;
	private OrderDetailsController orderDetailsCtrl;
	
	public CreateOrderTableModel(AuthenticationController authentication, Customer customer, Order order) throws SQLException, NotFoundException {
		orderCtrl = new OrderController();
		auth = authentication;
		this.order = order;
		orderLineCtrl = new OrderLineController();
		orderLines = orderDetailsCtrl.findByOrderId(order.getId());
	}
	
	@Override
	public int getRowCount() {
		return orderLines.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		OrderLine orderLine = orderLines.get(rowIndex);
		switch(columnIndex) {
			case 0: value = orderLine.getId();
			case 1: value = orderLine.getProduct();
			case 2: value = orderLine.getQuantity();
			default: value = null;
		}
		return value;
	}
	
	/**
	 *  Make cells uneditable
	 */
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    /**
     * Clears the table
     */
    public void clear() {
    	// update this model's orderLine copies
    	orderLines.clear();
    	// Update the rendered table
    	this.fireTableDataChanged();
    }

    /**
     * Removes a row from the table model and the database
     *
     * @param row the row
     * @throws SQLException 
     */
    public void remove(int row) throws SQLException {
    	OrderLine orderLine = orderLines.get(row);
    	if (orderLine != null) {
        	// update this model's itemLine copies
        	orderLines.remove(orderLine);
        	orderLineCtrl.deleteOrderLine(orderLine);
        	// Update the rendered table
        	this.fireTableRowsDeleted(row, row);
    	}
    }
    
    /**
     * Adds the orderLine to the table
     * @param itemLine the orderLine
     * @throws NotEnoughInStockException 
     * @throws NotFoundException 
     * @throws SQLException 
     */
    public void add(Product product, int quantity) throws SQLException, NotFoundException, NotEnoughInStockException {
    	orderCtrl.addProduct(order, product, quantity);
    	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() - 1);
    }
    
}
