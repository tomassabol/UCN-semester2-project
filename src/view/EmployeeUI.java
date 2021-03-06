package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.EmployeeController;
import controller.EmployeeTypeController;
import exceptions.NotFoundException;
import model.City;
import model.Department;
import model.Employee;
import model.Employee.EmployeeType;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.sql.SQLException;

public class EmployeeUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JTextField txtZip;
	private JTextField txtPassword;
	private JTextField txtCPR;
	private JButton btnSubmit;
	private JComboBox<EmployeeType> boxEmployeeType;
	private Employee employee;
	private EmployeeController employeeCtrl;
	private EmployeeTypeController employeeTypeCtrl;
	private Mode mode;
	AuthenticationController auth;
	private JButton btnSelectCity;
    private City zipCode;
    private JTextField txtDepartment;
    private JButton btnSelectDepartment;
	private Department department;
	/**
	 * Constructor: create new EmployeeUI
	 *
	 * @param auth the auth controller 
	 * @throws SQLException
	 * @throws NotFoundException
	 * @wbp.parser.constructor
	 */
	public EmployeeUI(AuthenticationController auth) throws SQLException, NotFoundException {
		this(auth, null, Mode.CREATE);
		this.employee = null;
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public EmployeeUI(AuthenticationController auth, Employee employee, Mode mode) throws SQLException, NotFoundException {
		this.auth = auth;
		this.mode = mode;
		this.employee = employee;
		
		employeeCtrl = new EmployeeController();
		employeeTypeCtrl = new EmployeeTypeController();
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{208, 208, 0};
		gbl_contentPane.rowHeights = new int[]{19, 0, 0, 0, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblId = new JLabel("Id");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		contentPane.add(lblId, gbc_lblId);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.anchor = GridBagConstraints.WEST;
		gbc_txtId.insets = new Insets(0, 0, 5, 5);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 0;
		gbc_txtId.gridy = 1;
		contentPane.add(txtId, gbc_txtId);
		
		JLabel lblName = new JLabel("Name *");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		contentPane.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.anchor = GridBagConstraints.WEST;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 1;
		contentPane.add(txtName, gbc_txtName);
		
		JLabel lblEmail = new JLabel("Email *");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 2;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.anchor = GridBagConstraints.WEST;
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 0;
		gbc_txtEmail.gridy = 3;
		contentPane.add(txtEmail, gbc_txtEmail);
		
		JLabel lblPhone = new JLabel("Phone *");
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.anchor = GridBagConstraints.WEST;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.gridx = 1;
		gbc_lblPhone.gridy = 2;
		contentPane.add(lblPhone, gbc_lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		GridBagConstraints gbc_txtPhone = new GridBagConstraints();
		gbc_txtPhone.anchor = GridBagConstraints.WEST;
		gbc_txtPhone.insets = new Insets(0, 0, 5, 5);
		gbc_txtPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhone.gridx = 1;
		gbc_txtPhone.gridy = 3;
		contentPane.add(txtPhone, gbc_txtPhone);
		
		JLabel lblZip = new JLabel("ZIP Code*");
		GridBagConstraints gbc_lblZip = new GridBagConstraints();
		gbc_lblZip.anchor = GridBagConstraints.WEST;
		gbc_lblZip.insets = new Insets(0, 0, 5, 5);
		gbc_lblZip.gridx = 0;
		gbc_lblZip.gridy = 4;
		contentPane.add(lblZip, gbc_lblZip);
		
		txtZip = new JTextField();
		txtZip.setColumns(10);
		GridBagConstraints gbc_txtZip = new GridBagConstraints();
		gbc_txtZip.insets = new Insets(0, 0, 5, 5);
		gbc_txtZip.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtZip.gridx = 0;
		gbc_txtZip.gridy = 5;
		contentPane.add(txtZip, gbc_txtZip);
		
		btnSelectCity = new JButton("Select");
		btnSelectCity.setForeground(new Color(255,255,255));
		btnSelectCity.setBackground(new Color(143,108,175));
		GridBagConstraints gbc_btnSelectCity = new GridBagConstraints();
		gbc_btnSelectCity.anchor = GridBagConstraints.WEST;
		gbc_btnSelectCity.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectCity.gridx = 1;
		gbc_btnSelectCity.gridy = 5;
		contentPane.add(btnSelectCity, gbc_btnSelectCity);
		
		JLabel lblAddress = new JLabel("Address *");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.WEST;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 0;
		gbc_lblAddress.gridy = 6;
		contentPane.add(lblAddress, gbc_lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		GridBagConstraints gbc_txtAddress = new GridBagConstraints();
		gbc_txtAddress.insets = new Insets(0, 0, 5, 5);
		gbc_txtAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddress.gridx = 0;
		gbc_txtAddress.gridy = 7;
		contentPane.add(txtAddress, gbc_txtAddress);
		
		JLabel lblEmployeeType = new JLabel("Type *");
		GridBagConstraints gbc_lblEmployeeType = new GridBagConstraints();
		gbc_lblEmployeeType.anchor = GridBagConstraints.WEST;
		gbc_lblEmployeeType.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmployeeType.gridx = 1;
		gbc_lblEmployeeType.gridy = 6;
		contentPane.add(lblEmployeeType, gbc_lblEmployeeType);
		
		boxEmployeeType = new JComboBox<>();
		for(EmployeeType employeeType : employeeTypeCtrl.findAll()) {
			boxEmployeeType.addItem(employeeType);
		}
		GridBagConstraints gbc_boxEmployeeType = new GridBagConstraints();
		gbc_boxEmployeeType.anchor = GridBagConstraints.WEST;
		gbc_boxEmployeeType.insets = new Insets(0, 0, 5, 5);
		gbc_boxEmployeeType.gridx = 1;
		gbc_boxEmployeeType.gridy = 7;
		contentPane.add(boxEmployeeType, gbc_boxEmployeeType);
		
		JLabel lblPassword = new JLabel("Password *");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 8;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 0;
		gbc_txtPassword.gridy = 9;
		contentPane.add(txtPassword, gbc_txtPassword);
		
		JLabel lblCPR = new JLabel("CPR *");
		GridBagConstraints gbc_lblCPR = new GridBagConstraints();
		gbc_lblCPR.anchor = GridBagConstraints.WEST;
		gbc_lblCPR.insets = new Insets(0, 0, 5, 5);
		gbc_lblCPR.gridx = 1;
		gbc_lblCPR.gridy = 8;
		contentPane.add(lblCPR, gbc_lblCPR);
		
		txtCPR = new JTextField();
		txtCPR.setColumns(10);
		GridBagConstraints gbc_txtCPR = new GridBagConstraints();
		gbc_txtCPR.insets = new Insets(0, 0, 5, 5);
		gbc_txtCPR.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCPR.gridx = 1;
		gbc_txtCPR.gridy = 9;
		contentPane.add(txtCPR, gbc_txtCPR);
		
		JLabel lblDepartment = new JLabel("Department *");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.anchor = GridBagConstraints.WEST;
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 0;
		gbc_lblDepartment.gridy = 10;
		contentPane.add(lblDepartment, gbc_lblDepartment);
		
		txtDepartment = new JTextField();
		txtDepartment.setColumns(10);
		GridBagConstraints gbc_txtDepartment = new GridBagConstraints();
		gbc_txtDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_txtDepartment.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDepartment.gridx = 0;
		gbc_txtDepartment.gridy = 11;
		contentPane.add(txtDepartment, gbc_txtDepartment);
		
		btnSelectDepartment = new JButton("Select");
		btnSelectDepartment.setForeground(new Color(255,255,255));
		btnSelectDepartment.setBackground(new Color(143,108,175));
		GridBagConstraints gbc_btnSelectDepartment = new GridBagConstraints();
		gbc_btnSelectDepartment.anchor = GridBagConstraints.WEST;
		gbc_btnSelectDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectDepartment.gridx = 1;
		gbc_btnSelectDepartment.gridy = 11;
		contentPane.add(btnSelectDepartment, gbc_btnSelectDepartment);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(new Color(255,255,255));
		btnSubmit.setBackground(new Color(183,26,134,255));
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnOk.gridx = 2;
		gbc_btnOk.gridy = 14;
		contentPane.add(btnSubmit, gbc_btnOk);
		
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View Employee - " + employee.getName());
				// Hide 'Update' button if in view mode
				btnSubmit.setVisible(false);
				// Disable fields
				this.disableFields();
				// Fill fields with content
				this.fillFields(employee);
				break;
			case EDIT: 
				// disable cpr txt field
				txtCPR.setEnabled(false);
				// Set title
				setTitle("Edit Employee");
				// Change submit button text to 'Update'
				btnSubmit.setText("Update");
				// Enable fields for editing
				this.enableFields();
				// Fill fields with content
				this.fillFields(employee);
				break;
			case CREATE:
				// Set title
				setTitle("Add New Employee");
				// Change submit button text to 'Create'
				btnSubmit.setText("Create");
				// Enable fields
				this.enableFields();
				// Peek ID
//				txtId.setText(String.valueOf(employee.getId()));
		}
		
		
		
		addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	/**
	 * Gets the Employee.
	 * Useful for Create mode (to get the created employee)
	 *
	 * @return the employee
	 */
	public Employee getEmployee() {
		return this.employee;
	}
	
	// Makes the text fields uneditable
	private void disableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JComboBox || c instanceof JButton) {
				      c.setEnabled(false);
				   }
			}
		txtId.setEnabled(false);
		txtCPR.setEnabled(false);
	}
	
	
	// Makes the text fields editable except ID field
	private void enableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JComboBox || c instanceof JButton) {
			      c.setEnabled(true);
			   }
			}
		txtId.setEnabled(false);
	}
	
	// FIll in the fields
	private void fillFields(Employee employee) {
		txtId.setText(String.valueOf(employee.getId()));
		txtName.setText(employee.getName());
		txtEmail.setText(employee.getEmail());
		txtPhone.setText(employee.getPhone());
		txtZip.setText(employee.getZipCode().getZipCode().toString());
        this.zipCode = employee.getZipCode();
		txtAddress.setText(employee.getAddress());
		boxEmployeeType.setSelectedItem(employee.getEmployeeType());
		txtPassword.setText(employee.getPassword());
		txtCPR.setText(employee.getCPR());
		txtDepartment.setText(employee.getDepartment().getName());
		this.department = employee.getDepartment();
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the employee
		btnSubmit.addActionListener(e -> {
			String message = "";
			if (mode == Mode.EDIT) {
				message = "Are you sure you want to update the changes to Employee?";
			} else if (mode == Mode.CREATE) {
				message = "Create Employee?";
			}
			if (Messages.confirm(EmployeeUI.this, message)) {
				
				// Validate name
				String name = txtName.getText().strip();
				if (name.isEmpty()) {
					Messages.error(this, "Employee name cannot be empty");
					return;
				}
				
				// Validate email
				String email = txtEmail.getText().strip();
				if (email.isEmpty()) {
					Messages.error(this, "Employee email cannot be empty");
					return;
				}
				
				// Validate phone
				String phone = txtPhone.getText().strip();
				if (phone.isEmpty()) {
					Messages.error(this, "Employee phone cannot be empty");
					return;
				}
				
				// Validate zip
                if (zipCode == null) {
                    Messages.error(this, "You must choose a Zip Code");
					return;
                }
						
				// Validate address
				String address = txtAddress.getText().strip();
				if (address.isEmpty()) {
					Messages.error(this, "Employee Address cannot be empty");
					return;
				}
			
				EmployeeType employeeType = (EmployeeType) boxEmployeeType.getSelectedItem();
				if (employeeType == null) {
					Messages.error(this, "Employee type cannot be empty. Select Employee Type");
					return;
				}

				String password = txtPassword.getText().strip();
				if (password.isEmpty()) {
					Messages.error(this, "Employee password cannot be empty");
					return;
				}

				String CPR = txtCPR.getText().strip();
				if (password.isEmpty()) {
					Messages.error(this, "Employee CPR cannot be empty");
					return;
				}
				
				// if mode == view, update data
				if (mode == Mode.EDIT) {
			
                    try {
                        employeeCtrl.updateEmployee(employee, name, email, phone, zipCode, address, employeeType, password, department);
                    } catch (SQLException e1) {
                    	Messages.error(this, "There was an error connecting to the database");
                    }

				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new employee
					try {
                        Employee employee = employeeCtrl.createEmployee(name, email, phone, zipCode, address, employeeType, password, CPR, department);
						this.employee = employee;
                    } catch (SQLException e1) {
                    	Messages.error(this, "There was an error connecting to the database");
                    };
				}


				
			}
			// Dispose of the window
			this.dispose();
		});

		btnSelectCity.addActionListener(e -> {
			ChooseCity frame;
			try {
				frame = new ChooseCity(auth);
				frame.setVisible(true);
				if (frame.getSelectedCity() != null) {
					this.zipCode = frame.getSelectedCity();
					txtZip.setText(zipCode.getZipCode());
				}
			} catch (SQLException e1) {
				Messages.error(this, "There was an error connecting to the database");
			} catch (NotFoundException e1) {
				Messages.error(this, "The window could not be opened. Please try again or report the issue!");
			}
		});

		btnSelectDepartment.addActionListener(e -> {
			ChooseDepartment frame;
			try {
				frame = new ChooseDepartment(auth);
				frame.setVisible(true);
				if (frame.getSelectedDepartment() != null) {
					this.department = frame.getSelectedDepartment();
					txtDepartment.setText(department.getName());
				}
			} catch (SQLException e1) {
				Messages.error(this, "There was an error connecting to the database");
			} catch (NotFoundException e1) {
				Messages.error(this, "The window could not be opened. Please try again or report the issue!");
			}
		});
	}
}