package view.tableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Product;

/**
 * @author Daniels Kanepe
 *
 */
public class ProductTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -2367962412967993282L;
	
	public enum Column {
		ID("ID"),
		NAME("Name"),
		DESCRIPTION("Description"),
		PRODUCT_TYPE("Product Type"),
		PRICE("Price"),
		DISCOUNT("Discount"),
		ACTIVE("Active");

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

    private List<Product> products;
    private List<Column> columns;

    /**
     * Instantiates a new product table model.
     * 
     *
     * @param products the products
     * @param columns the columns to be displayed
     * @throws SQLException
     */
    public ProductTableModel(List<Product> products, List<Column> columns) {
        this.columns = new ArrayList<Column>(columns);
        // Prevent possible external mutation
        this.products = new ArrayList<>(products);
    }
    
    /**
     * Instantiates a new product table model.
     * Note: This constructor shows all columns
     *
     * @param products the products
     */
    public ProductTableModel(List<Product> products) {
    	this.columns = new ArrayList<Column>(Arrays.asList(Column.class.getEnumConstants()));
        // Prevent possible external mutation
        this.products = new ArrayList<>(products);
    }

    @Override
    public int getRowCount() {
        return products.size();
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
    	Product product = products.get(rowIndex);
    	Column column = this.columns.get(columnIndex);
    	switch (column) {
        case ID: return product.getId();
		case NAME: return product.getName();
		case DESCRIPTION: return product.getDescription();
		case PRODUCT_TYPE: return product.getProductType().toString();
		case PRICE: return product.getPrice() + "EUR";
		case DISCOUNT: return product.getDiscount();
		case ACTIVE: return product.isActive();
		default: return "Error retrieving column name";
    	}
    }
    
    // Make cells uneditable
    @Override
    public boolean isCellEditable(int row, int column) {       
        return false;
    }
    
    public Product getObj(int row) {
    	return products.get(row);
    }
    
    public void remove(int row) {
    	this.products.remove(row);
    	this.fireTableRowsDeleted(row, row);
    }
    
    public void add(Product product) {
    	this.products.add(product);
    	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() -1);
    }

}