package view;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import controller.AuthenticationController;
import controller.SupplyOrderController;
import exceptions.NotFoundException;
import model.SupplyOrder;

import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import view.JLink.COLORS;
import view.tableModel.SupplyOrderTableModel;
import view.tableModel.SupplyOrderTableModel.Column;

import javax.swing.JTextField;

public class CRUDSupplyOrder extends JPanel {

	private JButton btnAddSupplyOrder;
	private SupplyOrderController supplyOrderCtrl;
 	private TableRowSorter<TableModel> rowSorter;

 	private static final long serialVersionUID = -8329527605114016878L;
 	private JTable tableMain;
 	private SupplyOrderTableModel tableModel;
 	private JLink btnView;
 	private JLink btnEdit;
 	private JLink btnDelete;
 	private AuthenticationController auth;
 	private JTextField txtSearch;

 	/**
 	 * Create the dialog.
 	 * @throws SQLException
 	 * @throws NotFoundException
 	 */
	public CRUDSupplyOrder(AuthenticationController auth) throws SQLException, NotFoundException {
 		this.auth = auth;
 		supplyOrderCtrl = new SupplyOrderController();
 		setLayout(new BorderLayout(0, 0));

 		tableModel = new SupplyOrderTableModel(supplyOrderCtrl.findAll(), 
 		Arrays.asList(
 			     Column.ID,
				 Column.PRODUCTID,
				 Column.QUANTITY,
				 Column.ORDERDATE,
				 Column.SUPPLIERID,
				 Column.DELIVERED,
				 Column.ENABLED
 			    )
 	        );

 		// ***** TOP PANEL *****
 		JPanel topPanel = new JPanel();
 		this.add(topPanel, BorderLayout.NORTH);
 		GridBagLayout gbl_topPanel = new GridBagLayout();
 		gbl_topPanel.columnWidths = new int[]{0, 0, 0, 0};
 		gbl_topPanel.rowHeights = new int[]{0, 0, 0, 0};
 		gbl_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
 		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
 		topPanel.setLayout(gbl_topPanel);
 		// ***** Title *****
 		JLabel lblTitle = new JLabel(
 			"SupplyOrder"
 		);
 		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
 		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
 		gbc_lblTitle.gridx = 1;
 		gbc_lblTitle.gridy = 0;
 		topPanel.add(lblTitle, gbc_lblTitle);

 		txtSearch = new JTextField();
 		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
 		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
 		gbc_txtSearch.gridx = 0;
 		gbc_txtSearch.gridy = 1;
 		topPanel.add(txtSearch, gbc_txtSearch);
 		txtSearch.setColumns(10);

 		// ***** button: Add SupplyOrder  *****
 		btnAddSupplyOrder = new JButton("Add supply order");
 		GridBagConstraints gbc_btnAddSupplyOrder = new GridBagConstraints();
 		gbc_btnAddSupplyOrder.insets = new Insets(0, 0, 5, 0);
 		gbc_btnAddSupplyOrder.gridx = 2;
 		gbc_btnAddSupplyOrder.gridy = 1;
 		topPanel.add(btnAddSupplyOrder, gbc_btnAddSupplyOrder);

 		// ***** Middle panel: Scroll panel *****
 		JScrollPane scrollPanel = new JScrollPane();
 		add(scrollPanel, BorderLayout.CENTER);

 		// ***** Table *****
 		tableMain = new JTable();
 		tableMain.setModel(tableModel);
 		tableMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 		scrollPanel.setViewportView(tableMain);

 		// ***** Bottom panel *****
 		JPanel bottomPanel = new JPanel();
 		this.add(bottomPanel, BorderLayout.SOUTH);
 		GridBagLayout gbl_bottomPanel = new GridBagLayout();
 		gbl_bottomPanel.columnWidths = new int[]{271, 0, 0, 0, 0};
 		gbl_bottomPanel.rowHeights = new int[]{21, 0};
 		gbl_bottomPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
 		gbl_bottomPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
 		bottomPanel.setLayout(gbl_bottomPanel);

 		// ***** View button *****
 		btnView = new JLink("View", COLORS.GREEN);
 		GridBagConstraints gbc_btnView = new GridBagConstraints();
 		gbc_btnView.insets = new Insets(0, 0, 0, 5);
 		gbc_btnView.gridx = 1;
 		gbc_btnView.gridy = 0;
 		bottomPanel.add(btnView, gbc_btnView);

 		// ***** Edit button *****
 		btnEdit = new JLink("Edit", COLORS.INDIGO);
 		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
 		gbc_btnEdit.insets = new Insets(0, 0, 0, 5);
 		gbc_btnEdit.gridx = 2;
 		gbc_btnEdit.gridy = 0;
 		bottomPanel.add(btnEdit, gbc_btnEdit);

 		// ***** Delete button *****
 		btnDelete = new JLink("Delete", COLORS.RED);
 		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
 		gbc_btnDelete.gridx = 3;
 		gbc_btnDelete.gridy = 0;
 		bottomPanel.add(btnDelete, gbc_btnDelete);

 		// By default: all selection buttons disabled
 		btnView.setEnabled(false);
 		btnEdit.setEnabled(false);
 		btnDelete.setEnabled(false);

 		// Add filtering
 		rowSorter = new TableRowSorter<TableModel>(tableModel);
 		tableMain.setRowSorter(rowSorter);

 		// Attach event handler
 		this.addEventHandlers();
 	}

