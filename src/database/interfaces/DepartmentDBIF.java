package database.interfaces;

import java.sql.SQLException;
import java.util.*;

import exceptions.NotFoundException;
import model.Department;

public interface DepartmentDBIF {
    public List<Department> findAll() throws SQLException, NotFoundException;
    public Department findByName(String name) throws SQLException, NotFoundException;
    public Department findById(int id) throws SQLException, NotFoundException;
    public void createDepartment(Department department) throws SQLException;
    public void updateDepartment(Department department) throws SQLException;
    public void deleteDepartment(Department department) throws SQLException;
}
