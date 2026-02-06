package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {


    private By checkoutButton = By.id("checkout");
    private By cartItems = By.className("cart_item");
    private By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }


    public boolean isDisplayed() {
        return !driver.findElements(checkoutButton).isEmpty();
    }


    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    /*public CheckoutPage clickCheckout() {
        driver.findElement(checkoutButton).click();
        return new CheckoutPage(driver);
    }*/

    public InventoryPage clickContinueShopping() {
        driver.findElement(continueShoppingButton).click();
        return new InventoryPage(driver);
    }
}
