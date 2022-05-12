package view.tableModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Supplier;

public class SupplierTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2367962412967993282L;
	
	public enum Column {
		ID("ID"),
		NAME("Name"),
		EMAIL("Email"),
		PHONE("Phone"),
		ZIP("ZIP"),
		ADDRESS("Address");
		
		private String value;
		
		Column(final String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return this.getValue();
		}
	}
	
	private List<Supplier> suppliers;
	private List<Column> columns;
	
	/**
	 * Instantiates a new supplier table model.
	 * 
	 * @param suppliers the suppliers
	 * @param columns the columns to be displayed
	 */
	public SupplierTableModel(List<Supplier> suppliers, List<Column> columns) {
		this.columns = new ArrayList<Column>(columns);
		// Prevent possible external mutation
		this.suppliers = new ArrayList<>(suppliers);
	}
	
	/**
	 * Instantiates a new supplier table model.
	 * Note: This constructor shows all columns
	 * 
	 * @param suppliers the suppliers
	 */
	public SupplierTableModel(List<Supplier> suppliers) {
		this.columns = new ArrayList<Column>(Arrays.asList(Column.class.getEnumConstants()));
		// Prevent possible external mutation
		this.suppliers = new ArrayList<>(suppliers);
	}
	
	@Override
	public int getRowCount() {
		return suppliers.size();
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
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			default: return String.class;
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Supplier supplier = suppliers.get(rowIndex);
		Column column = this.columns.get(columnIndex);
		switch (column) {
			case ID: return supplier.getId();
			case NAME: return supplier.getName();
			case EMAIL: return supplier.getEmail();
			case PHONE: return supplier.getPhone();
			case ZIP: return supplier.getZipCode().getZipCode();
			case ADDRESS: return supplier.getAddress();
			default: return "Error retreiving column name";
		}
	}
	
	// Make cells uneditable
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	public Supplier getObj(int row) {
		return suppliers.get(row);
	}
	
	public void remove(int row) {
		this.suppliers.remove(row);
		this.fireTableRowsDeleted(row, row);
	}
	
	public void add(Supplier supplier) {
		this.suppliers.add(supplier);
		this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() - 1);
	}
}
