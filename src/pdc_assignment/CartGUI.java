/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

/**
 *
 * @author xuyan
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class CartGUI extends JFrame {
    private JTable cartTable;
    private DefaultTableModel cartTableModel;
    
    private DatabaseManager dbManager;
    private JButton checkoutButton;
    private JButton removeButton;
    private Vector<Vector<Object>> carts;

    public CartGUI(Vector<Vector<Object>> carts) {
        this.carts = carts;
        setTitle("Shopping Cart");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        dbManager = new DatabaseManager();

        // Set layout manager
        setLayout(new BorderLayout());

        // Cart Table
        cartTableModel = new DefaultTableModel(new String[]{"Product ID", "Product Name", "Quantity", "Price"}, 0);
        cartTable = new JTable(cartTableModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        add(cartScrollPane, BorderLayout.CENTER);

        // Load cart items
        loadCartItems();

         // Buttons
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                if (cartTableModel.getRowCount() == 0)
                {
                    JOptionPane.showMessageDialog(CartGUI.this, "Cart is empty.");
                } else {
                    try {
                        processPurchase();
                        JOptionPane.showMessageDialog(CartGUI.this, "Checkout completed!.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(CartGUI.this, "Checkout failed: " + ex.getMessage());
                    }
                }
            }
        });

        // Remove Button
        removeButton = new JButton("Remove Selected Item");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = cartTable.getSelectedRow();
                if (selectedRow != -1) {
//                    cartItems.remove(selectedRow);
                    cartTableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(CartGUI.this, "Item removed from cart.");
                } else {
                    JOptionPane.showMessageDialog(CartGUI.this, "Please select an item to remove.");
                }
            }
        });
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(checkoutButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadCartItems() {
        Vector<Vector<Object>> products = dbManager.selectProduct();
        for (Vector<Object> cart : carts) {
            cartTableModel.addRow(cart);
        }
    }

    private void processPurchase() throws SQLException {
//        for (CartItem item : cartItems) {
//            dbManager.purchaseProduct(item.getProductId(), item.getQuantity());
//        }
//        cartItems.clear();
        cartTableModel.setRowCount(0);
        JOptionPane.showMessageDialog(this, "Purchase successful!");
    }
}

