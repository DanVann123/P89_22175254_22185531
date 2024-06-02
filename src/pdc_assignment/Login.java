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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Soprathna
 */

public class Login extends JFrame {
    public Login() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("User Name:");
        JTextField userText = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordText = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register Now");

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

        // Position Login Button
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(loginButton, constraints);

        // Position Register Button
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(registerButton, constraints);

        add(panel);

        loginButton.addActionListener(e -> {
            String userName = userText.getText();
            String passWord = new String(passwordText.getPassword());
            System.out.println("User Name: " + userName);
            System.out.println("Password: " + passWord);

            // Login validation logic here

            new InventoryManagementGUI().setVisible(true);
            this.dispose();
        });

        registerButton.addActionListener(e -> {
            new Register().setVisible(true);
            this.dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
