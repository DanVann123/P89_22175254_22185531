/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Soprathna
 */
public class MainMenu extends JFrame{
    
    public MainMenu()
    {
        setTitle("Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));
        
        //Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to our Inventory Management System group project", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(welcomeLabel, BorderLayout.NORTH);
        
        //Panel for button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton exitButton = new JButton("Exit");
        
        //Adjust the size
        Dimension buttonSize = new Dimension(100, 30);
        loginButton.setPreferredSize(buttonSize);
        registerButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);
        
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(exitButton);
        
        add(buttonPanel, BorderLayout.CENTER);
        
        loginButton.addActionListener(e -> {
            new Login().setVisible(true);
            this.dispose();
        });

        registerButton.addActionListener(e -> {
            new Register().setVisible(true);
            this.dispose();
        });

        exitButton.addActionListener(e -> System.exit(0));
    }
}