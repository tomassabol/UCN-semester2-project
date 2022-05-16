package view;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.DepartmentController;
import controller.ProductController;
import controller.ShelfController;
import exceptions.NotFoundException;
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

public class ShelfUi extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private ProductController prodController;
	private DepartmentController dController;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtDepartment;
	private JTextField txtProductId;
	private JButton btnSubmit;
	private Shelf shelf;
	private ShelfController shelfCtrl;
	private Mode mode;
	AuthenticationController auth;
	private JTextField textQuantity;
	private JLabel lblItemQuantity;
	/**
	 * Constructor: create new DepartmentUI
	 *
	 * @param auth the auth controller 
	 * @throws SQLException
	 */
	public ShelfUi(AuthenticationController auth, Mode mode) throws SQLException {
		this(auth, null, Mode.CREATE);
		this.shelf = null;
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException
	 * @wbp.parser.constructor
	 */
	public ShelfUi(AuthenticationController auth, Shelf shelf, Mode mode) throws SQLException {
		this.auth = auth;
		this.mode = mode;
		this.shelf = shelf;
		
		shelfCtrl = new ShelfController();
		dController = new DepartmentController();
		prodController = new ProductController();
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{208, 208, 0};
		gbl_contentPane.rowHeights = new int[]{19, 0, 0, 0, 19, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		JLabel lblProductId = new JLabel("ProductId");
		GridBagConstraints gbc_lblProductId = new GridBagConstraints();
		gbc_lblProductId.anchor = GridBagConstraints.WEST;
		gbc_lblProductId.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductId.gridx = 0;
		gbc_lblProductId.gridy = 2;
		contentPane.add(lblProductId, gbc_lblProductId);
		
		lblItemQuantity = new JLabel("ItemQuantity");
		GridBagConstraints gbc_lblItemQuantity = new GridBagConstraints();
		gbc_lblItemQuantity.anchor = GridBagConstraints.WEST;
		gbc_lblItemQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_lblItemQuantity.gridx = 1;
		gbc_lblItemQuantity.gridy = 2;
		contentPane.add(lblItemQuantity, gbc_lblItemQuantity);
		
		txtProductId = new JTextField();
		txtProductId.setColumns(10);
		GridBagConstraints gbc_txtProductId = new GridBagConstraints();
		gbc_txtProductId.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductId.gridx = 0;
		gbc_txtProductId.gridy = 3;
		contentPane.add(txtProductId, gbc_txtProductId);
		
		textQuantity = new JTextField();
		textQuantity.setEditable(false);
		textQuantity.setColumns(10);
		GridBagConstraints gbc_textQuantity = new GridBagConstraints();
		gbc_textQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_textQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_textQuantity.gridx = 1;
		gbc_textQuantity.gridy = 3;
		contentPane.add(textQuantity, gbc_textQuantity);
		
		JLabel lblDepartment = new JLabel("Department");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.anchor = GridBagConstraints.WEST;
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.gridx = 0;
		gbc_lblDepartment.gridy = 4;
		contentPane.add(lblDepartment, gbc_lblDepartment);
		
		txtDepartment = new JTextField();
		txtDepartment.setColumns(10);
		GridBagConstraints gbc_txtDepartment = new GridBagConstraints();
		gbc_txtDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_txtDepartment.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDepartment.gridx = 0;
		gbc_txtDepartment.gridy = 5;
		contentPane.add(txtDepartment, gbc_txtDepartment);
		
		
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 7;
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
	public Shelf getsShelf() {
		return this.shelf;
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
	private void fillFields(Shelf shelf) {
		txtId.setText(String.valueOf(shelf.getId()));
		txtName.setText(shelf.getName());
		txtProductId.setText(shelf.getProduct().getId()+"");
		textQuantity.setText(shelf.getProductQuantity()+"");
		txtDepartment.setText(shelf.getDepartment().getId()+"");
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
				message = "Are you sure you want to update the changes to Department?";
			} else if (mode == Mode.CREATE) {
				message = "Create Department?";
			}
			if (Messages.confirm(ShelfUi.this, message)) {
				
				// Validate name
				
				String productId = txtProductId.getText().strip();
				if(productId.isEmpty()){
					Messages.error(this, "ProductId cannot be null");
					return;
				}


				String name = txtName.getText().strip();
				if(name.isEmpty()){
					Messages.error(this, "Name cannot be empty");
					return;
				}

				String department = txtDepartment.getText().strip();
				if (department.isEmpty()) {
					Messages.error(this, "Department name cannot be empty");
					return;
				}
			
				// if mode == view, update data
				if (mode == Mode.EDIT) {
			
                    try {
                    	int quantity = Integer.parseInt(textQuantity.getText().strip());
						Department dep = dController.findById(Integer.parseInt(department));
						Product prod = prodController.findById(Integer.parseInt(productId));
                        shelfCtrl.updateShelf(shelf, name ,prod, quantity,dep);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }catch(NotFoundException e1){
						e1.printStackTrace();
					}

				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new department
					try {
						Department dep = dController.findById(Integer.parseInt(department));
						Product prod = prodController.findById(Integer.parseInt(productId));
						Shelf shelf =  ( shelfCtrl.createShelf(name, prod, dep));
						this.shelf = shelf;
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }catch(NotFoundException e1){
						e1.printStackTrace();
					}
				}

				//todo delete
				
			}
			// Dispose of the window
			this.dispose();
		});
	}
}