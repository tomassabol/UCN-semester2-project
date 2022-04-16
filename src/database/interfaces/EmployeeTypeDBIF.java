package database.interfaces;
import java.sql.SQLException;
import java.util.List;

import exceptions.NotFoundException;
import model.Employee.EmployeeType;

public interface EmployeeTypeDBIF {
    public List<EmployeeType> findAll() throws SQLException, NotFoundException;
    public EmployeeType findById(int id) throws SQLException, NotFoundException;
}
