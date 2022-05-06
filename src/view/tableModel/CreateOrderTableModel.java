package view.tableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.AuthenticationController;
import controller.OrderController;
import controller.OrderLineController;
import exceptions.NotEnoughInStockException;
import exceptions.NotFoundException;
import model.Order;
import model.OrderLine;
import model.Product;

/**
 * @author ttomy
 * @version 22 Apr 2022
 */
public class CreateOrderTableModel extends AbstractTableModel{

	//private static final String[] columnNames = {"ID", "Product", "Quantity"}; //TODO: Add price per orderLine
	
	public enum Column {
		ID("ID"),
		PRODUCT("Product"),
		QUANTITY("Quantity");
		

		private String value;
		
		Column(final String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			//return name().replace('_', ' ').substring(0,1).toUpperCase() + name().substring(1).toLowerCase();
			return this.getValue();
		}
	}
	
	private List<Column> columns;
	private List<OrderLine> orderLines;
	private OrderController orderCtrl;
	private OrderLineController orderLineCtrl;
	private Order order;
	AuthenticationController auth;
	
	public CreateOrderTableModel(AuthenticationController authentication, Order order, List<Column> columns) throws SQLException, NotFoundException {
		this.columns = new ArrayList<Column>(columns);
		orderCtrl = new OrderController();
		auth = authentication;
		this.order = order;
		orderLineCtrl = new OrderLineController();
		this.orderLines = new ArrayList<OrderLine>(order.getOrderLines());
	}
	
	
	@Override
	public int getRowCount() {
		return orderLines.size();
	}

	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public String getColumnName(int column) {
		return this.columns.get(column).toString();
	}
	
	
	 @Override
	 public Object getValueAt(int rowIndex, int columnIndex) {
		OrderLine orderLine = orderLines.get(rowIndex);
		Column column = this.columns.get(columnIndex);
		switch(column) {
			case ID: return orderLine.getId();
			case PRODUCT: return orderLine.getProduct().getName();
			case QUANTITY: return orderLine.getQuantity();
			default: return "Error retrieving column name"; 
		}		
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
    	this.orderLines.add(orderLine);
    	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() -1);
    }
    
}
