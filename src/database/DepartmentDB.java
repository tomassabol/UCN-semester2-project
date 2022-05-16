package database;

import java.sql.*;
import java.util.*;

import controller.CityController;
import database.interfaces.DepartmentDBIF;
import exceptions.NotFoundException;
import model.*;

public class DepartmentDB implements DepartmentDBIF {
    // PreparedStatements for DepartmentDB class
    private static final String FIND_ALL = "select * from Departments where Enabled = 1";
    private static final String FIND_BY_ID = "select * from Departments where Id = ?";
    private static final String CREATE_DEPARTMENT = "insert into Departments values(?, ?, ?, ?)";
    private static final String UPDATE_DEPARTMENT = "update Departments set Name = ?, ZIP = ?, Address = ? where Id = ?";
    private static final String DELETE_DEPARTMENT = "update Departments set Enabled = 0 where Id = ?";

    private PreparedStatement findAll;
    private PreparedStatement findById;
    private PreparedStatement createDepartment;
    private PreparedStatement updateDepartment;
    private PreparedStatement deleteDepartment;

    CityController cityCtrl = new CityController();

    /**
     * Constructor for the DepartmentDB class
     * @throws SQLException
     */
    public DepartmentDB() throws SQLException {
        findAll = DBConnection.getInstance().getConnection().prepareStatement(FIND_ALL);
        findById = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_ID);
        createDepartment = DBConnection.getInstance().getConnection().prepareStatement(CREATE_DEPARTMENT, Statement.RETURN_GENERATED_KEYS);
        updateDepartment = DBConnection.getInstance().getConnection().prepareStatement(UPDATE_DEPARTMENT);
        deleteDepartment = DBConnection.getInstance().getConnection().prepareStatement(DELETE_DEPARTMENT);
    }

    @Override
    public List<Department> findAll() throws SQLException, NotFoundException {
        ResultSet rs;
        rs = findAll.executeQuery();
        List<Department> departments = buildObjects(rs);
        return departments;
    }
    
    @Override
    public Department findById(int id) throws SQLException, NotFoundException {
        Department department = null;
        ResultSet rs;
        findById.setInt(1, id);
        rs = findById.executeQuery();
        while (rs.next()) {
            department = buildObject(rs);
        }

        if (department == null) { throw new NotFoundException("Department", id); }

        return department;
    }

    @Override
    public void createDepartment(Department department) throws SQLException {
        createDepartment.setString(1, department.getName());
        createDepartment.setString(2, department.getZipCode().getZipCode());
        createDepartment.setString(3, department.getAddress());
        createDepartment.setBoolean(4, true);
        department.setId(DBConnection.getInstance().executeSqlInsertWithIdentity(createDepartment));
    }

    @Override
    public void updateDepartment(Department department) throws SQLException {
        updateDepartment.setString(1, department.getName());
        updateDepartment.setString(2, department.getZipCode().getZipCode());
        updateDepartment.setString(3, department.getAddress());
        updateDepartment.setInt(4, department.getId());
        updateDepartment.executeUpdate();
    }

    @Override
    public void deleteDepartment(Department department) throws SQLException {
        deleteDepartment.setInt(1, department.getId());
        deleteDepartment.execute();
    }

    private Department buildObject(ResultSet rs) throws SQLException, NotFoundException {
        City zipCode = cityCtrl.findByZip(rs.getString("ZIP"));
        Department department = new Department(rs.getString("Name"), zipCode, rs.getString("Address"));
        department.setId(rs.getInt("Id"));
        return department;
    }

    private List<Department> buildObjects(ResultSet rs) throws SQLException, NotFoundException {
        List<Department> departments = new ArrayList<>();
        while (rs.next()) {
            departments.add(buildObject(rs));
        }
        return departments;
    }

}
