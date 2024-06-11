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
public class RemoveProductFrame extends JFrame{
   private JTextField IDField;
   
    private DatabaseManager dbManager;

    public RemoveProductFrame() {
        
        setTitle("Remove Product");
        setSize(400, 150);
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new GridLayout(3, 2));

        // Create label and text field for product information
        JLabel IDLabel = new JLabel("Producrt ID:");
        IDField = new JTextField();
       
        // Create buttons for removing and canceling
        JButton removeButton = new JButton("Remove");
        
        JButton cancelButton = new JButton("Cancel");
      
        
        // Add components to the frame
        add(IDLabel);
        add(IDField);
       
        add(removeButton);
        add(cancelButton);
        
        
        removeButton.addActionListener(e -> {
            // Get product information from text fields
            int ID = Integer.parseInt(IDField.getText());
            
            dbManager = new DatabaseManager();
            
            boolean isRemoved = dbManager.removeProduct(ID);

            if (isRemoved) {
                JOptionPane.showMessageDialog(this, "Product removed from inventory: Product ID: " + ID);
            } else {
                JOptionPane.showMessageDialog(this, "Product is not exist or insertion failed.", "Warning", JOptionPane.WARNING_MESSAGE);
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
      
    }


 
}
