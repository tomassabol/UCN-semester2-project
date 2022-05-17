package view.tableModel;

 import java.sql.SQLException;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.List;

 import javax.swing.table.AbstractTableModel;

import model.Employee;

 public class EmployeeTableModel extends AbstractTableModel {

 	private static final long serialVersionUID = -2367962412967993282L;

 	public enum Column {
 		ID("ID"),
 		NAME("Name"),
 		EMAIL("Email"),
 		PHONE("Phone"),
 		ZIP("ZIP"),
 		ADDRESS("Address"),
 		EMPLOYEE_TYPE("Employee type"),
 		PASSWORD("Password"),
 		CPR("CPR"),
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

     private List<Employee> employees;
     private List<Column> columns;

     /**
      * Instantiates a new employee table model.
      * 
      *
      * @param employees the employees
      * @param columns the columns to be displayed
      * @throws SQLException
      */
     public EmployeeTableModel(List<Employee> employees, List<Column> columns) {
         this.columns = new ArrayList<Column>(columns);
         // Prevent possible external mutation
         this.employees = new ArrayList<>(employees);
     }

     /**
      * Instantiates a new employee table model.
      * Note: This constructor shows all columns
      *
      * @param employees the employees
      */
     public EmployeeTableModel(List<Employee> employees) {
     	this.columns = new ArrayList<Column>(Arrays.asList(Column.class.getEnumConstants()));
         // Prevent possible external mutation
         this.employees = new ArrayList<>(employees);
     }

     @Override
     public int getRowCount() {
         return employees.size();
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
     	Employee employee = employees.get(rowIndex);
     	Column column = this.columns.get(columnIndex);
     	switch (column) {
            case ID: return employee.getId();
            case NAME: return employee.getName();
            case EMAIL: return employee.getEmail();
            case PHONE: return employee.getPhone();
            case ZIP: return employee.getZipCode().getZipCode();
            case ADDRESS: return employee.getAddress();
            case EMPLOYEE_TYPE: return employee.getEmployeeType();
            case PASSWORD: return employee.getPassword();
            case CPR: return employee.getCPR();
            case DEPARTMENT: return employee.getDepartment().getName();
            default: return "Error retrieving column name";
     	}
     }

     // Make cells uneditable
     @Override
     public boolean isCellEditable(int row, int column) {       
         return false;
     }

     public Employee getObj(int row) {
     	return employees.get(row);
     }

     public void remove(int row) {
     	this.employees.remove(row);
     	this.fireTableRowsDeleted(row, row);
     }

     public void add(Employee employee) {
     	this.employees.add(employee);
     	this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() -1);
     }

 }