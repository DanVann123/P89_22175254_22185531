/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author xuyan
 */
public class OrderTest {
    
     public OrderTest() {
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
     * Test of insertOrder method, of class DatabaseManager.
     */
    @Test
    public void testInsertOrder_int_double() throws Exception {
        System.out.println("insertOrder");
        int customerId = 456;
        double totalPrice = 110;
        DatabaseManager instance = new DatabaseManager();
        int expResult =1402;
        int result = instance.insertOrder(customerId, totalPrice);
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of insertSaleOrderItems method, of class DatabaseManager.
     */
    @Test
    public void testInsertSaleOrderItems() throws Exception {
        System.out.println("insertSaleOrderItems");
        int saleOrderId = 1;
        int productId = 2;
        int quantity = 1;
        double price = 4.99;
        String productName = "ruler"; 
        DatabaseManager instance = new DatabaseManager();
        instance.insertSaleOrderItems(saleOrderId, productId, quantity, price);

    }

    /**
     * Test of selectCustomerOrderHistory method, of class DatabaseManager.
     */
    @Test
    public void testSelectCustomerOrderHistory() {
        System.out.println("selectCustomerOrderHistory");
        int customerId = 456;
        DatabaseManager instance = new DatabaseManager();
        Vector<Vector<Object>> expResult = new Vector<>(); // Empty vector
        Vector<Vector<Object>> result = instance.selectCustomerOrderHistory(customerId);
        assertEquals(expResult, result);
    }
}
