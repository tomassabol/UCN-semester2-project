package view.tableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Shelf;

public class ShelfTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -2367962412967993282L;
	
	public enum Column {
        ID("ID"),
		NAME("Name"),
		PRODUCT("Product"),
		QUANTITY("Product Quantity"),
        DEPARTMENT("Department");
		

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

    private List<Shelf> shelfs;
    private List<Column> columns;

    /**
     * Instantiates a new city table model.
     * 
     *
     * @param city the cities
     * @param columns the columns to be displayed
     * @throws SQLException
     */
    public ShelfTableModel(List<Shelf> shelfs, List<Column> columns) {
        this.columns = new ArrayList<Column>(columns);
        // Prevent possible external mutation
        this.shelfs = new ArrayList<>(shelfs);
    }
    
    /**
     * Instantiates a new city table model.
     * Note: This constructor shows all columns
     *
     * @param cities the cities
     */
    public ShelfTableModel(List<Shelf> shelfs) {
    	this.columns = new ArrayList<Column>(Arrays.asList(Column.class.getEnumConstants()));
        // Prevent possible external mutation
        this.shelfs = new ArrayList<>(shelfs);
    }

    @Override
    public int getRowCount() {
        return shelfs.size();
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
            default: return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Shelf shelf = shelfs.get(rowIndex);
    	Column column = this.columns.get(columnIndex);
    	switch (column) {
        case ID: return shelf.getId();
        case NAME: return shelf.getName();
        case PRODUCT: 
            if (shelf.getProduct() == null) {
                return 0; 
            } else {
                return shelf.getProduct().getName(); 
            }
		case QUANTITY: return shelf.getProductQuantity();
        case DEPARTMENT: return shelf.getDepartment().getName();
		default: return "Error retrieving column name";
    	}
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    public Shelf getObj(int row) {
    	return shelfs.get(row);
    }
    
    public void remove(int row) {
    	this.shelfs.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }
    
    public void add(Shelf shelf) {
    	this.shelfs.add(shelf);
    	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() -1);
    }

}