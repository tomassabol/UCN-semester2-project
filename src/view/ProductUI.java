package view;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.ProductController;
import model.Product;
import model.Product.ProductType;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Objects;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtProductType;
	private JTextField txtDiscount;
	private JButton btnSubmit;
	private JTextArea txtDescription;
	private Product product;
	private ProductController productCtrl;
	private Mode mode;
	AuthenticationController auth;
	private JPanel panel;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JButton btnNewButton;
    private ProductType productType;
	/**
	 * Constructor: create new product
	 *
	 * @param auth the auth controller 
	 * @throws SQLException
	 */
	public ProductUI(AuthenticationController auth) throws SQLException {
		this(auth, null, Mode.CREATE);
		this.product = null;
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException
	 * @wbp.parser.constructor
	 */
	public ProductUI(AuthenticationController auth, Product product, Mode mode) throws SQLException {
		this.auth = auth;
		this.mode = mode;
		this.product = product;
		
		productCtrl = new ProductController();
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{208, 208, 0};
		gbl_contentPane.rowHeights = new int[]{19, 0, 0, 127, 0, 0, 19, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblId = new JLabel("Id");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		contentPane.add(lblId, gbc_lblId);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.anchor = GridBagConstraints.WEST;
		gbc_txtId.insets = new Insets(0, 0, 5, 5);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 0;
		gbc_txtId.gridy = 1;
		contentPane.add(txtId, gbc_txtId);
		
		JLabel lblName = new JLabel("Name *");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		contentPane.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.anchor = GridBagConstraints.WEST;
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 1;
		contentPane.add(txtName, gbc_txtName);
		
		JLabel lblDescription = new JLabel("Description *");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.WEST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 2;
		contentPane.add(lblDescription, gbc_lblDescription);
		
		txtDescription = new JTextArea();
		txtDescription.setLineWrap(true);
		GridBagConstraints gbc_txtDescription = new GridBagConstraints();
		gbc_txtDescription.gridwidth = 2;
		gbc_txtDescription.insets = new Insets(0, 0, 5, 0);
		gbc_txtDescription.fill = GridBagConstraints.BOTH;
		gbc_txtDescription.gridx = 0;
		gbc_txtDescription.gridy = 3;
		contentPane.add(txtDescription, gbc_txtDescription);
		
		JLabel lblProductType = new JLabel("Product Type*");
		GridBagConstraints gbc_lblProductType = new GridBagConstraints();
		gbc_lblProductType.anchor = GridBagConstraints.WEST;
		gbc_lblProductType.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductType.gridx = 0;
		gbc_lblProductType.gridy = 4;
		contentPane.add(lblProductType, gbc_lblProductType);
		
		txtProductType = new JTextField();
		txtProductType.setColumns(10);
		GridBagConstraints gbc_txtProductType = new GridBagConstraints();
		gbc_txtProductType.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductType.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductType.gridx = 0;
		gbc_txtProductType.gridy = 5;
		contentPane.add(txtProductType, gbc_txtProductType);
		
		btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblPrice = new JLabel("Price *");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 6;
		contentPane.add(lblPrice, gbc_lblPrice);
		
		JLabel lblDiscount = new JLabel("Discount");
		GridBagConstraints gbc_lblDiscount = new GridBagConstraints();
		gbc_lblDiscount.anchor = GridBagConstraints.WEST;
		gbc_lblDiscount.insets = new Insets(0, 0, 5, 0);
		gbc_lblDiscount.gridx = 1;
		gbc_lblDiscount.gridy = 6;
		contentPane.add(lblDiscount, gbc_lblDiscount);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		GridBagConstraints gbc_txtPrice = new GridBagConstraints();
		gbc_txtPrice.insets = new Insets(0, 0, 5, 5);
		gbc_txtPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrice.gridx = 0;
		gbc_txtPrice.gridy = 7;
		contentPane.add(txtPrice, gbc_txtPrice);
		
		txtDiscount = new JTextField();
		txtDiscount.setColumns(10);
		GridBagConstraints gbc_txtDiscount = new GridBagConstraints();
		gbc_txtDiscount.insets = new Insets(0, 0, 5, 0);
		gbc_txtDiscount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDiscount.gridx = 1;
		gbc_txtDiscount.gridy = 7;
		contentPane.add(txtDiscount, gbc_txtDiscount);
		
		JLabel lblActive = new JLabel("Active");
		GridBagConstraints gbc_lblActive = new GridBagConstraints();
		gbc_lblActive.anchor = GridBagConstraints.WEST;
		gbc_lblActive.insets = new Insets(0, 0, 5, 5);
		gbc_lblActive.gridx = 0;
		gbc_lblActive.gridy = 8;
		contentPane.add(lblActive, gbc_lblActive);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 9;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		rdbtnYes = new JRadioButton("Yes");
		GridBagConstraints gbc_rdbtnYes = new GridBagConstraints();
		gbc_rdbtnYes.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnYes.gridx = 0;
		gbc_rdbtnYes.gridy = 0;
		panel.add(rdbtnYes, gbc_rdbtnYes);
		
		rdbtnNo = new JRadioButton("No");
		GridBagConstraints gbc_rdbtnNo = new GridBagConstraints();
		gbc_rdbtnNo.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNo.gridx = 1;
		gbc_rdbtnNo.gridy = 0;
		panel.add(rdbtnNo, gbc_rdbtnNo);
		
		
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 11;
		contentPane.add(btnSubmit, gbc_btnOk);
		
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View Product - " + product.getName());
				// Hide 'Update' button if in view mode
				btnSubmit.setVisible(false);
				// Disable fields
				this.disableFields();
				// Fill fields with content
				this.fillFields(product);
				break;
			case EDIT: 
				// Set title
				setTitle("Edit Product");
				// Change submit button text to 'Update'
				btnSubmit.setText("Update");
				// Enable fields for editing
				this.enableFields();
				// Fill fields with content
				this.fillFields(product);
				break;
			case CREATE:
				// Set title
				setTitle("Add New Product");
				// Change submit button text to 'Create'
				btnSubmit.setText("Create");
				// Enable fields
				this.enableFields();
				// Peek ID
		}
		
		
		
		addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	/**
	 * Gets the product.
	 * Useful for Create mode (to get the created product)
	 *
	 * @return the product
	 */
	public Product getProduct() {
		return this.product;
	}
	
	// Makes the text fields uneditable
	private void disableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JRadioButton) {
				      c.setEnabled(false);
				   }
			}
		txtId.setEnabled(false);
		rdbtnNo.setEnabled(false);
		rdbtnYes.setEnabled(false);
	}
	
	
	// Makes the text fields editable except ID field
	private void enableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JRadioButton) {
			      c.setEnabled(true);
			   }
			}
		txtId.setEnabled(false);
		rdbtnNo.setEnabled(true);
		rdbtnYes.setEnabled(true);
	}
	
	// FIll in the fields
	private void fillFields(Product product) {
		txtId.setText(String.valueOf(product.getId()));
		txtName.setText(product.getName());
		txtDescription.setText(product.getDescription());
		txtProductType.setText(Objects.toString(product.getProductType().toString()));
        this.productType = product.getProductType();
		txtPrice.setText(Objects.toString(product.getPrice()));
		txtDiscount.setText(Objects.toString(product.getDiscount(), ""));
		if (product.isActive() == true) {
            rdbtnYes.setSelected(true);
        } else {
            rdbtnNo.setSelected(true);
        }
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the product
		btnSubmit.addActionListener(e -> {
			String message = "";
			if (mode == Mode.EDIT) {
				message = "Are you sure you want to update the changes to product?";
			} else if (mode == Mode.CREATE) {
				message = "Create product?";
			}
			if (Messages.confirm(ProductUI.this, message)) {
				
				// Validate name
				String name = txtName.getText().strip();
				if (name.isEmpty()) {
					Messages.error(this, "Product name cannot be empty!");
					return;
				}
				
				// Validate description
				String description = txtDescription.getText().strip();
				if (description.isEmpty()) {
					Messages.error(this, "Product description cannot be empty!");
					return;
				}
				
				// Validate ProductTyoe
				if (this.productType == null) {
                    Messages.error(this, "You must choose a product type");
					return;
                }
						
				// Validate price
				String priceString = txtPrice.getText().strip();
				BigDecimal price;
				if (priceString.isEmpty()) {
					price = null;
				} else {
					try {
						price = new BigDecimal(priceString);
					} catch (NumberFormatException e1) {
						Messages.error(this, "Price must be a positive decimal number, separated by dots");
						return;
					}
					// if less than zero
					if (price.compareTo(BigDecimal.ZERO) == -1) {
						Messages.error(this, "Price must be a positive decimal number, separated by dots");
					}
				}

                // Validate Discount
				int discount;
				try {
					discount = Integer.parseInt(txtDiscount.getText());
				} catch (NumberFormatException e1) {
					Messages.error(this, "Discount must be a positive whole number or 0");
					return;
				}

                boolean active = rdbtnYes.isSelected() ? true : false;
			
				// if mode == view, update data
				if (mode == Mode.EDIT) {
			
                    try {
                        productCtrl.updateProduct(product, name, description, productType, price, discount, active);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new product
					try {
                        Product product = productCtrl.createProduct(name, description, productType, price, discount, active);
						this.product = product;
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
