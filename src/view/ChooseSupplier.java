package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import exceptions.NotFoundException;
import model.Supplier;
import view.tableModel.SupplierTableModel;

public class ChooseSupplier extends JDialog{

	private static final long serialVersionUID = 2968937672159813565L;
	private final JPanel contentPane;
	private CRUDSuppliers CRUDPanel;
	private JButtonPrimary btnChoose;
	private JSpinner spinner;
	
	private Supplier selectedSupplier = null;
	
	AuthenticationController auth;
	private JPanel panel;
	
	/**
	 * Create the dialog.
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	public ChooseSupplier(AuthenticationController auth) throws SQLException, NotFoundException {
		this.auth = auth;
		this.setTitle("Choose a supplier...");
		setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{210, 25, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		CRUDPanel = new CRUDSuppliers(this.auth);
		
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
		
		panel = new JPanel();
		GridBagConstraints gbc_panel1 = new GridBagConstraints();
		gbc_panel1.fill = GridBagConstraints.BOTH;
		gbc_panel1.gridx = 0;
		gbc_panel1.gridy = 2;
		contentPane.add(panel, gbc_panel1);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnChoose = new JButtonPrimary("Choose...");
		GridBagConstraints gbc_btnChoose = new GridBagConstraints();
		gbc_btnChoose.gridx = 18;
		gbc_btnChoose.gridy = 0;
		panel.add(btnChoose, gbc_btnChoose);
		btnChoose.setEnabled(false);
		
		// Attach event handlers
		this.addEventHandlers();
	}
	
	/*
	 * *******************************************************
	 * *******************  Methods *******************
	 * *******************************************************
	 */
	public boolean isSupplierSelected() {
		return selectedSupplier != null;
	}
	
	public Supplier getSelectedSupplier() {
		return selectedSupplier;
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
				SupplierTableModel tableModel = CRUDPanel.getTableModel();
				Supplier supplier = tableModel.getObj(table.getSelectedRow());
				selectedSupplier = supplier;
				this.dispose();
			}
		});
	}
}
