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
import java.awt.*;
import com.sun.jdi.connect.spi.Connection;
import java.sql.*;
import java.util.Arrays;
import java.util.Vector;
public class ViewInventoryFrame extends JFrame {
    public DatabaseManager dbManager;
    public JTable inventoryTable;

    // Modified constructor to accept a DatabaseManager instance
    public ViewInventoryFrame(DatabaseManager dbManager) {
        this.dbManager = dbManager;

        setTitle("View Inventory");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());

        // Fetch inventory data
        String[] columnNames = {"Product ID", "Product Name", "Stock", "Price"};
        Vector<Vector<Object>> data = fetchInventoryData();

        // Create table model and table
        inventoryTable = new JTable(data, new Vector<>(Arrays.asList(columnNames)));
        JScrollPane scrollPane = new JScrollPane(inventoryTable);

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);

        // Add a button to close the window
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);
    }

    public Vector<Vector<Object>> fetchInventoryData() {
        Vector<Vector<Object>> data = dbManager.selectProduct();
        if (data.size() == 0) {
            JOptionPane.showMessageDialog(this, "There is no product in the Inventory, please add them first!");
        }
        return data;
    }

    public JTable getInventoryTable() {
        return inventoryTable;
    }
}