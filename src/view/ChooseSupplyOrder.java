package view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthenticationController;
import exceptions.NotFoundException;
import model.SupplyOrder;
import view.tableModel.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JTable;

public class ChooseSupplyOrder extends JDialog {

	private static final long serialVersionUID = 2968937672159813565L;
	private final JPanel contentPane;
	private CRUDSupplyOrder CRUDPanel;
	private JButtonPrimary btnChoose;
	
	private SupplyOrder selectedSupplyOrder = null;
	
	AuthenticationController auth;
	private JPanel panel;

	/**
	 * Create the dialog.
	 * @throws NotFoundException
	 * @throws SQLException
	 */
	public ChooseSupplyOrder(AuthenticationController auth, SupplyOrder supplyOrder) throws SQLException, NotFoundException {
		this.auth = auth;
		this.setTitle("Choose a SupplyOrder...");
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
		
		CRUDPanel = new CRUDSupplyOrder(this.auth);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(CRUDPanel, gbc_panel);
		
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
		
		btnChoose = new JButtonPrimary("Select...");
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
		public boolean isSupplyOrderSelected() {
			return selectedSupplyOrder != null;
		}
	
		public SupplyOrder getSelectedSupplyOrder() {
			return selectedSupplyOrder;
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
				SupplyOrderTableModel tableModel = CRUDPanel.getTableModel();
				SupplyOrder supplyOrder = tableModel.getObj(table.getSelectedRow());
				selectedSupplyOrder = supplyOrder;
				this.dispose();
			}
		});
	}
	
}


