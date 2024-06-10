/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import com.sun.jdi.connect.spi.Connection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
/**
 *
 * @author xuyan
 */
public class AddProductFrame extends JFrame {
     private JTextField IDField;
    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockField;
    private DatabaseManager dbManager;

    public AddProductFrame() {
        
        setTitle("Add Product");
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame on the screen
       
setLocationRelativeTo(null); // Center the frame on the screen

        // Create panel for the main content
        JPanel mainPanel = new JPanel(new GridLayout(5, 2));

        // Create labels and text fields for product information
        JLabel IDLabel = new JLabel("Product ID:");
        IDField = new JTextField();
        JLabel nameLabel = new JLabel("Product Name:");
        nameField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField();
        JLabel stockLabel = new JLabel("Stock:");
        stockField = new JTextField();

        // Add components to the main panel
        mainPanel.add(IDLabel);
        mainPanel.add(IDField);
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(priceLabel);
        mainPanel.add(priceField);
        mainPanel.add(stockLabel);
        mainPanel.add(stockField);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            // Your add button logic here
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        // Add buttons to the button panel
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(closeButton);

        // Add panels to the main frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        
        addButton.addActionListener(e -> {
            // Get product information from text fields
            int ID = Integer.parseInt(IDField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());
            dbManager = new DatabaseManager();
            
            boolean isInserted = dbManager.insertProduct(ID, name, stock, price);

            if (isInserted) {
                JOptionPane.showMessageDialog(this, "Product added to inventory: Product ID: "+ID+ ", Name: " + name + ", Price: " + price + ", Stock: " + stock);
            } else {
                JOptionPane.showMessageDialog(this, "Product already exists or insertion failed.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

   
         cancelButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        // Single click detected, close the window
                        dispose();
                    }
                }
         });
         
         dispose();
      
    

    closeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        // Single click detected, close the window
                        dispose();
                    }
                }
         });
         
         dispose();
      
    }


}

    
        


    

