package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    private By pageTitle = By.className("title");
    private By cartItems = By.className("cart_item");
    private By itemTotalLabel = By.className("summary_subtotal_label");
    private By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return !driver.findElements(pageTitle).isEmpty()
                && driver.findElement(pageTitle).getText().equalsIgnoreCase("Checkout: Overview");
    }

    public int getItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public String getTotalPrice() {
        return driver.findElement(itemTotalLabel).getText();
    }

    public CheckoutCompletePage clickFinish() {
        driver.findElement(finishButton).click();
        return new CheckoutCompletePage(driver);
    }
}
