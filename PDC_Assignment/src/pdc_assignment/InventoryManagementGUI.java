/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Soprathna
 */
public class InventoryManagementGUI extends JFrame {

    public InventoryManagementGUI() {
        setTitle("Inventory Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons for different functionalities
        JButton addButton = new JButton("Add Product");
        JButton sellButton = new JButton("Sell Product");
        JButton purchaseButton = new JButton("Purchase Product");
        JButton removeButton = new JButton("Remove Product");
        JButton viewInventoryButton = new JButton("View Inventory");

        // Set layout
        setLayout(new GridLayout(5, 1));

        // Add buttons to the frame
        add(addButton);
        add(sellButton);
        add(purchaseButton);
        add(removeButton);
        add(viewInventoryButton);


       // Add action listeners for button clicks
        addButton.addActionListener(e -> handleAdd());
        purchaseButton.addActionListener(e -> handlePurchase());
        sellButton.addActionListener(e -> handleSell());
        removeButton.addActionListener(e -> handleRemove());
        viewInventoryButton.addActionListener(e -> handleViewInventory());
    }

    // Event handlers for button clicks
    private void handlePurchase() {
        System.out.println("Handling Purchase...");
        // Implement purchase functionality here
    }

    private void handleSell() {
        System.out.println("Handling Sell...");
        // Implement sell functionality here
    }

    private void handleRemove() {
        System.out.println("Handling Remove...");
        // Implement remove functionality here
    }

    private void handleViewInventory() {
        System.out.println("Handling View Inventory...");
        // Implement view inventory functionality here
    }
    private void handleAdd() {
        System.out.println("Handling add...");
        // Implement purchase functionality here
    }

}