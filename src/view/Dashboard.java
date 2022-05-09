package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;


import controller.AuthenticationController;
import controller.DepartmentController;
import controller.EmployeeController;
import exceptions.NotFoundException;
import model.City;
import model.Customer;
import model.Department;
import model.Employee;
import model.Employee.EmployeeType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class Dashboard extends JFrame {

	// Fields for gui
	private JPanel contentPane;
	private JTabbedPane tabsPane;
	private Component lblGreeting;
	private JLabel lblCreateOrder;
	private JLabel lblCreateEmployee; // employee
	private JLabel lblUpdateEmployee; // employee
	private JLabel lblReadEmployee; // employee
	private JLabel lblDeleteEmployee; // employee
	private JButton btnCreateOrder;
	private JButton btnCreateEmployee; //employee
	private JButton btnUpdateEmployee; // employee
	private JButton btnReadEmployee; // employee
	private JButton btnDeleteEmployee; // employee
	private JLabel lblOrders;
	private JButton btnShowOrders;
	private JButton btnLogOut;
	private JButton btnNewButton;
	private JTextField txtCustomerEmail;
	private JTextField txtEmployeeEmail; // employee

	// Fields for classes created by us
	private AuthenticationController auth;
	private EmployeeController employeeCtrl;
	private Customer customer;
	private Employee employee;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Dashboard(AuthenticationController authentication) throws SQLException {
		auth = authentication;
		//Window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);
		// *Content pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// **Top panel (greeting & log out)
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
			
		// ***** TOP PANEL *****
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{143, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
				
		// ***** Greeting label *****
		lblGreeting = new JLabel("Hi, " + auth.getLoggedInUser().getName());
		lblGreeting.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblGreeting = new GridBagConstraints();
		gbc_lblGreeting.insets = new Insets(0, 0, 0, 5);
		gbc_lblGreeting.gridx = 0;
		gbc_lblGreeting.gridy = 0;
		topPanel.add(lblGreeting, gbc_lblGreeting);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnLogOut = new GridBagConstraints();
		gbc_btnLogOut.gridx = 2;
		gbc_btnLogOut.gridy = 0;
		topPanel.add(btnLogOut, gbc_btnLogOut);
			
		// ***** Tabs pane *****
		tabsPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabsPane, BorderLayout.CENTER);
		
		// ***** Order tab *****
		initOrderTab();
		initEmployeeTab(); // employee
			
			
		
		
		//Attach event handlers
		addEventHandlers();
	}
	
	/*
	 * -------------------------------------------------------
	 * ----------------------  Order tab ---------------------
	 * -------------------------------------------------------
	 */
	
	public void initOrderTab() {
		////////////// Copy this to create a new Tab (delete this comment after all the tabs are done) /////////////////////////
		JPanel orderPanel = new JPanel();
		orderPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		tabsPane.addTab("Orders", null, orderPanel, null);
		GridBagLayout gbl_orderPanel = new GridBagLayout();
		gbl_orderPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_orderPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_orderPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_orderPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		orderPanel.setLayout(gbl_orderPanel);
		
		btnNewButton = new JButton("Choose customer");
		btnNewButton.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		orderPanel.add(btnNewButton, gbc_btnNewButton);
		
		txtCustomerEmail = new JTextField();
		txtCustomerEmail.setFont(new Font("Open Sans", Font.PLAIN, 10));
		txtCustomerEmail.setText("Customer email");
		GridBagConstraints gbc_txtCustomerEmail = new GridBagConstraints();
		gbc_txtCustomerEmail.gridwidth = 3;
		gbc_txtCustomerEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtCustomerEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCustomerEmail.gridx = 1;
		gbc_txtCustomerEmail.gridy = 2;
		orderPanel.add(txtCustomerEmail, gbc_txtCustomerEmail);
		txtCustomerEmail.setColumns(10);
		///////////////////////////////////////////////////////////////////////
		
		lblCreateOrder = new JLabel("Create Order");
		lblCreateOrder.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblCreateOrder = new GridBagConstraints();
		gbc_lblCreateOrder.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreateOrder.gridx = 1;
		gbc_lblCreateOrder.gridy = 4;
		orderPanel.add(lblCreateOrder, gbc_lblCreateOrder);
		
		lblOrders = new JLabel("Show Orders");
		lblOrders.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblOrders = new GridBagConstraints();
		gbc_lblOrders.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrders.gridx = 3;
		gbc_lblOrders.gridy = 4;
		orderPanel.add(lblOrders, gbc_lblOrders);
		
		btnCreateOrder = new JButton("Create Order");
		btnCreateOrder.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnCreateOrder = new GridBagConstraints();
		gbc_btnCreateOrder.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateOrder.gridx = 1;
		gbc_btnCreateOrder.gridy = 5;
		orderPanel.add(btnCreateOrder, gbc_btnCreateOrder);
		
		btnShowOrders = new JButton("Show Orders");
		btnShowOrders.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnShowOrders = new GridBagConstraints();
		gbc_btnShowOrders.insets = new Insets(0, 0, 5, 5);
		gbc_btnShowOrders.gridx = 3;
		gbc_btnShowOrders.gridy = 5;
		orderPanel.add(btnShowOrders, gbc_btnShowOrders);
	}
	
	// employee
	public void initEmployeeTab() {
		JPanel employeePanel = new JPanel();
		employeePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		tabsPane.addTab("Employees", null, employeePanel, null);
		GridBagLayout gbl_employeePanel = new GridBagLayout();
		gbl_employeePanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_employeePanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_employeePanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_employeePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		employeePanel.setLayout(gbl_employeePanel);
		
		btnNewButton = new JButton("Choose employee");
		btnNewButton.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 4;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		employeePanel.add(btnNewButton, gbc_btnNewButton);
		
		txtEmployeeEmail = new JTextField();
		txtEmployeeEmail.setFont(new Font("Open Sans", Font.PLAIN, 10));
		txtEmployeeEmail.setText("Employee email");
		GridBagConstraints gbc_txtEmployeeEmail = new GridBagConstraints();
		gbc_txtEmployeeEmail.gridwidth = 4;
		gbc_txtEmployeeEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmployeeEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmployeeEmail.gridx = 1;
		gbc_txtEmployeeEmail.gridy = 2;
		employeePanel.add(txtEmployeeEmail, gbc_txtEmployeeEmail);
		txtEmployeeEmail.setColumns(10);
		
		lblCreateEmployee = new JLabel("Create Employee");
		lblCreateEmployee.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblCreateEmployee = new GridBagConstraints();
		gbc_lblCreateEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreateEmployee.gridx = 0;
		gbc_lblCreateEmployee.gridy = 4;
		employeePanel.add(lblCreateEmployee, gbc_lblCreateEmployee);
		
		btnCreateEmployee = new JButton("Create Employee");
		btnCreateEmployee.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnCreateEmployee = new GridBagConstraints();
		gbc_btnCreateEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateEmployee.gridx = 1;
		gbc_btnCreateEmployee.gridy = 5;
		employeePanel.add(btnCreateEmployee, gbc_btnCreateEmployee);
		
		lblUpdateEmployee = new JLabel("Update Employee");
		lblUpdateEmployee.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblUpdateEmployee = new GridBagConstraints();
		gbc_lblUpdateEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpdateEmployee.gridx = 2;
		gbc_lblUpdateEmployee.gridy = 4;
		employeePanel.add(lblUpdateEmployee, gbc_lblUpdateEmployee);
		
		btnUpdateEmployee = new JButton("Update Employee");
		btnUpdateEmployee.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnUpdateEmployee = new GridBagConstraints();
		gbc_btnUpdateEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdateEmployee.gridx = 2;
		gbc_btnUpdateEmployee.gridy = 5;
		employeePanel.add(btnUpdateEmployee, gbc_btnUpdateEmployee);

		lblReadEmployee = new JLabel("View Employee Info");
		lblReadEmployee.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblReadEmployee = new GridBagConstraints();
		gbc_lblReadEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_lblReadEmployee.gridx = 3;
		gbc_lblReadEmployee.gridy = 4;
		employeePanel.add(lblReadEmployee, gbc_lblReadEmployee);
		
		btnReadEmployee = new JButton("View Employee Info");
		btnReadEmployee.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnReadEmployee = new GridBagConstraints();
		gbc_btnReadEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_btnReadEmployee.gridx = 3;
		gbc_btnReadEmployee.gridy = 5;
		employeePanel.add(btnReadEmployee, gbc_btnReadEmployee);
		
		lblDeleteEmployee = new JLabel("Delete Employee");
		lblDeleteEmployee.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblDeleteEmployee = new GridBagConstraints();
		gbc_lblDeleteEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeleteEmployee.gridx = 4;
		gbc_lblDeleteEmployee.gridy = 4;
		employeePanel.add(lblDeleteEmployee, gbc_lblDeleteEmployee);
		
		btnDeleteEmployee = new JButton("Delete Employee");
		btnDeleteEmployee.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnDeleteEmployee = new GridBagConstraints();
		gbc_btnDeleteEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteEmployee.gridx = 4;
		gbc_btnDeleteEmployee.gridy = 5;
		employeePanel.add(btnDeleteEmployee, gbc_btnDeleteEmployee);
	}
	
	/*
	 * *******************************************************
	 * *******************  COMMON METHODS *******************
	 * *******************************************************
	 */
	
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	
	public void addEventHandlers() {
		
		//Log out button
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Messages.confirm(contentPane, "Are you sure you want to log out?", "Log out")) {
					auth.logout();
					Login frame = new Login();
					frame.setVisible(true);
			    	// free up memory by destroying the current dashboard
			    	Dashboard.this.dispose();
				}
			}
		});
		
		//Create order button
		btnCreateOrder.addActionListener(new ActionListener() {
			//TODO: Implement choose customer
			public void actionPerformed(ActionEvent e) {
				CreateOrder frame = new CreateOrder(auth, customer, null);
				frame.setVisible(true);
			}
		});
		
		//Create employee button
		btnCreateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CreateEmployee frame = new CreateEmployee();
					frame.setVisible(true);
				} catch (IllegalArgumentException | SQLException | NotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//Read employee button
		btnReadEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String employeeEmailInput = txtEmployeeEmail.getText();
					employeeCtrl = new EmployeeController();
					Employee foundEmployee = employeeCtrl.findOnlyByEmail(employeeEmailInput);
					ReadEmployee frame = new ReadEmployee(foundEmployee);
					frame.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (NotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//Delete employee button
		btnDeleteEmployee.addActionListener(new ActionListener() {
			//TODO: Implement choose employee
			public void actionPerformed(ActionEvent e) { 
				try {
					String employeeEmailInput = txtEmployeeEmail.getText();
					employeeCtrl = new EmployeeController();
					Employee foundEmployee = employeeCtrl.findOnlyByEmail(employeeEmailInput);
					employeeCtrl.deleteEmployee(foundEmployee);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (NotFoundException e1) {
					e1.printStackTrace();
				}	
			}
		});
	}

}

