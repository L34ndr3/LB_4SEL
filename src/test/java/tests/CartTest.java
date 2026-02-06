package tests;

import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import io.qameta.allure.*;
import static org.junit.jupiter.api.Assertions.*;

@Feature("Shopping cart")
public class CartTest extends BaseTest {

    @Test
    @Story("Empty cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the cart is empty when no product has been added")
    public void testEmptyCart() {
        LoginPage loginPage = new LoginPage(driver).open();

        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");

        CartPage cartPage = inventoryPage.clickCartIcon();

        assertTrue(
                cartPage.isDisplayed(),
                "Cart page should be displayed"
        );
        assertEquals(
                cartPage.getItemCount(),
                0,
                "Cart should be empty"
        );
    }

    @Test
    @Story("Cart with one product")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the cart contains exactly one product after adding an item")
    public void testCartWithOneProduct() {
        LoginPage loginPage = new LoginPage(driver).open();

        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");

        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.clickCartIcon();

        assertTrue(
                cartPage.isDisplayed(),
                "Cart page should be displayed"
        );
        assertEquals(
                cartPage.getItemCount(),
                1,
                "Cart should contain exactly one product"
        );
    }
}
