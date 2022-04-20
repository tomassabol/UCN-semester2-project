package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JButton btnLogin;

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 187, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Open Sans", Font.BOLD, 15));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 0);
		gbc_lblEmail.gridx = 7;
		gbc_lblEmail.gridy = 2;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 7;
		gbc_txtEmail.gridy = 3;
		contentPane.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Open Sans", Font.BOLD, 15));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 0);
		gbc_lblPassword.gridx = 7;
		gbc_lblPassword.gridy = 4;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 7;
		gbc_passwordField.gridy = 5;
		contentPane.add(passwordField, gbc_passwordField);
		
		btnLogin = new JButton("Log in");
		btnLogin.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.gridx = 7;
		gbc_btnLogin.gridy = 7;
		contentPane.add(btnLogin, gbc_btnLogin);
		
		//Add the event handlers
		addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * ***********************  Methods **********************
	 * *******************************************************
	 */
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	public void addEventHandlers() {
		
	}
	

}