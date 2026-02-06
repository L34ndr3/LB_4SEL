package tests;

import pages.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Order completion")
public class CheckoutCompleteTest extends BaseTest {

    @Test
    @Story("Complete order flow from login to order confirmation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user can complete an order from login to confirmation message containing 'Thank you'")
    void testCompleteOrderFlow() {

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
        assertTrue(overviewPage.isDisplayed(), "Checkout overview page should be displayed");
        assertEquals(overviewPage.getItemCount(), 1, "There should be exactly one item in the overview");

        CheckoutCompletePage completePage = overviewPage.clickFinish();
        assertTrue(completePage.isDisplayed(), "Checkout complete page should be displayed");

        String confirmation = completePage.getConfirmationMessage();
        assertTrue(confirmation.contains("Thank you"), "Confirmation message should contain 'Thank you'"
        );
    }
}
