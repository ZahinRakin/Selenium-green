package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By phoneixLogo = By.cssSelector(".logo");


    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //basic methods
    public void goToDashboardPage() {
        driver.get("http://localhost:4000/");
    }
    public void clickLogout() {
        By logoutButton = By.cssSelector("#crawler-sign-out");
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
    public void clickPhoenixLogo(){
        wait.until(ExpectedConditions.elementToBeClickable(phoneixLogo)).click();
    }
}