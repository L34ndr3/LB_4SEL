package tests;

import pages.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Checkout process")
public class CheckoutTest extends BaseTest {

    @Test
    @Story("Checkout with valid customer information")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that checkout continues to overview page when valid customer information is provided")
    void testCheckoutWithValidInformation() {

        LoginPage loginPage = new LoginPage(driver).open();
        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");

        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.clickCartIcon();

        assertTrue(cartPage.isDisplayed(), "Cart page should be displayed");


        CheckoutPage checkoutPage = cartPage.clickCheckout();

        assertTrue(checkoutPage.isDisplayed(), "Checkout page should be displayed");


        checkoutPage.fillInformation("John", "Doe", "75001");


        CheckoutOverviewPage overviewPage = checkoutPage.clickContinue();

        assertTrue(
                overviewPage.isDisplayed(),
                "Checkout overview page should be displayed"
        );
    }

    @Test
    @Story("Checkout with missing first name")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that an error message is displayed when first name is missing")
    void testCheckoutWithMissingFirstName() {


        LoginPage loginPage = new LoginPage(driver).open();
        InventoryPage inventoryPage =
                loginPage.login("standard_user", "secret_sauce");

        inventoryPage.addBackpackToCart();
        CartPage cartPage = inventoryPage.clickCartIcon();

        CheckoutPage checkoutPage = cartPage.clickCheckout();

        checkoutPage.fillInformation("", "Doe", "75001");
        checkoutPage.clickContinue();

        assertFalse(
                checkoutPage.getErrorMessage().isEmpty(),
                "Error message should be displayed when first name is missing"
        );
    }
}
