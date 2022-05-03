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

import controller.EmployeeController;
import controller.OrderController;
import exceptions.NotFoundException;
import model.Employee;

public class ReadEmployee extends JFrame {
	
	private JPanel contentPane;
	private JLabel lblEmployeeNameText;
	private JLabel lblEmployeeName;
	private JLabel lblEmployeeEmailText;
	private JLabel lblEmployeeEmail;
	private JLabel lblEmployeePhoneText;
	private JLabel lblEmployeePhone;
	private JLabel lblEmployeeZipcodeText;
	private JLabel lblEmployeeZipcode;
	private JLabel lblEmployeeAddressText;
	private JLabel lblEmployeeAddress;
	private JLabel lblEmployeeTypeText;
	private JLabel lblEmployeeType;
	private JLabel lblEmployeePasswordText;
	private JLabel lblEmployeePassword;
	private JLabel lblEmployeeCPRText;
	private JLabel lblEmployeeCPR;
	private JLabel lblEmployeeDepartmentText;
	private JLabel lblEmployeeDepartment;
	
	private Employee employee;
	private EmployeeController employeeCtrl;
	
	public ReadEmployee(Employee employee) {
		try {
			employeeCtrl = new EmployeeController();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.employee = employee;
		
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
		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_topPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_topPanel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		// ***** Title *****
		JLabel lblTitle = new JLabel("Employee information");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 4;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);
		
		// ***** Middle panel *****
		JPanel middlePanel = new JPanel(new GridLayout(5, 2));
		getContentPane().add(middlePanel);
		
		// ***** Employee name *****
		lblEmployeeNameText = new JLabel("Name");
		lblEmployeeNameText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeNameText);
		
		lblEmployeeName = new JLabel(String.format("%s", employee.getName()));
		lblEmployeeName.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeName);
		
		// ***** Employee email *****
		lblEmployeeEmailText = new JLabel("Email");
		lblEmployeeEmailText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeEmailText);
		
		lblEmployeeEmail = new JLabel(String.format("%s", employee.getEmail()));
		lblEmployeeEmail.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeEmail);
		
		// ***** Employee phone *****
		lblEmployeePhoneText = new JLabel("Phone");
		lblEmployeePhoneText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeePhoneText);
		
		lblEmployeePhone = new JLabel(String.format("%s", employee.getPhone()));
		lblEmployeePhone.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeePhone);
		
		// ***** Employee zip code *****
		lblEmployeeZipcodeText = new JLabel("Zip code");
		lblEmployeeZipcodeText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeZipcodeText);
		
		lblEmployeeZipcode = new JLabel(String.format("%s", employee.getZipCode().getZipCode()));
		lblEmployeeZipcode.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeZipcode);
		
		// ***** Employee address *****
		lblEmployeeAddressText = new JLabel("Address");
		lblEmployeeAddressText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeAddressText);
		
		lblEmployeeAddress = new JLabel(String.format("%s", employee.getAddress()));
		lblEmployeeAddress.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeAddress);
		
		// ***** Employee type *****
		lblEmployeeTypeText = new JLabel("Type");
		lblEmployeeTypeText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeTypeText);
		
		lblEmployeeType = new JLabel(String.format("%s", employee.getEmployeeType()));
		lblEmployeeType.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeType);
		
		// ***** Employee password *****
		lblEmployeePasswordText = new JLabel("Password");
		lblEmployeePasswordText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeePasswordText);
		
		lblEmployeePassword = new JLabel(String.format("%s", employee.getPassword()));
		lblEmployeePassword.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeePassword);
		
		// ***** Employee CPR *****
		lblEmployeeCPRText = new JLabel("CPR");
		lblEmployeeCPRText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeCPRText);
		
		lblEmployeeCPR = new JLabel(String.format("%s", employee.getCPR()));
		lblEmployeeCPR.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeCPR);
		
		// ***** Employee department *****
		lblEmployeeDepartmentText = new JLabel("Department");
		lblEmployeeDepartmentText.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeDepartmentText);
		
		lblEmployeeDepartment = new JLabel(String.format("%s", employee.getDepartment().getName()));
		lblEmployeeDepartment.setFont(new Font("Open Sans", Font.PLAIN, 10));
		middlePanel.add(lblEmployeeDepartment);
	}
}
