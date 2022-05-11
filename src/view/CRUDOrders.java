package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import exceptions.NotFoundException;
import model.City;
import model.Customer;
import model.Order;
import view.JLink.COLORS;
import view.tableModel.OrdersTableModel;
import view.tableModel.OrdersTableModel.Column;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CRUDOrders extends JFrame {

	private JLabel label;
	private JTextField txtSearch;
	private JButton btnAddOrder;
	private JTable tableMain;
	private JLink btnView;
	private JLink btnRemove;
	private JPanel contentPane;
	
	public enum Mode {
		CUSTOMER,
		ALL
	}
	
	private OrdersTableModel tableModel;
	private AuthenticationController auth;
	private Customer customer;



	/**
	 * Create the frame.
	 */
	public CRUDOrders(AuthenticationController auth, Customer customer, Mode mode) {
		this.auth = auth;
		this.customer = customer;
		switch(mode) {
			case CUSTOMER: {
				try {
					tableModel = new OrdersTableModel(auth,	Arrays.asList(Column.ID, Column.CUSTOMER, Column.DATE), customer);
				} catch (SQLException e) {
					Messages.error(this, "There was an error connecting to the database");
				} catch (NotFoundException e) {
					Messages.error(this, "There was an error finding the choosen customer in the database");
				}			
			}
			case ALL: {
				try {
					tableModel = new OrdersTableModel(auth, Arrays.asList(Column.ID, Column.CUSTOMER, Column.DATE), null);
				} catch (SQLException e) {
					Messages.error(this, "There was an error connecting to the database");
				} catch (NotFoundException e) {
					Messages.error(this, "There was an error finding the customers");
				}
			}
		}
		
		// ***** Window *****
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
		gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		// ***** Title *****
		JLabel lblTitle = new JLabel(
			String.format("Orders")
		);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);
		
		label = new JLabel(
			String.format("Search")
		);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		topPanel.add(label, gbc_label);
		
		txtSearch = new JTextField();
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.gridx = 0;
		gbc_txtSearch.gridy = 1;
		topPanel.add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
		
		// ***** button: Add order *****
		btnAddOrder = new JButton("Add a new order");
		GridBagConstraints gbc_btnAddCity = new GridBagConstraints();
		gbc_btnAddCity.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddCity.gridx = 2;
		gbc_btnAddCity.gridy = 1;
		topPanel.add(btnAddOrder, gbc_btnAddCity);
		
		// ***** Middle panel: Scroll panel *****
		JScrollPane scrollPanel = new JScrollPane();
		getContentPane().add(scrollPanel, BorderLayout.CENTER);
		
		// ***** Table *****
		tableMain = new JTable();
		tableMain.setModel(tableModel);
		tableMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel.setViewportView(tableMain);
		
		// ***** Bottom panel *****
		JPanel bottomPanel = new JPanel();
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottomPanel = new GridBagLayout();
		gbl_bottomPanel.columnWidths = new int[]{271, 0, 0, 0, 0};
		gbl_bottomPanel.rowHeights = new int[]{21, 0};
		gbl_bottomPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bottomPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		bottomPanel.setLayout(gbl_bottomPanel);
		
		// ***** View button *****
		btnView = new JLink("View", COLORS.GREEN);
		GridBagConstraints gbc_btnView = new GridBagConstraints();
		gbc_btnView.insets = new Insets(0, 0, 0, 5);
		gbc_btnView.gridx = 1;
		gbc_btnView.gridy = 0;
		bottomPanel.add(btnView, gbc_btnView);
		
		// ***** Remove button *****
		btnRemove = new JLink("Disable", COLORS.RED);
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.gridx = 3;
		gbc_btnRemove.gridy = 0;
		bottomPanel.add(btnRemove, gbc_btnRemove);
		
		// By default: all selection buttons disabled
		btnView.setEnabled(false);
		btnRemove.setEnabled(false);
		
		//Attach event handlers
		addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	public void addEventHandlers() {
		// Table row selection
		tableMain.getSelectionModel().addListSelectionListener(e -> {
			if (tableMain.getSelectionModel().isSelectionEmpty()) {
				// Not selected
				btnView.setEnabled(false);
				btnRemove.setEnabled(false);
			} else {
				// Selected
				int row = tableMain.getSelectedRow();
				tableModel.getObj(row);
				btnView.setEnabled(true);
				btnRemove.setEnabled(false);
			}
		});
		
		// View city
		btnView.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			Order order = tableModel.getObj(row);
			OrderUI frame;
			frame = new OrderUI(auth, customer, order, OrderUI.Mode.VIEW);
			frame.setVisible(true);
		});
	}

}