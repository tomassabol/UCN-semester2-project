package view;

import java.awt.BorderLayout;
import java.awt.Button;
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
import controller.OrderController;
import model.Authentication;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Dashboard extends JFrame {

	// Fields for gui
	private JPanel contentPane;
	private JTabbedPane tabsPane;
	private Component lblGreeting;
	private JLabel lblCreateOrder;
	private JButton btnCreateOrder;
	private JLabel lblOrders;
	private JButton btnShowOrders;
	private JButton btnLogOut;

	// Fields for classes created by us
	private AuthenticationController authCtrl;
	
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Dashboard(Authentication authentication) throws SQLException {
		 authCtrl = new AuthenticationController();
		// Window
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
			gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
			gbl_topPanel.rowHeights = new int[]{0, 0};
			gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_topPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			topPanel.setLayout(gbl_topPanel);
				
				// ***** Greeting label *****
				lblGreeting = new JLabel("Hi, " + authCtrl.getLoggedInUser());
				GridBagConstraints gbc_lblGreeting = new GridBagConstraints();
				gbc_lblGreeting.insets = new Insets(0, 0, 0, 5);
				gbc_lblGreeting.gridx = 0;
				gbc_lblGreeting.gridy = 0;
				topPanel.add(lblGreeting, gbc_lblGreeting);
				
				btnLogOut = new JButton("Log out");
				btnLogOut.setFont(new Font("Open Sans", Font.PLAIN, 10));
				GridBagConstraints gbc_btnLogOut = new GridBagConstraints();
				gbc_btnLogOut.gridx = 3;
				gbc_btnLogOut.gridy = 0;
				topPanel.add(btnLogOut, gbc_btnLogOut);
		
			// ***** Tabs pane *****
			tabsPane = new JTabbedPane(JTabbedPane.TOP);
			contentPane.add(tabsPane, BorderLayout.CENTER);
			
			//// Order tab /////
			initOrderTab();
			
			////
			
			
			//Adding the event handlers to the code
			addEventHandlers();
	}
	
	///////////////////////////////////////////
	//// Methods ////
	
	public void initOrderTab() {
		////////////// Copy this to create a new Tab (delete this comment after all the tabs are done) /////////////////////////
		JPanel orderPanel = new JPanel();
		orderPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		tabsPane.addTab("Orders", null, orderPanel, null);
		GridBagLayout gbl_orderPanel = new GridBagLayout();
		gbl_orderPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_orderPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_orderPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_orderPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		orderPanel.setLayout(gbl_orderPanel);
		///////////////////////////////////////////////////////////////////////
		
		lblCreateOrder = new JLabel("Create Order");
		lblCreateOrder.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblCreateOrder = new GridBagConstraints();
		gbc_lblCreateOrder.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreateOrder.gridx = 4;
		gbc_lblCreateOrder.gridy = 9;
		orderPanel.add(lblCreateOrder, gbc_lblCreateOrder);
		
		lblOrders = new JLabel("Show Orders");
		lblOrders.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblOrders = new GridBagConstraints();
		gbc_lblOrders.insets = new Insets(0, 0, 5, 0);
		gbc_lblOrders.gridx = 15;
		gbc_lblOrders.gridy = 9;
		orderPanel.add(lblOrders, gbc_lblOrders);
		
		btnCreateOrder = new JButton("Create Order");
		btnCreateOrder.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnCreateOrder = new GridBagConstraints();
		gbc_btnCreateOrder.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreateOrder.gridx = 4;
		gbc_btnCreateOrder.gridy = 10;
		orderPanel.add(btnCreateOrder, gbc_btnCreateOrder);
		
		btnShowOrders = new JButton("Show Orders");
		GridBagConstraints gbc_btnShowOrders = new GridBagConstraints();
		gbc_btnShowOrders.gridx = 15;
		gbc_btnShowOrders.gridy = 10;
		orderPanel.add(btnShowOrders, gbc_btnShowOrders);
	}
	
	public void addEventHandlers() {
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Ask for confirmation
				authCtrl.logout();
			}
		});
	}

}

