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
        // This targets the first real list (ignores the "add-new" list at the end)
        By addCardButton = By.cssSelector(".list:not(.add-new) footer .add-new");
        wait.until(ExpectedConditions.elementToBeClickable(addCardButton)).click();
    }

    public void insertCardName(String cardName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("card_name"))).sendKeys(cardName);
    }

    public void clickAddCardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("#new_card_form button[type='submit']")))).click();
    }

    public void clickCancelCardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("#new_card_form a")))).click();
    }

    //--------------------------------------------------//
    //                  Card Modal                      //
    //--------------------------------------------------//
    public void CardModalOpen(){
        By firstCardSelector = By.cssSelector(".lists-wrapper .list:not(.add-new) .cards-wrapper .card .card-content");

        WebElement firstCard = wait.until(ExpectedConditions.elementToBeClickable(firstCardSelector));
        firstCard.click();
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

    public void enterDescription(String description){
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea:nth-child(2)")));
        textarea.clear();
        textarea.sendKeys(description);
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
    public void insertComment(String comment){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea"))).sendKeys(comment);
    }

    public void saveComment(){
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }
}
