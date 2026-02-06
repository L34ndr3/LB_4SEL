package tests;

import org.junit.jupiter.api.Test;
import pages.InventoryPage;
import pages.LoginPage;
import io.qameta.allure.*;
import static org.junit.jupiter.api.Assertions.*;

@Feature("Inventory management")
public class InventoryTest extends BaseTest {

    @Test
    @Story("Inventory page display")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the inventory page is displayed after a successful login")
    public void testInventoryPageDisplayed() {
        LoginPage loginPage = new LoginPage(driver).open();

        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");

        assertTrue(
                inventoryPage.isDisplayed(),
                "Inventory page should be displayed"
        );
        assertEquals(
                inventoryPage.getPageTitle(),
                "Products",
                "Inventory page title should be 'Products'"
        );
    }

    @Test
    @Story("Add product to cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that a product can be added to the cart from the inventory page")
    public void testAddProductToCart() {
        LoginPage loginPage = new LoginPage(driver).open();

        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");

        inventoryPage.addBackpackToCart();

        assertEquals(
                inventoryPage.getCartItemCount(),
                1,
                "Cart badge should display 1 item"
        );
    }
}
