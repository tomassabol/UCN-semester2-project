package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.AuthenticationController;
import controller.ItemController;
import controller.OrderController;
import controller.OrderLineController;
import exceptions.NotFoundException;
import model.Customer;
import model.Order;
import model.OrderLine;
import model.Product;
import view.tableModel.CreateOrderTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateOrder extends JFrame {

	private JPanel contentPane;
	private JButton btnEditQuantity;
	private JButton btnRemove;
	private JButton btnClear;
	private JButton btnAddItem;
	private JTable tableMain;
	private JButton btnCreateQuote;
	private JLabel lblTotalValue;
	private JComponent lblDiscountValue;
	private JLabel lblSubtotalValue;
	private JButton btnCreateOrder;
	
	private CreateOrderTableModel tableModel;
	private AuthenticationController auth;
	private Customer customer;
	private Order order;
	private OrderController orderCtrl;
	private ItemController itemCtrl;
	private OrderLineController orderLineCtrl;

	/**
	 * Create the frame.
	 */
	public CreateOrder(AuthenticationController auth, Customer customer, Order order) {
		this.auth = auth;
		this.customer = customer;
		
		try {
			itemCtrl = new ItemController();
		} catch (SQLException e2) {
			Messages.error(contentPane, "There was an error connecting to the database");
		}
		
		try {
			orderCtrl = new OrderController();
		} catch (SQLException e1) {
			Messages.error(contentPane, "There was an error connecting to the database");
		}
		
		try {
			orderLineCtrl = new OrderLineController();
		} catch (SQLException e1) {
			Messages.error(contentPane, "There was an error connecting to the database");
		}
		
		if(order == null) {	
			try {
				this.order = orderCtrl.createOrder(this.auth.getLoggedInUser(), this.customer);
			} catch (SQLException e1) {
				Messages.error(contentPane, "There was an error connecting to the database");
			} catch (NotFoundException e1) {
				Messages.error(contentPane, "The order was not found in the database");
			}
		}else {
			this.order = order;
		}
		
		// ***** WINDOW *****
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		JLabel lblTitle = new JLabel(String.format("%s's order", customer.getName()));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 3;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);
			
		// ***** Button: add item *****
		btnClear = new JButton("Clear");
		GridBagConstraints gbc_btnTest_2 = new GridBagConstraints();
		gbc_btnTest_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnTest_2.gridx = 1;
		gbc_btnTest_2.gridy = 1;
		topPanel.add(btnClear, gbc_btnTest_2);
					
		// ***** button: clear  *****
		btnAddItem = new JButton("Add item");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 2;
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
		try {
			tableModel = new CreateOrderTableModel(this.auth, this.customer, order);
		} catch (SQLException e) {
			Messages.error(contentPane, "There was an error connecting to the database");
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			Messages.error(contentPane, "The given order was not found in the database");
		}
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
		lblSubtotalValue = new JLabel("N/A"); //TODO: Make it write out the correct value
		lblSubtotalValue.setForeground(new Color(102, 102, 102));
		GridBagConstraints gbc_lblSubtotalValue = new GridBagConstraints();
		gbc_lblSubtotalValue.anchor = GridBagConstraints.WEST;
		gbc_lblSubtotalValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubtotalValue.gridx = 2;
		gbc_lblSubtotalValue.gridy = 0;
		priceAndSubmitPanel.add(lblSubtotalValue, gbc_lblSubtotalValue);
						
		// ***** Customer type discount label *****
		JLabel lblDiscount = new JLabel(customer.getCustomerType() + " Discount:");
		GridBagConstraints gbc_lblDiscount = new GridBagConstraints();
		gbc_lblDiscount.anchor = GridBagConstraints.WEST;
		gbc_lblDiscount.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiscount.gridx = 0;
		gbc_lblDiscount.gridy = 1;
		priceAndSubmitPanel.add(lblDiscount, gbc_lblDiscount);
						
		// ***** Customer type discount value (might not be needed) *****
		lblDiscountValue = new JLabel("N/A"); //TODO: Make it write out the correct value
		lblDiscountValue.setForeground(new Color(0, 102, 0));
		GridBagConstraints gbc_lblDiscountValue = new GridBagConstraints();
		gbc_lblDiscountValue.anchor = GridBagConstraints.WEST;
		gbc_lblDiscountValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiscountValue.gridx = 2;
		gbc_lblDiscountValue.gridy = 1;
		priceAndSubmitPanel.add(lblDiscountValue, gbc_lblDiscountValue);
						
		// ***** Total price label *****
		JLabel lblTotal = new JLabel("Total:");
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotal.anchor = GridBagConstraints.WEST;
		gbc_lblTotal.gridx = 0;
		gbc_lblTotal.gridy = 2;
		priceAndSubmitPanel.add(lblTotal, gbc_lblTotal);
						
		// ***** Total price value *****
		lblTotalValue = new JLabel("N/A"); //TODO: Make it write out the correct value
		GridBagConstraints gbc_lblTotalValue = new GridBagConstraints();
		gbc_lblTotalValue.anchor = GridBagConstraints.WEST;
		gbc_lblTotalValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalValue.gridx = 2;
		gbc_lblTotalValue.gridy = 2;
		priceAndSubmitPanel.add(lblTotalValue, gbc_lblTotalValue);
		
		// ***** Create order button *****
		btnCreateOrder = new JButton("Create order");
		GridBagConstraints gbc_btnCreateOrder = new GridBagConstraints();
		gbc_btnCreateOrder.anchor = GridBagConstraints.EAST;
		gbc_btnCreateOrder.gridx = 3;
		gbc_btnCreateOrder.gridy = 3;
		priceAndSubmitPanel.add(btnCreateOrder, gbc_btnCreateOrder);
		
		//Attach event handlers
		addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  COMMON METHODS *******************
	 * *******************************************************
	 */
	
	/**
	 * The action performed when clicking the add item button
	 */
	private void addProduct() {
		//TODO: Create window for inputing product and quantity
		/*
		if (frame.isProductSelected()) {
			Product product = frame.getSelectedProduct();
			int quantity = frame.getSelectedQuantity();
			// add to cart
			try {
				tableModel.add(product, quantity);
			} catch (NotEnoughInStockException e) {
				Messages.error(CreateOrder.this, String.format("There is not enough items in stock from %d", product.getName())
				
				// Show the 'add to cart' window, again.
				this.addItem();
			}
			*/
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	public void addEventHandlers() {
		// toggle bottom table buttons depending on whether a table row is selected
		tableMain.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tableMain.getSelectionModel().isSelectionEmpty()) {
					// NOT SELECTED
					btnEditQuantity.setEnabled(false);
					btnRemove.setEnabled(false);
				} else {
					// SELECTED
					btnEditQuantity.setEnabled(true);
					btnRemove.setEnabled(true);
				}
			}
		});
		
		// Action for add item button
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProduct();
			}
		});
		
		// Action for clear button
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Messages.confirm(contentPane, "Are you sure you want to clear the order?")); {
					tableModel.clear();
				}
			}
		});
		
		// Action for createQuote button
		btnCreateQuote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Messages.confirm(contentPane, "Do you want to finalize the order?")) {
					try {
						orderCtrl.finishOrder(order);
					} catch (SQLException e1) {
						Messages.error(contentPane, "There was an error connecting to the database");
					} catch (NotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		/**
		 * TODO: Try to simplify it later (it should work as it is, but it looks way too complicated)
		 * Action for editQuantity button
		 */
		btnEditQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Get the selected row
				int row = tableMain.getSelectedRow();
				
				//Get the orderLine from the selected row
				OrderLine orderLine = null;
				try {
					orderLine = orderLineCtrl.findById((int) tableMain.getValueAt(row, 0));
				} catch (SQLException e2) {
					Messages.error(contentPane, "There was an error connecting to the database");
				} catch (NotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
				
				//Get the product from the orderLine
				Product product = orderLine.getProduct();
				
				//Get the available quantity from the product
				int availableQuantity = 0;
				try {
					availableQuantity = itemCtrl.findAllPerProduct(product).size();
				} catch (SQLException e1) {
					Messages.error(contentPane, "There was an error connecting to the database");
				} catch (NotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
						Messages.error(contentPane, "The given value is not a number");
					}
					
					//Set the new quantity for the product
					orderLine.setQuantity(newQuantity);
					try {
						orderLineCtrl.updateOrderLine(orderLine);
					} catch (SQLException e1) {
						Messages.error(contentPane, "There was an error connecting to the database");
					}
				}
				
			}
		});
		
		//Action for remove button
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableMain.getSelectedRow();
				try {
					tableModel.remove(row);
				} catch (SQLException e1) {
					Messages.error(contentPane, "There was an error connecting to the database");
				}
			}
		});
		
		//Action for clear button
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.clear();
			}
		});
	}
	

}
