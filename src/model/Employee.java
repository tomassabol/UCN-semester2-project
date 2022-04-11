package model;

public class Employee extends Person {

    private EmployeeType employeeType;
    private String password;
    private final String CPR;
    private Department department;

    private enum EmployeeType {
        ADMIN,
        CEO,
        EMPLOYEE
    }
    
    /**
     * Constructor for the Employee class
     * @param name - employee's name
     * @param email - employee's email
     * @param phone - employee's phone number
     * @param zipCode - zip code of the employee's city of residence
     * @param address - employee's address
     * @param employeeType - employee type
     * @param password - employee's system password
     * @param CPR - employee's CPR number
     * @param department - department where employee works
     */
    public Employee(String name, String email, String phone, City zipCode, String address, EmployeeType employeeType, String password, String CPR, Department department) {
        super(name, email, phone, zipCode, address);
        this.employeeType = employeeType;
        this.password = password;
        this.CPR = CPR;
        this.department = department;
    }

    public EmployeeType getEmployeeType() {
        return this.employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCPR() {
        return this.CPR;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
