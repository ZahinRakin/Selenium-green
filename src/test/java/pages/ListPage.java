package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ListPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void clickAddList() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".list.add-new > .inner"))).click();
    }

    public void insertListName(String listName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("list_name"))).sendKeys(listName);
    }

    public void clickAddListButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#new_list_form button[type='submit']"))).click();
    }

    public void clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("cancel"))).click();
    }

    public void clickListTitle(String titleText) {
        List<WebElement> headers = driver.findElements(By.cssSelector(".list:not(.add-new) h4"));
        for (WebElement h : headers) {
            if (h.getText().trim().equals(titleText)) {
                wait.until(ExpectedConditions.elementToBeClickable(h)).click();
                break;
            }
        }
    }

    public void updateListName(String newName) {
        var input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("list_name")));
        input.clear();
        input.sendKeys(newName);
    }

    public void clickUpdateListButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#new_list_form button[type='submit']"))).click();
    }

    public void clickCancelUpdateButton() { //debug later. then have to integrate it in changeListName method of PhoenixTest.java file
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("cancel"))).click();
    }
}
