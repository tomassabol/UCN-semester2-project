package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import controller.CustomerTypeController;
import exceptions.NotFoundException;
import model.Customer;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;


public class UpdateCustomer extends JFrame {

	private JPanel contentPane;
	private JLabel lblCustomerNameText;
	private JLabel lblCustomerEmailText;
	private JLabel lblCustomerPhoneText;
	private JLabel lblCustomerZipcodeText;
	private JLabel lblCustomerAddressText;
	private JLabel lblCustomerIdText;
	private JLabel lblCustomerId;

	private Customer customer;
	private CustomerController CustomerCtrl;
	private JTextField textName;
	private JTextField textEmail;
	private JTextField textPhone;
	private JTextField textZip;
	private JTextField textAddress;
	private JButton btnSaveChanges;
	private JLabel lblCustomerType;
	private JComboBox comboBox;

	public UpdateCustomer(Customer customer) {
		try {
			CustomerCtrl = new CustomerController();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.customer = customer;

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
		gbl_topPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_topPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_topPanel.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_topPanel.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		topPanel.setLayout(gbl_topPanel);

		// ***** Title *****
		JLabel lblTitle = new JLabel("Customer information");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 4;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);

		//Middle panel
		JPanel middlePanel = new JPanel(new GridLayout(7, 2));
		getContentPane().add(middlePanel);
		
		//Bottom panel
		JPanel bottomPanel = new JPanel();
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottomPanel = new GridBagLayout();
		gbl_bottomPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10};
		gbl_bottomPanel.rowHeights = new int[]{0, 10};
		gbl_bottomPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bottomPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		bottomPanel.setLayout(gbl_bottomPanel);
		
		btnSaveChanges = new JButton("Save Changes");
		
		GridBagConstraints gbc_btnSaveChanges = new GridBagConstraints();
		gbc_btnSaveChanges.gridx = 14;
		gbc_btnSaveChanges.gridy = 0;
		bottomPanel.add(btnSaveChanges, gbc_btnSaveChanges);
		

		//customer name
		lblCustomerNameText = new JLabel("Name");
		lblCustomerNameText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerNameText);
		
		textName = new JTextField();
		middlePanel.add(textName);
		textName.setColumns(10);
		textName.setText(customer.getName());

		//customer email 
		lblCustomerEmailText = new JLabel("Email");
		lblCustomerEmailText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerEmailText);
		
		textEmail = new JTextField();
		middlePanel.add(textEmail);
		textEmail.setColumns(10);
		textEmail.setText(customer.getEmail());

		//customer phone
		lblCustomerPhoneText = new JLabel("Phone");
		lblCustomerPhoneText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerPhoneText);
		
		textPhone = new JTextField();
		middlePanel.add(textPhone);
		textPhone.setColumns(10);
		textPhone.setText(customer.getPhone());

		//customer zip
		lblCustomerZipcodeText = new JLabel("Zip code");
		lblCustomerZipcodeText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerZipcodeText);
		
		textZip = new JTextField();
		middlePanel.add(textZip);
		textZip.setColumns(10);
		textZip.setText(customer.getZipCode().getZipCode());

		//customer address
		lblCustomerAddressText = new JLabel("Address");
		lblCustomerAddressText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerAddressText);
		
		textAddress = new JTextField();
		middlePanel.add(textAddress);
		textAddress.setColumns(10);
		textAddress.setText(customer.getAddress());;

		//Customer type
		lblCustomerType = new JLabel("Customer type");
		lblCustomerType.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerType);
		
		comboBox = new JComboBox(Customer.CustomerType.values());
		comboBox.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(comboBox);
		
		lblCustomerIdText = new JLabel("Id");
		lblCustomerIdText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerIdText);

		lblCustomerId = new JLabel(String.format("%s", customer.getId()));
		lblCustomerId.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerId);

		ActionListener();
		
	}
		public void ActionListener(){
			btnSaveChanges.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
						CustomerTypeController customerTypeController = new CustomerTypeController();
						customer.setAddress(textAddress.getText());
						customer.setEmail(textEmail.getText());
						customer.setName(textName.getText());
						customer.setPhone(textPhone.getText());
						//TODO: zip
						customer.setCustomerType(customerTypeController.findByName(comboBox.getSelectedItem().toString()));
						CustomerCtrl.updateCustomer(customer);
						//TODO: message
					}catch(SQLException ex){
						ex.printStackTrace();
					}catch(NotFoundException exception){
						exception.printStackTrace();
					}
				}
			});
		}
	
}
