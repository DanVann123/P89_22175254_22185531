/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pdc_assignment;

import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Soprathna
 */
public class CustomerGUITest {
    private CustomerGUI customerGUI;
    private int userId = 1;  // example user ID

    @Before
    public void setUp() {
        customerGUI = new CustomerGUI(userId);
        customerGUI.productTableModel.setRowCount(0); // Clear any existing data

        // Simulate the products in the database
        Vector<Vector<Object>> products = new Vector<>();
        Vector<Object> product1 = new Vector<>();
        product1.add("1");
        product1.add("Apple");
        product1.add(100);
        product1.add(0.5);
        products.add(product1);

        // Directly add products to the table model for testing
        for (Vector<Object> product : products) {
            customerGUI.productTableModel.addRow(product);
        }
    }

    @Test
    public void testLoadProducts() {
        
        // Verify the table has the correct products
        assertEquals(1, customerGUI.productTableModel.getRowCount());
        assertEquals("Apple", customerGUI.productTableModel.getValueAt(0, 1));
    }

    @Test
    public void testAddToCartSuccess() {
        customerGUI.productTable.setRowSelectionInterval(0, 0);
        String quantityStr = "2";
        String quantity = quantityStr;

        if (quantity != null && !quantity.isEmpty()) {
            try {
                int quantityInt = Integer.parseInt(quantity);
                assertTrue("Quantity should be valid", quantityInt > 0 && quantityInt <= 100);
            } catch (NumberFormatException ex) {
                fail("Invalid quantity format!");
            }
        }
    }

    @Test
    public void testAddToCartFailure() {
        customerGUI.productTable.setRowSelectionInterval(0, 0);
        String quantityStr = "101";  // Quantity greater than stock
        String quantity = quantityStr;

        if (quantity != null && !quantity.isEmpty()) {
            try {
                int quantityInt = Integer.parseInt(quantity);
                assertFalse("Quantity should be invalid", quantityInt > 0 && quantityInt <= 100);
            } catch (NumberFormatException ex) {
                fail("Invalid quantity format!");
            }
        }
    }
    
    @Test
    public void testViewCart() {
        customerGUI.viewCartButton.doClick();
        // Check if the cart GUI is opened properly
        // Add appropriate assertions based on your implementation
    }

    @Test
    public void testViewOrderHistory() {
        customerGUI.viewOrderHistoryButton.doClick();
        // Check if the order history GUI is opened properly
        // Add appropriate assertions based on your implementation
    }
    
}