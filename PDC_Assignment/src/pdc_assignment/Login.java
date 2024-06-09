/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.sql.SQLException;
import javax.swing.JComboBox;

/**
 *
 * @author Soprathna
 */

public class Login extends JFrame {
    
    public JTextField userText;
    public JPasswordField passwordText;
    public JComboBox<String> userGroupComboBox;
    public JButton loginButton;
    private JButton registerButton;
    private InventoryManagementGUI inventoryManagementGUI;
    private DatabaseManager databaseManager;
    
    public Login() {
        this.databaseManager = new DatabaseManager();
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("User Name:");
        userText = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordText = new JPasswordField(20);
        
        // add one selection combobox of usergroup
        JLabel userGroupLabel = new JLabel("Login As:");
        String[] userGroups = {"admin", "customer"};
        userGroupComboBox = new JComboBox<>(userGroups);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register Now");

        // Position User Label
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(userLabel, constraints);

        // Position User Text Field
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(userText, constraints);

        // Position Password Label
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        // Position Password Text Field
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordText, constraints);

        // Position User Group Label
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(userGroupLabel, constraints);
        
        // Position User Group ComboBox
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(userGroupComboBox, constraints);

        // Position Login Button
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(loginButton, constraints);

        // Position Register Button
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(registerButton, constraints);

        add(panel);

        loginButton.addActionListener(e -> {
            String userName = userText.getText();
            String passWord = new String(passwordText.getPassword());
            String selectedUserGroup = (String) userGroupComboBox.getSelectedItem();
            System.out.println("User Name: " + userName);
            System.out.println("Password: " + passWord);
            System.out.println("User Group: " + selectedUserGroup);
            
            // Login validation logic here
            DatabaseManager databaseManager = new DatabaseManager();
            
             try {
                 int userId = databaseManager.userExists(userName, passWord, selectedUserGroup);
                 if (userId != -1) {
                    if (selectedUserGroup.equals("admin")) {
                        inventoryManagementGUI = new InventoryManagementGUI();
                        inventoryManagementGUI.setVisible(true);
                    } else if (selectedUserGroup.equals("customer")) {
                        new CustomerGUI(userId).setVisible(true);
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred during login.");
            }
        });

        registerButton.addActionListener(e -> {
            new Register().setVisible(true);
            this.dispose();
        });
    }
    
    public InventoryManagementGUI getInventoryManagementGUI() {
        return inventoryManagementGUI;
    }
    
    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
//    }
}
