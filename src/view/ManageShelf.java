package view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import exceptions.NotFoundException;

import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.awt.GridBagConstraints;

public class ManageShelf extends JDialog {

	private static final long serialVersionUID = 2968937622159813565L;
	private final JPanel contentPane;
	private CRUDShelf CRUDPanel;
	
	AuthenticationController auth;


	/**
	 * Create the dialog.
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	public ManageShelf(AuthenticationController auth) throws SQLException, NotFoundException {
		this.auth = auth;
		this.setTitle("Manage Shelves");
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{420, 0};
		gbl_contentPane.rowHeights = new int[]{210, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		CRUDPanel = new CRUDShelf(auth);
		GridBagConstraints gbc_CRUDPanel = new GridBagConstraints();
		gbc_CRUDPanel.fill = GridBagConstraints.BOTH;
		gbc_CRUDPanel.gridx = 0;
		gbc_CRUDPanel.gridy = 0;
		getContentPane().add(CRUDPanel, gbc_CRUDPanel);
		
		// Attach event handlers
		this.addEventHandlers();
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
	}
	
}


