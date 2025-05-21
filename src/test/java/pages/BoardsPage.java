package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BoardsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Example locators (update as needed)
    private final By createBoardBtn = By.cssSelector("#new_board_form button[type='submit']");
    private final By cancelBoardBtn = By.cssSelector("#new_board_form a[href='#']");
    private final By addListBtn = By.cssSelector(".add-new > .inner"); //cancel add board button
    private final By cancelLink = By.cssSelector("#new_list_form a[href='#']"); //cancel list button
    private final By boardCard = By.cssSelector(".board:not(.form) .inner");
    private final By listNameInput = By.id("list_name");
    private final By addOtherUser = By.cssSelector(".fa-plus"); // add another user button

    public BoardsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAddList() {
        wait.until(ExpectedConditions.elementToBeClickable(addListBtn)).click();
    }
    public void clickCancelList(){
        wait.until(ExpectedConditions.elementToBeClickable(cancelBoardBtn)).click();
    }

    public void insertListName(String listName) {
        var input = driver.findElement(listNameInput);
        input.clear();
        input.sendKeys(listName);
    }

    public void clickCreateList() {
        wait.until(ExpectedConditions.elementToBeClickable(createListBtn)).click();
    }

    public String getBoardTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(boardTitle)).getText();
    }
}