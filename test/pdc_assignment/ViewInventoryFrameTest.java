/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pdc_assignment;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
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
public class ViewInventoryFrameTest {

    private ViewInventoryFrame viewInventoryFrame;

    @Before
    public void setUp() {
        viewInventoryFrame = new ViewInventoryFrame(new StubDatabaseManager()); // Pass stub implementation for testing
    }

    @Test
    public void testFetchInventoryData_NoData() {

        viewInventoryFrame = new ViewInventoryFrame(new StubDatabaseManagerEmpty());
        Vector<Vector<Object>> data = viewInventoryFrame.fetchInventoryData();
        System.out.println("Fetched data: " + data);
        assertTrue("Data should be empty", data.isEmpty());
    }

    @Test
    public void testFetchInventoryData_WithData() {

        Vector<Vector<Object>> data = viewInventoryFrame.fetchInventoryData();
        System.out.println("Fetched data: " + data);
        assertFalse("Data should not be empty", data.isEmpty());
        assertEquals("Data should contain one product", 1, data.size());
        assertEquals("Product name should be 'Apple'", "Apple", data.get(0).get(1));
    }

    @Test
    public void testGetInventoryTable() {
        JTable table = viewInventoryFrame.getInventoryTable();
        assertNotNull("Inventory table should not be null", table);

    }

    @Test
    public void testFrameInitialization() {
        assertNotNull("ViewInventoryFrame should not be null", viewInventoryFrame);

    }


    private static class StubDatabaseManager extends DatabaseManager {
        @Override
        public Vector<Vector<Object>> selectProduct() {
            Vector<Vector<Object>> products = new Vector<>();
            Vector<Object> product1 = new Vector<>();
            product1.add("1");
            product1.add("Apple");
            product1.add(100);
            product1.add(0.5);
            products.add(product1);
            return products;
        }
    }

   
    private static class StubDatabaseManagerEmpty extends DatabaseManager {
        @Override
        public Vector<Vector<Object>> selectProduct() {
            return new Vector<>();
        }
    }
}