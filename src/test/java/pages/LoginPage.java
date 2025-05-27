package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.JavascriptExecutor;

public class LoginPage {
    private final WebDriver driver;

    private final By emailInput = By.id("user_email");
    private final By passwordInput = By.id("user_password");
    private final By loginBtn = By.cssSelector("button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void insertEmail(String email) {
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
    }

    public void insertPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(loginBtn))
                .click();
    }

    // public void clearAutofilledFields() {
    //     ((JavascriptExecutor) driver).executeScript(
    //             "document.getElementById('user_email').value='';" +
    //                     "document.getElementById('user_password').value='';"
    //     );
    // }

    public void login(String email, String password) {
        insertEmail(email);
        insertPassword(password);
        clickLogin();
    }

    public void goToLoginPage() {
        driver.get("http://localhost:4000/sign_in");
        // clearAutofilledFields();
    }
}