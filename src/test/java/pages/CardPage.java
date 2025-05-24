package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CardPage {
    private WebDriver driver;
    private WebDriverWait wait;
    public CardPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void clickAddCard() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add a new card..."))).click();
    }

    public void insertCardName(String cardName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("card_name"))).sendKeys(cardName);
    }

    public void clickAddCardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }

    public void clickCancelCardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("cancel"))).click();
    }

    //--------------------------------------------------//
    //                  Card Modal                      //
    //--------------------------------------------------//
    public void CardModalOpen(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#card_3 > .card-content"))).click();
    }

    public void CardModalCross() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fa-close"))).click();
    }

    //------------------------------------------------//
    //                    Edit Card                   //
    //------------------------------------------------//
    public void clickEditCard() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Edit"))).click();
    }

    public void updateCardName(String newCardName) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input")));
        input.clear();
        input.sendKeys(newCardName);
    }

    public void enterDescription(){
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea:nth-child(2)")));
        textarea.clear();
        textarea.sendKeys("insert description");
    }

    public void clickSaveCardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button:nth-child(3)"))).click();
    }

    public void clickCancelEditButton(){
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("cancel"))).click();
    }

    //--------------------------------------------------//
    //                Delete Card                      //
    //--------------------------------------------------//
    public void clickDeleteCardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fa-trash-o"))).click();
    }

    //--------------------------------------------------//
    //             members of Edit Card                 //
    //--------------------------------------------------//
    public void clickMembersButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Members"))).click();
    }

    public void clickMembersCross(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close:nth-child(2) > .fa"))).click();
    }

    //--------------------------------------------------//
    //                Tags of Edit Card                 //
    //--------------------------------------------------//
    public void clickTagsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tags"))).click();
    }

    public void clickTagsCross(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close:nth-child(2) > .fa"))).click();
    }

    public void selectTagColor(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".yellow"))).click();
    }

    //--------------------------------------------------//
    //                      Comments                    //
    //--------------------------------------------------//
    public void insertComment(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea"))).sendKeys("comment 1");
    }

    public void saveComment(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }

    public void clickCardModalCross(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fa-close"))).click();
    }
}
