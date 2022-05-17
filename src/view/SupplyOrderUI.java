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
import javax.swing.JRadioButton;

public class SupplyOrderUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtProductId;
	private JTextField txtQuantity;
	private JTextField txtOrderDate;
	private SupplyOrder supplyOrder;
	private SupplyOrderController supplyOrderCtrl;
	private Mode mode;
	AuthenticationController auth;
	private JLabel lblQuantity_2;
	private JLabel lblSupplierid;
	private JTextField textSupplierId;
	private JPanel panel;
	private JRadioButton rdbtnYesActive;
	private JRadioButton rdbtnNoActive;
	private JLabel lblActive;
	private JButton btnSubmit;
	private JLabel lblDelivered;
	private JPanel panelDelivered;
	private JRadioButton rdbtnDelivered;
	private JRadioButton rdbtnNoDelivered;
	private JTextField textField;
	private JLabel lblQuantity;
	private JButton btnSearch;
	
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
		gbl_contentPane.rowHeights = new int[]{19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblProduct = new JLabel("Id");
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.anchor = GridBagConstraints.WEST;
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.gridx = 0;
		gbc_lblProduct.gridy = 0;
		contentPane.add(lblProduct, gbc_lblProduct);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.anchor = GridBagConstraints.WEST;
		gbc_txtId.insets = new Insets(0, 0, 5, 5);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 0;
		gbc_txtId.gridy = 1;
		contentPane.add(txtId, gbc_txtId);
		
		JLabel lblProductID = new JLabel("ProductId: ");
		GridBagConstraints gbc_lblProductID = new GridBagConstraints();
		gbc_lblProductID.anchor = GridBagConstraints.WEST;
		gbc_lblProductID.insets = new Insets(0, 0, 5, 0);
		gbc_lblProductID.gridx = 1;
		gbc_lblProductID.gridy = 0;
		contentPane.add(lblProductID, gbc_lblProductID);
		
		txtProductId = new JTextField();
		txtProductId.setColumns(10);
		GridBagConstraints gbc_txtProductId = new GridBagConstraints();
		gbc_txtProductId.anchor = GridBagConstraints.WEST;
		gbc_txtProductId.insets = new Insets(0, 0, 5, 0);
		gbc_txtProductId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductId.gridx = 1;
		gbc_txtProductId.gridy = 1;
		contentPane.add(txtProductId, gbc_txtProductId);
		
		lblQuantity_2 = new JLabel("Quantity: ");
		GridBagConstraints gbc_lblQuantity_2 = new GridBagConstraints();
		gbc_lblQuantity_2.anchor = GridBagConstraints.WEST;
		gbc_lblQuantity_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity_2.gridx = 0;
		gbc_lblQuantity_2.gridy = 2;
		contentPane.add(lblQuantity_2, gbc_lblQuantity_2);
		
		JLabel lblOrderDate = new JLabel("Order date: ");
		GridBagConstraints gbc_OrderDate = new GridBagConstraints();
		gbc_OrderDate.anchor = GridBagConstraints.WEST;
		gbc_OrderDate.insets = new Insets(0, 0, 5, 0);
		gbc_OrderDate.gridx = 1;
		gbc_OrderDate.gridy = 2;
		contentPane.add(lblOrderDate, gbc_OrderDate);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		GridBagConstraints gbc_txtQuantity = new GridBagConstraints();
		gbc_txtQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_txtQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantity.gridx = 0;
		gbc_txtQuantity.gridy = 3;
		contentPane.add(txtQuantity, gbc_txtQuantity);
		
		txtOrderDate = new JTextField();
		txtOrderDate.setColumns(10);
		GridBagConstraints gbc_txtOrderDate = new GridBagConstraints();
		gbc_txtOrderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOrderDate.insets = new Insets(0, 0, 5, 0);
		gbc_txtOrderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOrderDate.gridx = 1;
		gbc_txtOrderDate.gridy = 3;
		contentPane.add(txtOrderDate, gbc_txtOrderDate);
		
		lblSupplierid = new JLabel("SupplierId: ");
		GridBagConstraints gbc_lblSupplierid = new GridBagConstraints();
		gbc_lblSupplierid.anchor = GridBagConstraints.WEST;
		gbc_lblSupplierid.insets = new Insets(0, 0, 5, 5);
		gbc_lblSupplierid.gridx = 0;
		gbc_lblSupplierid.gridy = 4;
		contentPane.add(lblSupplierid, gbc_lblSupplierid);
		
		textSupplierId = new JTextField();
		textSupplierId.setEditable(false);
		textSupplierId.setColumns(10);
		GridBagConstraints gbc_textSupplierId = new GridBagConstraints();
		gbc_textSupplierId.insets = new Insets(0, 0, 5, 5);
		gbc_textSupplierId.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSupplierId.gridx = 0;
		gbc_textSupplierId.gridy = 5;
		contentPane.add(textSupplierId, gbc_textSupplierId);
		
		btnSearch = new JButton("Search");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.anchor = GridBagConstraints.WEST;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 5;
		contentPane.add(btnSearch, gbc_btnSearch);
		
		lblQuantity = new JLabel("Quantity: ");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 0;
		gbc_lblQuantity.gridy = 6;
		contentPane.add(lblQuantity, gbc_lblQuantity);
		
		lblActive = new JLabel("Active:");
		GridBagConstraints gbc_lblActive = new GridBagConstraints();
		gbc_lblActive.anchor = GridBagConstraints.WEST;
		gbc_lblActive.insets = new Insets(0, 0, 5, 0);
		gbc_lblActive.gridx = 1;
		gbc_lblActive.gridy = 6;
		contentPane.add(lblActive, gbc_lblActive);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 7;
		contentPane.add(textField, gbc_textField);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 7;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		rdbtnYesActive = new JRadioButton("Yes");
		GridBagConstraints gbc_rdbtnYesActive = new GridBagConstraints();
		gbc_rdbtnYesActive.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnYesActive.gridx = 0;
		gbc_rdbtnYesActive.gridy = 0;
		panel.add(rdbtnYesActive, gbc_rdbtnYesActive);
		
		rdbtnNoActive = new JRadioButton("No");
		GridBagConstraints gbc_rdbtnNoActive = new GridBagConstraints();
		gbc_rdbtnNoActive.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNoActive.gridx = 1;
		gbc_rdbtnNoActive.gridy = 0;
		panel.add(rdbtnNoActive, gbc_rdbtnNoActive);
		
		lblDelivered = new JLabel("Delivered:");
		GridBagConstraints gbc_lblDelivered = new GridBagConstraints();
		gbc_lblDelivered.insets = new Insets(0, 0, 5, 5);
		gbc_lblDelivered.gridx = 0;
		gbc_lblDelivered.gridy = 8;
		contentPane.add(lblDelivered, gbc_lblDelivered);
		
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 0);
		gbc_btnSubmit.gridx = 1;
		gbc_btnSubmit.gridy = 8;
		contentPane.add(btnSubmit, gbc_btnSubmit);
		
		panelDelivered = new JPanel();
		GridBagConstraints gbc_panelDelivered = new GridBagConstraints();
		gbc_panelDelivered.insets = new Insets(0, 0, 5, 5);
		gbc_panelDelivered.fill = GridBagConstraints.BOTH;
		gbc_panelDelivered.gridx = 0;
		gbc_panelDelivered.gridy = 9;
		contentPane.add(panelDelivered, gbc_panelDelivered);
		GridBagLayout gbl_panelDelivered = new GridBagLayout();
		gbl_panelDelivered.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelDelivered.rowHeights = new int[]{0, 0};
		gbl_panelDelivered.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelDelivered.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelDelivered.setLayout(gbl_panelDelivered);
		
		rdbtnDelivered = new JRadioButton("Yes");
		GridBagConstraints gbc_rdbtnDelivered = new GridBagConstraints();
		gbc_rdbtnDelivered.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnDelivered.gridx = 0;
		gbc_rdbtnDelivered.gridy = 0;
		panelDelivered.add(rdbtnDelivered, gbc_rdbtnDelivered);
		
		rdbtnNoDelivered = new JRadioButton("No");
		GridBagConstraints gbc_rdbtnNoDelivered = new GridBagConstraints();
		gbc_rdbtnNoDelivered.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNoDelivered.gridx = 1;
		gbc_rdbtnNoDelivered.gridy = 0;
		panelDelivered.add(rdbtnNoDelivered, gbc_rdbtnNoDelivered);
		
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
		txtId.setEnabled(false);
	}
	
	// FIll in the fields
	private void fillFields(SupplyOrder supplyOrder) {
		txtId.setText(String.valueOf(supplyOrder.getId()));
		txtProductId.setText(String.valueOf(supplyOrder.getQuantity()));
		txtQuantity.setText(String.valueOf(supplyOrder.getSupplier()));
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
				
				// Validate ProductId
				int productId 
				
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
				
				////Validate deliverd

				// if mode == view, update data
				if (mode == Mode.EDIT) {
			
                    try {
                        supplyOrderCtrl.updateSupplyOrder(supplyOrder, )
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new product
					try {
                        SupplyOrder supplyOrder = supplyOrderCtrl.createSupplyOrder(supplyOrder,)
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