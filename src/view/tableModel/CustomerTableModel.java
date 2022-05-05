package view.tableModel;

import java.lang.annotation.Retention;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Customer;

public class CustomerTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -2367962412967993283L;
	
	public enum Column {
		ID("ID"),
		NAME("Name"),
		EMAIL("Email"),
		PHONE("Phone"),
        ZIP("Zip"),
        ADDRESS("Address"),
        CUSTOMERTYPE("CustomerType"),
        ACTIVE("Active");


		private String value;
		
		Column(final String value) {
			this.value = value;
		}
		//vro
		public String getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			//return name().replace('_', ' ').substring(0,1).toUpperCase() + name().substring(1).toLowerCase();
			return this.getValue();
		}
	}

    private List<Customer> customers;
    private List<Column> columns;

    /**
     * Instantiates a new product table model.
     * 
     *
     * @param customer the products
     * @param columns the columns to be displayed
     * @throws SQLException
     */
    public CustomerTableModel(List<Customer> customer, List<Column> columns) {
        this.columns = new ArrayList<Column>(columns);
        // Prevent possible external mutation
        this.customers = new ArrayList<>(customer);
    }
    
    /**
     * Instantiates a new product table model.
     * Note: This constructor shows all columns
     *
     * @param customer the products
     */
    public CustomerTableModel(List<Customer> customer) {
    	this.columns = new ArrayList<Column>(Arrays.asList(Column.class.getEnumConstants()));
        // Prevent possible external mutation
        this.customers = new ArrayList<>(customer);
    }

    @Override
    public int getRowCount() {
        return customers.size();
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
    	Column column = this.columns.get(columnIndex);
        switch (column) {
        case ACTIVE: return Boolean.class;
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Customer customer = customers.get(rowIndex);
    	Column column = this.columns.get(columnIndex);
    	switch (column) {
        case ID: return customer.getId();
		case NAME: return customer.getName();
        case EMAIL: return customer.getEmail();
        case PHONE: return customer.getPhone();
        case ZIP: return customer.getZipCode().getZipCode();
        case ADDRESS: return customer.getAddress();
        case CUSTOMERTYPE: return customer.getCustomerType().getId();
		default: return "Error retrieving column name";
    	}
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    public Customer getObj(int row) {
    	return customers.get(row);
    }
    
    public void remove(int row) {
    	this.customers.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }
    
    public void add(Customer product) {
    	this.customers.add(product);
    	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() -1);
    }

}