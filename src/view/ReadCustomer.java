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
import model.Customer;


public class ReadCustomer extends JFrame {

	private JPanel contentPane;
	private JLabel lblCustomerNameText;
	private JLabel lblCustomerName;
	private JLabel lblCustomerEmailText;
	private JLabel lblCustomerEmail;
	private JLabel lblCustomerPhoneText;
	private JLabel lblCustomerPhone;
	private JLabel lblCustomerZipcodeText;
	private JLabel lblCustomerZipcode;
	private JLabel lblCustomerAddressText;
	private JLabel lblCustomerAddress;
	private JLabel lblCustomerIdText;
	private JLabel lblCustomerId;

	private Customer customer;
	private CustomerController CustomerCtrl;

	public ReadCustomer(Customer customer) {
		try {
			CustomerCtrl = new CustomerController();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.customer = customer;

		//WINDOW
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		//TOP PANEL
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_topPanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_topPanel.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_topPanel.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		topPanel.setLayout(gbl_topPanel);

		//Title
		JLabel lblTitle = new JLabel("Customer information");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 4;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);

		//Middle panel
		JPanel middlePanel = new JPanel(new GridLayout(5, 2));
		getContentPane().add(middlePanel);

		//customer name
		lblCustomerNameText = new JLabel("Name");
		lblCustomerNameText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerNameText);

		lblCustomerName = new JLabel(String.format("%s", customer.getName()));
		lblCustomerName.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerName);

		//customer email 
		lblCustomerEmailText = new JLabel("Email");
		lblCustomerEmailText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerEmailText);

		lblCustomerEmail = new JLabel(String.format("%s", customer.getEmail()));
		lblCustomerEmail.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerEmail);

		//customer phone
		lblCustomerPhoneText = new JLabel("Phone");
		lblCustomerPhoneText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerPhoneText);

		lblCustomerPhone = new JLabel(String.format("%s", customer.getPhone()));
		lblCustomerPhone.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerPhone);

		//customer zip
		lblCustomerZipcodeText = new JLabel("Zip code");
		lblCustomerZipcodeText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerZipcodeText);

		lblCustomerZipcode = new JLabel(String.format("%s", customer.getZipCode().getZipCode()));
		lblCustomerZipcode.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerZipcode);

		//customer address
		lblCustomerAddressText = new JLabel("Address");
		lblCustomerAddressText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerAddressText);

		lblCustomerAddress = new JLabel(String.format("%s", customer.getAddress()));
		lblCustomerAddress.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerAddress);

		//customer id
		lblCustomerIdText = new JLabel("Id");
		lblCustomerIdText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerIdText);

		lblCustomerId = new JLabel(String.format("%s", customer.getId()));
		lblCustomerId.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblCustomerId);

		}
}
