package view.tableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.AuthenticationController;
import controller.ItemController;
import controller.OrderController;
import controller.OrderDetailsController;
import controller.OrderLineController;
import controller.OrderLineDetailsController;
import exceptions.NotEnoughInStockException;
import exceptions.NotFoundException;
import model.Order;
import model.OrderLine;
import model.Product;
import view.Messages;

/**
 * @author ttomy
 * @version 22 Apr 2022
 */
public class CreateOrderTableModel extends AbstractTableModel{

	//private static final String[] columnNames = {"ID", "Product", "Quantity"}; //TODO: Add price per orderLine
	
	public enum Column {
		ID("OrderLine ID"),
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
	private OrderDetailsController orderDetCtrl;
	private OrderLineController orderLineCtrl;
	private OrderLineDetailsController orderLineDetCtrl;
	private Order order;
	AuthenticationController auth;
	private ItemController itemCtrl;
	
	public CreateOrderTableModel(AuthenticationController authentication, Order order, List<Column> columns) throws SQLException, NotFoundException {
		this.columns = new ArrayList<Column>(columns);
		orderCtrl = new OrderController();
		orderDetCtrl = new OrderDetailsController();
		orderLineCtrl = new OrderLineController();
		orderLineDetCtrl = new OrderLineDetailsController();
		auth = authentication;
		this.order = order;
		this.orderLines = new ArrayList<OrderLine>(orderDetCtrl.findByOrderId(this.order.getId()));
		itemCtrl = new ItemController();
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
	
	public List<OrderLine> getOrderLines() {
		return orderLines;
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
     * @throws SQLException 
     * @throws NotFoundException 
     */
    public void clear(Order order) throws SQLException, NotFoundException {
    	// update this model's orderLine copies
    	for(OrderLine orderLine : orderLines) {
			order.removeOrderLine(orderLine);
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
            	order.removeOrderLine(orderLine);
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
    	if(order.isProductPresent(product)) {
			quantity += order.getOrderLinebyProduct(product).getQuantity();
			// basic stock check 
			// TODO: update once the stock UC is implemented
			if(itemCtrl.findAllPerProduct(product).size() < quantity) {
				int currentStock = itemCtrl.findAllPerProduct(product).size();
				throw new NotEnoughInStockException(quantity, currentStock);
			}
			OrderLine orderLine = order.getOrderLinebyProduct(product);
			orderLine.setQuantity(quantity);
			this.fireTableRowsUpdated(this.getRowCount() - 1, this.getRowCount() -1);
			
		}else {
			OrderLine newOrderLine = orderCtrl.addProduct(order, product, quantity);
			this.orderLines.add(newOrderLine);
			this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() -1);    					
		}
    }
    
    /**
     * Returns an orderLine from the chosen row
     * @param row - the row the orderLine is in
     * @return the orderLine
     */
    public OrderLine getOrderLine(int row) {
    	return orderLines.get(row);
    }
    
}
