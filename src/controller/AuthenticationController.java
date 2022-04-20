package controller;

import java.sql.SQLException;

import exceptions.NotFoundException;
import model.Authentication;
import model.Employee;

public class AuthenticationController {
    
    private Authentication authentication;
    private EmployeeController employeeCtrl;

    /**
     * Constructor for the AuthenticationController class
     * @throws SQLException
     */
    public AuthenticationController() throws SQLException {
        authentication = new Authentication();
        employeeCtrl = new EmployeeController();
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
        Employee employee = employeeCtrl.findByEmail(email, password);
        if (employee != null) {
            authentication.login(employee);
            login = true;
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
}
