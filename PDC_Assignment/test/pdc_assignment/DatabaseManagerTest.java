/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pdc_assignment;

import java.util.List;
import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xuyan
 */
public class DatabaseManagerTest {
    
    public DatabaseManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        
    }
    
    @After
    public void tearDown() {
        
        
    }

    /**
     * Test of insertUser method, of class DatabaseManager.
     */
    @Test
    public void testInsertUser() throws Exception {
        System.out.println("insertUser");
        String username = "111";
        String password = "111";
        String userGroup = "admin";
        DatabaseManager instance = new DatabaseManager();
        int expResult = 1;
        int result = instance.insertUser(username, password, userGroup);
        assertEquals(expResult, result);
    }

    /**
     * Test of userExists method, of class DatabaseManager.
     */
    @Test
    public void testUserExists() throws Exception {
        System.out.println("userExists");
        String username = "";
        String password = "";
        String userGroup = "";
        DatabaseManager instance = new DatabaseManager();
        int expResult = 0;
        int result = instance.userExists(username, password, userGroup);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of insertProduct method, of class DatabaseManager.
     */
    @Test
    public void testInsertProduct() {
        System.out.println("insertProduct");
        int productId = 0;
        String productName = "";
        int stock = 0;
        double price = 0.0;
        DatabaseManager instance = new DatabaseManager();
        boolean expResult = false;
        boolean result = instance.insertProduct(productId, productName, stock, price);
        assertEquals(expResult, result);
      
    }

    /**
     * Test of selectProduct method, of class DatabaseManager.
     */
    @Test
    public void testSelectProduct() {
        System.out.println("selectProduct");
        DatabaseManager instance = new DatabaseManager();
        Vector<Vector<Object>> expResult = null;
        Vector<Vector<Object>> result = instance.selectProduct();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of removeProduct method, of class DatabaseManager.
     */
    @Test
    public void testRemoveProduct() {
        System.out.println("removeProduct");
        int productId = 0;
        DatabaseManager instance = new DatabaseManager();
        boolean expResult = false;
        boolean result = instance.removeProduct(productId);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of insertOrder method, of class DatabaseManager.
     */
    @Test
    public void testInsertOrder_int_double() throws Exception {
        System.out.println("insertOrder");
        int customerId = 0;
        double totalPrice = 0.0;
        DatabaseManager instance = new DatabaseManager();
        int expResult = 0;
        int result = instance.insertOrder(customerId, totalPrice);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of updateInventory method, of class DatabaseManager.
     */
    @Test
    public void testUpdateInventory() throws Exception {
        System.out.println("updateInventory");
        Vector<Object> cartItem = null;
        DatabaseManager instance = new DatabaseManager();
        instance.updateInventory(cartItem);
      
    }

    /**
     * Test of insertSaleOrderItems method, of class DatabaseManager.
     */
    @Test
    public void testInsertSaleOrderItems() throws Exception {
        System.out.println("insertSaleOrderItems");
        int saleOrderId = 0;
        int productId = 0;
        int quantity = 0;
        double price = 0.0;
        DatabaseManager instance = new DatabaseManager();
        instance.insertSaleOrderItems(saleOrderId, productId, quantity, price);

    }

    /**
     * Test of checkout method, of class DatabaseManager.
     */
    @Test
    public void testCheckout() throws Exception {
        System.out.println("checkout");
        int customerId = 0;
        Vector<Vector<Object>> cartItems = null;
        DatabaseManager instance = new DatabaseManager();
        instance.checkout(customerId, cartItems);
       
    }

    /**
     * Test of processOrder method, of class DatabaseManager.
     */
    @Test
    public void testProcessOrder() throws Exception {
        System.out.println("processOrder");
        Vector<Vector<Object>> cart_items = null;
        DatabaseManager instance = new DatabaseManager();
        instance.processOrder(cart_items);
      
    }

    /**
     * Test of insertCart method, of class DatabaseManager.
     */
    @Test
    public void testInsertCart() {
        System.out.println("insertCart");
        int userId = 0;
        int productId = 0;
        String productName = "";
        int quantity = 0;
        double price = 0.0;
        DatabaseManager instance = new DatabaseManager();
        boolean expResult = false;
        boolean result = instance.insertCart(userId, productId, productName, quantity, price);
        assertEquals(expResult, result);
       
    }
 

    /**
     * Test of selectCart method, of class DatabaseManager.
     */
    @Test
    public void testSelectCart() {
        System.out.println("selectCart");
        DatabaseManager instance = new DatabaseManager();
        Vector<Vector<Object>> expResult = null;
        Vector<Vector<Object>> result = instance.selectCart();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of deleteCartItem method, of class DatabaseManager.
     */
    @Test
    public void testDeleteCartItem() {
        System.out.println("deleteCartItem");
        Integer productId = null;
        DatabaseManager instance = new DatabaseManager();
        Boolean expResult = null;
        Boolean result = instance.deleteCartItem(productId);
        assertEquals(expResult, result);
     
    }

    /**
     * Test of checkoutCartItem method, of class DatabaseManager.
     */
    @Test
    public void testCheckoutCartItem() {
        System.out.println("checkoutCartItem");
        Integer order_Id = null;
        DatabaseManager instance = new DatabaseManager();
        Boolean expResult = null;
        Boolean result = instance.checkoutCartItem(order_Id);
        assertEquals(expResult, result);
    
    }

    /**
     * Test of selectCustomerOrderHistory method, of class DatabaseManager.
     */
    @Test
    public void testSelectCustomerOrderHistory() {
        System.out.println("selectCustomerOrderHistory");
        int customerId = 0;
        DatabaseManager instance = new DatabaseManager();
        Vector<Vector<Object>> expResult = null;
        Vector<Vector<Object>> result = instance.selectCustomerOrderHistory(customerId);
        assertEquals(expResult, result);
      
    }

    /**
     * Test of managerSelectCustomerOrderHistory method, of class DatabaseManager.
     */
    @Test
    public void testManagerSelectCustomerOrderHistory() {
        System.out.println("managerSelectCustomerOrderHistory");
        DatabaseManager instance = new DatabaseManager();
        Vector<Vector<Object>> expResult = null;
        Vector<Vector<Object>> result = instance.managerSelectCustomerOrderHistory();
        assertEquals(expResult, result);
   
    }

    /**
     * Test of insertOrder method, of class DatabaseManager.
     */
    @Test
    public void testInsertOrder_int_List() {
        System.out.println("insertOrder");
        int customerId = 0;
        List<CartItem> cartList = null;
        DatabaseManager instance = new DatabaseManager();
        boolean expResult = false;
        boolean result = instance.insertOrder(customerId, cartList);
        assertEquals(expResult, result);
     
    }

    /**
     * Test of clearCartItems method, of class DatabaseManager.
     */
    @Test
    public void testClearCartItems() {
        System.out.println("clearCartItems");
        List<CartItem> cartList = null;
        DatabaseManager instance = new DatabaseManager();
        instance.clearCartItems(cartList);
      
    }
    
}
