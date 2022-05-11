package view.tableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.AuthenticationController;
import controller.OrderController;
import exceptions.NotEnoughInStockException;
import exceptions.NotFoundException;
import model.Customer;
import model.Order;

public class OrdersTableModel extends AbstractTableModel{
	
	public enum Column {
		ID("Order ID"),
		CUSTOMER("Customer"),
		DATE("Date");
		//EMPLOYEE("Employee") //Not sure if needed

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
	private List<Order> orders;
	private OrderController orderCtrl;
	private AuthenticationController auth;
	
	public OrdersTableModel(AuthenticationController auth, List<Column> columns, Customer customer) throws SQLException, NotFoundException {
		
		this.auth = auth;
		orderCtrl = new OrderController();
		this.columns = new ArrayList<Column>(columns);
		if(customer == null) {
			orders = new ArrayList<Order>(orderCtrl.findAll());						
		}else {
			orders = new ArrayList<Order>(orderCtrl.findByCustomer(customer));
		}
	}
	
	
	@Override
	public int getRowCount() {
		return orders.size();
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
		Order order = orders.get(rowIndex);
		Column column = this.columns.get(columnIndex);
		switch(column) {
			case ID: return order.getId();
			case CUSTOMER: return order.getCustomer().getName();
			case DATE: return order.getOrderDate();
			//case EMPLOYEE: return order.getEmployee();
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
    
    
    public Order getObj(int row) {
    	return orders.get(row);
    }
    
    /**
     * Clears the table
     */
    public void clear() {
    	// update this model's orderLine copies
    	for(Order order : orders) {
    		try {
				orderCtrl.deleteOrder(order);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    		
    	}
    	orders.clear();
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
    	for(Order order : orders) {
    		if(order.getId() == row && order != null) {
    			// update this model's itemLine copies
            	orders.remove(order);
            	orderCtrl.deleteOrder(order);
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
    public void add() {
    	
    	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() -1);
    }

}
