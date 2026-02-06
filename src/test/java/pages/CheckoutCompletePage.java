package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private By pageTitle = By.className("title");
    private By confirmationMessage = By.className("h2[data-test='complete-header']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return !driver.findElements(pageTitle).isEmpty()
                && driver.findElement(pageTitle).getText().equalsIgnoreCase("Checkout: Complete!");
    }

    public String getConfirmationMessage() {
        if (!driver.findElements(confirmationMessage).isEmpty()) {
            return driver.findElement(confirmationMessage).getText();
        }
        return "";
    }
}
