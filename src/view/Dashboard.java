package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;


import controller.AuthenticationController;
import controller.CustomerController;
import controller.DepartmentController;
import controller.EmployeeController;
import database.interfaces.CustomerDBIF;
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
	private JButton btnCreateOrder;
	private JButton btnShowOrders;
	private JButton btnLogOut;
	private JLabel lblOrders;
	
	//employee 
	private JLabel lblCreateEmployee;
	private JLabel lblUpdateEmployee; 
	private JLabel lblReadEmployee; 
	private JLabel lblDeleteEmployee;
	private JButton btnCreateEmployee;
	private JButton btnUpdateEmployee;
	private JButton btnReadEmployee; 
	private JButton btnDeleteEmployee;
	private JTextField txtEmployeeEmail;
	private JButton btnNewButton;

	//customer buttons
	private JTextField txtCustomerId;
	private JButton chooseCustomerButton;
	private JLabel lblCreateCustomer; 
	private JLabel lblUpdateCustomer;
	private JLabel lblReadCustomer; 
	private JLabel lblDeleteCustomer;
	private JButton btnCreateCustomer;
	private JButton btnUpdateCustomer;
	private JButton btnReadCustomer; 
	private JButton btnDeleteCustomer;
	
	// Fields for classes created by us
	private AuthenticationController auth;
	private EmployeeController employeeCtrl;
	private Customer customer;
	private Employee employee;
	private JTabbedPane tabbedPane;
	private CustomerController custControl;
	
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
		initCustomerTab();
			
			
		
		
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
		
		chooseCustomerButton = new JButton("Choose customer");
		chooseCustomerButton.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		orderPanel.add(chooseCustomerButton, gbc_btnNewButton);
		
		txtCustomerId = new JTextField();
		txtCustomerId.setFont(new Font("Open Sans", Font.PLAIN, 10));
		txtCustomerId.setText("Customer email");
		GridBagConstraints gbc_txtCustomerEmail = new GridBagConstraints();
		gbc_txtCustomerEmail.gridwidth = 3;
		gbc_txtCustomerEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtCustomerEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCustomerEmail.gridx = 1;
		gbc_txtCustomerEmail.gridy = 2;
		orderPanel.add(txtCustomerId, gbc_txtCustomerEmail);
		txtCustomerId.setColumns(10);
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
			gbc_lblCreateEmployee.gridx = 1;
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
	
	public void initCustomerTab(){

		JPanel customerPanel = new JPanel();
		customerPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		tabsPane.addTab("Customers", null, customerPanel, null);
		GridBagLayout gbl_customerPanel = new GridBagLayout();
		gbl_customerPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_customerPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_customerPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_customerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		customerPanel.setLayout(gbl_customerPanel);
		
		
		chooseCustomerButton = new JButton("Choose customer");
		chooseCustomerButton.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 4;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		customerPanel.add(chooseCustomerButton, gbc_btnNewButton);
		
		txtCustomerId = new JTextField();
		txtCustomerId.setFont(new Font("Open Sans", Font.PLAIN, 10));
		txtCustomerId.setText("Customer email");
		GridBagConstraints gbc_txtCustomerId = new GridBagConstraints();
		gbc_txtCustomerId.gridwidth = 4;
		gbc_txtCustomerId.insets = new Insets(0, 0, 5, 5);
		gbc_txtCustomerId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCustomerId.gridx = 1;
		gbc_txtCustomerId.gridy = 2;
		customerPanel.add(txtCustomerId, gbc_txtCustomerId);
		txtCustomerId.setColumns(10);

		lblCreateCustomer = new JLabel("Create Customer");
		lblCreateCustomer.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblCreateCustomer = new GridBagConstraints();
		gbc_lblCreateCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreateCustomer.gridx = 1;
		gbc_lblCreateCustomer.gridy = 4;
		customerPanel.add(lblCreateCustomer, gbc_lblCreateCustomer);
		
		btnCreateCustomer = new JButton("Create Customer");
		btnCreateCustomer.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnCreateCustomer = new GridBagConstraints();
		gbc_btnCreateCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateCustomer.gridx = 1;
		gbc_btnCreateCustomer.gridy = 5;
		customerPanel.add(btnCreateCustomer, gbc_btnCreateCustomer);
		
		lblUpdateCustomer = new JLabel("Update Customer");
		lblUpdateCustomer.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblUpdateCustomer = new GridBagConstraints();
		gbc_lblUpdateCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpdateCustomer.gridx = 2;
		gbc_lblUpdateCustomer.gridy = 4;
		customerPanel.add(lblUpdateCustomer, gbc_lblUpdateCustomer);
		
		btnUpdateCustomer = new JButton("Update Customer");
		btnUpdateCustomer.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnUpdateCustomer = new GridBagConstraints();
		gbc_btnUpdateCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdateCustomer.gridx = 2;
		gbc_btnUpdateCustomer.gridy = 5;
		customerPanel.add(btnUpdateCustomer, gbc_btnUpdateCustomer);
		
		lblReadCustomer = new JLabel("View Customer Info");
		lblReadCustomer.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblReadCustomer = new GridBagConstraints();
		gbc_lblReadCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_lblReadCustomer.gridx = 3;
		gbc_lblReadCustomer.gridy = 4;
		customerPanel.add(lblReadCustomer, gbc_lblReadCustomer);
		
		btnReadCustomer = new JButton("View Customer Info");
		btnReadCustomer.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnReadCustomer = new GridBagConstraints();
		gbc_btnReadCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_btnReadCustomer.gridx = 3;
		gbc_btnReadCustomer.gridy = 5;
		customerPanel.add(btnReadCustomer, gbc_btnReadCustomer);
		
		lblDeleteCustomer = new JLabel("Delete Customer");
		lblDeleteCustomer.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblDeleteCustomer = new GridBagConstraints();
		gbc_lblDeleteCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeleteCustomer.gridx = 4;
		gbc_lblDeleteCustomer.gridy = 4;
		customerPanel.add(lblDeleteCustomer, gbc_lblDeleteCustomer);
		
		btnDeleteCustomer = new JButton("Delete Customer");
		btnDeleteCustomer.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnDeleteCustomer = new GridBagConstraints();
		gbc_btnDeleteCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteCustomer.gridx = 4;
		gbc_btnDeleteCustomer.gridy = 5;
		customerPanel.add(btnDeleteCustomer, gbc_btnDeleteCustomer);
		
	}
	
	/*
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
					String employeeEmailInput = txtCustomerId.getText();
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
		
		//Delete Employee button
		btnDeleteCustomer.addActionListener(new ActionListener() {
			//TODO: Implement choose employee
			public void actionPerformed(ActionEvent e) { 
				try {
					String employeeEmailInput = txtCustomerId.getText();
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

		//Choose customer for customer ui
		//TODO: add findByEmail methode to controller
		chooseCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					custControl = new CustomerController();
					customer = custControl.findById(Integer.parseInt(txtCustomerId.getText()));
				}catch(SQLException e1){
					e1.printStackTrace();
				}catch(NotFoundException e1){
					e1.printStackTrace();
				}
			}
		});

		//Create customer
		btnCreateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateCustomer frame = new CreateCustomer();
				frame.setVisible(true);
			}
		});
		//Update customer
		btnUpdateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				UpdateCustomer frame = new UpdateCustomer(customer);
				frame.setVisible(true);
			}
		});
		//Read customer
		btnReadCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ReadCustomer frame = new ReadCustomer(customer);
				frame.setVisible(true);
			}
		});
		//Delete customer
		btnDeleteCustomer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					custControl.deleteCustomer(customer);
				}catch(SQLException e1){
					e1.printStackTrace();
				}
			}
		});
	}
	
	
}

