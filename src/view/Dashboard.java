package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;


import controller.AuthenticationController;
import controller.OrderController;
import exceptions.NotFoundException;
import model.Customer;
import model.Order;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class Dashboard extends JFrame {

	// Fields for gui
	private JPanel contentPane;
	private JTabbedPane tabsPane;
	private Component lblGreeting;
	private JLabel lblCreateOrder;
	private JLabel lblDepartment; // department
	private JLabel lblCities; // cities
	private JLabel lblProduct; // product
	private JLabel lblCustomer; // customer
	private JLabel lblEmployee; // employee
	private JLabel lblSupplier;
	private JLabel lblSupplyOrder;//supplyOrder
	private JLabel lblShelf;//Shelf
	private JButton btnCreateOrder;
	private JButton btnDepartment; // department
	private JButton btnCities; // cities
	private JButton btnProduct; // product
	private JButton btnCustomer; // customer
	private JButton btnEmployee; // employee
	private JButton btnSupplier;
	private JButton btnShowCustomerOrders;
	private JButton btnLogOut;
	private JButton btnChooseCustomer;
	private JTextField txtCustomerEmail;

	// Fields for classes created by us
	private AuthenticationController auth;
	private Customer customer;
	private OrderController orderCtrl;
	private Order order;
	private JLabel lblAllOrders;
	private JButton btnShowAllOrders;
	private JLabel lblOrders;
	private JButton btnReportBug;
	private JButton btnSupplyOrder;
	private JButton btnShelf;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Dashboard(AuthenticationController authentication) throws SQLException {
		auth = authentication;
		orderCtrl = new OrderController();

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
		gbl_topPanel.columnWidths = new int[]{143, 0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		btnReportBug = new JButton("Report issue");
		btnReportBug.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnReportBug = new GridBagConstraints();
		gbc_btnReportBug.insets = new Insets(0, 0, 0, 5);
		gbc_btnReportBug.gridx = 2;
		gbc_btnReportBug.gridy = 0;
		topPanel.add(btnReportBug, gbc_btnReportBug);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnLogOut = new GridBagConstraints();
		gbc_btnLogOut.gridx = 3;
		gbc_btnLogOut.gridy = 0;
		topPanel.add(btnLogOut, gbc_btnLogOut);
			
		// ***** Tabs pane *****
		tabsPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabsPane, BorderLayout.CENTER);
		
		// ***** Order tab *****
		initOrderTab();
		initLocationTab(); // locations
		initStorageTab(); // storage
		initPeopleTab(); // people
			
			
		
		
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
		gbl_orderPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_orderPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_orderPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_orderPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		orderPanel.setLayout(gbl_orderPanel);
		
		btnChooseCustomer = new JButton("Choose customer");
		btnChooseCustomer.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnChooseCustomer = new GridBagConstraints();
		gbc_btnChooseCustomer.gridwidth = 5;
		gbc_btnChooseCustomer.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnChooseCustomer.insets = new Insets(0, 0, 5, 0);
		gbc_btnChooseCustomer.gridx = 1;
		gbc_btnChooseCustomer.gridy = 1;
		orderPanel.add(btnChooseCustomer, gbc_btnChooseCustomer);
		
		txtCustomerEmail = new JTextField();
		txtCustomerEmail.setFont(new Font("Open Sans", Font.PLAIN, 10));
		//txtCustomerEmail.setText("Customer email");
		GridBagConstraints gbc_txtCustomerEmail = new GridBagConstraints();
		gbc_txtCustomerEmail.gridwidth = 5;
		gbc_txtCustomerEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtCustomerEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCustomerEmail.gridx = 1;
		gbc_txtCustomerEmail.gridy = 2;
		orderPanel.add(txtCustomerEmail, gbc_txtCustomerEmail);
		txtCustomerEmail.setColumns(10);
		///////////////////////////////////////////////////////////////////////
		
		lblCreateOrder = new JLabel();
		ImageIcon createOrderIcon = new ImageIcon("images/CreateOrder.png");
		lblCreateOrder.setIcon(createOrderIcon);
		lblCreateOrder.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblCreateOrder = new GridBagConstraints();
		gbc_lblCreateOrder.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreateOrder.gridx = 1;
		gbc_lblCreateOrder.gridy = 4;
		orderPanel.add(lblCreateOrder, gbc_lblCreateOrder);
		
		lblAllOrders = new JLabel();
		ImageIcon allOrders = new ImageIcon("images/Orders.png");
		lblAllOrders.setIcon(allOrders);
		GridBagConstraints gbc_lblAllOrders = new GridBagConstraints();
		gbc_lblAllOrders.insets = new Insets(0, 0, 5, 5);
		gbc_lblAllOrders.gridx = 5;
		gbc_lblAllOrders.gridy = 4;
		orderPanel.add(lblAllOrders, gbc_lblAllOrders);
		
		lblOrders = new JLabel();
		ImageIcon customerOrders = new ImageIcon("images/customerOrders.png");
		lblOrders.setIcon(customerOrders);
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
		
		btnShowCustomerOrders = new JButton("Show Customer Orders");
		btnShowCustomerOrders.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnShowCustomerOrders = new GridBagConstraints();
		gbc_btnShowCustomerOrders.insets = new Insets(0, 0, 5, 5);
		gbc_btnShowCustomerOrders.gridx = 3;
		gbc_btnShowCustomerOrders.gridy = 5;
		orderPanel.add(btnShowCustomerOrders, gbc_btnShowCustomerOrders);
		
		btnShowAllOrders = new JButton("Show All Orders");
		btnShowAllOrders.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnShowAllOrders = new GridBagConstraints();
		gbc_btnShowAllOrders.insets = new Insets(0, 0, 5, 5);
		gbc_btnShowAllOrders.gridx = 5;
		gbc_btnShowAllOrders.gridy = 5;
		orderPanel.add(btnShowAllOrders, gbc_btnShowAllOrders);

	}
	
	// locations
	public void initLocationTab() {
		JPanel locationPanel = new JPanel();
		locationPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		tabsPane.addTab("Locations", null, locationPanel, null);
		GridBagLayout gbl_locationPanel = new GridBagLayout();
		gbl_locationPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_locationPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_locationPanel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_locationPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		locationPanel.setLayout(gbl_locationPanel);

		btnDepartment = new JButton("Departments");
		btnDepartment.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnDepartment = new GridBagConstraints();
		gbc_btnDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_btnDepartment.gridx = 1;
		gbc_btnDepartment.gridy = 5;
		locationPanel.add(btnDepartment, gbc_btnDepartment);
				
		lblDepartment = new JLabel();
		ImageIcon departmentIcon = new ImageIcon("images/Department.png");
		lblDepartment.setIcon(departmentIcon);
		lblDepartment.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 1;
		gbc_lblDepartment.gridy = 4;
		locationPanel.add(lblDepartment, gbc_lblDepartment);
		
		lblCities = new JLabel();
		ImageIcon cityIcon = new ImageIcon("images/Cities.png");
		lblCities.setIcon(cityIcon);
		lblCities.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblCities = new GridBagConstraints();
		gbc_lblCities.insets = new Insets(0, 1, 5, 5);
		gbc_lblCities.gridx = 3;
		gbc_lblCities.gridy = 4;
		locationPanel.add(lblCities, gbc_lblCities);
		
		btnCities = new JButton("Cities");
		btnCities.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnCities = new GridBagConstraints();
		gbc_btnCities.insets = new Insets(0, 1, 5, 5);
		gbc_btnCities.gridx = 3;
		gbc_btnCities.gridy = 5;
		locationPanel.add(btnCities, gbc_btnCities);
		
	}

	// storage
	public void initStorageTab() {
		JPanel storagePanel = new JPanel();
		storagePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		tabsPane.addTab("Storage", null, storagePanel, null);
		GridBagLayout gbl_storagePanel = new GridBagLayout();
		gbl_storagePanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_storagePanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_storagePanel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_storagePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		storagePanel.setLayout(gbl_storagePanel);
		
		lblProduct = new JLabel();
		ImageIcon productIcon = new ImageIcon("images/product.png");
		lblProduct.setIcon(productIcon);
		lblProduct.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.gridx = 1;
		gbc_lblProduct.gridy = 4;
		storagePanel.add(lblProduct, gbc_lblProduct);
		
		btnProduct = new JButton("Products");
		btnProduct.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnProduct = new GridBagConstraints();
		gbc_btnProduct.insets = new Insets(0, 0, 5, 5);
		gbc_btnProduct.gridx = 1;
		gbc_btnProduct.gridy = 5;
		storagePanel.add(btnProduct, gbc_btnProduct);
		
		lblShelf = new JLabel();
		ImageIcon shelfIcon = new ImageIcon("images/shelf.png");
		lblShelf.setIcon(shelfIcon);
		lblShelf.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblShelf = new GridBagConstraints();
		gbc_lblShelf.insets = new Insets(0, 0, 5, 5);
		gbc_lblShelf.gridx = 2;
		gbc_lblShelf.gridy = 4;
		storagePanel.add(lblShelf, gbc_lblShelf);
		
		btnShelf = new JButton("Shelf");
		btnShelf.setFont(new Font("Dialog", Font.PLAIN, 10));
		GridBagConstraints gbc_btnShelf = new GridBagConstraints();
		gbc_btnShelf.insets = new Insets(0, 0, 5, 5);
		gbc_btnShelf.gridx = 2;
		gbc_btnShelf.gridy = 5;
		storagePanel.add(btnShelf, gbc_btnShelf);
		
		lblSupplyOrder = new JLabel();
		ImageIcon supplyOrderIcon = new ImageIcon("images/SupplyOrder.png");
		lblSupplyOrder.setIcon(supplyOrderIcon);
		lblSupplyOrder.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblSupplyOrder = new GridBagConstraints();
		gbc_lblSupplyOrder.insets = new Insets(0, 0, 5, 5);
		gbc_lblSupplyOrder.gridx = 3;
		gbc_lblSupplyOrder.gridy = 4;
		storagePanel.add(lblSupplyOrder, gbc_lblSupplyOrder);
		

		btnSupplyOrder = new JButton("SupplyOrder");
		btnSupplyOrder.setFont(new Font("Dialog", Font.PLAIN, 10));
		GridBagConstraints gbc_btnSupplyOrder = new GridBagConstraints();
		gbc_btnSupplyOrder.insets = new Insets(0, 0, 5, 5);
		gbc_btnSupplyOrder.gridx = 3;
		gbc_btnSupplyOrder.gridy = 5;
		storagePanel.add(btnSupplyOrder, gbc_btnSupplyOrder);
	}

	// people
	public void initPeopleTab() {
		JPanel peoplePanel = new JPanel();
		peoplePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		tabsPane.addTab("People", null, peoplePanel, null);
		GridBagLayout gbl_peoplePanel = new GridBagLayout();
		gbl_peoplePanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_peoplePanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_peoplePanel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_peoplePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		peoplePanel.setLayout(gbl_peoplePanel);

		lblCustomer = new JLabel();
		ImageIcon customerIcon = new ImageIcon("images/customer.png");
		lblCustomer.setIcon(customerIcon);
		lblCustomer.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblCustomert = new GridBagConstraints();
		gbc_lblCustomert.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomert.gridx = 1;
		gbc_lblCustomert.gridy = 4;
		peoplePanel.add(lblCustomer, gbc_lblCustomert);

		btnCustomer = new JButton("Customers");
		btnCustomer.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnCustomer = new GridBagConstraints();
		gbc_btnCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_btnCustomer.gridx = 1;
		gbc_btnCustomer.gridy = 5;
		peoplePanel.add(btnCustomer, gbc_btnCustomer);
		
		lblEmployee = new JLabel();
		ImageIcon employeeIcon = new ImageIcon("images/employee.png");
		lblEmployee.setIcon(employeeIcon);
		lblEmployee.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblEmployee = new GridBagConstraints();
		gbc_lblEmployee.insets = new Insets(0, 1, 5, 5);
		gbc_lblEmployee.gridx = 2;
		gbc_lblEmployee.gridy = 4;
		peoplePanel.add(lblEmployee, gbc_lblEmployee);
		
		btnEmployee = new JButton("Employees");
		btnEmployee.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnEmployee = new GridBagConstraints();
		gbc_btnEmployee.insets = new Insets(0, 1, 5, 5);
		gbc_btnEmployee.gridx = 2;
		gbc_btnEmployee.gridy = 5;
		peoplePanel.add(btnEmployee, gbc_btnEmployee);
		
		lblSupplier = new JLabel();
		ImageIcon supplierIcon = new ImageIcon("images/supplier.png");
		lblSupplier.setIcon(supplierIcon);
		lblSupplier.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblSupplier = new GridBagConstraints();
		gbc_lblSupplier.insets = new Insets(0, 1, 5, 5);
		gbc_lblSupplier.gridx = 3;
		gbc_lblSupplier.gridy = 4;
		peoplePanel.add(lblSupplier, gbc_lblSupplier);
		
		btnSupplier = new JButton("Suppliers");
		btnSupplier.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnSupplier = new GridBagConstraints();
		gbc_btnSupplier.insets = new Insets(0, 1, 5, 5);
		gbc_btnSupplier.gridx = 3;
		gbc_btnSupplier.gridy = 5;
		peoplePanel.add(btnSupplier, gbc_btnSupplier);
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

		btnChooseCustomer.addActionListener(e-> {
			try {
				ChooseCustomer frame;
				frame = new ChooseCustomer(auth);
				frame.setVisible(true);
				if (frame.isCustomerSelected() == true) {
					customer = frame.getSelectedCustomer();
				}
				txtCustomerEmail.setText(customer.getId() + " " + customer.getName());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		//Create order button
		btnCreateOrder.addActionListener(e-> {
			
			if(customer == null) {
				Messages.error(this, "You need to choose a customer in order to open this window");
			}else {
				try {
					order = orderCtrl.createOrder(auth.getLoggedInUser(), customer);
				} catch (SQLException e1) {
					Messages.error(contentPane, "There was an error connecting to the database");
				} catch (NotFoundException e1) {
					Messages.error(contentPane, "The order was not created for some reason");
				}
				OrderUI frame = new OrderUI(auth, customer, order, OrderUI.Mode.CREATE);
				frame.setVisible(true);
			}
		});
		
		//Shows the orders for a specific customer
		btnShowCustomerOrders.addActionListener(e -> {
			if(customer == null) {
				Messages.error(this, "You need to choose a customer in order to open this window");
			}else {
				CRUDOrders frame = new CRUDOrders(auth, customer, CRUDOrders.Mode.CUSTOMER);
				frame.setVisible(true);
			}
		});
		
		btnShowAllOrders.addActionListener(e -> {
			CRUDOrders frame = new CRUDOrders(auth, null, CRUDOrders.Mode.ALL);
			frame.setVisible(true);
		});

		btnDepartment.addActionListener(e -> {
			ManageDepartments frame;
			try {
				frame = new ManageDepartments(auth);
				frame.setVisible(true);
			} catch (SQLException | NotFoundException e1) {
				e1.printStackTrace();
			}
		});

		btnProduct.addActionListener(e -> {
			ManageProducts frame;
			try {
				frame = new ManageProducts(auth);
				frame.setVisible(true);
			} catch (SQLException | NotFoundException e1) {
				e1.printStackTrace();
			}
		});

		btnCities.addActionListener(e -> {
			ManageCities frame;
			try {
				frame = new ManageCities(auth);
				frame.setVisible(true);
			} catch (SQLException | NotFoundException e1) {
				e1.printStackTrace();
			}
		
		});

		btnCustomer.addActionListener(e -> {
			ManageCustomer frame;
			try {
				frame = new ManageCustomer(auth);
				frame.setVisible(true);
			} catch (SQLException | NotFoundException e1) {
				e1.printStackTrace();
			}
		});

		btnEmployee.addActionListener(e -> {
			ManageEmployees frame;
			try {
				frame = new ManageEmployees(auth);
				frame.setVisible(true);
			} catch (SQLException | NotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		btnSupplier.addActionListener(e -> {
			ManageSupplier frame;
			try {
				frame = new ManageSupplier(auth);
				frame.setVisible(true);
			} catch(SQLException | NotFoundException e1) {
				e1.printStackTrace();
			}
		});

		btnReportBug.addActionListener(e -> {
			ReportBugUI frame;
			frame = new ReportBugUI(auth);
			frame.setVisible(true);
		});
		
		btnShelf.addActionListener(e -> {
			ManageShelf frame;
			try {
				frame = new ManageShelf(auth);
				frame.setVisible(true);
			} catch (SQLException |NotFoundException e1) {
				
				e1.printStackTrace();
			}
			
		});
		
		btnSupplyOrder.addActionListener(e ->{
			ManageSupplyOrder frame;
			try {
				frame = new ManageSupplyOrder(auth);
				frame.setVisible(true);
				
			}catch(SQLException|NotFoundException e1){
				e1.printStackTrace();
			}
		});
	}

}
