package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private By firstNameInput = By.id("first-name");
    private By lastNameInput  = By.id("last-name");
    private By zipCodeInput   = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By errorMessage   = By.cssSelector("h3[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return !driver.findElements(firstNameInput).isEmpty()
                && !driver.findElements(continueButton).isEmpty();
    }

    public CheckoutPage fillInformation(String firstName, String lastName, String zipCode) {
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);

        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);

        driver.findElement(zipCodeInput).clear();
        driver.findElement(zipCodeInput).sendKeys(zipCode);

        return this;
    }

    public CheckoutOverviewPage clickContinue() {
        driver.findElement(continueButton).click();
        return new CheckoutOverviewPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
