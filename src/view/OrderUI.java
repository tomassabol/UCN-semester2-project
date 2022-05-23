package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.AuthenticationController;
import controller.ItemController;
import controller.OrderController;
import controller.OrderLineController;
import controller.ShelfController;
import exceptions.NotEnoughInStockException;
import exceptions.NotFoundException;
import model.Customer;
import model.Order;
import model.OrderLine;
import model.Product;
import view.tableModel.CreateOrderTableModel;
import view.tableModel.CreateOrderTableModel.Column;

import java.awt.Font;

public class OrderUI extends JFrame {

	private JPanel contentPane;
	private JButton btnEditQuantity;
	private JButton btnRemove;
	private JButton btnClear;
	private JButton btnAddItem;
	private JTable tableMain;
	private JLabel lblTotalValue;
	private JLabel lblSubtotalValue;
	private JButton btnCreateOrder;
	private JTextField txtSearch;
	private JLabel label;
	
	public enum Mode {
		VIEW,
		CREATE
	}
	
	private TableRowSorter<TableModel> rowSorter;
	private CreateOrderTableModel tableModel;
	private AuthenticationController auth;
	Customer customer;
	private Order order;
	private OrderController orderCtrl;
	private ItemController itemCtrl;
	private OrderLineController orderLineCtrl;
	private ShelfController shelfCtrl;
	private int availableQuantity;
	private Mode mode;
	

	/**
	 * Create the frame.
	 */
	public OrderUI(AuthenticationController auth, Customer customer, Order order, Mode mode) {
		this.auth = auth;
		this.mode = mode;
		this.customer = customer;
		this.order = order;
		
		try {
			itemCtrl = new ItemController();
			orderCtrl = new OrderController();
			orderLineCtrl = new OrderLineController();
			orderLineCtrl = new OrderLineController();
			shelfCtrl = new ShelfController();
		} catch (SQLException e2) {
			Messages.error(this, "There was an error connecting to the database");
		}
		
		try {
			tableModel = new CreateOrderTableModel(auth, order, Arrays.asList(
					Column.ID,
					Column.PRODUCT,
					Column.QUANTITY
				    )
		        );
		} catch (SQLException e1) {
			Messages.error(this, "There was an error connecting to the database");
		} catch (NotFoundException e1) {
			Messages.error(this, "There was an error creating the table. Please try again or report the issue!");
		}
		
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
		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		// ***** Title *****
		//JLabel lblTitle = new JLabel(String.format("%s's order", customer.getName()));
		JLabel lblTitle = new JLabel("Order");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 5;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);
		
		label = new JLabel(String.format("Search"));
		label.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 5, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		topPanel.add(label, gbc_label);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.fill = GridBagConstraints.BOTH;
		gbc_txtSearch.anchor = GridBagConstraints.WEST;
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.gridx = 1;
		gbc_txtSearch.gridy = 1;
		topPanel.add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
			
		// ***** Button: clear *****
		btnClear = new JButton("Clear");
		GridBagConstraints gbc_btnTest_2 = new GridBagConstraints();
		gbc_btnTest_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnTest_2.gridx = 3;
		gbc_btnTest_2.gridy = 1;
		topPanel.add(btnClear, gbc_btnTest_2);
					
		// ***** button: Add item  *****
		btnAddItem = new JButton("Add item");
		btnAddItem.setForeground(new Color(255,255,255));
		btnAddItem.setBackground(new Color(143,108,175));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 1;
		topPanel.add(btnAddItem, gbc_btnNewButton);
					
		// ***** Middle panel *****
		JPanel middlePanel = new JPanel();
		getContentPane().add(middlePanel, BorderLayout.CENTER);
		middlePanel.setLayout(new BorderLayout(0, 0));
					
		// ***** Scroll panel *****
		JScrollPane scrollPanel = new JScrollPane();
		middlePanel.add(scrollPanel);
				
		// ***** Table *****
		tableMain = new JTable();
		tableMain.setModel(tableModel);
		tableMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanel.setViewportView(tableMain);
				
		// ***** Bottom panel *****
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
			
		// ***** Panel for table options at the bottom *****
		JPanel tableBottomOptionsPanel = new JPanel();
		bottomPanel.add(tableBottomOptionsPanel, BorderLayout.NORTH);
		GridBagLayout gbl_tableBottomOptionsPanel = new GridBagLayout();
		gbl_tableBottomOptionsPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_tableBottomOptionsPanel.rowHeights = new int[]{0, 0};
		gbl_tableBottomOptionsPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_tableBottomOptionsPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		tableBottomOptionsPanel.setLayout(gbl_tableBottomOptionsPanel);
				
