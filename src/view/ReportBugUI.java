package view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;

import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.sql.SQLException;
import java.util.*;

public class ReportBugUI extends JDialog {

	private JPanel contentPane;
	private JTextField txtTitle;
	private JButton btnSubmit;
	private JTextArea txtDescription;
	AuthenticationController auth;
	
	/**
	 * Create the frame.
	 * @throws SQLException
	 * @wbp.parser.constructor
	 */
	public ReportBugUI(AuthenticationController auth) throws SQLException {
		this.auth = auth;
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{208, 208, 0};
		gbl_contentPane.rowHeights = new int[]{0, 19, 0, 0, 127, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblTitle = new JLabel("Title *");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 1;
		contentPane.add(lblTitle, gbc_lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		GridBagConstraints gbc_txtTitle = new GridBagConstraints();
		gbc_txtTitle.anchor = GridBagConstraints.WEST;
		gbc_txtTitle.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitle.gridx = 0;
		gbc_txtTitle.gridy = 2;
		contentPane.add(txtTitle, gbc_txtTitle);
		
		JLabel lblDescription = new JLabel("Description *");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.WEST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 3;
		contentPane.add(lblDescription, gbc_lblDescription);
		
		txtDescription = new JTextArea();
		txtDescription.setLineWrap(true);
		GridBagConstraints gbc_txtDescription = new GridBagConstraints();
		gbc_txtDescription.gridwidth = 2;
		gbc_txtDescription.insets = new Insets(0, 0, 5, 0);
		gbc_txtDescription.fill = GridBagConstraints.BOTH;
		gbc_txtDescription.gridx = 0;
		gbc_txtDescription.gridy = 4;
		contentPane.add(txtDescription, gbc_txtDescription);
		
		
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 6;
		contentPane.add(btnSubmit, gbc_btnOk);
		
		addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	
	 private void reportIssue() {
		 // Recipient's email ID needs to be mentioned.
		 String to = "djl60625@jeoce.com";

		 // Sender's email ID needs to be mentioned
		 String from = "test@gmail.com";
   
		 // Assuming you are sending email from localhost
		 String host = "localhost";
   
		 // Get system properties
		 Properties properties = System.getProperties();
   
		 // Setup mail server
		 properties.setProperty("mail.smtp.host", host);
   
		 // Get the default Session object.
		 Session session = Session.getDefaultInstance(properties);
   
		 try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
   
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
   
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
   
			// Set Subject: header field
			message.setSubject("This is the Subject Line!");
   
			// Now set the actual message
			message.setText("First bug report xdd");
   
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		 } catch (MessagingException mex) {
			mex.printStackTrace();
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

			// Validate name
			String name = txtTitle.getText().strip();
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


            Messages.info(null, "Problem has been reported");
			// Dispose of the window
			this.dispose();
		});
	}
}
