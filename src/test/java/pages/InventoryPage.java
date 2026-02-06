package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {


    private By pageTitle = By.className("title");
    private By backpackAddToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartIcon = By.className("shopping_cart_link");


    public InventoryPage(WebDriver driver) {
        super(driver);
    }


    public boolean isDisplayed() {
        return driver.findElement(pageTitle).isDisplayed()
                && getPageTitle().equalsIgnoreCase("Products");
    }


    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public InventoryPage addBackpackToCart() {
        driver.findElement(backpackAddToCartButton).click();
        return this;
    }

    public int getCartItemCount() {
        if (driver.findElements(cartBadge).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    public CartPage clickCartIcon() {
        driver.findElement(cartIcon).click();
        return new CartPage(driver);
    }
}
