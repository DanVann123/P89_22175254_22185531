/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Soprathna
 */
public class DatabaseManager {
    
    private static final String DB_URL = "jdbc:derby:InventoryManagementDB;create = true";
    
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    
    //SQL quiery
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE users (id INT PRIMARY KEY, username VARCHAR(255), password VARCHAR(255))";
    private static final String INSERT_USER_QUERY = "INSERT INTO users (id, username, password) VALUES (?, ?, ?)";
    private static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE username = ? AND password = ?"; 
    
    // Establish database connection
    private Connection connection;

    public DatabaseManager(){
        try {
            
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create users table if it doesn't exist
    private void createTableIfNotExists() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_QUERY)) {
            statement.executeUpdate();
        }
    }

    // Insert a new user into the database
    public void insertUser(int id, String username, String password) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY)) {
            statement.setInt(1, id);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.executeUpdate();
        }
    }

    // Check if a user with given username and password exists in the database
    public boolean userExists(String username, String password) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_QUERY)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Returns true if a user exists, false otherwise
            }
        }
    }

    // Close database connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
