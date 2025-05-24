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

    // Click the "Add a new list..." button
    public void clickAddList() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".add-new > .inner"))).click();
    }

    // Insert the list name in the input field
    public void insertListName(String listName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("list_name"))).sendKeys(listName);
    }

    // Click the "Add List" button (usually a button in the modal)
    public void clickAddListButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }

    // Click the "cancel" link in the add/update list modal
    public void clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("cancel"))).click();
    }

    // Click on a list's title to start editing (e.g., for list with id 3)
    public void clickListTitle(int listId) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#list_" + listId + " h4"))).click();
    }

    // Update the list name in the input field
    public void updateListName(String newName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("list_name"))).sendKeys(newName);
    }

    // Click the "Update List" button (usually a button in the modal)
    public void clickUpdateListButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }
}
