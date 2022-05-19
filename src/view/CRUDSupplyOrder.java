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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CRUDSupplyOrder extends JPanel {

	private static final long serialVersionUID = -8329527605114016878L;

	private JButton btnAddSupplyOrder;
 	private JTable tableMain;
 	private JLink btnView;
 	private JLink btnEdit;
 	private JLink btnDelete;
 	private JTextField txtSearch;
	private JButton btnStock;

	private TableRowSorter<TableModel> rowSorter;
	private SupplyOrderTableModel tableModel;
	private AuthenticationController auth;
	private SupplyOrderController supplyOrderCtrl;
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
				 Column.DELIVERED
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
 		bottomPanel.setLayout(new BorderLayout(0, 0));
 		
 		// ***** Panel for table options at the bottom *****
 		JPanel tableBottomOptionsPanel = new JPanel();
 		bottomPanel.add(tableBottomOptionsPanel, BorderLayout.NORTH);
 		GridBagLayout gbl_tableBottomOptionsPanel = new GridBagLayout();
 		gbl_tableBottomOptionsPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
 		gbl_tableBottomOptionsPanel.rowHeights = new int[]{0, 0};
 		gbl_tableBottomOptionsPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
 		gbl_tableBottomOptionsPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
 		tableBottomOptionsPanel.setLayout(gbl_tableBottomOptionsPanel);

 		// ***** View button *****
 		btnView = new JLink("View", COLORS.GREEN);
 		GridBagConstraints gbc_btnView = new GridBagConstraints();
 		gbc_btnView.insets = new Insets(0, 0, 0, 5);
 		gbc_btnView.gridx = 1;
 		gbc_btnView.gridy = 0;
 		tableBottomOptionsPanel.add(btnView, gbc_btnView);

 		// ***** Edit button *****
 		btnEdit = new JLink("Edit", COLORS.INDIGO);
 		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
 		gbc_btnEdit.insets = new Insets(0, 0, 0, 5);
 		gbc_btnEdit.gridx = 2;
 		gbc_btnEdit.gridy = 0;
 		tableBottomOptionsPanel.add(btnEdit, gbc_btnEdit);

 		// ***** Delete button *****
 		btnDelete = new JLink("Delete", COLORS.RED);
 		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
 		gbc_btnDelete.gridx = 3;
 		gbc_btnDelete.gridy = 0;
 		tableBottomOptionsPanel.add(btnDelete, gbc_btnDelete);

 		// ***** Panel for put into stock button *****
 		JPanel stock = new JPanel();
		bottomPanel.add(stock, BorderLayout.SOUTH);
		GridBagLayout gbl_stock = new GridBagLayout();
		gbl_stock.columnWidths = new int[]{0, 17, 0, 0, 0};
		gbl_stock.rowHeights = new int[]{0, 0, 0};
		gbl_stock.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_stock.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		stock.setLayout(gbl_stock);
		
		// ***** Put into stock button *****
		btnStock = new JButton();
		GridBagConstraints gbc_btnStock = new GridBagConstraints();
		gbc_btnStock.anchor = GridBagConstraints.EAST;
		gbc_btnStock.gridx = 3;
		gbc_btnStock.gridy = 1;
		stock.add(btnStock, gbc_btnStock);
		btnStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStock.setText("Put into stock");
		
 		// By default: all selection buttons disabled
 		btnView.setEnabled(false);
 		btnEdit.setEnabled(false);
 		btnDelete.setEnabled(false);
 		btnStock.setEnabled(false);

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
 				btnStock.setEnabled(false);
 			} else {
 				// Selected
 				//int row = tableMain.getSelectedRow();
 				//  = tableModel.getObj(row);
 				btnView.setEnabled(true);
 				btnEdit.setEnabled(true);
 				btnStock.setEnabled(true);
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

 		// View supplyOrder
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
                 frame = new SupplyOrderUI(auth, supplyOrder, SupplyOrderUI.Mode.EDIT);
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
                 frame = new SupplyOrderUI(auth);
                 frame.setVisible(true);
                 if (frame.getSupplyOrder() != null) {
                     tableModel.add(frame.getSupplyOrder());
					 setTableModel(tableModel);
                 }
             } catch (SQLException e1) {
                 e1.printStackTrace();
             }
 		});
 		
 		btnStock.addActionListener(e -> {
 			//First get selected supplyOrder
 			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
 			SupplyOrder supplyOrder = tableModel.getObj(row);
 			//Then check if delivered
 			if(supplyOrder.getIsDelivered() == false) {
 				ChooseShelf frame = null;
 				try {
					frame = new ChooseShelf(auth, supplyOrder);
				} catch (SQLException e1) {
					Messages.error(this, "There was an error connecting to the database");
				} catch (NotFoundException e1) {
					Messages.error(this, "Some error occured");
				}
 				frame.setVisible(true);
 				if(frame.getSelectedShelf() != null) {
 					try {
						supplyOrderCtrl.addToStock(supplyOrder, frame.getSelectedShelf());
					} catch (SQLException e1) {
						Messages.error(this, "There was an error connecting to the database");
					}
 				}
 			}else {
 				Messages.error(this, "This supply order is already delivered");
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