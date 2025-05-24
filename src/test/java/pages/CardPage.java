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
        // From AddCard.java: driver.findElement(By.linkText("Add a new card...")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add a new card..."))).click();
    }

    public void insertCardName(String cardName){
        // From AddCard.java: driver.findElement(By.id("card_name")).sendKeys("item2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("card_name"))).sendKeys(cardName);
    }

    public void clickAddCardButton() {
        // From AddCard.java: driver.findElement(By.cssSelector("button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }

    public void clickCancelCardButton() {
        // From CancelCard.java: driver.findElement(By.linkText("cancel")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("cancel"))).click();
    }

    //--------------------------------------------------//
    //                  Card Modal                      //
    //--------------------------------------------------//
    public void CardModalOpen(){
        // From AddingCommentToCardTest.java: driver.findElement(By.cssSelector("#card_3 > .card-content")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#card_3 > .card-content"))).click();
    }

    public void CardModalCross() {
        // From CardModalCross.java: driver.findElement(By.cssSelector(".fa-close")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fa-close"))).click();
    }

    //------------------------------------------------//
    //                    Edit Card                   //
    //------------------------------------------------//
    public void clickEditCard() {
        // From ClickEdit.java: driver.findElement(By.linkText("Edit")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Edit"))).click();
    }

    public void updateCardName(String newCardName) {
        // From EditInsertNameTest.java: driver.findElement(By.cssSelector("input")).sendKeys("item2_updated");
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input")));
        input.clear();
        input.sendKeys(newCardName);
    }

    public void enterDescription(){
        // From EditInsertDescriptionTest.java: driver.findElement(By.cssSelector("textarea:nth-child(2)")).sendKeys("insert description");
        WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea:nth-child(2)")));
        textarea.clear();
        textarea.sendKeys("insert description");
    }

    public void clickSaveCardButton() {
        // From ClickSaveEditTest.java: driver.findElement(By.cssSelector("button:nth-child(3)")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button:nth-child(3)"))).click();
    }

    public void clickCancelEditButton(){
        // From EditClickCancelBtnTest.java: driver.findElement(By.linkText("cancel")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("cancel"))).click();
    }

    //--------------------------------------------------//
    //                Delete Card                      //
    //--------------------------------------------------//
    public void clickDeleteCardButton() {
        // From CardDelete.java: driver.findElement(By.cssSelector(".fa-trash-o")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fa-trash-o"))).click();
    }

    //--------------------------------------------------//
    //             members of Edit Card                 //
    //--------------------------------------------------//
    public void clickMembersButton() {
        // From ClickMembersButtonTest.java: driver.findElement(By.linkText("Members")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Members"))).click();
    }

    public void clickMembersCross(){
        // From MembersCrossTest.java: driver.findElement(By.cssSelector(".close:nth-child(2) > .fa")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close:nth-child(2) > .fa"))).click();
    }

    //--------------------------------------------------//
    //                Tags of Edit Card                 //
    //--------------------------------------------------//
    public void clickTagsButton() {
        // From ClickTags.java: driver.findElement(By.linkText("Tags")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tags"))).click();
    }

    public void clickTagsCross(){
        // From TagsCross.java: driver.findElement(By.cssSelector(".close:nth-child(2) > .fa")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".close:nth-child(2) > .fa"))).click();
    }

    public void selectTagColor(){
        // From ClickTagsColor.java: driver.findElement(By.cssSelector(".yellow")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".yellow"))).click();
    }

    //--------------------------------------------------//
    //                      Comments                    //
    //--------------------------------------------------//
    public void insertComment(){
        // From InsertComment.java: driver.findElement(By.cssSelector("textarea")).sendKeys("comment 1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea"))).sendKeys("comment 1");
    }

    public void saveComment(){
        // From ClickSaveComment.java: driver.findElement(By.cssSelector("button")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button"))).click();
    }

    public void clickCardModalCross(){
        // From CardModalCross.java: driver.findElement(By.cssSelector(".fa-close")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fa-close"))).click();
    }
}
