package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.CityController;
import controller.DepartmentController;
import controller.EmployeeController;
import exceptions.NotFoundException;
import model.City;
import model.Department;
import model.Employee;
import model.Employee.EmployeeType;

public class CreateEmployee extends JFrame {
	
	private JButton btnCancelEmployee;
	private JButton btnCreateEmployee;
	private JPanel contentPane;
	private JTextField txtEmployeeName;
	private JTextField txtEmployeeType;
	private JTextField txtEmployeeEmail;
	private JTextField txtEmployeePassword;
	private JTextField txtEmployeePhone;
	private JTextField txtEmployeeZipcode;
	private JTextField txtEmployeeAddress;
	private JTextField txtEmployeeCPR;
	private JTextField txtEmployeeDepartment;
	private JComboBox employeeType;
	private JComboBox employeeDepartment;
	
	private DepartmentController departmentCtrl;
	private EmployeeController employeeCtrl;
	private CityController cityCtrl;
	
	public CreateEmployee() throws IllegalArgumentException, SQLException, NotFoundException{
		departmentCtrl = new DepartmentController();
		
		// ***** WINDOW *****
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// ***** TOP PANEL *****
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		// ***** Title *****
		JLabel lblTitle = new JLabel("Create new employee");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 4;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);
		
		// ***** Middle panel *****
		JPanel middlePanel = new JPanel(new GridLayout(5, 2));
		getContentPane().add(middlePanel);
		
		// ***** Employee name field *****
		txtEmployeeName = new JTextField();
		txtEmployeeName.setFont(new Font("Open Sans", Font.PLAIN, 10));
		txtEmployeeName.setText("Name");
		middlePanel.add(txtEmployeeName);
		
		// ***** Employee type dropdown menu *****
		employeeType = new JComboBox(Employee.EmployeeType.values());
		employeeType.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(employeeType);

		// ***** Employee email field *****
		txtEmployeeEmail = new JTextField();
		txtEmployeeEmail.setFont(new Font("Open Sans", Font.PLAIN, 10));
		txtEmployeeEmail.setText("Email");
		middlePanel.add(txtEmployeeEmail);
		
		// ***** Employee password field *****
		txtEmployeePassword = new JTextField();
		txtEmployeePassword.setFont(new Font("Open Sans", Font.PLAIN, 10));
		txtEmployeePassword.setText("Employee password");
		middlePanel.add(txtEmployeePassword);
		
		// ***** Employee phone field *****
		txtEmployeePhone = new JTextField();
		txtEmployeePhone.setFont(new Font("Open Sans", Font.PLAIN, 10));
		txtEmployeePhone.setText("Phone");
		middlePanel.add(txtEmployeePhone);
		
		// ***** Employee CPR field *****
		txtEmployeeCPR = new JTextField();
		txtEmployeeCPR.setFont(new Font("Open Sans", Font.PLAIN, 10));
		txtEmployeeCPR.setText("CPR");
		middlePanel.add(txtEmployeeCPR);
		
		// ***** Employee zip code field *****
		txtEmployeeZipcode = new JTextField();
		txtEmployeeZipcode.setFont(new Font("Open Sans", Font.PLAIN, 10));
		txtEmployeeZipcode.setText("Zip code");
		middlePanel.add(txtEmployeeZipcode);
		
		// ***** Employee department dropdown menu *****
		employeeDepartment = new JComboBox();
		for(Department department : departmentCtrl.findAll()) {
			employeeDepartment.addItem(department.getName());
		}
		employeeDepartment.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(employeeDepartment);
		
		// ***** Employee address field *****
		txtEmployeeAddress = new JTextField();
		txtEmployeeAddress.setFont(new Font("Open Sans", Font.PLAIN, 10));
		txtEmployeeAddress.setText("Address");
		middlePanel.add(txtEmployeeAddress);
		
		// ***** Bottom panel *****
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		// ***** Panel for cancellation and creation buttons *****
		JPanel cancelAndCreatePanel = new JPanel();
		bottomPanel.add(cancelAndCreatePanel, BorderLayout.SOUTH);
		GridBagLayout gbl_cancelAndCreatePanel = new GridBagLayout();
		gbl_cancelAndCreatePanel.columnWidths = new int[]{0, 17, 0, 0, 0};
		gbl_cancelAndCreatePanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_cancelAndCreatePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_cancelAndCreatePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		cancelAndCreatePanel.setLayout(gbl_cancelAndCreatePanel);
		
		// ***** Cancel employee button *****
		btnCancelEmployee = new JButton("Cancel");
		GridBagConstraints gbc_btnCancelEmployee = new GridBagConstraints();
		gbc_btnCancelEmployee.anchor = GridBagConstraints.EAST;
		gbc_btnCancelEmployee.gridx = 3;
		gbc_btnCancelEmployee.gridy = 3;
		cancelAndCreatePanel.add(btnCancelEmployee, gbc_btnCancelEmployee);
		
		// ***** Create employee button *****
		btnCreateEmployee = new JButton("Create");
		GridBagConstraints gbc_btnCreateEmployee = new GridBagConstraints();
		gbc_btnCreateEmployee.anchor = GridBagConstraints.EAST;
		gbc_btnCreateEmployee.gridx = 4;
		gbc_btnCreateEmployee.gridy = 3;
		cancelAndCreatePanel.add(btnCreateEmployee, gbc_btnCreateEmployee);
		
		addEventHandlers();
	}
	
	public void addEventHandlers() {
		
		// Create button
		btnCreateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameInput = txtEmployeeName.getText();
				EmployeeType typeInput = (EmployeeType)employeeType.getSelectedItem();
				String emailInput = txtEmployeeEmail.getText();
				String passwordInput = txtEmployeePassword.getText();
				String phoneInput = txtEmployeePhone.getText();
				String CPRInput = txtEmployeeCPR.getText();
				String zipcodeInput = txtEmployeeZipcode.getText();
				Object selectedDepartment = employeeDepartment.getSelectedItem();
				Department departmentInput = null;
				try {
					departmentInput = departmentCtrl.findByName((String)selectedDepartment);
				} catch (SQLException | NotFoundException e2) {
					e2.printStackTrace();
				}
				String addressInput = txtEmployeeAddress.getText();
				
				try {
					employeeCtrl = new EmployeeController();
					cityCtrl = new CityController();
					City foundZip = cityCtrl.findByZip(zipcodeInput);
					employeeCtrl.createEmployee(nameInput, emailInput, phoneInput, foundZip, addressInput, typeInput, passwordInput, CPRInput, departmentInput);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (NotFoundException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		// Cancel button
		btnCancelEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateEmployee.this.setVisible(false);
			}
		});
	}
}
