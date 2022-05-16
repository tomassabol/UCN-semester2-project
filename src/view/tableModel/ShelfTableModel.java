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
 		PRODUCTID("ProductId"),
        ITEMQUANTITY("Item quantity"),
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

     private List<Shelf> shelves;
     private List<Column> columns;

   
     public ShelfTableModel(List<Shelf> shelves, List<Column> columns) {
         this.columns = new ArrayList<Column>(columns);
         // Prevent possible external mutation
         this.shelves = new ArrayList<>(shelves);
     }

  
     public ShelfTableModel(List<Shelf> shelves) {
     	this.columns = new ArrayList<Column>(Arrays.asList(Column.class.getEnumConstants()));
         // Prevent possible external mutation
         this.shelves = new ArrayList<>(shelves);
     }

     @Override
     public int getRowCount() {
         return shelves.size();
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
     	Shelf shelf= shelves.get(rowIndex);
     	Column column = this.columns.get(columnIndex);
     	switch (column) {
            case ID: return shelf.getId();
            case NAME: return shelf.getName();
            case PRODUCTID: return shelf.getProduct().getId();
            case ITEMQUANTITY: return shelf.getProductQuantity();
            case DEPARTMENT: return shelf.getDepartment().getId();

            default: return "Error retrieving column name";
     	}
     }

     // Make cells uneditable
     @Override
     public boolean isCellEditable(int row, int column) {       
         return false;
     }

     public Shelf getObj(int row) {
     	return shelves.get(row);
     }

     public void remove(int row) {
     	this.shelves.remove(row);
     	this.fireTableRowsDeleted(row, row);
     }

     public void add(Shelf shelf) {
     	this.shelves.add(shelf);
     	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() -1);
     }

 }