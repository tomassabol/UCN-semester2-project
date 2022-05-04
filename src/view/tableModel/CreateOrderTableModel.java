package view.tableModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

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
	
	private Set<OrderLine> orderLines;
	private OrderController orderCtrl;
	private OrderLineController orderLineCtrl;
	private Order order;
	AuthenticationController auth;
	
	public CreateOrderTableModel(AuthenticationController authentication, Customer customer, Order order) throws SQLException, NotFoundException {
		orderCtrl = new OrderController();
		auth = authentication;
		this.order = order;
		orderLineCtrl = new OrderLineController();
		orderLines = order.getOrderLines();
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
		for(OrderLine orderLine : orderLines) {
			if(orderLine.getId() == rowIndex) {
				switch(columnIndex) {
				case 0: value = orderLine.getId();
				case 1: value = orderLine.getProduct();
				case 2: value = orderLine.getQuantity();
				default: value = null;
				}
			}
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
    	for(OrderLine orderLine : orderLines) {
    		try {
				orderLineCtrl.deleteOrderLine(orderLine);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    		
    	}
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
    	for(OrderLine orderLine : orderLines) {
    		if(orderLine.getId() == row && orderLine != null) {
    			// update this model's itemLine copies
            	orderLines.remove(orderLine);
            	orderLineCtrl.deleteOrderLine(orderLine);
            	// Update the rendered table
            	this.fireTableRowsDeleted(row, row);
    		}
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
    	OrderLine orderLine = orderCtrl.addProduct(order, product, quantity);
    	orderLines.add(orderLine);
    	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() - 1);
    }
    
}
