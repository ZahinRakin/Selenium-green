package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class BoardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public BoardPage(WebDriver driver) {
        this.driver = driver; // url : http://localhost:4000
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAddNewBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_new_board"))).click();
    }
    
    public void insertBoardName(String boardName) {
        driver.findElement(By.id("board_name")).sendKeys(boardName);
    }
    
    public void clickCreateBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }
    
    public void clickCancelBoard() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("cancel"))).click();
    }

    //------------------------------------//
    //       Boards dropdown methods      //
    //------------------------------------//
    
    public void clickBoardsDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#boards_nav span"))).click();
    }
    
    public void clickBoardsDropdownItem(String item) { //name of the board
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(item))).click();
    }
    
    public void clickViewAllBoards() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("View all boards"))).click();
    }
}