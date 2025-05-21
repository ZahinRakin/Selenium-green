package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By addBoardBtn = By.id("add_new_board");
    private final By boardNameInput = By.id("board_name");
    private final By logoutBtn = By.cssSelector("#crawler-sign-out > span");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAddBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(addBoardBtn)).click();
    }

    public void insertBoardName(String boardName) {
        var input = driver.findElement(boardNameInput);
        input.click();
        input.clear();
        input.sendKeys(boardName);
    }

    public void clickCreateBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(createBoardBtn)).click();
    }
    public void clickCancelBoard(){
        wait.until(ExpectedConditions.elementToBeClickable(cancelBoardBtn)).click();
    }

    public void clickBoardCard() {
        wait.until(ExpectedConditions.elementToBeClickable(boardCard)).click();
    }

    public void insertListName(String listName) {
        var input = driver.findElement(listNameInput);
        input.clear();
        input.sendKeys(listName);
    }

    public void addList(String listName) {
        insertListName(listName);
        clickCreateBoard();
    }

    public void clickAddIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(addIcon)).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
    }

    public void createBoard(String boardName, String listName) {
        addBoard(boardName);
        openFirstBoard();
        addList(listName);
    }

    public void doRandomClicksAndLogout() {
        clickAddIcon();
        clickAddIcon();
        clickLogout();
    }

    public void cancelAddList() {
        driver.findElement(addNewListBtn).click();
        driver.findElement(cancelLink).click();
    }

    public void addBoard(String boardName) {
        clickAddBoard();
        insertBoardName(boardName);
        clickCreateBoard();
    }

    public void openFirstBoard() {
        clickBoardCard();
    }

    public void clickPhoenixLogo(){
        driver.findElement(By.cssSelector(".logo")).click();
    }
}