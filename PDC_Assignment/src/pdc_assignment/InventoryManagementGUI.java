/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import com.sun.jdi.connect.spi.Connection;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.sql.SQLException;

/**
 *
 * @author Soprathna
 */
public class InventoryManagementGUI extends JFrame {
    
    private RemoveProductFrame removeProductFrame;
    private ViewInventoryFrame viewInventoryFrame;
    private AddProductFrame addProductFrame;

    public InventoryManagementGUI() {
        setTitle("Inventory Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons for different functionalities
        JButton addButton = new JButton("Add Product");
        
        JButton removeButton = new JButton("Remove Product");
        JButton viewInventoryButton = new JButton("View Inventory");

        // Set layout
        setLayout(new GridLayout(3, 2));

        // Add buttons to the frame
        add(addButton);
        
        add(removeButton);
        add(viewInventoryButton);

        String url = "jdbc:derby://localhost:1527/InventoryManagement;create=true";
        String user = "pdc"; // Update with your database username
        String password = "pdc"; // Update with your database password


       // Add action listeners for button clicks
        addButton.addActionListener(e -> handleAdd());
        removeButton.addActionListener(e -> handleRemove());
        viewInventoryButton.addActionListener(e -> handleViewInventory());
        
        addButton.addActionListener(e -> new AddProductFrame().setVisible(true));
    }

    
    public void handleRemove() {
        System.out.println("Handling Remove...");
        // Implement remove functionality here
        removeProductFrame = new RemoveProductFrame();
        removeProductFrame.setVisible(true);
    }

    public void handleViewInventory() {
        System.out.println("Handling View Inventory...");
        // Implement view inventory functionality here
        viewInventoryFrame = new ViewInventoryFrame();
        viewInventoryFrame.setVisible(true);
    }
    public void handleAdd() {
        System.out.println("Handling add...");
        // Implement purchase functionality here
        addProductFrame = new AddProductFrame();
        addProductFrame.setVisible(true);
    }
    
    public RemoveProductFrame getRemoveProductFrame() {
        return removeProductFrame;
    }
    
    public ViewInventoryFrame getViewInventoryFrame() {
        return viewInventoryFrame;
    }
    
    public AddProductFrame getAddProductFrame() {
        return addProductFrame;
    }
    
    
    
    
    
}