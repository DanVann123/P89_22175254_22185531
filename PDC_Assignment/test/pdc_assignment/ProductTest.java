/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author xuyan
 */
public class ProductTest {
    
     public ProductTest() {
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
     * Test of insertProduct method, of class DatabaseManager.
     */
    @Test
    public void testInsertProduct() {
        System.out.println("insertProduct");
        int productId = 5;
        String productName = "pen box";
        int stock = 100;
        double price = 10.99;
        DatabaseManager instance = new DatabaseManager();
        // Ensure product does not exist before insertion
        instance.removeProduct(productId);
        
        boolean expResult = true;
        boolean result = instance.insertProduct(productId, productName, stock, price);
        assertEquals(expResult, result);
        // Verify the product was actually inserted
        Vector<Vector<Object>> products = instance.selectProduct();
        boolean productFound = false;
        for (Vector<Object> product : products) {
            if ((int) product.get(0) == productId) {
                assertEquals(productName, product.get(1));
                assertEquals(stock, product.get(2));
                assertEquals(price, product.get(3));
                productFound = true;
                break;
            }
        }
        assertTrue(productFound);
    }

     /**
     * Test of selectProduct method, of class DatabaseManager.
     */
    @Test
    public void testSelectProduct() {
       System.out.println("selectProduct");
       DatabaseManager instance = new DatabaseManager();
       Vector<Vector<Object>> result = instance.selectProduct();
        
        
        assertNotNull(result);
        
     
         for (Vector<Object> row : result) {
            assertNotNull(row.get(0)); // productId
            assertNotNull(row.get(1)); // productName
            assertNotNull(row.get(2)); // stock
            assertNotNull(row.get(3)); // price
        }
    }
    /**
     * Test of removeProduct method, of class DatabaseManager.
     */
    @Test
    public void testRemoveProduct() {
       
    System.out.println("removeProduct");
    int productId = 1;
    DatabaseManager instance = new DatabaseManager();
    // Insert a product to remove
    instance.insertProduct(productId, "pen", 1000, 3.99);

    boolean result = instance.removeProduct(productId);
    assertTrue(result);

    }
}
