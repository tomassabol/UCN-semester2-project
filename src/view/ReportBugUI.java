package view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.sql.SQLException;

import model.Mail;
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
	public ReportBugUI(AuthenticationController auth) {
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
		btnSubmit.setForeground(new Color(255,255,255));
		btnSubmit.setBackground(new Color(183,26,134,255));
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
	
	
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the product
		btnSubmit.addActionListener(e -> {

			// Validate name
			String title = txtTitle.getText().strip();
			if (title.isEmpty()) {
				Messages.error(this, "Product name cannot be empty!");
				return;
			}
			
			// Validate description
			String description = txtDescription.getText().strip();
			if (description.isEmpty()) {
				Messages.error(this, "Product description cannot be empty!");
				return;
            }

			try {
				Mail.sendMail(title, description);
				Messages.info(null, "Problem has been reported");
			} catch (MessagingException e1) {
				e1.printStackTrace();
				Messages.error(this, "Error occured when trying to send an email. Try again later");
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
				Messages.error(this, "Error with collecting system information");
			}

			// Dispose of the window
			this.dispose();
		});
	}
}
