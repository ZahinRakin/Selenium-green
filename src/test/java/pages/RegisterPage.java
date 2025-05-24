package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void enterUsername(String username) {
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("username"))
        );
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("email"))
        );
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("password"))
        );
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickRegisterButton() {
        WebElement registerButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("register"))
        );
        registerButton.click();
    }

    public String getErrorMessage() {
        WebElement errorMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("error-message"))
        );
        return errorMsg.getText();
    }
}
