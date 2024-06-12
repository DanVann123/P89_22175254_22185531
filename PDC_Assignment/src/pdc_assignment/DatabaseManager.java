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
import java.sql.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.DatabaseMetaData;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Soprathna
 */
public class DatabaseManager {

    private String DB_URL = "jdbc:derby://localhost:1527/inventorymanagement;create=true";  // DB URL
    private String USERNAME = "pdc";  // DB username
    private String PASSWORD = "pdc";  // DB password
    
    //SQL quiery
    private static final String CREATE_USER_TABLE_QUERY = "CREATE TABLE user_account (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, username VARCHAR(50) NOT NULL, password VARCHAR(255) NOT NULL, userGroup VARCHAR(32) NOT NULL)";
    private static final String CREATE_PRODUCT_TABLE_QUERY = "CREATE TABLE product (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, productId VARCHAR(50) NOT NULL, productName VARCHAR(100) NOT NULL, stock INT NOT NULL, price DECIMAL(10, 2) NOT NULL)";
    private static final String CREATE_SALE_ORDER_TABLE_QUERY = "CREATE TABLE sale_order (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, customer_id INT NOT NULL, purchase_date DATE NOT NULL, total_price DECIMAL(10, 2) NOT NULL)";
    private static final String CREATE_SALE_ORDER_ITEMS_TABLE_QUERY = "CREATE TABLE sale_order_items (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, sale_order_id INT NOT NULL, product_id INT NOT NULL, product_name VARCHAR(255) NOT NULL, quantity INT NOT NULL, price DECIMAL(10, 2) NOT NULL)";
    private static final String CREATE_CART_ITEMS_TABLE_QUERY = "CREATE TABLE cart_items (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, customer_id INT NOT NULL, product_id INT NOT NULL, product_name VARCHAR(255) NOT NULL, quantity INT NOT NULL, price DECIMAL(10, 2) NOT NULL)";
    
    
    private static final String INSERT_USER_QUERY = "INSERT INTO user_account (username, password, userGroup) VALUES (?, ?, ?)";
    private static final String SELECT_USER_QUERY = "SELECT * FROM user_account WHERE username = ? AND password = ? AND userGroup = ?"; 
    
    // Establish database connection
    private Connection connection;

    public DatabaseManager(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(DB_URL);
            createTableIfNotExists();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create users table if it doesn't exist
    private void createTableIfNotExists() throws SQLException {
        
        try {
               if (!isTableExists("user_account")) {
                   createTable(CREATE_USER_TABLE_QUERY);
               }
               if (!isTableExists("product")) {
                   createTable(CREATE_PRODUCT_TABLE_QUERY);
               }
               if (!isTableExists("sale_order")) {
                   createTable(CREATE_SALE_ORDER_TABLE_QUERY);
               }
               if (!isTableExists("sale_order_items")) {
                   createTable(CREATE_SALE_ORDER_ITEMS_TABLE_QUERY);
               }
               if (!isTableExists("cart_items")) {
                   createTable(CREATE_CART_ITEMS_TABLE_QUERY);
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
    }
    
    private void createTable(String query) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }
    
    private boolean isTableExists(String tableName) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet rs = meta.getTables(null, null, tableName.toUpperCase(), null);
        return rs.next();
    }

    // Insert a new user into the database
    public int insertUser(String username, String password, String userGroup) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, userGroup);
            int count = statement.executeUpdate();
            System.out.println("executeUpdate count: " + count);
            connection.commit();
            return count;
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

