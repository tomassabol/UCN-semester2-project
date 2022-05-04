package view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import exceptions.NotFoundException;
import view.tableModel.*;
import model.Product;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import java.awt.Font;

public class ChooseProduct extends JDialog {

	private static final long serialVersionUID = 2968937672159813565L;
	private final JPanel contentPane;
	private CRUDProducts CRUDPanel;
	private JButtonPrimary btnChoose;
	
	private Product selectedProduct = null;
	private int selectedQuantity;
	private int quantity;
	
	AuthenticationController auth;

	/**
	 * Create the dialog.
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	public ChooseProduct(AuthenticationController auth) throws SQLException, NotFoundException {
		this.auth = auth;
		this.setTitle("Choose a product...");
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 420, 0};
		gbl_contentPane.rowHeights = new int[]{210, 25, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		CRUDPanel = new CRUDProducts(this.auth);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(CRUDPanel, gbc_panel);
		
		//Create the spinner which makes the edit quantity look better
		/*SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		JSpinner spinner = new JSpinner(spinnerModel);
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		
		try {
			int quantity = Integer.parseInt(String.valueOf(spinner.getValue()));
		} catch (NumberFormatException e1) {
			Messages.error(contentPane, "The given value was not a number");
		}*/
		
		JLabel lblSpinner = new JLabel("Quantity:");
		lblSpinner.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblSpinner = new GridBagConstraints();
		gbc_lblSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_lblSpinner.gridx = 0;
		gbc_lblSpinner.gridy = 1;
		contentPane.add(lblSpinner, gbc_lblSpinner);
		
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		JSpinner spinner = new JSpinner(spinnerModel);
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.insets = new Insets(0, 0, 0, 5);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 1;
		contentPane.add(spinner, gbc_spinner);
		
		try {
			quantity = Integer.parseInt(String.valueOf(spinner.getValue()));
		} catch (NumberFormatException e1) {
			Messages.error(contentPane, "The given value was not a number");
		}
		
		btnChoose = new JButtonPrimary("Choose...");
		btnChoose.setEnabled(false);
		GridBagConstraints gbc_btnChoose = new GridBagConstraints();
		gbc_btnChoose.anchor = GridBagConstraints.EAST;
		gbc_btnChoose.gridx = 2;
		gbc_btnChoose.gridy = 1;
		contentPane.add(btnChoose, gbc_btnChoose);
		
		// Attach event handlers
		this.addEventHandlers();
	}
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
		public boolean isProductSelected() {
			return selectedProduct != null;
		}
	
		public Product getSelectedProduct() {
			return selectedProduct;
		}
		
		public int getSelectedQuantity() {
			return selectedQuantity;
		}

	
	/*
	 * *******************************************************
	 * *******************  EVENT HANDLERS *******************
	 * *******************************************************
	 */
	private void addEventHandlers() {
		CRUDPanel.getTable().getSelectionModel().addListSelectionListener(e -> {
			JTable table = CRUDPanel.getTable();
			// Toggle 'choose' button
			if (table.getSelectionModel().isSelectionEmpty()) {
				// ** not selected **
				btnChoose.setEnabled(false);
			} else {
				//** selected**
				btnChoose.setEnabled(true);
				// get product
				int row = table.convertRowIndexToModel(table.getSelectedRow());
				CRUDPanel.getTableModel().getObj(row);
			}
			
		});
		
		// Choose button
		btnChoose.addActionListener(e -> {
			JTable table = CRUDPanel.getTable();
			if (!table.getSelectionModel().isSelectionEmpty()) {
				CustomerTableModel tableModel = CRUDPanel.getTableModel();
				Product product = tableModel.getObj(table.getSelectedRow());
				selectedProduct = product;
				selectedQuantity = quantity;
				this.dispose();
			}
		});
	}
	
}


