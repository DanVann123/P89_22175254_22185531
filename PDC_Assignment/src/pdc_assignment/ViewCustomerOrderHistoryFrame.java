/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;
/**
 *
 * @author xuyan
 */
public class ViewCustomerOrderHistoryFrame extends JFrame{
   
    private JTable orderHistoryTable;
    private DefaultTableModel orderHistoryTableModel;
    private DatabaseManager dbManager;

    /**
     * Constructs the ViewCustomerOrderHistoryFrame.
     *
     * @param dbManager  The DatabaseManager instance.
     * @param customerId The ID of the customer whose order history is being viewed.
     */
    public ViewCustomerOrderHistoryFrame() {
       
        setTitle("Customer Order History");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.dbManager = new DatabaseManager();

        // Set layout manager
        setLayout(new BorderLayout());

        // Order History Table
        orderHistoryTableModel = new DefaultTableModel(new String[]{"Order ID", "Customer Id", "Purchase Date", "Total Price"}, 0);

        orderHistoryTable = new JTable(orderHistoryTableModel);
        JScrollPane orderHistoryScrollPane = new JScrollPane(orderHistoryTable);
        add(orderHistoryScrollPane, BorderLayout.CENTER);

        // Load customer order history from database
        loadCustomerOrderHistory();
    }

    
     //Loads customer order history from the database a
     
    private void loadCustomerOrderHistory() {
        Vector<Vector<Object>> orders = dbManager.managerSelectCustomerOrderHistory();
        for (Vector<Object> order : orders) {
            orderHistoryTableModel.addRow(order);
        }
    }

}
