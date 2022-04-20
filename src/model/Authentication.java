package model;

public class Authentication {
    
    private Employee loggedInUser;

    /**
     * Constructor for the Authentication class
     */
    public Authentication() {
        this.loggedInUser = null;
    }

    /**
     * login as Employee
     * @param employee - employee to be logged in
     */
    public void login(Employee employee) {
        this.loggedInUser = employee;
    }

    /**
     * gets the logged in employee
     * @return the Employee object
     */
    public Employee getLoggedInUser() {
        return this.loggedInUser;
    }

    /**
     * logs out the Employee 
     */
    public void logout() {
        this.loggedInUser = null;
    }
}
