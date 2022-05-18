package view;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.SupplyOrderController;
import exceptions.NotFoundException;
import model.Product;
import model.SupplyOrder;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

import javax.lang.model.element.QualifiedNameable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Supplier;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SupplyOrderUI extends JDialog {
    public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtQuantity;
	private JTextField txtOrderDate;
	private SupplyOrder supplyOrder;
	private SupplyOrderController supplyOrderCtrl;
	private Mode mode;
	AuthenticationController auth;
	private JLabel lblQuantity_2;
	private JLabel lblSupplierid;
	private JTextField textSupplierId;
	private JLabel lblDelivered;
	private JPanel panelDelivered;
	private JRadioButton rdbtnDelivered;
	private JButton btnSearch;
	private Product product;
	private Supplier supplier;
	private JButton btnSubmit;
	private JLabel lblProductID;
	private JTextField textProduct;
	private JButton btnSearch2;
	private JLabel lblOrderDate;

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
		gbl_contentPane.rowHeights = new int[]{19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblProduct = new JLabel("Id");
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.anchor = GridBagConstraints.WEST;
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.gridx = 0;
		gbc_lblProduct.gridy = 0;
		contentPane.add(lblProduct, gbc_lblProduct);
		
		lblOrderDate = new JLabel("Order date: ");
		GridBagConstraints gbc_lblOrderDate = new GridBagConstraints();
		gbc_lblOrderDate.anchor = GridBagConstraints.WEST;
		gbc_lblOrderDate.insets = new Insets(0, 0, 5, 0);
		gbc_lblOrderDate.gridx = 1;
		gbc_lblOrderDate.gridy = 0;
		contentPane.add(lblOrderDate, gbc_lblOrderDate);
		
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
		
		txtOrderDate = new JTextField();
		txtOrderDate.setEditable(false);
		txtOrderDate.setColumns(10);
		GridBagConstraints gbc_txtOrderDate = new GridBagConstraints();
		gbc_txtOrderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOrderDate.insets = new Insets(0, 0, 5, 0);
		gbc_txtOrderDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOrderDate.gridx = 1;
		gbc_txtOrderDate.gridy = 1;
		contentPane.add(txtOrderDate, gbc_txtOrderDate);
		
		lblQuantity_2 = new JLabel("Quantity: ");
		GridBagConstraints gbc_lblQuantity_2 = new GridBagConstraints();
		gbc_lblQuantity_2.anchor = GridBagConstraints.WEST;
		gbc_lblQuantity_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity_2.gridx = 0;
		gbc_lblQuantity_2.gridy = 2;
		contentPane.add(lblQuantity_2, gbc_lblQuantity_2);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		GridBagConstraints gbc_txtQuantity = new GridBagConstraints();
		gbc_txtQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_txtQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantity.gridx = 0;
		gbc_txtQuantity.gridy = 3;
		contentPane.add(txtQuantity, gbc_txtQuantity);
		
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
		
		lblProductID = new JLabel("ProductId: ");
		GridBagConstraints gbc_lblProductID = new GridBagConstraints();
		gbc_lblProductID.anchor = GridBagConstraints.WEST;
		gbc_lblProductID.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductID.gridx = 0;
		gbc_lblProductID.gridy = 6;
		contentPane.add(lblProductID, gbc_lblProductID);
		
		textProduct = new JTextField();
		textProduct.setEditable(false);
		textProduct.setColumns(10);
		GridBagConstraints gbc_textProduct = new GridBagConstraints();
		gbc_textProduct.insets = new Insets(0, 0, 5, 5);
		gbc_textProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_textProduct.gridx = 0;
		gbc_textProduct.gridy = 7;
		contentPane.add(textProduct, gbc_textProduct);
		
		btnSearch2 = new JButton("Search");
		GridBagConstraints gbc_btnSearch2 = new GridBagConstraints();
		gbc_btnSearch2.anchor = GridBagConstraints.WEST;
		gbc_btnSearch2.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch2.gridx = 1;
		gbc_btnSearch2.gridy = 7;
		contentPane.add(btnSearch2, gbc_btnSearch2);
		
		lblDelivered = new JLabel("Delivered:");
		GridBagConstraints gbc_lblDelivered = new GridBagConstraints();
		gbc_lblDelivered.anchor = GridBagConstraints.WEST;
		gbc_lblDelivered.insets = new Insets(0, 0, 5, 5);
		gbc_lblDelivered.gridx = 0;
		gbc_lblDelivered.gridy = 9;
		contentPane.add(lblDelivered, gbc_lblDelivered);
		
		panelDelivered = new JPanel();
		GridBagConstraints gbc_panelDelivered = new GridBagConstraints();
		gbc_panelDelivered.insets = new Insets(0, 0, 5, 5);
		gbc_panelDelivered.fill = GridBagConstraints.BOTH;
		gbc_panelDelivered.gridx = 0;
		gbc_panelDelivered.gridy = 10;
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
		
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.anchor = GridBagConstraints.EAST;
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 0);
		gbc_btnSubmit.gridx = 1;
		gbc_btnSubmit.gridy = 12;
		contentPane.add(btnSubmit, gbc_btnSubmit);
		
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
		product = supplyOrder.getProduct();
		supplier = supplyOrder.getSupplier();
		txtId.setText(String.valueOf(supplyOrder.getId()));
		textProduct.setText(product.getName());
		txtQuantity.setText(String.valueOf(supplyOrder.getQuantity()));
		txtOrderDate.setText(String.valueOf(supplyOrder.getOrderDate()));
		textSupplierId.setText(supplier.getName());
		rdbtnDelivered.setSelected(supplyOrder.isIsDelivered());
	}

	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	
	private void addEventHandlers() {

		btnSearch.addActionListener(e -> {
			ChooseSupplier frame;
			try {
				frame = new ChooseSupplier(auth);
				frame.setVisible(true);
				if (frame.getSelectedSupplier() != null) {
					this.supplier = frame.getSelectedSupplier();
					textSupplierId.setText(supplier.getName());
				}
			} catch (SQLException | NotFoundException e1) {
				e1.printStackTrace();
			}
		});
		btnSearch2.addActionListener(e -> {
			ChooseProduct frame;
			try {
				frame = new ChooseProduct(auth);
				frame.setVisible(true);
				if (frame.getSelectedProduct() != null) {
					this.product = frame.getSelectedProduct();
					textProduct.setText(product.getName());
				}
			} catch (SQLException | NotFoundException e1) {
				e1.printStackTrace();
			}
		});
		btnSubmit.addActionListener(e -> {
			
			String message = "";
			if (mode == Mode.EDIT) {
				message = "Are you sure you want to update the changes to Supply Order?";
			} else if (mode == Mode.CREATE) {
				message = "Create Supply Order?";
			}
			if (Messages.confirm(SupplyOrderUI.this, message)) {
				
				
				//validate product
				if(product==null){
					Messages.error(this, "You need to select a product");
					return;
				}

				//validate quantity
				int quantity = Integer.valueOf(txtQuantity.getText()); 
				if(quantity == 0){
					Messages.error(this, "Quantity cannot be zero");
					return;
				}

				//validate supplier
				if(supplier==null){
					Messages.error(this, "You need to choose a product");
					return;
				}

				// if mode == view, update data
				if (mode == Mode.EDIT) {
			
                    try {
						supplyOrderCtrl.updateSupplyOrder(supplyOrder,  product, quantity, LocalDate.now(), supplier); 
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new department
					try {
                        SupplyOrder supplyOrder = supplyOrderCtrl.createSupplyOrder(product, quantity, LocalDate.now(), supplier);
						this.supplyOrder = supplyOrder;
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