            try (ResultSet resultSet = checkStatement.executeQuery()) {
                resultSet.next();

                int count = resultSet.getInt(1);

                if (count > 0) {
                    // Product exists, proceed with removal
                    try (PreparedStatement removeStatement = connection.prepareStatement(removeQuery)) {
                        removeStatement.setInt(1, productId);
                        removeStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Product removed from inventory: Product ID: " + productId);
                        return true;
                    }
                } else {
                    // Product does not exist
                    JOptionPane.showMessageDialog(null, "Product does not exist.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public int insertOrder(int customerId, double totalPrice) throws SQLException {
        String insertOrderSQL = "INSERT INTO sale_order (customer_id, purchase_date, total_price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, customerId);
            pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            pstmt.setDouble(3, totalPrice);
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to insert order, no ID obtained.");
            }
        }
    }

    
    public void updateInventory(int productId, int quantity) throws SQLException {
        String updateInventorySQL = "UPDATE product SET stock = stock - ? WHERE productId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(updateInventorySQL)) {
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
        }
    }
    
    public void insertSaleOrderItems(int saleOrderId, int productId, int quantity, double price) throws SQLException {
        String insertOrderItemsSQL = "INSERT INTO sale_order_items (sale_order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertOrderItemsSQL)) {
            pstmt.setInt(1, saleOrderId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            pstmt.setDouble(4, price);
            pstmt.executeUpdate();
        }
    }
    
    public void checkout(int customerId, Vector<Vector<Object>> cartItems) throws SQLException {
        connection.setAutoCommit(false);
        try {
            double totalPrice = calculateTotal(cartItems);
            int saleOrderId = insertOrder(customerId, totalPrice);

            for (Vector<Object> cartItem : cartItems) {
                int productId = (int) cartItem.get(1);
                int quantity = (int) cartItem.get(3);
                double price = (double) cartItem.get(4);

                if (quantity > 0) {
                    // Deduct stock from the database
                    updateInventory(productId, quantity);

                    // Insert sale order items
                    insertSaleOrderItems(saleOrderId, productId, quantity, price);
                }
            }

            clearCart(customerId);
            connection.commit();
            JOptionPane.showMessageDialog(null, "Checkout completed!");
        } catch (SQLException ex) {
            connection.rollback();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Checkout failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            connection.setAutoCommit(true);
        }
    }
    
    
    public void processOrder(Vector<Vector<Object>> cart_items) throws SQLException {
        // Logic to save the order in the database
        String orderQuery = "INSERT INTO orders (orderId, totalAmount) VALUES (?, ?)";
        try (PreparedStatement orderStmt = connection.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS)) {
            orderStmt.setInt(1, generateOrderId());
            orderStmt.setDouble(2, calculateTotal(cart_items));
            orderStmt.executeUpdate();

            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                for (Vector<Object> item : cart_items) {
                    String orderDetailQuery = "INSERT INTO sale_order_items (orderId, productId, quantity, price) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement orderDetailStmt = connection.prepareStatement(orderDetailQuery)) {
                        orderDetailStmt.setInt(1, orderId);
                        orderDetailStmt.setInt(2, (int)item.get(2));
                        orderDetailStmt.setInt(3, (int)item.get(4));
                        orderDetailStmt.setDouble(4, (double)item.get(5));
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

    private double calculateTotal(Vector<Vector<Object>> cart_items) {
        double total = 0;
        for (Vector<Object> cart_item : cart_items) {
            total += (int)cart_item.get(4) * (double)cart_item.get(5);
        }
        return total;
    }
    
    public boolean insertCart(int userId, int productId, String productName, int quantity, double price) {
        String checkQuery = "SELECT * FROM cart_items WHERE customer_id =? AND product_id = ?";
        String insertQuery = "INSERT INTO cart_items (customer_id, product_id, product_name, quantity, price) VALUES (?, ?, ?, ?, ?)";
        String stockQuery = "SELECT stock FROM product WHERE productId = ?";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
             PreparedStatement stockStatement = connection.prepareStatement(stockQuery)) {

            // Check if the item is already in the cart
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, productId);
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next()) {
                    // If the item is already in the cart, update the quantity
                    int oldQuantity = resultSet.getInt("quantity");
                    int newQuantity = oldQuantity + quantity;
                    // Update cart
                    String updateCartQuery = "UPDATE cart_items SET quantity = ? WHERE customer_id = ? AND product_id = ?";
                    try (PreparedStatement updateCartStatement = connection.prepareStatement(updateCartQuery)) {
                        updateCartStatement.setInt(1, newQuantity);
                        updateCartStatement.setInt(2, userId);
                        updateCartStatement.setInt(3, productId);
                        updateCartStatement.executeUpdate();
                    }
                } else {
                    // If the item is not in the cart, insert it
                    // Check stock availability before insertion
                    stockStatement.setInt(1, productId);
                    try (ResultSet stockResultSet = stockStatement.executeQuery()) {
                        if (stockResultSet.next()) {
                            int availableStock = stockResultSet.getInt("stock");
                            if (quantity <= availableStock) {
                                insertStatement.setInt(1, userId);
                                insertStatement.setInt(2, productId);
                                insertStatement.setString(3, productName);
                                insertStatement.setInt(4, quantity);
                                insertStatement.setDouble(5, price);
                                insertStatement.executeUpdate();
                            } else {
                                // Insufficient stock, show error message
                                JOptionPane.showMessageDialog(null, "Insufficient stock for " + productName, "Error", JOptionPane.ERROR_MESSAGE);
                                return false;
                            }
                        }
                    }
                }
            }

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public Vector<Vector<Object>> selectCart() {
        Vector<Vector<Object>> data = new Vector<>();
        String query = "SELECT * FROM cart_items WHERE quantity > 0";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
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
    
    
    public Boolean deleteCartItem(Integer productId) {
        String selectCartItemQuery = "SELECT quantity FROM cart_items WHERE product_id = ?";
        String updateCartItemQuery = "UPDATE cart_items SET quantity = 0 WHERE product_id = ?";

        try (PreparedStatement selectStmt = connection.prepareStatement(selectCartItemQuery);
             PreparedStatement updateStmt = connection.prepareStatement(updateCartItemQuery)) {

            // Check if the item exists in the cart
            selectStmt.setInt(1, productId);
            ResultSet resultSet = selectStmt.executeQuery();

            if (resultSet.next()) {
                // Update the cart item quantity to 0
                updateStmt.setInt(1, productId);
                int result = updateStmt.executeUpdate();

                if (result > 0) {
                    System.out.println("Cart item quantity updated to 0 for productId: " + productId);
                    return true;
                }
            } else {
                // Item not found in cart
                JOptionPane.showMessageDialog(null, "Item not found in cart.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
    
    
    public Boolean checkoutCartItem(Integer order_Id){
        
        String updateQuery = "UPDATE orders SET status = ? WHERE order_Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)){
           statement.setString(1, "Pending");
           statement.setInt(2,  order_Id);
           int rowsAffected = statement.executeUpdate();
        }catch (SQLException ex) {
             ex.printStackTrace();
        }
        return true;
    } 
     
    private void clearCart(int userId) {
        String deleteCartQuery = "DELETE FROM cart_items WHERE customer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteCartQuery)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     }
    
    public void increaseProductStock(int productId, int quantity) throws SQLException {
        String updateStockQuery = "UPDATE product SET stock = stock + ? WHERE productId = ?";
        try (PreparedStatement updateStockStmt = connection.prepareStatement(updateStockQuery)) {
            updateStockStmt.setInt(1, quantity);
            updateStockStmt.setInt(2, productId);
            updateStockStmt.executeUpdate();
        }
    }
     
    public Vector<Vector<Object>> selectCustomerOrderHistory(int customerId) {
        Vector<Vector<Object>> orders = new Vector<>();
        String query = "SELECT id, customer_id, purchase_date, total_price FROM sale_order WHERE customer_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId); // 设置参数值
            ResultSet resultSet = statement.executeQuery(); // 执行查询
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("id"));
                row.add(resultSet.getInt("customer_id"));
                row.add(resultSet.getDate("purchase_date"));
                row.add(resultSet.getDouble("total_price")); // 使用 getDouble 获取 DECIMAL 类型的列
                orders.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;
    }
    
    public Vector<Vector<Object>> managerSelectCustomerOrderHistory() {
        Vector<Vector<Object>> orders = new Vector<>();
        String query = "SELECT id, customer_id, purchase_date, total_price FROM sale_order";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(); // 执行查询
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("id"));
                row.add(resultSet.getInt("customer_id"));
                row.add(resultSet.getDate("purchase_date"));
                row.add(resultSet.getDouble("total_price")); // 使用 getDouble 获取 DECIMAL 类型的列
                orders.add(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;
    }

     
    public boolean insertOrder(int customerId, List<CartItem> cartList) {
        String checkQuery = "SELECT * FROM sale_order WHERE customer_id =?";
        String insertQuery = "INSERT INTO sale_order (customer_id, purchase_date, total_price) VALUES (?, ?, ?)";
        String insertOrderItemsQuery = "INSERT INTO sale_order_items (sale_order_id, product_id, product_name, quantity, price) VALUES (?,?, ?, ?, ?)";
        String updateProduct = "UPDATE product SET stock = ? WHERE productId =?";
        String queryProduct = "SELECT * FROM product WHERE productId =?";
  
        double totalPrice = 0.0;
        
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertOrderItemsStatement = connection.prepareStatement(insertOrderItemsQuery);
             PreparedStatement updateProductStatement = connection.prepareStatement(updateProduct);
             PreparedStatement queryProductStatement = connection.prepareStatement(queryProduct);

            ) {
            
            Date date = new Date(System.currentTimeMillis()); 
            insertStatement.setInt(1, customerId);
            insertStatement.setDate(2, date);
            
            for (CartItem item : cartList) {
              totalPrice += item.getPrice() * item.getQuantity();
            }
            insertStatement.setDouble(3, totalPrice);
       
            
             int affectedRows = insertStatement.executeUpdate();
             if (affectedRows == 0) {
                throw new SQLException("Inserting order failed, no rows affected.");
             }
             
             try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);

                // Insert order items into sale_order_items table
                for (CartItem item : cartList) {
                    insertOrderItemsStatement.setInt(1, orderId);
                    insertOrderItemsStatement.setInt(2, item.getProductId());
                    insertOrderItemsStatement.setString(3, item.getProductName());
                    insertOrderItemsStatement.setInt(4, item.getQuantity());
                    insertOrderItemsStatement.setDouble(5, item.getPrice());
                    
                    // 查询库存，更新库存
                    queryProductStatement.setInt(1, item.getProductId()); 
                    ResultSet queryResultSet =  queryProductStatement.executeQuery(); // 执行查询
                    while (queryResultSet.next()) {
                        Integer productId = queryResultSet.getInt("productId");
                        Integer stock = queryResultSet.getInt("stock");
                        
                        Integer less = stock - item.getQuantity();
                        updateProductStatement.setInt(1,less);
                        updateProductStatement.setInt(2,productId);
                        int rowsAffected = updateProductStatement.executeUpdate();
                    }

                    insertOrderItemsStatement.addBatch(); // Add batch for efficient insertion
                }
                // Execute batch insertion
                insertOrderItemsStatement.executeBatch();
            } else {
                throw new SQLException("Inserting order failed, no ID obtained.");
            }
                
        }  
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
                
         return true; 
    }
     
    public void clearCartItems(List<CartItem> cartList) {
        String deleteCartQuery = "DELETE FROM cart_items WHERE customer_id = ? and product_id = ?";
       try (PreparedStatement statement = connection.prepareStatement(deleteCartQuery)) {
            for (CartItem item : cartList) {
                statement.setInt(1, item.getCustomerId());
                statement.setInt(2, item.getProductId());
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     }
     

}
