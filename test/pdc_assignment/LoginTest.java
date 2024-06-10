/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pdc_assignment;

import org.junit.Before;
import org.junit.Test;
import javax.swing.SwingUtilities;
import static org.junit.Assert.*;

/**
 *
 * @author Soprathna
 */
public class LoginTest {
    
    private Login login;

    @Before
    public void setUp() {
        login = new Login();
        login.setDatabaseManager(new DatabaseManager() {
            @Override
            public int userExists(String userName, String password, String userGroup) {
                if ("dan".equals(userName) && "dan123".equals(password) && "admin".equals(userGroup)) {
                    return 1; // Simulate successful admin login
                } else if ("invalidUser".equals(userName) && "wrongPassword".equals(password) && "admin".equals(userGroup)) {
                    return -1; // Simulate failed login
                }
                return -1;
            }
        });
    }

    @Test
    public void testLoginAsAdmin() {
        // Simulate user input
        login.userText.setText("dan");
        login.passwordText.setText("dan123");
        login.userGroupComboBox.setSelectedItem("admin");

        login.loginButton.doClick();

        InventoryManagementGUI inventoryManagementGUI = login.getInventoryManagementGUI();
        assertNotNull("InventoryManagementGUI should be instantiated", inventoryManagementGUI);
        assertTrue("InventoryManagementGUI should be visible", inventoryManagementGUI.isVisible());
    }

    @Test
    public void testLoginInvalidUser() {
        // Simulate user input
        login.userText.setText("invalidUser");
        login.passwordText.setText("wrongPassword");
        login.userGroupComboBox.setSelectedItem("admin");

        login.loginButton.doClick();

        InventoryManagementGUI inventoryManagementGUI = login.getInventoryManagementGUI();
        assertNull("InventoryManagementGUI should not be instantiated", inventoryManagementGUI);
    }
}