package view.tableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.AuthenticationController;
import controller.DepartmentController;
import controller.OrderDetailsController;
import controller.OrderLineDetailsController;
import controller.ShelfDetailsController;
import exceptions.NotFoundException;
import model.Item;
import model.Order;
import model.OrderLine;
import view.Messages;
import view.tableModel.CreateOrderTableModel.Column;

public class OrderDetailTableModel extends AbstractTableModel{
	
	public enum Column {
		ID("Item ID"),
		PRODUCT("Product"),
		DEPARTMENT("Department"),
		LOCATION("Location");
		

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
	
	private AuthenticationController auth;
	private List<Column> columns;
	private List<OrderLine> orderLines;
	private List<Item> items;
	private OrderDetailsController orderDetCtrl;
	private OrderLineDetailsController orderLineDetCtrl;
	private DepartmentController depCtrl;
	private ShelfDetailsController shelfDetCtrl;
	
	public OrderDetailTableModel(AuthenticationController auth, List<Column> columns, Order order) throws SQLException, NotFoundException {
		this.auth = auth;
		this.columns = columns;
		orderDetCtrl = new OrderDetailsController();
		this.orderLines = orderDetCtrl.findByOrderId(order.getId());
		orderLineDetCtrl = new OrderLineDetailsController();
		depCtrl = new DepartmentController();
		shelfDetCtrl = new ShelfDetailsController();
		this.items = getItemsPerOrder();
	}
	
	public List<Item> getItemsPerOrder() throws SQLException, NotFoundException {
		ArrayList<Item> items = new ArrayList<Item>();
		for(OrderLine orderLine : orderLines) {
			for(Item item : orderLineDetCtrl.findByOrderLine(orderLine.getId())) {
				items.add(item);
			}
		}
		return items;
	}
	
	@Override
	public int getRowCount() {
		return items.size();
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
		Item item = items.get(rowIndex);
		Column column = this.columns.get(columnIndex);
		switch(column) {
			case ID: return item.getId();
			case PRODUCT: return item.getProduct().getName();
			case DEPARTMENT: return auth.getLoggedInUser().getDepartment().getName();
			case LOCATION: {				
				try {
					return shelfDetCtrl.findByItemId(item.getId()).getName();
				} catch (SQLException e) {
					Messages.error(null, "Error connecting to database");
				} catch (NotFoundException e) {
					Messages.error(null, "We have no idea what this is"); // We actually do cause we created this exception
				}
			}
			default: return "Error retrieving column name"; 
		}
	}
}
