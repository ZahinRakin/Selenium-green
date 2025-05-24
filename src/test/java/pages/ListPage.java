package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ListPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void clickAddList() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".add-new > .inner"))).click();
    }

    public void insertListName(String listName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("list_name"))).sendKeys(listName);
    }

    public void clickAddListButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }

    public void clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("cancel"))).click();
    }

    public void clickListTitle(int listId) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#list_" + listId + " h4"))).click();
    }

    public void updateListName(String newName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("list_name"))).sendKeys(newName);
    }

    public void clickUpdateListButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }
}
