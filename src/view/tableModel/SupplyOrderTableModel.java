package view.tableModel;

 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;


import javax.swing.table.AbstractTableModel;

import model.SupplyOrder;



 public class SupplyOrderTableModel extends AbstractTableModel {

 	private static final long serialVersionUID = -2367962412967993282L;

 	public enum Column {
 		ID("ID"),
        PRODUCTID("ProductId"),
        QUANTITY ("Quantity"),
        ORDERDATE("OrderDate"),
        SUPPLIERID("Supplier"),
        DELIVERED ("Delivered");

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

     private List<SupplyOrder> supplyOrders;
     private List<Column> columns;

     /**
      * Instantiates a new SupplyOrder table model.
      * 
      *
      * @param supplyOrder the SupplyOrder
      * @param columns the columns to be displayed
      * @throws SQLException
      */
     public SupplyOrderTableModel(List<SupplyOrder> supplyOrder, List<Column> columns) {
         this.columns = new ArrayList<Column>(columns);
         // Prevent possible external mutation
         this.supplyOrders = new ArrayList<>(supplyOrder);
     }

     /**
      * Instantiates a new SupplyOrder table model.
      * Note: This constructor shows all columns
      *
      * @param supplyOrder the SupplyOrder
      */
     public SupplyOrderTableModel(List<SupplyOrder> supplyOrder) {
     	this.columns = new ArrayList<Column>(Arrays.asList(Column.class.getEnumConstants()));
         // Prevent possible external mutation
         this.supplyOrders = new ArrayList<>(supplyOrder);
     }

     @Override
     public int getRowCount() {
         return supplyOrders.size();
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
        case DELIVERED: return Boolean.class;
            default: return String.class;
        }
     }


     @Override
     public Object getValueAt(int rowIndex, int columnIndex) {
        SupplyOrder supplyOrder = supplyOrders.get(rowIndex);
     	Column column = this.columns.get(columnIndex);
     	switch (column) {
            case ID: return supplyOrder.getId();
            case PRODUCTID: return supplyOrder.getProduct().getName();
            case QUANTITY :return supplyOrder.getQuantity();
            case ORDERDATE:return supplyOrder.getQuantity();
            case SUPPLIERID: return supplyOrder.getSupplier().getName();
            case DELIVERED: return supplyOrder.getIsDelivered();
            
            //enabled
            default: return "Error retrieving column name";
     	}
     }

     // Make cells uneditable
     @Override
     public boolean isCellEditable(int row, int column) {       
         return false;
     }

     public SupplyOrder getObj(int row) {
     	return supplyOrders.get(row);
     }

     public void remove(int row) {
     	this.supplyOrders.remove(row);
     	this.fireTableRowsDeleted(row, row);
     }

     public void add(SupplyOrder supplyOrder) {
     	this.supplyOrders.add(supplyOrder);
     	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() -1);
     }

 }