package view;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import controller.AuthenticationController;
import controller.CityController;
import exceptions.NotFoundException;
import model.City;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import view.JLink.COLORS;
import view.tableModel.CityTableModel;
import view.tableModel.CityTableModel.Column;

import javax.swing.JTextField;

public class CRUDCity extends JPanel {
	
	private JButton btnAddCity;
	private JPanel contentPane;
	private CityController cityCtrl;
	private TableRowSorter<TableModel> rowSorter;
	private static final long serialVersionUID = -8329527605114016878L;
	private JTable tableMain;
	private CityTableModel tableModel;
	private JLink btnView;
	private JLink btnEdit;
	private JLink btnRemove;
	private AuthenticationController auth;
	private JTextField txtSearch;
	private JLabel label;

	/**
	 * Create the dialog.
	 * @throws SQLException
	 * @throws NotFoundException
	 */
	public CRUDCity(AuthenticationController auth) throws SQLException, NotFoundException {
		this.auth = auth;
		cityCtrl = new CityController();
		setLayout(new BorderLayout(0, 0));
		
		tableModel = new CityTableModel(cityCtrl.findAll(),
		Arrays.asList(
				Column.ID,
				Column.ZIPCODE,
				Column.NAME
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
			String.format("Cities")
		);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);
		
		label = new JLabel(
		String.format("Search")
		);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		topPanel.add(label, gbc_label);
		
		txtSearch = new JTextField();
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.gridx = 0;
		gbc_txtSearch.gridy = 1;
		topPanel.add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
			
		// ***** button: Add city *****
		btnAddCity = new JButton("Add a new city");
		GridBagConstraints gbc_btnAddCity = new GridBagConstraints();
		gbc_btnAddCity.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddCity.gridx = 2;
		gbc_btnAddCity.gridy = 1;
		topPanel.add(btnAddCity, gbc_btnAddCity);
		
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
			
		// ***** Remove button *****
		btnRemove = new JLink("Remove", COLORS.RED);
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.gridx = 3;
		gbc_btnRemove.gridy = 0;
		bottomPanel.add(btnRemove, gbc_btnRemove);
		
		// By default: all selection buttons disabled
		btnView.setEnabled(false);
		btnEdit.setEnabled(false);
		btnRemove.setEnabled(false);
		
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
	
	public CityTableModel getTableModel() {
		return tableModel;
	}
	
	/**
	 * Select a city in the CRUD table.
	 *
	 * @param city the city
	 * @return true, if successful
	 */
	public boolean selectCity(City city) {
		int rows = tableModel.getRowCount();
		for (int i = 0; i < rows; i++) {
			City foundCity = tableModel.getObj(i);
			if (foundCity == city) {
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
				btnRemove.setEnabled(false);
			} else {
				// Selected
				int row = tableMain.getSelectedRow();
				City city = tableModel.getObj(row);
				btnView.setEnabled(true);
				btnEdit.setEnabled(true);
				btnRemove.setEnabled(false);
			}});
					
		// Remove city
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableMain.getSelectedRow();
				City city = tableModel.getObj(row);
				try {
					cityCtrl.deleteCity(city);
				} catch (SQLException e1) {
					Messages.error(contentPane, "There was an error connecting to the database");
				}
			}
		});
		
		// View city
		btnView.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			City city = tableModel.getObj(row);
			CityUI frame;
            try {
                frame = new CityUI(auth, city, CityUI.Mode.VIEW);
                frame.setVisible(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
		});
		
		// Edit city
		btnEdit.addActionListener(e -> {
			int row = tableMain.convertRowIndexToModel(tableMain.getSelectedRow());
			City city = tableModel.getObj(row);
			CityUI frame;
            try {
                frame = new CityUI(auth, city, CityUI.Mode.EDIT);
                frame.setVisible(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
			tableModel.fireTableRowsUpdated(row, row);
			tableMain.clearSelection();
			tableMain.getSelectionModel().setSelectionInterval(0, row);
		});
		
		// Create city
		btnAddCity.addActionListener(e -> {
			CityUI frame;
            try {
                frame = new CityUI(auth);
                frame.setVisible(true);
                if (frame.getCity() != null) {
                    tableModel.add(frame.getCity());
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
			public void changedUpdate(DocumentEvent e) {}
		});
		}
	}
