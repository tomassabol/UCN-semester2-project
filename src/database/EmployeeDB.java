package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import database.interfaces.EmployeeDBIF;
import database.interfaces.EmployeeTypeDBIF;
import exceptions.NotFoundException;
import model.Employee;
import model.Employee.EmployeeType;

public class EmployeeDB implements EmployeeDBIF {
    
    private static final String FINDA_ALL = "select * from Employees";
    private static final String FIND_BY_ID= "select * from Employees where id = ?";
    private static final String CREATE_EMPLOYEE= "insert into Employees values(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_EMPLOYEE= "update Employees set Name = ?,Email = ?,Phone = ?,ZIP=?,Address=?,EmployeeTypeId=?,Password=?,CPR=?,DepartmentId=?";
    private static final String DELETE_EMPLOYEE= "delete from Employees where id = ?";

    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement createEmployee;
    private PreparedStatement updateEmployee;
    private PreparedStatement deleteEmployee;

    EmployeeTypeDBIF employeeTypeDBIF = new EmployeeTypeDB();

    @Override
    public List<Employee> findAll()throws SQLException,NotFoundException{
        return null;
    }
    
    @Override
    public Employee findById(int id)throws SQLException,NotFoundException{
        return null;
    }

    @Override
    public void createEmployee(Employee employee)throws SQLException{

    }

    @Override
    public void updateEmployee(Employee employee)throws SQLException{

    }

    public void deleteEmployee(Employee employee)throws SQLException{

    }

    private Employee buildObject(ResultSet rs)throws SQLException,NotFoundException{
        EmplpoyeeType employeeType
        Employee employee = new Employee(rs.getString("Name"), rs.getString("Email"), rs.getString("ZipCode"), rs.getString("Address"), employeeType,rs.getString("Password"), rs.getString("CPR"), department)
        return null;

        //String name, String email, String phone, City zipCode, String address, EmployeeType employeeType, String password, String CPR, Department department
    }

    private List<Employee> buildObjects(ResultSet rs)throws SQLException,NotFoundException{
    //name email,zip,address,employeetype,password,cpr,department
    
    return null;
    }

}
