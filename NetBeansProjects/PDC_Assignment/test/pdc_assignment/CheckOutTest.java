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
public class CheckOutTest {
    
    public CheckOutTest() {
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
     * Test of checkout method, of class DatabaseManager.
     */
    @Test
    public void testCheckout() throws Exception {
        System.out.println("checkout");
    int customerId = 456;
    Vector<Vector<Object>> cartItems = new Vector<>();
    
    Vector<Object> cartItem1 = new Vector<>();
    cartItem1.add(2); // product_id
    cartItem1.add("ruler"); // product_name
    cartItem1.add(2); // quantity
    cartItem1.add(4.99); // price
    cartItems.add(cartItem1);

    Vector<Object> cartItem2 = new Vector<>();
    cartItem2.add(1); // product_id
    cartItem2.add("pen"); // product_name
    cartItem2.add(1); // quantity
    cartItem2.add(1.99); // price
    cartItems.add(cartItem2);

    DatabaseManager instance = new DatabaseManager();
    
    // Insertcart items
    instance.insertCart(customerId, 2, "ruler", 2, 4.99);
    instance.insertCart(customerId, 1, "pen", 1, 1.99);

    // Perform checkout
    instance.checkout(customerId, cartItems);

    // Clear out the cartItems vector
    cartItems.clear();
    // Verify the cart is cleared
    Vector<Vector<Object>> cartAfterCheckout = instance.selectCart();
    for (Vector<Object> item : cartAfterCheckout) {
        assertNotEquals(customerId, item.get(0));
    }

    
    }
}
