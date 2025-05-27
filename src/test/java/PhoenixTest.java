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

    //repetitive tasks in the tests
    private void createBoard(BoardPage boardPage, String boardName) {
        boardPage.clickAddNewBoard();
        boardPage.insertBoardName(boardName);
        boardPage.clickCreateBoard();
        try { Thread.sleep(50); } catch (InterruptedException ignored) {}
    }

    private void validLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.insertEmail("john@phoenix-trello.com");
        loginPage.insertPassword("12345678");
        loginPage.clickLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add_new_board")));
    }

    private void createList(ListPage listPage, String listName) {
        listPage.clickAddList();
        listPage.insertListName(listName);
        listPage.clickAddListButton();
        try { Thread.sleep(50); } catch (InterruptedException ignored) {}
    }

    private void createCard(CardPage cardPage, String cardName) {
        cardPage.clickAddCard();
        cardPage.insertCardName(cardName);
        cardPage.clickAddCardButton();
        try { Thread.sleep(50); } catch (InterruptedException ignored) {}
    }

    private void registerUser(RegisterPage registerPage, String firstName, String lastName, String email, String password) {
        registerPage.clickRegisterUser();
        registerPage.enterFirstName(firstName);
        registerPage.enterLastname(lastName);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.enterPasswordConfirmation(password);
        registerPage.clickRegisterButton();
    }
    // --- REGISTER PAGE TESTS ---//

    @Test
    public void registerWithValidDataSucceeds() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerUser(registerPage, "Zahin", "Rakin", "zahinabdullahrakin@gmail.com", "12345678");
    }
    @Test
    public void registerWithInsufficientDataDoesNothing() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickRegisterUser();
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
        RegisterPage registerPage = new RegisterPage(driver);
        registerUser(registerPage, "Ridoan", "Ryene", "ridoanislamryene@gmail.com", "12345678");

        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        createBoard(boardPage, "test_user_addition");


        UserPage userPage = new UserPage(driver);
        userPage.clickAddNewUser();
        userPage.insertUserEmail("not the email I want");
        userPage.clickCancel();

        userPage.clickAddNewUser();
        userPage.insertUserEmail("ridoanislamryene@gmail.com");
        userPage.clickAddUserButton();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
    }
    // --- LIST PAGE TESTS ---
    @Test
    public void createList() {
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        createBoard(boardPage, "test_list_creation");
        // boardPage.selectBoard("test_list_creation");

        ListPage listPage = new ListPage(driver);
        listPage.clickAddList();
        listPage.insertListName("not_the_list_name_i_want");
        listPage.clickCancelButton();

        createList(listPage, "list_created_successfully");
    }

    @Test
    public void changeListName(){
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        createBoard(boardPage, "test_changing_list_name");
        // boardPage.selectBoard("test_changing_list_name");
        ListPage listPage = new ListPage(driver);
        createList(listPage, "name_before_change");
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
        createBoard(boardPage, "test_card_creation");
        // boardPage.selectBoard("test_card_creation");

        ListPage listPage = new ListPage(driver);
        createList(listPage, "dummy_list");

        CardPage cardPage = new CardPage(driver);
        cardPage.clickAddCard();
        cardPage.insertCardName("not_the_card_name_i_want");
        cardPage.clickCancelCardButton();

        createCard(cardPage, "card_created_successfully");
    }

    @Test
    public void cardModalOpenClose() {
        validLogin();

        BoardPage boardPage = new BoardPage(driver);
        createBoard(boardPage, "modal_test");
        // boardPage.selectBoard("modal_test");

        ListPage listPage = new ListPage(driver);
        createList(listPage, "dummy_list");

        CardPage cardPage = new CardPage(driver);
        createCard(cardPage, "card_for_modal_test");

        cardPage.CardModalOpen();
        cardPage.CardModalCross();
    }

    @Test
    public void cardAddComment(){
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        createBoard(boardPage, "commenting_test");
        // boardPage.selectBoard("commenting_test");

        ListPage listPage = new ListPage(driver);
        createList(listPage, "dummy_list");

        CardPage cardPage = new CardPage(driver);
        createCard(cardPage, "card_for_commenting_test");

        cardPage.CardModalOpen();
        cardPage.insertComment("testing commenting in the card");
        cardPage.saveComment();
        cardPage.CardModalCross();
    }

    @Test
    public void clickMembersButton(){
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        createBoard(boardPage, "members_button_test_board");
        // boardPage.selectBoard("members_button_test_board");

        ListPage listPage = new ListPage(driver);
        createList(listPage, "dummy_list");

        CardPage cardPage = new CardPage(driver);
        createCard(cardPage, "card_for_members_button_test_board");

        cardPage.CardModalOpen();
        cardPage.clickMembersButton();
        cardPage.clickMembersCross();
        cardPage.CardModalCross();
    }

    @Test
    public void selectTagsColor(){
        validLogin();
        BoardPage boardPage = new BoardPage(driver);
        createBoard(boardPage, "select_tags_color_test_board");
        // boardPage.selectBoard("select_tags_color_test_board");

        ListPage listPage = new ListPage(driver);
        createList(listPage, "dummy_list");

        CardPage cardPage = new CardPage(driver);
        createCard(cardPage, "card_for_tags_color_test_board");

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
        createBoard(boardPage, "editing_card");
        // boardPage.selectBoard("editing_card");

        ListPage listPage = new ListPage(driver);
        createList(listPage, "dummy_list");

        CardPage cardPage = new CardPage(driver);
        createCard(cardPage, "card_for_editing_card");
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
        createBoard(boardPage, "test_deleting_card");
        // boardPage.selectBoard("test_deleting_card");

        ListPage listPage = new ListPage(driver);
        createList(listPage, "dummy_list");

        CardPage cardPage = new CardPage(driver);
        createCard(cardPage, "card_will_be_deleted");

        cardPage.CardModalOpen();
        cardPage.clickDeleteCardButton();
    }
}