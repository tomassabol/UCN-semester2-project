package view.tableModel;

 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;

 import javax.swing.table.AbstractTableModel;

import model.Department;

 public class DepartmentTableModel extends AbstractTableModel {

 	private static final long serialVersionUID = -2367962412967993282L;

 	public enum Column {
 		ID("ID"),
 		NAME("Name"),
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
 			//return name().replace('_', ' ').substring(0,1).toUpperCase() + name().substring(1).toLowerCase();
 			return this.getValue();
 		}
 	}

     private List<Department> departments;
     private List<Column> columns;

     /**
      * Instantiates a new product table model.
      * 
      *
      * @param products the products
      * @param columns the columns to be displayed
      * @throws SQLException
      */
     public DepartmentTableModel(List<Department> departments, List<Column> columns) {
         this.columns = new ArrayList<Column>(columns);
         // Prevent possible external mutation
         this.departments = new ArrayList<>(departments);
     }

     /**
      * Instantiates a new product table model.
      * Note: This constructor shows all columns
      *
      * @param products the products
      */
     public DepartmentTableModel(List<Department> departments) {
     	this.columns = new ArrayList<Column>(Arrays.asList(Column.class.getEnumConstants()));
         // Prevent possible external mutation
         this.departments = new ArrayList<>(departments);
     }

     @Override
     public int getRowCount() {
         return departments.size();
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
     	Department department= departments.get(rowIndex);
     	Column column = this.columns.get(columnIndex);
     	switch (column) {
            case ID: return department.getId();
            case NAME: return department.getName();
            case ZIP: return department.getZipCode().getZipCode();
            case ADDRESS: return department.getAddress();
            default: return "Error retrieving column name";
     	}
     }

     // Make cells uneditable
     @Override
     public boolean isCellEditable(int row, int column) {       
         return false;
     }

     public Department getObj(int row) {
     	return departments.get(row);
     }

     public void remove(int row) {
     	this.departments.remove(row);
     	this.fireTableRowsDeleted(row, row);
     }

     public void add(Department department) {
     	this.departments.add(department);
     	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() -1);
     }

 }