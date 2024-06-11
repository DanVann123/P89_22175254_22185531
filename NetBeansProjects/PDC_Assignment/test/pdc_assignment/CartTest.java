/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author xuyan
 */
public class CartTest {
    
    public CartTest() {
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
     * Test of insertCart method, of class DatabaseManager.
     */
    @Test
    public void testInsertCart() {
        System.out.println("insertCart");
        int userId = 1;
        int productId = 1;
        String productName = "Test Product";
        int quantity = 2;
        double price = 19.99;

        DatabaseManager instance = new DatabaseManager();
        boolean expResult = true; // Expecting the insertion to be successful
        boolean result = instance.insertCart(userId, productId, productName, quantity, price);
        assertEquals(expResult, result);

        // Verify the cart item was actually inserted
        Vector<Vector<Object>> cartItems = instance.selectCart();
        boolean itemFound = false;
        for (Vector<Object> item : cartItems) {
            if ((int) item.get(1) == productId && (int) item.get(0) == userId) {
                assertEquals(productName, item.get(2));
                assertEquals(quantity, item.get(3));
                assertEquals(price, item.get(4));
                itemFound = true;
                break;
            }
        }
    assertTrue(itemFound);
}
    

    /**
     * Test of selectCart method, of class DatabaseManager.
     */
    @Test
    public void testSelectCart() {
        System.out.println("selectCart");
        DatabaseManager instance = new DatabaseManager();
        Vector<Vector<Object>> result = instance.selectCart();
        assertNotNull(result);

        //  at least one cart item exists 
        for (Vector<Object> row : result) {
            assertNotNull(row.get(0)); // customer_id
            assertNotNull(row.get(1)); // product_id
            assertNotNull(row.get(2)); // product_name
            assertNotNull(row.get(3)); // quantity
            assertNotNull(row.get(4)); // price
        }    }
/**
 * Test of deleteCartItem method, of class DatabaseManager.
 */
@Test
public void testDeleteCartItem() {
    System.out.println("deleteCartItem");
    int userId = 1;
    int productId = 1;

    DatabaseManager instance = new DatabaseManager();
    
    // Insert a dummy cart item for deletion
    instance.insertCart(userId, productId, "Test Product", 2, 19.99);

    // Ensure the cart item exists before deletion
    Vector<Vector<Object>> cartItemsBeforeDelete = instance.selectCart();
    boolean itemFound = false;
    for (Vector<Object> item : cartItemsBeforeDelete) {
        if ((int) item.get(1) == productId && (int) item.get(0) == userId) {
            itemFound = true;
            break;
        }
    }
    assertTrue(itemFound);

    // Perform deletion
    Boolean result = instance.deleteCartItem(productId);
    assertTrue(result);

    // Ensure the cart item is deleted
    Vector<Vector<Object>> cartItemsAfterDelete = instance.selectCart();
    itemFound = false;
    for (Vector<Object> item : cartItemsAfterDelete) {
        if ((int) item.get(1) == productId && (int) item.get(0) == userId) {
            itemFound = true;
            break;
        }
    }
    assertFalse(itemFound);
}

}
