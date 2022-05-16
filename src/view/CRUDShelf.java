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
import controller.ShelfController;
import exceptions.NotFoundException;

import model.Shelf;

import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import view.JLink.COLORS;
import view.tableModel.ShelfTableModel;
import view.tableModel.ShelfTableModel.Column;

import javax.swing.JTextField;

public class CRUDShelf extends JPanel {

	private JButton btnAddshelf;
	private ShelfController shelfCtrl;
 	private TableRowSorter<TableModel> rowSorter;

 	private static final long serialVersionUID = -8329527605114016878L;
 	private JTable tableMain;
 	private ShelfTableModel tableModel;
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
	public CRUDShelf(AuthenticationController auth) throws SQLException, NotFoundException {
 		this.auth = auth;
 		shelfCtrl = new ShelfController();
 		setLayout(new BorderLayout(0, 0));

 		tableModel = new ShelfTableModel(shelfCtrl.findAll(), 
 		Arrays.asList(
 			    Column.ID,
 			    Column.NAME,
				Column.PRODUCTID,
				Column.ITEMQUANTITY,
				Column.DEPARTMENT 			    
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
 			"Shelves"
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

 		btnAddshelf = new JButton("Add shelf");
 		GridBagConstraints gbc_btnAddShelf = new GridBagConstraints();
 		gbc_btnAddShelf.insets = new Insets(0, 0, 5, 0);
 		gbc_btnAddShelf.gridx = 2;
 		gbc_btnAddShelf.gridy = 1;
 		topPanel.add(btnAddshelf, gbc_btnAddShelf);

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

 	public ShelfTableModel getTableModel() {
 		return tableModel;
 	}

	public void setTableModel(ShelfTableModel tableModel) {
 		this.tableMain.setModel(tableModel);
 		this.tableModel = tableModel;
 		// Update table row sorter
 		rowSorter = new TableRowSorter<>(tableMain.getModel());
 		tableMain.setRowSorter(rowSorter);
 	}

 	/**
 	 * Select a shelf in the CRUD table.
 	 *
 	 * @param shelf the shelf
 	 * @return true, if successful
 	 */
 	public boolean selectshelf(Shelf shelf) {
 		int rows = tableModel.getRowCount();
 		for (int i = 0; i < rows; i++) {
 			Shelf foundShelf = tableModel.getObj(i);
 			if (foundShelf == shelf) {
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
 			
 				btnView.setEnabled(false);
 				btnEdit.setEnabled(false);
 			} else {
 				
 				btnView.setEnabled(true);
 				btnEdit.setEnabled(true);
 				btnDelete.setEnabled(false);
 			}
 		});

 		// Delete department
		 /*
 		btnDelete.addActionListener(e -> {
 			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
 			Shelf shelf = tableModel.getObj(row);
 			if (Messages.confirm(this, String.format("Are you sure you wish to delete the Department '%s'?",
 					shelf.getName()))) {
                     try {
						shelfCtrl.de(shelf);
                     } catch (SQLException e1) {
						e1.printStackTrace();
                     } 
                 }

 				tableModel.fireTableRowsUpdated(row, row);
 				tableMain.getSelectionModel().clearSelection();
 			}
 		);
		*/
 		// View shelf
 		btnView.addActionListener(e -> {
 			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
 			Shelf shelf = tableModel.getObj(row);
 			ShelfUi frame;
             try {
                 frame = new ShelfUi(auth, shelf, ShelfUi.Mode.VIEW);
                 frame.setVisible(true);
             } catch (SQLException e1) {
                 e1.printStackTrace();
             }
 		});

 		// Edit shelf
 		btnEdit.addActionListener(e -> {
 			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
 			Shelf shelf = tableModel.getObj(row);
 			ShelfUi frame;
             try {
                 frame = new ShelfUi(auth, shelf, ShelfUi.Mode.EDIT);
                 frame.setVisible(true);
             } catch (SQLException e1) {
                 e1.printStackTrace();
             }
 			tableModel.fireTableRowsUpdated(row, row);
 			// Refresh selection (e.g. in case sell price is now set to nothing)
 			tableMain.clearSelection();
 			tableMain.getSelectionModel().setSelectionInterval(0, row);
 		});

 		// Create department
 		btnAddshelf.addActionListener(e -> {
 			ShelfUi frame;
             try {
                 frame = new ShelfUi(auth, ShelfUi.Mode.CREATE);
                 frame.setVisible(true);
                 if (frame.getsShelf() != null) {
                     tableModel.add(frame.getsShelf());
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