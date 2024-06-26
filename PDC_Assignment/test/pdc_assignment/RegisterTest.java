/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pdc_assignment;

import java.lang.reflect.InvocationTargetException;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import static org.junit.Assert.*;

/**
 *
 * @author Soprathna
 */
public class RegisterTest {

    private Register register;

    @Before
    public void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            register = new Register();
            register.databaseManager = new DatabaseManager() {
                @Override
                public void insertUser(String userName, String password, String userGroup) throws SQLException {

                    System.out.println("User inserted: " + userName + ", " + userGroup);
                }

                @Override
                public void closeConnection() {
                    System.out.println("Database connection closed.");
                }
            };
            register.setVisible(false);
        });
    }

    @Test
    public void testRegisterNormalUser() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            register.userText.setText("testUser");
            register.passwordText.setText("testPass");
            register.userGroupComboBox.setSelectedItem("customer");

            register.submitButton.doClick();
        });
    }

    @Test
    public void testRegisterAdminUser() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            register.userText.setText("adminUser");
            register.passwordText.setText("adminPass");
            register.userGroupComboBox.setSelectedItem("admin");

            UIManager.put("OptionPane.cancelButtonText", "admin");
            UIManager.put("OptionPane.yesButtonText", "admin");

            register.submitButton.doClick();
        });

    }

    @Test
    public void testInvalidAdminCredentials() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            
            register.userText.setText("adminUser");
            register.passwordText.setText("adminPass");
            register.userGroupComboBox.setSelectedItem("admin");

            UIManager.put("OptionPane.cancelButtonText", "wrongAdmin");
            UIManager.put("OptionPane.yesButtonText", "wrongPass");

            register.submitButton.doClick();
        });
    }

    @Test
    public void testDispose() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            register.dispose();
            assertFalse(register.isVisible());
            assertNotNull(register.databaseManager); 
        });
    }
}