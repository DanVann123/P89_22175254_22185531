/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 *
 * @author xuyan
 */
public class OrderHistoryFrame extends JFrame{
    private JTable orderHistoryTable;
    private DefaultTableModel orderHistoryTableModel;
    private DatabaseManager dbManager;

    public OrderHistoryFrame(Integer customerId,DatabaseManager dbManager) {
        setTitle("Order History");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.dbManager = dbManager;
    
        // Set layout manager
        setLayout(new BorderLayout());

        // Order History Table
        orderHistoryTableModel = new DefaultTableModel(new String[]{"Order ID", "Customer Id", "Purchase Date", "Total Price"}, 0);
        orderHistoryTable = new JTable(orderHistoryTableModel);
        JScrollPane orderHistoryScrollPane = new JScrollPane(orderHistoryTable);
        add(orderHistoryScrollPane, BorderLayout.CENTER);

        // Load order history from database
        loadOrderHistory(customerId);
    }

    public void loadOrderHistory(int customerId) {
        // Clear the existing order history
        orderHistoryTableModel.setRowCount(0);

        // Fetch order history from the database for the specified customer ID
        Vector<Vector<Object>> orders = dbManager.selectCustomerOrderHistory(customerId);

        // Populate the order history table with the fetched data
        for (Vector<Object> order : orders) {
            orderHistoryTableModel.addRow(order);
        }
    }
}