		// ***** Button: edit quantity *****
		btnEditQuantity = new JButton("Edit Quantity");
		btnEditQuantity.setEnabled(false);
		GridBagConstraints gbc_btnEditQuantity = new GridBagConstraints();
		gbc_btnEditQuantity.insets = new Insets(0, 0, 0, 5);
		gbc_btnEditQuantity.gridx = 2;
		gbc_btnEditQuantity.gridy = 0;
		tableBottomOptionsPanel.add(btnEditQuantity, gbc_btnEditQuantity);
					
		// ***** Button: Remove *****
		btnRemove = new JButton("Remove");
		btnRemove.setEnabled(false);
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.gridx = 3;
		gbc_btnRemove.gridy = 0;
		tableBottomOptionsPanel.add(btnRemove, gbc_btnRemove);
		
		// ***** Panel for pricing and create order button *****
		JPanel priceAndSubmitPanel = new JPanel();
		bottomPanel.add(priceAndSubmitPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_priceAndSubmitPanel = new GridBagLayout();
		gbl_priceAndSubmitPanel.columnWidths = new int[]{0, 17, 0, 0, 0};
		gbl_priceAndSubmitPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_priceAndSubmitPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_priceAndSubmitPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		priceAndSubmitPanel.setLayout(gbl_priceAndSubmitPanel);
				
		// ***** Subtotal label *****
		JLabel lblSubtotal = new JLabel("Subtotal:");
		GridBagConstraints gbc_lblSubtotal = new GridBagConstraints();
		gbc_lblSubtotal.anchor = GridBagConstraints.WEST;
		gbc_lblSubtotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubtotal.gridx = 0;
		gbc_lblSubtotal.gridy = 0;
		priceAndSubmitPanel.add(lblSubtotal, gbc_lblSubtotal);
				
		// ***** Subtotal value *****
		lblSubtotalValue = new JLabel();
		lblSubtotalValue.setForeground(new Color(102, 102, 102));
		GridBagConstraints gbc_lblSubtotalValue = new GridBagConstraints();
		gbc_lblSubtotalValue.anchor = GridBagConstraints.WEST;
		gbc_lblSubtotalValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubtotalValue.gridx = 2;
		gbc_lblSubtotalValue.gridy = 0;
		priceAndSubmitPanel.add(lblSubtotalValue, gbc_lblSubtotalValue);
						
		// ***** Total price label *****
		JLabel lblTotal = new JLabel("Total: ");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.anchor = GridBagConstraints.WEST;
		gbc_lblTotal.gridx = 0;
		gbc_lblTotal.gridy = 2;
		priceAndSubmitPanel.add(lblTotal, gbc_lblTotal);
						
		// ***** Total price value *****
		lblTotalValue = new JLabel();
		GridBagConstraints gbc_lblTotalValue = new GridBagConstraints();
		gbc_lblTotalValue.anchor = GridBagConstraints.WEST;
		gbc_lblTotalValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalValue.gridx = 2;
		gbc_lblTotalValue.gridy = 2;
		priceAndSubmitPanel.add(lblTotalValue, gbc_lblTotalValue);
		
		// ***** Create order button *****
		btnCreateOrder = new JButton();
		btnCreateOrder.setForeground(new Color(255,255,255));
		btnCreateOrder.setBackground(new Color(183,26,134,255));
		switch(mode) {
		case CREATE: btnCreateOrder.setText("Create order"); break;
		case VIEW: btnCreateOrder.setText("View Details");
		}
		GridBagConstraints gbc_btnCreateOrder = new GridBagConstraints();
		gbc_btnCreateOrder.anchor = GridBagConstraints.EAST;
		gbc_btnCreateOrder.gridx = 3;
		gbc_btnCreateOrder.gridy = 3;
		priceAndSubmitPanel.add(btnCreateOrder, gbc_btnCreateOrder);
		
		// Add filtering
		rowSorter = new TableRowSorter<TableModel>(tableModel);
		tableMain.setRowSorter(rowSorter);
		
		//Set the buttons to enabled or disabled
		setEnabled();
		
		//Set the price
		refreshPrice();
		
		//Attach event handlers
		addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  COMMON METHODS *******************
	 * *******************************************************
	 */
	
	public void setTableModel(CreateOrderTableModel tableModel) {
		this.tableMain.setModel(tableModel);
		this.tableModel = tableModel;
		// Update table row sorter
		rowSorter = new TableRowSorter<>(tableMain.getModel());
		tableMain.setRowSorter(rowSorter);
		
	}
	
	public void setEnabled() {
		switch(mode) {
			case CREATE: {
				btnEditQuantity.setEnabled(false);
				btnRemove.setEnabled(false);
				break;
			}
			case VIEW: {
				btnEditQuantity.setEnabled(false);
				btnRemove.setEnabled(false);
				btnCreateOrder.setEnabled(true);
				btnAddItem.setEnabled(false);
				btnClear.setEnabled(false);
				break;
			}
		}
	}
	
	/**
	 * recalculates the subtotal and the total price of the order
	 * and updates the labels
	 */
	private void refreshPrice() {
		switch(mode) {
			case CREATE: {
				try {
					lblSubtotalValue.setText(String.format("%.2f EUR", orderCtrl.getOrderPrice(order, true)));
				} catch (SQLException e) {
					Messages.error(this, "There was an error connecting to the database");
				} catch (NotFoundException e) {
					Messages.error(this, "There was an error setting the price. Please try again or report the issue!");
				}
				try {
					lblTotalValue.setText(String.format("%.2f EUR", orderCtrl.getOrderPriceAfterDiscount(order, true)));
				} catch (SQLException e) {
					Messages.error(this, "There was an error connecting to the database");
				} catch (NotFoundException e) {
					Messages.error(this, "There was an error setting the price. Please try again or report the issue!");
				}
				break;
			}
			case VIEW: {
				try {
					lblSubtotalValue.setText(String.format("%.2f EUR", orderCtrl.getOrderPrice(order, false)));
				} catch (SQLException e) {
					Messages.error(this, "There was an error connecting to the database");
				} catch (NotFoundException e) {
					Messages.error(this, "There was an error setting the price. Please try again or report the issue!");
				}
				try {
					lblTotalValue.setText(String.format("%.2f EUR", orderCtrl.getOrderPriceAfterDiscount(order, false)));
				} catch (SQLException e) {
					Messages.error(this, "There was an error connecting to the database");
				} catch (NotFoundException e) {
					Messages.error(this, "There was an error setting the price. Please try again or report the issue!");
				}
				break;
			}
		}
	}
	
	/**
	 * The action performed when clicking the add item button
	 */
	private void addProduct() {
		ChooseProduct frame = null;
		try {
			frame = new ChooseProduct(auth);
		} catch (SQLException e) {
			Messages.error(frame, "There was an error connecting to the database");
		} catch (NotFoundException e) {
			Messages.error(this, "The window could not be opened. Please try again or report the issue!");//Messages.error(frame, "Ok");
		}
		frame.setVisible(true);
		refreshPrice();
		
		if (frame.isProductSelected()) {
			Product product = frame.getSelectedProduct();
			int quantity = frame.getSelectedQuantity();
			if(quantity < 0) {
				Messages.error(frame, "You cannot give a negative number as quantity");
				return;
			} else if (product.isActive() == false) {
				Messages.error(this, "You cannot select disabled product");
				return;
			} else {
			try {
				if (quantity > shelfCtrl.productQuantityPerDepartment(auth.getLoggedInUser().getDepartment(), product)) {
					Messages.error(frame, "There is not enouth items in stock");
					return;
				}
			} catch (SQLException e) {
				Messages.error(frame, "There was an error connecting to the database");
			} catch (NotFoundException e) {
				Messages.error(frame, String.format("There is not enough items in stock from %s", product.getName()));
			}}
			// add to order
			try {
				try {
					tableModel.add(product, quantity);
					setTableModel(tableModel);
				} catch (SQLException e) {
					Messages.error(frame, "There was an error connecting to the database");
					this.addProduct();
				} catch (NotFoundException e) {
					Messages.error(frame, "The selected product was not found in the database");
					this.addProduct();
				}
			} catch (NotEnoughInStockException e) {
				Messages.error(frame, String.format("There is not enough items in stock from %s", product.getName()));
				// Repeat the whole thing again
				this.addProduct();
			}
			refreshPrice();
		}
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	public void addEventHandlers() {
		//Refreshes the price labels whenever there is a change in the table
		tableMain.getModel().addTableModelListener(e -> {
			this.refreshPrice();
		});
		
		// toggle bottom table buttons depending on whether a table row is selected
		tableMain.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				switch(mode) {
					case CREATE: {
						if (tableMain.getSelectionModel().isSelectionEmpty()) {
							// NOT SELECTED
							btnEditQuantity.setEnabled(false);
							btnRemove.setEnabled(false);
						} else {
							// SELECTED
							btnEditQuantity.setEnabled(true);
							btnRemove.setEnabled(true);
						}
						break;
					}
					case VIEW: {
						btnEditQuantity.setEnabled(false);
						btnRemove.setEnabled(false);
						btnCreateOrder.setEnabled(true);
						btnAddItem.setEnabled(false);
						btnClear.setEnabled(false);
					}
				}
			}
		});
		
