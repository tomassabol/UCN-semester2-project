package view;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.SupplyOrderController;
import model.SupplyOrder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.sql.SQLException;

public class SupplyOrderUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtProduct;
	private JTextField txtQuantity;
	private JTextField txtSupplier;
	private JTextField txtOrderDate;
	private JButton btnSubmit;
	private SupplyOrder supplyOrder;
	private SupplyOrderController supplyOrderCtrl;
	private Mode mode;
	AuthenticationController auth;
	
	/**
	 * Constructor: Create a Supply Order
	 *
	 * @param auth the auth controller 
	 * @wbp.parser.constructor
	 * @throws SQLException
	 */
	public SupplyOrderUI(AuthenticationController auth) throws SQLException {
		this(auth, null, Mode.CREATE);
		this.supplyOrder = null;
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public SupplyOrderUI(AuthenticationController auth, SupplyOrder supplyOrder, Mode mode) throws SQLException {
		this.auth = auth;
		this.mode = mode;
		this.supplyOrder = supplyOrder;
		
		supplyOrderCtrl = new SupplyOrderController();
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{208, 208, 0};
		gbl_contentPane.rowHeights = new int[]{19, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblProduct = new JLabel("Product");
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.anchor = GridBagConstraints.WEST;
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.gridx = 0;
		gbc_lblProduct.gridy = 0;
		contentPane.add(lblProduct, gbc_lblProduct);
		
		txtProduct = new JTextField();
		txtProduct.setColumns(10);
		GridBagConstraints gbc_txtProduct = new GridBagConstraints();
		gbc_txtProduct.anchor = GridBagConstraints.WEST;
		gbc_txtProduct.insets = new Insets(0, 0, 5, 5);
		gbc_txtProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProduct.gridx = 0;
		gbc_txtProduct.gridy = 1;
		contentPane.add(txtProduct, gbc_txtProduct);
		
		JLabel lblQuantity = new JLabel("Quantity: ");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.WEST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_lblQuantity.gridx = 1;
		gbc_lblQuantity.gridy = 0;
		contentPane.add(lblQuantity, gbc_lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		GridBagConstraints gbc_txtQuantity = new GridBagConstraints();
		gbc_txtQuantity.anchor = GridBagConstraints.WEST;
		gbc_txtQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_txtQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantity.gridx = 1;
		gbc_txtQuantity.gridy = 1;
		contentPane.add(txtQuantity, gbc_txtQuantity);
		
		JLabel lblSupplier = new JLabel("Supplier: ");
		GridBagConstraints gbc_Supplier = new GridBagConstraints();
		gbc_Supplier.anchor = GridBagConstraints.WEST;
		gbc_Supplier.insets = new Insets(0, 0, 5, 5);
		gbc_Supplier.gridx = 0;
		gbc_Supplier.gridy = 2;
		contentPane.add(lblSupplier, gbc_Supplier);
		
		JLabel lblOrderDate = new JLabel("Order date: ");
		GridBagConstraints gbc_OrderDate = new GridBagConstraints();
		gbc_OrderDate.anchor = GridBagConstraints.WEST;
		gbc_OrderDate.insets = new Insets(0, 0, 5, 0);
		gbc_OrderDate.gridx = 1;
		gbc_OrderDate.gridy = 2;
		contentPane.add(lblOrderDate, gbc_OrderDate);
		
		txtSupplier = new JTextField();
		txtSupplier.setColumns(10);
		GridBagConstraints gbc_txtSupplier = new GridBagConstraints();
		gbc_txtSupplier.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSupplier.insets = new Insets(0, 0, 5, 5);
		gbc_txtSupplier.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSupplier.gridx = 0;
		gbc_txtSupplier.gridy = 3;
		contentPane.add(txtSupplier, gbc_txtSupplier);
		
		txtOrderDate = new JTextField();
		txtOrderDate.setColumns(10);
		GridBagConstraints gbc_txtOrderDate = new GridBagConstraints();
		gbc_txtOrderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOrderDate.insets = new Insets(0, 0, 5, 0);
		gbc_txtOrderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOrderDate.gridx = 1;
		gbc_txtOrderDate.gridy = 3;
		contentPane.add(txtOrderDate, gbc_txtOrderDate);
				
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 5;
		contentPane.add(btnSubmit, gbc_btnOk);
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View SupplyOrder - " + supplyOrder.getId());
				// Hide 'Update' button if in view mode
				btnSubmit.setVisible(false);
				// Disable fields
				this.disableFields();
				// Fill fields with content
				this.fillFields(supplyOrder);
				break;
			case EDIT: 
				// Set title
				setTitle("Edit SupplyOrder");
				// Change submit button text to 'Update'
				btnSubmit.setText("Update");
				// Enable fields for editing
				this.enableFields();
				// Fill fields with content
				this.fillFields(supplyOrder);
				break;
			case CREATE:
				// Set title
				setTitle("Add New SupplyOrder");
				// Change submit button text to 'Create'
				btnSubmit.setText("Create");
				// Enable fields
				this.enableFields();
		}
		
		
		
		addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	/**
	 * Gets the supply order.
	 * Useful for Create mode (to get the created supply order)
	 *
	 * @return the supply order
	 */
	public SupplyOrder getSupplyOrder() {
		return this.supplyOrder;
	}
	
	// Makes the text fields uneditable
	private void disableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea) {
				      c.setEnabled(false);
				   }
			}
	}
	
	
	// Makes the text fields editable except ID field
	private void enableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea) {
			      c.setEnabled(true);
			   }
			}
		txtProduct.setEnabled(false);
	}
	
	// FIll in the fields
	private void fillFields(SupplyOrder supplyOrder) {
		txtProduct.setText(String.valueOf(supplyOrder.getId()));
		txtQuantity.setText(String.valueOf(supplyOrder.getQuantity()));
		txtSupplier.setText(String.valueOf(supplyOrder.getSupplier()));
		txtOrderDate.setText(String.valueOf(supplyOrder.getOrderDate()));
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the city
		btnSubmit.addActionListener(e -> {
			String message = "";
			if (mode == Mode.EDIT) {
				message = "Are you sure you want to update the supply order?";
			} else if (mode == Mode.CREATE) {
				message = "Add a new SupplyOrder?";
			}
			if (Messages.confirm(SupplyOrderUI.this, message)) {
				
				// Validate product
				String product = txtProduct.getText().strip();
				if (product.isEmpty()) {
					Messages.error(this, "Supply order product cannot be empty!");
					return;
				}
				
				// Validate quantity
				String quantity = txtQuantity.getText().strip();
				if (quantity.isEmpty()) {
					Messages.error(this, "Quantity cannot be empty!");
					return;
				}
				
				//Validate orderdate
				String orderDate = txtOrderDate.getText().strip();
				if (orderDate.isEmpty()) {
					Messages.error(this, "OrderDate cannot be empty!");
					return;
				}
					
				//Validate supplier
				String supplier = txtSupplier.getText().strip();
				if (supplier.isEmpty()) {
					Messages.error(this, "Supplier cannot be empty!");
					return;
				}
			
				// if mode == view, update data
				if (mode == Mode.EDIT) {
			
                    try {
                        supplyOrderCtrl.updateSupplyOrder(supplyOrder, product, quantity, orderDate, supplier)
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new product
					try {
                        SupplyOrder supplyOrder = supplyOrderCtrl.createSupplyOrder(supplyOrder,product,quantity,orderDate,supplier)
						this.supplyOrder=supplyOrder;
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    };
				}


				
			}
			// Dispose of the window
			this.dispose();
		});
	}
}