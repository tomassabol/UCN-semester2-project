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

import controller.CityController;
import controller.CustomerController;
import controller.CustomerTypeController;
import exceptions.NotFoundException;
import model.City;
import model.Customer;
import model.Employee;
import model.Customer.CustomerType;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;


public class CreateCustomer extends JFrame {

	private JPanel contentPane;
	private JLabel lblCustomerNameText;
	private JLabel lblCustomerEmailText;
	private JLabel lblCustomerPhoneText;
	private JLabel lblCustomerZipcodeText;
	private JLabel lblCustomerAddressText;

	private Customer customer;
	private CustomerController CustomerCtrl;
	private CustomerTypeController custTypeCtrl;
	private JTextField textName;
	private JTextField textEmail;
	private JTextField textPhone;
	private JTextField textZip;
	private JTextField textAddress;
	private JButton btnSaveChanges;
	private JLabel lblCustomerType;
	private JComboBox customerTypeBox;

	public CreateCustomer() {
		try {
			CustomerCtrl = new CustomerController();
			custTypeCtrl = new CustomerTypeController();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


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
		JLabel lblTitle = new JLabel("Create customer");
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
		
		btnSaveChanges = new JButton("Create Customer");
		
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

		//customer email 
		lblCustomerEmailText = new JLabel("Email");
		lblCustomerEmailText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerEmailText);
		
		textEmail = new JTextField();
		middlePanel.add(textEmail);
		textEmail.setColumns(10);		

		//customer phone
		lblCustomerPhoneText = new JLabel("Phone");
		lblCustomerPhoneText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerPhoneText);
		
		textPhone = new JTextField();
		middlePanel.add(textPhone);
		textPhone.setColumns(10);

		//customer zip
		lblCustomerZipcodeText = new JLabel("Zip code");
		lblCustomerZipcodeText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerZipcodeText);
		
		textZip = new JTextField();
		middlePanel.add(textZip);
		textZip.setColumns(10);

		//customer address
		lblCustomerAddressText = new JLabel("Address");
		lblCustomerAddressText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerAddressText);
		
		textAddress = new JTextField();
		middlePanel.add(textAddress);
		textAddress.setColumns(10);
		
		lblCustomerType = new JLabel("CustomerType");
		middlePanel.add(lblCustomerType);
		
		customerTypeBox = new JComboBox(Customer.CustomerType.values());
		customerTypeBox.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(customerTypeBox);
		
		ActionListener();
	}

	public void ActionListener(){
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String name = textName.getText();
					String email = textEmail.getText();
					String phone = textPhone.getText();
					//TODO: i need city to be finished to finish this
					City zipCode = new City("CZ-60200", "Brno");
					String address = textAddress.getText();
					CustomerType customerType = custTypeCtrl.findByName(customerTypeBox.getSelectedItem().toString());
					CustomerCtrl.createCustomer(name, email, phone, zipCode , address, customerType);
				}catch(SQLException ex){
					ex.printStackTrace();
				}catch(NotFoundException ee){
					ee.printStackTrace();
				}
			}
		});
	}
	
}
