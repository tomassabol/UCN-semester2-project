package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import controller.AuthenticationController;
import controller.OrderController;
import gui.Dashboard;
import gui.JLink;
import gui.Login;
import gui.Messages;
import gui.statistics.charts.OrdersChart;
import gui.windows.ChooseCustomer;
import gui.windows.ManageContractors;
import gui.windows.ManageCustomerTypes;
import gui.windows.ManageCustomers;
import gui.windows.ManageEmployees;
import gui.windows.ManageLoans;
import gui.windows.ManageOrders;
import gui.windows.ManageProducts;
import gui.windows.ManageQuotes;
import gui.windows.ManageShelves;
import gui.windows.ManageShoppingCarts;
import gui.windows.ManageStorageLocations;
import gui.windows.ManageSupplyOffers;
import gui.windows.ManageSupplyOrders;
import models.Customer;
import models.Order;
import java.awt.Font;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabsPane;
	private Component btnLogout;
	private Component lblGreeting;
	private JLabel lblCreateOrder;
	private JButton btnCreateOrder;
	private JLabel lblOrders;
	private JButton btnShowOrders;

	/**
	 * Create the frame.
	 */
	public Dashboard() {
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
			gbl_topPanel.rowHeights = new int[]{0, 0, 0};
			gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_topPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			topPanel.setLayout(gbl_topPanel);
				
				// // ***** Greeting label *****
				lblGreeting = new JLabel("Hi,");
				GridBagConstraints gbc_lblGreeting = new GridBagConstraints();
				gbc_lblGreeting.insets = new Insets(0, 0, 5, 5);
				gbc_lblGreeting.gridx = 0;
				gbc_lblGreeting.gridy = 0;
				topPanel.add(lblGreeting, gbc_lblGreeting);
		
				// // ***** Log out button *****
				//btnLogout = new JLink("Log out");
				//GridBagConstraints gbc_lblLogout = new GridBagConstraints();
				//gbc_lblLogout.insets = new Insets(0, 0, 5, 0);
				//gbc_lblLogout.gridx = 3;
				//gbc_lblLogout.gridy = 0;
				//topPanel.add(btnLogout, gbc_lblLogout);
		
			// ***** Tabs pane *****
			tabsPane = new JTabbedPane(JTabbedPane.TOP);
			contentPane.add(tabsPane, BorderLayout.CENTER);
			
			initOrderTab();
			
			
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
	
	

}