 	/*
 	 * *******************************************************
 	 * *******************  Methods *******************
 	 * *******************************************************
 	 */

 	public JTable getTable() {
 		return tableMain;
 	}

 	public SupplyOrderTableModel getTableModel() {
 		return tableModel;
 	}

	public void setTableModel(SupplyOrderTableModel tableModel) {
 		this.tableMain.setModel(tableModel);
 		this.tableModel = tableModel;
 		// Update table row sorter
 		rowSorter = new TableRowSorter<>(tableMain.getModel());
 		tableMain.setRowSorter(rowSorter);
 	}

 	
 	
 	public boolean selectedSupplyOrder(SupplyOrder supplyOrder  ) {
 		int rows = tableModel.getRowCount();
 		for (int i = 0; i < rows; i++) {
			SupplyOrder foundSupplyOrder = tableModel.getObj(i);
 			if (foundSupplyOrder == supplyOrder) {
 				tableMain.getSelectionModel().setSelectionInterval(0, i);
 				return true;
 			}
 		}
 		return false;
 	}

 	/*
 	 * *******************************************************
 	 * *******************  EVENT HANDLERS *******************
 	 * *******************************************************
 	 */
 	private void addEventHandlers() {
 		// Table row selection
 		tableMain.getSelectionModel().addListSelectionListener(e -> {
 			if (tableMain.getSelectionModel().isSelectionEmpty()) {
 				// Not selected
 				btnView.setEnabled(false);
 				btnEdit.setEnabled(false);
 			} else {
 				// Selected
 				//int row = tableMain.getSelectedRow();
 				//  = tableModel.getObj(row);
 				btnView.setEnabled(true);
 				btnEdit.setEnabled(true);
 				btnDelete.setEnabled(false);
 			}
 		});

 		// Delete SupplyOrder
 		btnDelete.addActionListener(e -> {
 			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
 			SupplyOrder supplyOrder = tableModel.getObj(row);
 			if (Messages.confirm(this, String.format("Are you sure you wish to delete the SupplyOrder '%s'?",
 					supplyOrder.getId()))) {
                     try {
						supplyOrderCtrl.disableSupplyOrder(supplyOrder);
                     } catch (SQLException e1) {
						e1.printStackTrace();
                     } 
                 }

 				tableModel.fireTableRowsUpdated(row, row);
 				tableMain.getSelectionModel().clearSelection();
 			}
 		);

 		// View department
 		btnView.addActionListener(e -> {
 			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
 			SupplyOrder supplyOrder = tableModel.getObj(row);
 			SupplyOrderUI frame;
             try {
                 frame = new SupplyOrderUI(auth, supplyOrder, SupplyOrderUI.Mode.VIEW);
                 frame.setVisible(true);
             } catch (SQLException e1) {
                 e1.printStackTrace();
             }
 		});

 		// Edit SupplyOrder
 		btnEdit.addActionListener(e -> {
 			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
 			SupplyOrder supplyOrder = tableModel.getObj(row);
 			SupplyOrderUI frame;
             try {
                 frame = new SupplyOrderUI(auth, supplyOrder, SupplierUI.Mode.EDIT);
                 frame.setVisible(true);
             } catch (SQLException e1) {
                 e1.printStackTrace();
             }
 			tableModel.fireTableRowsUpdated(row, row);
 			// Refresh selection (e.g. in case sell price is now set to nothing)
 			tableMain.clearSelection();
 			tableMain.getSelectionModel().setSelectionInterval(0, row);
 		});

 		// Create SupplyOrder
 		btnAddSupplyOrder.addActionListener(e -> {
			SupplyOrderUI frame;
             try {
                 frame = new SupplyOrderUI(auth, Mode.CREATE);
                 frame.setVisible(true);
                 if (frame.getSupplyOrder() != null) {
                     tableModel.add(frame.getSupplyOrder());
					 setTableModel(tableModel);
                 }
             } catch (SQLException e1) {
                 e1.printStackTrace();
             }
 		});

 		// Search implementation
 		txtSearch.getDocument().addDocumentListener(new DocumentListener(){

 			private void search() {
 				String text = txtSearch.getText();
 				if(text.trim().length() == 0) {
 					rowSorter.setRowFilter(null);
 				} else {
 					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text)));
 				}
 			}

 			@Override
 			public void insertUpdate(DocumentEvent e) {
 				search();
 			}

 			@Override
 			public void  removeUpdate(DocumentEvent e) {
 				search();
 			}

 			@Override
 			public void changedUpdate(DocumentEvent e) { /* Empty due to interface */ }
 		});
 	}
 }