package controller;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import database.BackupDB;
import database.interfaces.BackupDBIF;
import exceptions.NotFoundException;
import model.Authentication;
import model.Employee;

public class AuthenticationController {
    
    private Authentication authentication;
    private EmployeeController employeeCtrl;
    private BackupDBIF backupDBIF;

    /**
     * Constructor for the AuthenticationController class
     * @throws SQLException
     */
    public AuthenticationController() throws SQLException {
        authentication = new Authentication();
        employeeCtrl = new EmployeeController();
        backupDBIF = new BackupDB();
    }

    /**
     * Logs in the user, if credentials are correct
     * @param email - employee's email
     * @param password - employee's password
     * @return true if login is successfull
     * @throws SQLException
     * @throws NotFoundException
     */
    public boolean logIn(String email, String password) throws SQLException, NotFoundException {
        boolean login = false;
        Employee employee = employeeCtrl.findByEmail(email);
        if (employee != null) {
            if (comparePassword(password, employee.getPassword())) {
                authentication.login(employee);
                login = true;
            }
        }

        return login;
    }

    /**
     * gets the logged in Employee
     * @return the logged in Employee
     */
    public Employee getLoggedInUser() {
        return authentication.getLoggedInUser();
    }

    /**
     * logs out the Employee
     */
    public void logout() {
        authentication.logout();
    }

    /**
     * database backup
     * @throws SQLException
     */
    public void backUp() throws SQLException {
        backupDBIF.backUp();
    }

    public boolean comparePassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
