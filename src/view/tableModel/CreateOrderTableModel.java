/**
 * 
 */
package view.tableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.OrderController;
import controller.OrderDetailsController;
import controller.OrderLineController;
import exceptions.NotFoundException;
import model.OrderLine;

/**
 * @author ttomy
 * @version 22 Apr 2022
 */
public class CreateOrderTableModel extends AbstractTableModel{

	private static final String[] columnNames = {"ID", "Product", "Quantity"}; //TODO: Add price per orderLine
	
	private List<OrderLine> orderLines;
	private OrderController orderCtrl;
	private OrderLineController orderLineCtrl;
	private OrderDetailsController orderDetailsCtrl;
	
	public CreateOrderTableModel(int id) throws SQLException, NotFoundException {
		orderCtrl = new OrderController();
		orderLineCtrl = new OrderLineController();
		orderLines = orderDetailsCtrl.findByOrderId(id);
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return orderLines.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
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
     *
     * @param itemLine the orderLine
     */
    public void add(OrderLine orderLine) {
    	orderLines.add(orderLine);
    	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() - 1);
    }
    
}
