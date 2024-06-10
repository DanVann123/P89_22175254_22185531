/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pdc_assignment;

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
public class InventoryManagementGUITest {
    
    
    
    @Test
    public void testHandleRemove() {
        System.out.println("handleRemove");
        InventoryManagementGUI gui = new InventoryManagementGUI();
        gui.handleRemove();
        assertNotNull("RemoveProductFrame should be instantiated", gui.getRemoveProductFrame());
        assertTrue("RemoveProductFrame should be visible", gui.getRemoveProductFrame().isVisible());
    }

    @Test
    public void testHandleViewInventory() {
        System.out.println("handleViewInventory");
        InventoryManagementGUI gui = new InventoryManagementGUI();
        gui.handleViewInventory();
        assertNotNull("ViewInventoryFrame should be instantiated", gui.getViewInventoryFrame());
        assertTrue("ViewInventoryFrame should be visible", gui.getViewInventoryFrame().isVisible());
    }

    @Test
    public void testHandleAdd() {
        System.out.println("handleAdd");
        InventoryManagementGUI gui = new InventoryManagementGUI();
        gui.handleAdd();
        assertNotNull("AddProductFrame should be instantiated", gui.getAddProductFrame());
        assertTrue("AddProductFrame should be visible", gui.getAddProductFrame().isVisible());
    }
}
