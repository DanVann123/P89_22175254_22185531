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

    public OrderHistoryFrame(DatabaseManager dbManager) {
        setTitle("Order History");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.dbManager = dbManager;

        // Set layout manager
        setLayout(new BorderLayout());

        // Order History Table
        orderHistoryTableModel = new DefaultTableModel(new String[]{"Order ID", "Product Name", "Quantity", "Price", "Order Date"}, 0);
        orderHistoryTable = new JTable(orderHistoryTableModel);
        JScrollPane orderHistoryScrollPane = new JScrollPane(orderHistoryTable);
        add(orderHistoryScrollPane, BorderLayout.CENTER);

        // Load order history from database
        loadOrderHistory();
    }

    private void loadOrderHistory() {
        Vector<Vector<Object>> orders = dbManager.selectOrderHistory();
        for (Vector<Object> order : orders) {
            orderHistoryTableModel.addRow(order);
        }
    }

}
