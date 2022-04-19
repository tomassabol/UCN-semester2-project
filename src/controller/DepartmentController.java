package controller;

import java.sql.SQLException;
import java.util.List;

import database.DepartmentDB;
import database.interfaces.DepartmentDBIF;
import exceptions.NotFoundException;
import model.City;
import model.Department;

public class DepartmentController {
    
    private DepartmentDBIF departmentDBIF;

    /**
     * Constructor for the DepartmentController class
     * @throws SQLException
     */
    public DepartmentController() throws SQLException {
        departmentDBIF = new DepartmentDB();
    }

    /**
     * finds all departments
     * @return all departments
     * @throws SQLException
     * @throws NotFoundException
     */
    public List<Department> findAll() throws SQLException, NotFoundException {
        List<Department> departments = departmentDBIF.findAll();
        return departments;
    }

    /**
     * finds a department by the desired ID
     * @param id - desired id
     * @return department with the desired ID
     * @throws SQLException
     * @throws NotFoundException
     */
    public Department findById(int id) throws SQLException, NotFoundException {
        Department department = departmentDBIF.findById(id);
        return department;
    }

    /**
     * Creates new Department
     * @param name - name of the department
     * @param zipCode - zip code of the department
     * @param address - address of the department
     * @throws SQLException
     */
    public void createDepartment(String name, City zipCode, String address) throws SQLException {
        Department department = new Department(name, zipCode, address);
        departmentDBIF.createDepartment(department);
    }

    /**
     * Updates department details
     * @param department - department to be updated
     * @param name - updated name
     * @param zipCode - updated zipCode
     * @param address - updated address
     * @throws SQLException
     */
    public void updateDepartment(Department department, String name, City zipCode, String address) throws SQLException {
        department.setName(name);
        department.setZipCode(zipCode);
        department.setAddress(address);
        departmentDBIF.updateDepartment(department);
    }

    /**
     * delete department
     * @param department - department to be deleted
     * @throws SQLException
     */
    public void deleteDepartment(Department department) throws SQLException {
        departmentDBIF.deleteDepartment(department);
    }
}
