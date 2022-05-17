package view;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.CustomerController;
import exceptions.NotFoundException;
import model.City;
import model.Customer;
import model.Customer.CustomerType;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class CustomerUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtZip;
	private JTextField txtAddress;
	private JButton btnSubmit;
	private Customer customer;
	private CustomerController customerCtrl;
	private Mode mode;
	AuthenticationController auth;
	private JPanel panel;
	private JTextField txtEmail;
    private JTextField txtPhone;
    private JLabel lblCustomerType;
    private JComboBox<CustomerType> boxCustomerType;
    private JLabel lblphone;
	private City zipCode;
	private JButton btnSelect;

	/**
	 * Constructor: create new customer
	 *@wbp.parser.constructor
	 * @throws SQLException
	 */
	public CustomerUI(AuthenticationController auth) throws SQLException {
		this(auth, null, Mode.CREATE);
		this.customer = null;
	}
	
	
	public CustomerUI(AuthenticationController auth, Customer customer, Mode mode) throws SQLException {
		this.auth = auth;
		this.mode = mode;
		this.customer = customer;
		//SAD
		customerCtrl = new CustomerController();
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{208, 208, 0};
		gbl_contentPane.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		JLabel lblName = new JLabel("Name");
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
		
		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		lblphone = new JLabel("Phone");
		GridBagConstraints gbc_lblphone = new GridBagConstraints();
		gbc_lblphone.anchor = GridBagConstraints.WEST;
		gbc_lblphone.insets = new Insets(0, 0, 5, 0);
		gbc_lblphone.gridx = 1;
		gbc_lblphone.gridy = 3;
		contentPane.add(lblphone, gbc_lblphone);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 0;
		gbc_txtEmail.gridy = 4;
		contentPane.add(txtEmail, gbc_txtEmail);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		GridBagConstraints gbc_txtPhone = new GridBagConstraints();
		gbc_txtPhone.insets = new Insets(0, 0, 5, 0);
		gbc_txtPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhone.gridx = 1;
		gbc_txtPhone.gridy = 4;
		contentPane.add(txtPhone, gbc_txtPhone);
		
		JLabel lblZip = new JLabel("Zip");
		GridBagConstraints gbc_lblZip = new GridBagConstraints();
		gbc_lblZip.anchor = GridBagConstraints.WEST;
		gbc_lblZip.insets = new Insets(0, 0, 5, 5);
		gbc_lblZip.gridx = 0;
		gbc_lblZip.gridy = 6;
		contentPane.add(lblZip, gbc_lblZip);
		
		txtZip = new JTextField();
		txtZip.setColumns(10);
		GridBagConstraints gbc_txtZip = new GridBagConstraints();
		gbc_txtZip.insets = new Insets(0, 0, 5, 5);
		gbc_txtZip.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtZip.gridx = 0;
		gbc_txtZip.gridy = 7;
		contentPane.add(txtZip, gbc_txtZip);
		
		btnSelect = new JButton("Select");
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.anchor = GridBagConstraints.WEST;
		gbc_btnSelect.insets = new Insets(0, 0, 5, 0);
		gbc_btnSelect.gridx = 1;
		gbc_btnSelect.gridy = 7;
		contentPane.add(btnSelect, gbc_btnSelect);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 9;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblCustomerType = new JLabel("Customer type");
		GridBagConstraints gbc_lblCustomerType = new GridBagConstraints();
		gbc_lblCustomerType.insets = new Insets(0, 0, 0, 5);
		gbc_lblCustomerType.gridx = 0;
		gbc_lblCustomerType.gridy = 0;
		panel.add(lblCustomerType, gbc_lblCustomerType);
		
		JLabel lblAddress = new JLabel("Address");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.WEST;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 0);
		gbc_lblAddress.gridx = 1;
		gbc_lblAddress.gridy = 9;
		contentPane.add(lblAddress, gbc_lblAddress);
		
		boxCustomerType = new JComboBox<>(CustomerType.values());
		GridBagConstraints gbc_boxCustomerType = new GridBagConstraints();
		gbc_boxCustomerType.insets = new Insets(0, 0, 5, 5);
		gbc_boxCustomerType.fill = GridBagConstraints.HORIZONTAL;
		gbc_boxCustomerType.gridx = 0;
		gbc_boxCustomerType.gridy = 10;
		contentPane.add(boxCustomerType, gbc_boxCustomerType);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		GridBagConstraints gbc_txtAddress = new GridBagConstraints();
		gbc_txtAddress.insets = new Insets(0, 0, 5, 0);
		gbc_txtAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddress.gridx = 1;
		gbc_txtAddress.gridy = 10;
		contentPane.add(txtAddress, gbc_txtAddress);
		
		
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 11;
		contentPane.add(btnSubmit, gbc_btnOk);
		
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View Customer - " + customer.getName());
				// Hide 'Update' button if in view mode
				btnSubmit.setVisible(false);
				// Disable fields
				this.disableFields();
				// Fill fields with content
				this.fillFields(customer);
				break;
			case EDIT: 
				// Set title
				setTitle("Edit Customer");
				// Change submit button text to 'Update'
				btnSubmit.setText("Update");
				// Enable fields for editing
				this.enableFields();
				// Fill fields with content
				this.fillFields(customer);
				break;
			case CREATE:
				// Set title
				setTitle("Add New Customer");
				// Change submit button text to 'Create'
				btnSubmit.setText("Create");
				// Enable fields
				this.enableFields();
				// Peek ID
				//txtId.setText(String.valueOf(customer.getId()));
		}
		
		
		
		addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	/**
	 * Gets the customer.
	 * Useful for Create mode (to get the created customer)
	 *
	 * @return the customert
	 */
	public Customer getCustomer() {
		return this.customer;
	}
	
	// Makes the text fields 
	private void disableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JComboBox || c instanceof JButton) {
				      c.setEnabled(false);
				   }
			}
		txtId.setEnabled(false);
		boxCustomerType.setEnabled(false);
		txtZip.setEnabled(false);
	}
	
	
	// Makes the text fields editable except ID field
	private void enableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JComboBox || c instanceof JButton) {
			      c.setEnabled(true);
			   }
			}
		txtId.setEnabled(false);
		boxCustomerType.setEnabled(true);
		txtZip.setEnabled(true);
	}
	
	// FIll in the fields
	private void fillFields(Customer customer) {
		txtId.setText(String.valueOf(customer.getId()));
		txtName.setText(customer.getName());
		txtEmail.setText(customer.getEmail());
		txtPhone.setText(customer.getPhone());
		txtZip.setText(customer.getZipCode().getZipCode());
		this.zipCode = customer.getZipCode();
		txtAddress.setText(customer.getAddress());
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {

		btnSubmit.addActionListener(e -> {
			String message = "";
			if (mode == Mode.EDIT) {
				message = "Are you sure you want to update the changes to Customer?";
			} else if (mode == Mode.CREATE) {
				message = "Create Customer?";
			}
			if (Messages.confirm(CustomerUI.this, message)) {
				
				// Validate name
				String name = txtName.getText().strip();
				if (name.isEmpty()) {
					Messages.error(this, "Customer name cannot be empty");
					return;
				}
				
				// Validate email
				String email = txtEmail.getText().strip();
				if (email.isEmpty()) {
					Messages.error(this, "Customer email cannot be empty");
					return;
				}
				
				// Validate phone
				String phone = txtPhone.getText().strip();
				if (phone.isEmpty()) {
					Messages.error(this, "Customer phone cannot be empty");
					return;
				}
				
				// Validate zip
                if (zipCode == null) {
                    Messages.error(this, "You must choose a Zip Code");
					return;
                }
						
				// Validate address
				String address = txtAddress.getText().strip();
				if (address.isEmpty()) {
					Messages.error(this, "Customer Address cannot be empty");
					return;
				}
			
				CustomerType customerType = (CustomerType) boxCustomerType.getSelectedItem();
				if (customerType == null) {
					Messages.error(this, "Customer type cannot be empty. Select Employee Type");
					return;
				}

				
				// if mode == view, update data
				if (mode == Mode.EDIT) {
			
                    try {
                        customerCtrl.updateCustomer(customer, name, email, phone, zipCode, address, customerType);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new employee
					try {
                        Customer customer = customerCtrl.createCustomer(name, email, phone, zipCode, address, customerType);
						this.customer = customer;
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    };
				}


				
			}
			// Dispose of the window
			this.dispose();
		});

		btnSelect.addActionListener(e -> {
			ChooseCity frame;
			try {
				frame = new ChooseCity(auth);
				frame.setVisible(true);
				if (frame.getSelectedCity() != null) {
					this.zipCode = frame.getSelectedCity();
					txtZip.setText(zipCode.getZipCode());
				}
			} catch (SQLException | NotFoundException e1) {
				e1.printStackTrace();
			}
		});

	}
}
