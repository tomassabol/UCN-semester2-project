package view;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.ShelfController;
import model.Department;
import model.Product;
import model.Shelf;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.sql.SQLException;

public class ShelfUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtQuantity;
	private JTextField txtProduct;
	private JButton btnSubmit;
	private Shelf shelf;
	private ShelfController shelfCtrl;
	private Mode mode;
	AuthenticationController auth;
	private JLabel lblDepartment;
	private JTextField txtDepartment;
	Department department;
    Product product;
	/**
	 * Constructor: create new DepartmentUI
	 *
	 * @param auth the auth controller 
	 * @throws SQLException
	 */
	public ShelfUI(AuthenticationController auth, Mode mode) throws SQLException {
		this(auth, null, Mode.CREATE);
		this.shelf = null;
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException
	 * @wbp.parser.constructor
	 */
	public ShelfUI(AuthenticationController auth, Shelf shelf, Mode mode) throws SQLException {
		this.auth = auth;
		this.mode = mode;
		this.shelf = shelf;
		
		shelfCtrl = new ShelfController();
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{208, 208, 0};
		gbl_contentPane.rowHeights = new int[]{19, 0, 0, 0, 0, 0, 19, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		contentPane.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.anchor = GridBagConstraints.WEST;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 0;
		gbc_txtName.gridy = 3;
		contentPane.add(txtName, gbc_txtName);
		
		JLabel lblProduct = new JLabel("Product *");
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.anchor = GridBagConstraints.WEST;
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.gridx = 0;
		gbc_lblProduct.gridy = 4;
		contentPane.add(lblProduct, gbc_lblProduct);
		
		txtProduct = new JTextField();
		txtProduct.setColumns(10);
		GridBagConstraints gbc_txtProduct = new GridBagConstraints();
		gbc_txtProduct.insets = new Insets(0, 0, 5, 5);
		gbc_txtProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProduct.gridx = 0;
		gbc_txtProduct.gridy = 5;
		contentPane.add(txtProduct, gbc_txtProduct);
		
		JLabel lblQuantity = new JLabel("Product Quantity *");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.WEST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 0;
		gbc_lblQuantity.gridy = 6;
		contentPane.add(lblQuantity, gbc_lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		GridBagConstraints gbc_txtQuantity = new GridBagConstraints();
		gbc_txtQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_txtQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantity.gridx = 0;
		gbc_txtQuantity.gridy = 7;
		contentPane.add(txtQuantity, gbc_txtQuantity);
		
		lblDepartment = new JLabel("Department *");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.anchor = GridBagConstraints.WEST;
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 0;
		gbc_lblDepartment.gridy = 8;
		contentPane.add(lblDepartment, gbc_lblDepartment);
		
		txtDepartment = new JTextField();
		txtDepartment.setColumns(10);
		GridBagConstraints gbc_txtDepartment = new GridBagConstraints();
		gbc_txtDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_txtDepartment.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDepartment.gridx = 0;
		gbc_txtDepartment.gridy = 9;
		contentPane.add(txtDepartment, gbc_txtDepartment);		
		
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 11;
		contentPane.add(btnSubmit, gbc_btnOk);
		
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View Shelf - " + shelf.getName());
				// Hide 'Update' button if in view mode
				btnSubmit.setVisible(false);
				// Disable fields
				this.disableFields();
				// Fill fields with content
				this.fillFields(shelf);
				break;
			case EDIT: 
				// Set title
				setTitle("Edit Shelf");
				// Change submit button text to 'Update'
				btnSubmit.setText("Update");
				// Enable fields for editing
				this.enableFields();
				// Fill fields with content
				this.fillFields(shelf);
				break;
			case CREATE:
				// Set title
				setTitle("Add New Shelf");
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
	 * Gets the Department.
	 * Useful for Create mode (to get the created department)
	 *
	 * @return the department
	 */
	public Shelf getShelf() {
		return this.shelf;
	}
	
	// Makes the text fields uneditable
	private void disableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JButton) {
				      c.setEnabled(false);
				   }
			}
        txtId.setEnabled(false);
        txtProduct.setEnabled(false);
        txtQuantity.setEnabled(false);
        txtDepartment.setEnabled(false);
	}
	
	
	// Makes the text fields editable except ID field
	private void enableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JButton) {
			      c.setEnabled(true);
			   }
			}
		txtId.setEnabled(false);
        txtProduct.setEnabled(false);
        txtQuantity.setEnabled(false);
        txtDepartment.setEnabled(false);
	}
	
	// FIll in the fields
	private void fillFields(Shelf shelf) {
		txtId.setText(String.valueOf(shelf.getId()));
		txtName.setText(shelf.getName());
		if (shelf.getProduct() == null) {
			txtProduct.setText("none");
		} else {
			txtProduct.setText(shelf.getProduct().getName());
		}
		txtQuantity.setText(String.valueOf(shelf.getProductQuantity()));
		txtDepartment.setText(shelf.getDepartment().getName());
        this.department = shelf.getDepartment();
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the department
		btnSubmit.addActionListener(e -> {
			String message = "";
			if (mode == Mode.EDIT) {
				message = "Are you sure you want to update the changes to Shelf?";
			} else if (mode == Mode.CREATE) {
				message = "Create Department?";
			}
			if (Messages.confirm(ShelfUI.this, message)) {
				
				// Validate name
				String name = txtName.getText().strip();
				if (name.isEmpty()) {
					Messages.error(this, "Department name cannot be empty");
					return;
				}
				
					
			
				// if mode == view, update data
				if (mode == Mode.EDIT) {
			
                    try {
						shelfCtrl.updateShelf(shelf, name, shelf.getProductQuantity());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new department
					try {
                        Shelf shelf = shelfCtrl.createShelf(name, auth.getLoggedInUser().getDepartment());
						this.shelf = shelf;
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