package view.tableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controller.DepartmentController;
import controller.ShelfController;
import exceptions.NotFoundException;
import model.Department;
import model.Product;
import view.Messages;

public class ProductTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -2367962412967993282L;
	
	public enum Column {
		ID("ID"),
		NAME("Name"),
		DESCRIPTION("Description"),
		PRODUCT_TYPE("Product Type"),
		PRICE("Price"),
		DISCOUNT("Discount"),
		ACTIVE("Active"),
		DEP1QUANTITY("Dep1 quantity"),
		DEP2QUANTITY("Dep2 quantity");

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
    private ShelfController shelfCtrl;
    private DepartmentController depCtrl;
    private Department dep1;
    private Department dep2;

    /**
     * Instantiates a new product table model.
     * 
     *
     * @param products the products
     * @param columns the columns to be displayed
     * @throws SQLException
     * @throws NotFoundException 
     */
    public ProductTableModel(List<Product> products, List<Column> columns) throws SQLException, NotFoundException {
        this.columns = new ArrayList<Column>(columns);
        // Prevent possible external mutation
        this.products = new ArrayList<>(products);
        shelfCtrl = new ShelfController();
        depCtrl = new DepartmentController();
        dep1 = depCtrl.findById(1);
        dep2 = depCtrl.findById(2);
        
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
		case DEP1QUANTITY: try {
				return shelfCtrl.productQuantityPerDepartment(dep1, product);
			} catch (SQLException e) {
				Messages.error(null, "Error connecting to database");
			} catch (NotFoundException e) {
				Messages.error(null, "Internal system error");
			}
		case DEP2QUANTITY: try {
				return shelfCtrl.productQuantityPerDepartment(dep2, product);
			} catch (SQLException e) {
				Messages.error(null, "Error connecting to database");
			} catch (NotFoundException e) {
				Messages.error(null, "Internal system error");
			}
		
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