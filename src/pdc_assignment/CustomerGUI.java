/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author xuyan
 */
public class CustomerGUI extends JFrame{
    public JTable productTable;
    public DefaultTableModel productTableModel;
    public DatabaseManager dbManager;
    public JButton addToCartButton;
    public JButton viewCartButton;
    public JButton viewOrderHistoryButton;
//    private Integer userId;

    public CustomerGUI(int userId) {
//        userId = userId;
        setTitle("89 Market");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        dbManager = new DatabaseManager();
//        cartItems = new Vector<>();

        // Set layout manager
        setLayout(new BorderLayout());

        // Product Table
        productTableModel = new DefaultTableModel(new String[]{"Product ID", "Product Name", "Stock", "Price"}, 0);
        productTable = new JTable(productTableModel);
        JScrollPane productScrollPane = new JScrollPane(productTable);
        add(productScrollPane, BorderLayout.CENTER);

        // Load products from database
        loadProducts();

        // Add to Cart Button
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow != -1) {
                    String productId = (String)productTableModel.getValueAt(selectedRow, 0);
                    String productName = (String) productTableModel.getValueAt(selectedRow, 1);
                    int stock = (int) productTableModel.getValueAt(selectedRow, 2);
                    double price = (double) productTableModel.getValueAt(selectedRow, 3);

                    // Prompt for quantity
                    String quantityStr = JOptionPane.showInputDialog("Enter quantity:");
                    if (quantityStr != null && !quantityStr.isEmpty()) {
                        try {
                            int quantity = Integer.parseInt(quantityStr);
                            if (quantity > 0 && quantity <= stock) {
                                // Add to cart
                                boolean res = dbManager.insertCart(userId, Integer.parseInt(productId), productName, quantity, price);
                                if (res) {
                                    JOptionPane.showMessageDialog(CustomerGUI.this, "Added to cart!");
                                } else {
                                    JOptionPane.showMessageDialog(CustomerGUI.this, "Failed to add to cart!");
                                }
                            } else {
                                JOptionPane.showMessageDialog(CustomerGUI.this, "Invalid quantity!");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(CustomerGUI.this, "Invalid quantity format!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(CustomerGUI.this, "Please select a product!");
                }
            }
        });

        // View Cart Button
        viewCartButton = new JButton("View Cart");
        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              Vector<Vector<Object>> carts = dbManager.selectCart();
                           
               new CartGUI(carts).setVisible(true);
            }
        });

        // View Order History Button
        viewOrderHistoryButton = new JButton("View Order History");
        viewOrderHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderHistoryFrame(dbManager).setVisible(true);
            }
        });

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addToCartButton);
        buttonPanel.add(viewCartButton);
        buttonPanel.add(viewOrderHistoryButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    public void loadProducts() {
        Vector<Vector<Object>> products = dbManager.selectProduct();
        for (Vector<Object> product : products) {
            productTableModel.addRow(product);
        }
    }
    
    
    
}