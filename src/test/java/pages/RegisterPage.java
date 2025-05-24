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

    public void clickCreateAccountBtn() {
        WebElement createAccountLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Create new account"))
        );
        createAccountLink.click();
    }

    public void enterFirstName(String firstName){
        WebElement firstNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user_first_name"))
        );
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastname(String lastName){
        WebElement lastNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user_last_name"))
        );
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user_email"))
        );
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user_password"))
        );
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterPasswordConfirmation(String passwordConfirmation) {
        WebElement passwordConfirmationField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user_password_confirmation"))
        );
        passwordConfirmationField.clear();
        passwordConfirmationField.sendKeys(passwordConfirmation);
    }

    public void clickRegisterButton() {
        WebElement registerButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button"))
        );
        registerButton.click();
    }
}
