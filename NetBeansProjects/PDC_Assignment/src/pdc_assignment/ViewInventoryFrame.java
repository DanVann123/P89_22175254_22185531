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
    private DatabaseManager dbManager;
    private JTable inventoryTable;

    public ViewInventoryFrame() {
        
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

    private Vector<Vector<Object>> fetchInventoryData() {

        this.dbManager = new DatabaseManager();
        
        Vector<Vector<Object>> data = dbManager.selectProduct();
        
        if(data.size() == 0){
           JOptionPane.showMessageDialog(this, "Have no product, please add product first!");
        }


        return data;
    }

}
