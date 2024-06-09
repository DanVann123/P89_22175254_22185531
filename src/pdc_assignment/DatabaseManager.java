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
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Soprathna
 */
public class DatabaseManager {

    private String DB_URL = "jdbc:derby://localhost:1527/InventoryManagement;create=true;";  // DB URL
    private String USERNAME = "pdc";  // DB username
    private String PASSWORD = "pdc";  // DB password
    
    //SQL quiery
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE user_account (id INT PRIMARY KEY, username VARCHAR(50), password VARCHAR(255), userGroup VARCHAR(32) NOT NULL)";
    private static final String INSERT_USER_QUERY = "INSERT INTO user_account (username, password, userGroup) VALUES (?, ?, ?)";
    private static final String SELECT_USER_QUERY = "SELECT * FROM user_account WHERE username = ? AND password = ? AND userGroup = ?"; 
    
    // Establish database connection
    private Connection connection;

    public DatabaseManager(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
//            createTableIfNotExists();
        } catch (Exception e) {
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
    public void insertUser(String username, String password, String userGroup) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, userGroup);
            int count = statement.executeUpdate();
            System.out.println("executeUpdate count: " + count);
            connection.commit();
        }
    }

    // Check if a user with given username and password exists in the database
    public int userExists(String username, String password, String userGroup) throws SQLException {
        System.out.println("executeUpdate count1111: " + connection);
        PreparedStatement statement = connection.prepareStatement(SELECT_USER_QUERY);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, userGroup);
        ResultSet resultSet = statement.executeQuery();

        
        while(resultSet.next()){
            System.out.println("resultSet username is: " + resultSet.getString("username"));
            System.out.println("resultSet password is: " + resultSet.getString("password"));
            return resultSet.getInt("id");
        }
        
        return -1; // Returns true if a user exists, false otherwise
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
    
    public boolean insertProduct(int productId, String productName, int stock, double price) {
       
        String checkQuery = "SELECT COUNT(*) FROM product WHERE productId = ? or productName = ?";
        String insertQuery = "INSERT INTO product (productId,productName, stock, price) VALUES (?,?, ?, ?)";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {

            checkStatement.setInt(1, productId);
            checkStatement.setString(2,productName);

            try (ResultSet resultSet = checkStatement.executeQuery()) {
                resultSet.next();
                
                int count = resultSet.getInt(1);

                if (count > 0) {
                    return false;
                } else {
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                        insertStatement.setString(1, String.valueOf(productId));
                        insertStatement.setString(2, productName);
                        insertStatement.setInt(3, stock);
                        insertStatement.setDouble(4, price);
                        
                        insertStatement.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
    public Vector<Vector<Object>> selectProduct() {

        Vector<Vector<Object>> data = new Vector<>();
        String query = "SELECT * FROM product";
                         
          try (PreparedStatement statement = connection.prepareStatement(query)) {

             ResultSet resultSet = statement.executeQuery();
                // System.out.println(1113331111);

            while (resultSet.next()) {
                // System.out.println(1111111);
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("productId"));
                row.add(resultSet.getString("productName"));
                row.add(resultSet.getInt("stock"));
                row.add(resultSet.getDouble("price"));
                data.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     
         return data;
     }
    
    public boolean removeProduct(int productId) {
        String checkQuery = "SELECT COUNT(*) FROM product WHERE productId = ?";
        String removeQuery = "DELETE FROM product WHERE productId = ?";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {

            checkStatement.setInt(1, productId);
            
            System.out.println("21212121");
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                resultSet.next();
                
                int count = resultSet.getInt(1);
                 System.out.println("212123333:"+count);

                if (count < 0) {
                    return false;
                } else {
                    System.out.println("2121244444");
                    try (PreparedStatement removeStatement = connection.prepareStatement(removeQuery)) {
                        removeStatement.setInt(1, productId);
                        removeStatement.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
   public void processOrder(Vector<Vector<Object>> cart) throws SQLException {
        // Logic to save the order in the database
        String orderQuery = "INSERT INTO orders (orderId, totalAmount) VALUES (?, ?)";
        try (PreparedStatement orderStmt = connection.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS)) {
            orderStmt.setInt(1, generateOrderId());
            orderStmt.setDouble(2, calculateTotal(cart));
            orderStmt.executeUpdate();

            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                for (Vector<Object> item : cart) {
                    String orderDetailQuery = "INSERT INTO order_details (orderId, productId, quantity, price) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement orderDetailStmt = connection.prepareStatement(orderDetailQuery)) {
                        orderDetailStmt.setInt(1, orderId);
                        orderDetailStmt.setInt(2, (int)item.get(0));
                        orderDetailStmt.setInt(3, (int)item.get(2));
                        orderDetailStmt.setDouble(4, (double)item.get(3));
                        orderDetailStmt.executeUpdate();
                    }
                }
            }
        }
    }

    private int generateOrderId() {
        // Logic to generate a unique order ID
        // For simplicity, return a random number here
        return (int) (Math.random() * 10000);
    }

    private double calculateTotal(Vector<Vector<Object>> cart) {
        double total = 0;
        for (Vector<Object> item : cart) {
            total += (int)item.get(2) * (double)item.get(3);
        }
        return total;
    }
    public Vector<Vector<Object>> selectOrderHistory() {
        Vector<Vector<Object>> data = new Vector<>();
        String query = "SELECT orderId, productName, quantity, price, orderDate FROM sale_order";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("orderId"));
                row.add(resultSet.getString("productName"));
                row.add(resultSet.getInt("quantity"));
                row.add(resultSet.getDouble("price"));
                row.add(resultSet.getDate("orderDate"));
                data.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return data;
    }
    public boolean purchaseProduct(int productId, int quantity) throws SQLException {
    String updateStockQuery = "UPDATE product_info SET stock = stock - ? WHERE productId = ?";
    String insertOrderQuery = "INSERT INTO orders (productId, quantity, price, orderDate) VALUES (?, ?, (SELECT price FROM product_info WHERE productId = ?), NOW())";

    try (PreparedStatement updateStockStmt = connection.prepareStatement(updateStockQuery);
           PreparedStatement insertOrderStmt = connection.prepareStatement(insertOrderQuery)) {
           connection.setAutoCommit(false);

           // Update stock
           updateStockStmt.setInt(1, quantity);
           updateStockStmt.setInt(2, productId);
           updateStockStmt.executeUpdate();

           // Insert order
           insertOrderStmt.setInt(1, productId);
           insertOrderStmt.setInt(2, quantity);
           insertOrderStmt.setInt(3, productId);
           insertOrderStmt.executeUpdate();

           connection.commit();
           return true;
        } catch (SQLException ex) {
            connection.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    
    }
    
    public boolean insertCart(int userId, int productId, String productName, int quantity, double price) {
       
        String checkQuery = "SELECT * FROM cart_items WHERE customer_id =? or product_id = ?";
        String insertQuery = "INSERT INTO cart_items (customer_id, product_id,product_name, quantity, price) VALUES (?, ?, ?, ?, ?)";
        String updateSql = "UPDATE cart_items set quantity =? where id =?";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {

            checkStatement.setInt(1, userId);
            checkStatement.setInt(2,productId);

            try (ResultSet resultSet = checkStatement.executeQuery()) {
                resultSet.next();
                
                int id = resultSet.getInt("id");

                if (id > 0) {
                    //update 操作
                   int oldQuantity = resultSet.getInt("quantity");

                   PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                   updateStatement.setInt(1, oldQuantity+quantity);
                   updateStatement.setInt(3, id);

                   updateStatement.executeUpdate();
                    
                    return true;
                } else {
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                        insertStatement.setInt(1, userId);
                        insertStatement.setInt(2, productId);
                        insertStatement.setString(3, productName);
                        insertStatement.setInt(4, quantity);
                        insertStatement.setDouble(5, price);
                        
                        insertStatement.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
    public Vector<Vector<Object>> selectCart() {

        Vector<Vector<Object>> data = new Vector<>();
        String query = "SELECT * FROM cart_items";
                         
          try (PreparedStatement statement = connection.prepareStatement(query)) {

             ResultSet resultSet = statement.executeQuery();
                // System.out.println(1113331111);
            while (resultSet.next()) {
                // System.out.println(1111111);
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("customer_id"));
                row.add(resultSet.getInt("product_id"));
                row.add(resultSet.getString("product_name"));
                row.add(resultSet.getInt("quantity"));
                row.add(resultSet.getDouble("price"));
                data.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     
         return data;
     }
    

}