		// Action for add item button
		btnAddItem.addActionListener(e -> {
				addProduct();
		});
		
		// Action for createOrder button
		btnCreateOrder.addActionListener(e -> {
			
			switch(mode) {
				case CREATE: {
					if(Messages.confirm(this, "Do you want to finalize the order?")) {
							try {
								if(!order.getOrderLines().isEmpty()) {
									orderCtrl.finishOrder(order, auth.getLoggedInUser().getDepartment());
									this.dispose();
								}else {
									Messages.error(contentPane, "You can not create an empty order");
									return;
								}
							} catch (SQLException e1) {
								Messages.error(this, "There was an error connecting to the database");
							} catch (NotFoundException e1) {
								Messages.error(this, "There was an error finding the created order");
							} catch (NotEnoughInStockException e1) {
								Messages.error(this, "There is not enough items in stock");
							}							
					}
					break;
				}
				case VIEW: {
					ViewOrderDetails frame = new ViewOrderDetails(auth, order);
					frame.setVisible(true);
					break;
				}
			}
		});
		
		/**
		 * TODO: Try to simplify it later (it should work as it is, but it looks way too complicated)
		 * Action for editQuantity button
		 */
		btnEditQuantity.addActionListener(e -> {	
			//Get the selected row
			int row = tableMain.getSelectedRow();
			
			//Get the orderLine from the selected row
			OrderLine orderLine = tableModel.getOrderLine(row); 
			
			//Get the product from the orderLine
			Product product = orderLine.getProduct();
			
			//Get the available quantity from the product
			try {
				availableQuantity = itemCtrl.findAllPerProduct(product).size();
			} catch (SQLException e1) {
				Messages.error(this, "There was an error connecting to the database");
			} catch (NotFoundException e1) {
				Messages.error(this, "There was an error getting the available quantity from the product. Please try again or report the issue!");
			}
				
			//Get the original quantity of the orderLine
			int quantity = orderLine.getQuantity();
			
			//Create the spinner which makes the edit quantity look better
			SpinnerNumberModel spinnerModel = new SpinnerNumberModel(quantity, 1, availableQuantity, 1);
			JSpinner spinner = new JSpinner(spinnerModel);
				
			//Create the window where you can edit the quantity
			int option = JOptionPane.showInternalConfirmDialog(contentPane, spinner, "Edit quantity", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if(option == JOptionPane.OK_OPTION) {
				//Get the new quantity for the product from the spinner
				int newQuantity = 0;
				try {
					newQuantity = Integer.parseInt(String.valueOf(spinner.getValue()));
				} catch (NumberFormatException e1){
					Messages.error(this, "The given value is not a number");
				}
				
				//Set the new quantity for the product
				orderLine.setQuantity(newQuantity);
				try {
					orderLineCtrl.updateOrderLine(orderLine);
				} catch (SQLException e1) {
					Messages.error(this, "There was an error connecting to the database");
				}
			}
			refreshPrice();
		});
		
		//Action for remove button
		btnRemove.addActionListener(e -> {
			int row = tableMain.getSelectedRow();
			try {
				tableModel.remove(row);
			} catch (SQLException e1) {
				Messages.error(this, "There was an error connecting to the database");
			}
			refreshPrice();
		});
		
		//Action for clear button
		btnClear.addActionListener(e -> {
			if(Messages.confirm(this, "Do you want to clear the order?")) {
				try {
					tableModel.clear(order);
				} catch (SQLException e1) {
					Messages.error(this, "There was an error connecting to the database");
				} catch (NotFoundException e1) {
					Messages.error(this, "There was an error clearing the table. Please try again or report the issue!");
				}	
				refreshPrice();
			}
		});
		
		// Search implementation
		txtSearch.getDocument().addDocumentListener(new DocumentListener(){
					
			private void search() {
				String text = txtSearch.getText();
				if(text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text)));
				}
			}
															
			@Override
			public void insertUpdate(DocumentEvent e) {
				search();
			}
			
			@Override
			public void  removeUpdate(DocumentEvent e) {
				search();
			}
															
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
	

}
