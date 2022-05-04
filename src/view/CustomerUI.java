package view;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.CityController;
import controller.CustomerController;
import controller.CustomerTypeController;
import exceptions.NotFoundException;
import model.City;
import model.Customer;
import model.Customer.CustomerType;
import controller.CustomerTypeController;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
	private CustomerTypeController custTypeCtrl;
	private Mode mode;
	AuthenticationController auth;
	private JPanel panel;
    private CustomerType customerType;
	private JTextField textField;
    private JTextField textPhone;
    private JLabel lblCustomerType;
    private JComboBox comboBoxCustomerType;
    private JLabel lblphone;
	private City city;
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
		custTypeCtrl = new CustomerTypeController();
		
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
		gbc_lblphone.insets = new Insets(0, 0, 5, 0);
		gbc_lblphone.gridx = 1;
		gbc_lblphone.gridy = 3;
		contentPane.add(lblphone, gbc_lblphone);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 4;
		contentPane.add(textField, gbc_textField);
		
		textPhone = new JTextField();
		textPhone.setColumns(10);
		GridBagConstraints gbc_textPhone = new GridBagConstraints();
		gbc_textPhone.insets = new Insets(0, 0, 5, 0);
		gbc_textPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPhone.gridx = 1;
		gbc_textPhone.gridy = 4;
		contentPane.add(textPhone, gbc_textPhone);
		
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
		
		comboBoxCustomerType = new JComboBox(Customer.CustomerType.values());
		GridBagConstraints gbc_comboBoxCustomerType = new GridBagConstraints();
		gbc_comboBoxCustomerType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCustomerType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCustomerType.gridx = 0;
		gbc_comboBoxCustomerType.gridy = 10;
		contentPane.add(comboBoxCustomerType, gbc_comboBoxCustomerType);
		
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
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JComboBox) {
				      c.setEnabled(false);
				   }
			}
		txtId.setEnabled(false);
		comboBoxCustomerType.setEnabled(false);
	}
	
	
	// Makes the text fields editable except ID field
	private void enableFields() {
		for (Component c : this.getContentPane().getComponents()) {
			   if (c instanceof JTextField || c instanceof JTextArea || c instanceof JComboBox) {
			      c.setEnabled(true);
			   }
			}
		txtId.setEnabled(false);
		comboBoxCustomerType.setEnabled(true);
	}
	
	// FIll in the fields
	private void fillFields(Customer customer) {
		txtId.setText(String.valueOf(customer.getId()));
		txtName.setText(customer.getName());
		textField.setText(customer.getEmail());
		textPhone.setText(customer.getPhone());
		txtZip.setText(customer.getZipCode().getZipCode());
		this.city = customer.getZipCode();
		txtAddress.setText(customer.getAddress());
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		btnSelect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "";
				if(mode == mode.EDIT){
					message ="Are you sure you want to make changes to the customer?"; 
				}
				else if( mode == mode.CREATE){
					message = "Do you want to create a new customer?";
				}
				if(Messages.confirm(null, message)){
					//TODO: validate here
					
					if(mode == mode.EDIT){
						try{
							CustomerTypeController customerTypeController = new CustomerTypeController();
							
							customer.setName(txtName.getText());
							customer.setEmail(textField.getText());
							customer.setPhone(textPhone.getText());
							customer.setAddress(txtAddress.getText());
							customer.setZipCode(city);
							customer.setCustomerType(customerTypeController.findByName(comboBoxCustomerType.getSelectedItem().toString()));
							customerCtrl.updateCustomer(customer);
							Messages.info(null, "Customer updated");
						}catch(SQLException ex){
							ex.printStackTrace();
						}catch(NotFoundException exception){
							exception.printStackTrace();
						}
					}else if (mode == mode.CREATE){
						try{
							String name = txtName.getText();
							String email = textField.getText();
							String phone = textPhone.getText();
							String address = txtAddress.getText();
							CustomerType customerType = custTypeCtrl.findByName(comboBoxCustomerType.getSelectedItem().toString());
							customerCtrl.createCustomer(name, email, phone, city , address, customerType);
							Messages.info(null, "Customer created");
						}catch(SQLException er){
							er.printStackTrace();
						}catch(NotFoundException er){
							er.printStackTrace();
						}
					}
				}
			}
		});

	}
}
