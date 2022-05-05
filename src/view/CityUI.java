package view;

import java.awt.Component;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import controller.CityController;
import model.City;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.sql.SQLException;

public class CityUI extends JDialog {
	
	public enum Mode {
		VIEW,
		EDIT,
		CREATE
	}

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtZipcode;
	private JButton btnSubmit;
	private City city;
	private CityController cityCtrl;
	private Mode mode;
	AuthenticationController auth;
	
	/**
	 * Constructor: Create a New City
	 *
	 * @param auth the auth controller 
	 * @wbp.parser.constructor
	 * @throws SQLException
	 */
	public CityUI(AuthenticationController auth) throws SQLException {
		this(auth, null, Mode.CREATE);
		this.city = null;
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public CityUI(AuthenticationController auth, City city, Mode mode) throws SQLException {
		this.auth = auth;
		this.mode = mode;
		this.city = city;
		
		cityCtrl = new CityController();
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{208, 208, 0};
		gbl_contentPane.rowHeights = new int[]{19, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		contentPane.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.anchor = GridBagConstraints.WEST;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 1;
		contentPane.add(txtName, gbc_txtName);
		
		JLabel lblZipcode = new JLabel("Zipcode *");
		GridBagConstraints gbc_Zipcode = new GridBagConstraints();
		gbc_Zipcode.anchor = GridBagConstraints.WEST;
		gbc_Zipcode.insets = new Insets(0, 0, 5, 5);
		gbc_Zipcode.gridx = 0;
		gbc_Zipcode.gridy = 2;
		contentPane.add(lblZipcode, gbc_Zipcode);
		
		txtZipcode = new JTextField();
		txtZipcode.setColumns(10);
		GridBagConstraints gbc_txtZipcode = new GridBagConstraints();
		gbc_txtZipcode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtZipcode.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtZipcode.gridx = 0;
		gbc_txtZipcode.gridy = 3;
		contentPane.add(txtZipcode, gbc_txtZipcode);
				
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 0, 5);
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.gridx = 1;
		gbc_btnOk.gridy = 5;
		contentPane.add(btnSubmit, gbc_btnOk);
		
		switch (mode) {
			case VIEW:
				// Set title
				setTitle("View City - " + city.getName());
				// Hide 'Update' button if in view mode
				btnSubmit.setVisible(false);
				// Disable fields
				this.disableFields();
				// Fill fields with content
				this.fillFields(city);
				break;
			case EDIT: 
				// Set title
				setTitle("Edit City");
				// Change submit button text to 'Update'
				btnSubmit.setText("Update");
				// Enable fields for editing
				this.enableFields();
				// Fill fields with content
				this.fillFields(city);
				break;
			case CREATE:
				// Set title
				setTitle("Add New City");
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
	 * Gets the city.
	 * Useful for Create mode (to get the created city)
	 *
	 * @return the city
	 */
	public City getCity() {
		return this.city;
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
	private void fillFields(City city) {
		txtId.setText(String.valueOf(city.getId()));
		txtName.setText(city.getName());
		txtZipcode.setText(city.getZipCode());
	}
	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		
		// 'update' button: Update the city
		btnSubmit.addActionListener(e -> {
			String message = "";
			if (mode == Mode.EDIT) {
				message = "Are you sure you want to update the city?";
			} else if (mode == Mode.CREATE) {
				message = "Add a new City?";
			}
			if (Messages.confirm(CityUI.this, message)) {
				
				// Validate name
				String name = txtName.getText().strip();
				if (name.isEmpty()) {
					Messages.error(this, "City name cannot be empty!");
					return;
				}
				
				// Validate zipcode
				String zipcode = txtZipcode.getText().strip();
				if (name.isEmpty()) {
					Messages.error(this, "Zipcode cannot be empty!");
					return;
				}
				
			
				// if mode == view, update data
				if (mode == Mode.EDIT) {
			
                    try {
                        cityCtrl.updateCity(city, zipcode, name);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

				} else if (mode == Mode.CREATE) {
					// if mode == Create, create a new product
					try {
                        City city = cityCtrl.createCity(name,zipcode);
						this.city = city;
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    };
				}


				
			}
			// Dispose of the window
			this.dispose();
		});
	}
}