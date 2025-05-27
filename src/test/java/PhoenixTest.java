import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.*;

import pages.LoginPage;
import pages.DashboardPage;
import pages.BoardPage;
import pages.CardPage;
import pages.ListPage;
import pages.UserPage;
import pages.RegisterPage;

import java.time.Duration;

public class PhoenixTest {

    //----------------- variables for the tests methods ----------------//
    private String boardID = "1-board_01";


    //----------------- WebDriver and WebDriverWait ----------------//
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/opt/firefox/firefox");
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
    // --- REGISTER PAGE TESTS ---//

    @Test
    public void registerWithValidDataSucceeds() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.goToRegisterPage();
        registerPage.enterFirstName("Zahin");
        registerPage.enterLastname("Abdullah Rakin");
        registerPage.enterEmail("zahinabdullahrakin@gmail.com");
        registerPage.enterPassword("12345678");
        registerPage.enterPasswordConfirmation("12345678");
        registerPage.clickRegisterButton();
    }
    @Test
    public void registerWithInsufficientDataDoesNothing() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.goToRegisterPage();
        registerPage.enterEmail("rsrsrsrakin87@gmail.com");
        registerPage.enterPassword("12345678");
        registerPage.enterPasswordConfirmation("12345678");
        registerPage.clickRegisterButton();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:4000/sign_up", currentUrl);
    }

    // --- LOGIN PAGE TESTS ---

    @Test
    public void loginWithValidCredentialsSucceeds() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.insertEmail("john@phoenix-trello.com");
        loginPage.insertPassword("12345678");
        loginPage.clickLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add_new_board")));
    }

    @Test
    public void loginWithEmptyFieldsDoesNothing() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        String urlBefore = driver.getCurrentUrl();
        loginPage.insertEmail("");
        loginPage.insertPassword("");
        loginPage.clickLogin();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        String urlAfter = driver.getCurrentUrl();
        Assert.assertEquals(urlBefore, urlAfter);
    }


    @Test
    public void loginWithInvalidCredentialsShowsError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        String urlBefore = driver.getCurrentUrl();
        loginPage.insertEmail("asdfaa");
        loginPage.insertPassword("1234567as8");
        loginPage.clickLogin();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        String urlAfter = driver.getCurrentUrl();
        Assert.assertEquals(urlBefore, urlAfter); // URL should not change
    }

    public void validLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.insertEmail("john@phoenix-trello.com");
        loginPage.insertPassword("12345678");
        loginPage.clickLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add_new_board")));
    }

    // --- DASHBOARD PAGE TESTS ---
    @Test
    public void loginAndLogout(){
        validLogin();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickLogout();
    }
    @Test
    public void clickPhoenixLogoNavigatesToDashboard() {
        validLogin();

        DashboardPage dashboardPage = new DashboardPage(driver);
        
        BoardPage boardPage = new BoardPage(driver);
        boardPage.clickAddNewBoard();
        boardPage.insertBoardName("board_01");
        boardPage.clickCreateBoard();
        
        dashboardPage.clickPhoenixLogo();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:4000/", currentUrl);
    }

    // --- BOARD PAGE TESTS ---
    @Test
    public void createBoard(){
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        boardPage.clickAddNewBoard();
        boardPage.insertBoardName("board_01");
        boardPage.clickCreateBoard();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals("http://localhost:4000/", currentUrl);
    }
    @Test
    public void clickCreateBoardButCancel(){
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        boardPage.clickAddNewBoard();
        boardPage.insertBoardName("board_01");
        boardPage.clickCancelBoard();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:4000/", currentUrl);
    }
    
    @Test
    public void clickBoardsDropdownAndSelectABoard(){
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        boardPage.clickBoardsDropdown();
        boardPage.clickBoardsDropdownItem("board_01");
    }

    @Test
    public void clickBoardsDropdownAndViewAllBoards() {
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        boardPage.clickBoardsDropdown();
        boardPage.clickViewAllBoards();
    }

    @Test
    public void addAnotherMemberToBoard() {
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        boardPage.gotoBoard(boardID);

        UserPage userPage = new UserPage(driver);
        userPage.clickAddNewUser();
        userPage.insertUserEmail("not the email I want");
        userPage.clickCancel();

        userPage.clickAddNewUser();
        userPage.insertUserEmail("zahinabdullahrakin@gmail.com");
        userPage.clickAddUserButton();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
    }
    // --- LIST PAGE TESTS ---
    @Test
    public void createList() {
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        boardPage.gotoBoard(boardID);

        ListPage listPage = new ListPage(driver);
        listPage.clickAddList();
        listPage.insertListName("not_the_list_name_i_want");
        listPage.clickCancelButton();
        listPage.clickAddList();
        listPage.insertListName("test_create_list");
        listPage.clickAddListButton();
    }

    @Test
    public void changeListName(){
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        boardPage.gotoBoard(boardID);
        ListPage listPage = new ListPage(driver);
        listPage.clickAddList();
        listPage.insertListName("name_before_change");
        listPage.clickAddListButton();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        // Now change the name of the list
        listPage.clickListTitle("name_before_change");
        listPage.updateListName("name_after_change");
        listPage.clickUpdateListButton();
    }

    // --- CARD PAGE TESTS ---

    @Test
    public void createCard() {
        validLogin();

        BoardPage boardPage = new BoardPage(driver);
        boardPage.gotoBoard(boardID);

        CardPage cardPage = new CardPage(driver);
        cardPage.clickAddCard();
        cardPage.insertCardName("not_the_card_name_i_want");
        cardPage.clickCancelCardButton();

        cardPage.clickAddCard();
        cardPage.insertCardName("card_01");
        cardPage.clickAddCardButton();
    }

    @Test
    public void cardModalOpenClose() {
        validLogin();

        BoardPage boardPage = new BoardPage(driver);
        boardPage.gotoBoard(boardID);

        CardPage cardPage = new CardPage(driver);
        cardPage.clickAddCard();
        cardPage.insertCardName("card_for_modal_test");
        cardPage.clickAddCardButton();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        cardPage.CardModalOpen();
        cardPage.CardModalCross();
    }

    @Test
    public void cardAddComment(){
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        boardPage.gotoBoard(boardID);

        CardPage cardPage = new CardPage(driver);
        cardPage.CardModalOpen();
        cardPage.insertComment("testing commenting in the card");
        cardPage.saveComment();
        cardPage.CardModalCross();
    }

    @Test
    public void clickMembersButton(){
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        boardPage.gotoBoard(boardID);

        CardPage cardPage = new CardPage(driver);
        cardPage.CardModalOpen();
        cardPage.clickMembersButton();
        cardPage.clickMembersCross();
        cardPage.CardModalCross();
    }

    @Test
    public void selectTagsColor(){
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        boardPage.gotoBoard(boardID);

        CardPage cardPage = new CardPage(driver);
        cardPage.CardModalOpen();
        cardPage.clickTagsButton();
        cardPage.selectTagColor();
        cardPage.clickTagsCross();
        cardPage.CardModalCross();
    }

    @Test
    public void editCard(){
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        boardPage.gotoBoard(boardID);

        CardPage cardPage = new CardPage(driver);
        cardPage.CardModalOpen();

        cardPage.clickEditCard();
        cardPage.updateCardName("updated_card_name");
        cardPage.enterDescription("This is a description for the card.");
        cardPage.clickCancelEditButton(); //cancelling the edit

        cardPage.clickEditCard();
        cardPage.updateCardName("updated_card_name");
        cardPage.enterDescription("This is a description for the card.");
        cardPage.clickSaveCardButton(); //saving the edit

        cardPage.CardModalCross();
    }

    @Test
    public void deleteCard(){
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        boardPage.gotoBoard(boardID);

        CardPage cardPage = new CardPage(driver);
        cardPage.CardModalOpen();
        cardPage.clickDeleteCardButton();
    }
}