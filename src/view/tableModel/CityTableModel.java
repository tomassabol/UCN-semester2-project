package view.tableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.City;

public class CityTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -2367962412967993282L;
	
	public enum Column {
		ID("ID"),
		ZIPCODE("Zipcode"),
		NAME("Name");
		

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

    private List<City> cities;
    private List<Column> columns;

    /**
     * Instantiates a new product table model.
     * 
     *
     * @param products the products
     * @param columns the columns to be displayed
     * @throws SQLException
     */
    public CityTableModel(List<City> cities, List<Column> columns) {
        this.columns = new ArrayList<Column>(columns);
        // Prevent possible external mutation
        this.cities = new ArrayList<>(cities);
    }
    
    /**
     * Instantiates a new product table model.
     * Note: This constructor shows all columns
     *
     * @param products the products
     */
    public CityTableModel(List<City> products) {
    	this.columns = new ArrayList<Column>(Arrays.asList(Column.class.getEnumConstants()));
        // Prevent possible external mutation
        this.cities = new ArrayList<>(products);
    }

    @Override
    public int getRowCount() {
        return cities.size();
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
    	City city = cities.get(rowIndex);
    	Column column = this.columns.get(columnIndex);
    	switch (column) {
        case ID: return city.getId();
        case ZIPCODE: return city.getZipCode();
		case NAME: return city.getName();
		default: return "Error retrieving column name";
    	}
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    public City getObj(int row) {
    	return cities.get(row);
    }
    
    public void remove(int row) {
    	this.cities.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }
    
    public void add(City city) {
    	this.cities.add(city);
    	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() -1);
    }

}