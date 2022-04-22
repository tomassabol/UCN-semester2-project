package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class CreateOrder_in_case_of_emergency extends JFrame {

	private JPanel contentPane;
	private JTextField txtProduct;
	private JTextField txtQuantity;

	/**
	 * Create the frame.
	 */
	public CreateOrder_in_case_of_emergency() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 61, 0, 0, 32, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblGreeting = new JLabel("Create order");
		lblGreeting.setFont(new Font("Open Sans", Font.BOLD, 15));
		GridBagConstraints gbc_lblGreeting = new GridBagConstraints();
		gbc_lblGreeting.gridwidth = 5;
		gbc_lblGreeting.insets = new Insets(0, 0, 5, 0);
		gbc_lblGreeting.gridx = 1;
		gbc_lblGreeting.gridy = 0;
		contentPane.add(lblGreeting, gbc_lblGreeting);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setFont(new Font("Open Sans", Font.PLAIN, 10));
		lblProduct.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.anchor = GridBagConstraints.WEST;
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.gridx = 1;
		gbc_lblProduct.gridy = 2;
		contentPane.add(lblProduct, gbc_lblProduct);
		
		txtProduct = new JTextField();
		txtProduct.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_txtProduct = new GridBagConstraints();
		gbc_txtProduct.gridwidth = 4;
		gbc_txtProduct.insets = new Insets(0, 0, 5, 5);
		gbc_txtProduct.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProduct.gridx = 1;
		gbc_txtProduct.gridy = 3;
		contentPane.add(txtProduct, gbc_txtProduct);
		txtProduct.setColumns(10);
		
		JButton btnProduct = new JButton("Choose Product");
		btnProduct.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnProduct = new GridBagConstraints();
		gbc_btnProduct.insets = new Insets(0, 0, 5, 0);
		gbc_btnProduct.gridx = 5;
		gbc_btnProduct.gridy = 3;
		contentPane.add(btnProduct, gbc_btnProduct);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.WEST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 1;
		gbc_lblQuantity.gridy = 5;
		contentPane.add(lblQuantity, gbc_lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_txtQuantity = new GridBagConstraints();
		gbc_txtQuantity.gridwidth = 4;
		gbc_txtQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_txtQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantity.gridx = 1;
		gbc_txtQuantity.gridy = 6;
		contentPane.add(txtQuantity, gbc_txtQuantity);
		txtQuantity.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 5;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 7;
		contentPane.add(textArea, gbc_textArea);
		
		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.insets = new Insets(0, 0, 0, 5);
		gbc_btnClose.gridx = 3;
		gbc_btnClose.gridy = 8;
		contentPane.add(btnClose, gbc_btnClose);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdd.gridx = 4;
		gbc_btnAdd.gridy = 8;
		contentPane.add(btnAdd, gbc_btnAdd);
		
		JButton btnCreaeteOrder = new JButton("Create Order");
		btnCreaeteOrder.setFont(new Font("Open Sans", Font.PLAIN, 10));
		GridBagConstraints gbc_btnCreaeteOrder = new GridBagConstraints();
		gbc_btnCreaeteOrder.gridx = 5;
		gbc_btnCreaeteOrder.gridy = 8;
		contentPane.add(btnCreaeteOrder, gbc_btnCreaeteOrder);
		
	}

}
