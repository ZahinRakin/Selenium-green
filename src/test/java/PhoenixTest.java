import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.*;

import pages.LoginPage;
import pages.DashboardPage;

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

    // --- LOGIN PAGE TESTS ---

    @Test
    public void loginWithValidCredentialsSucceeds() {
        goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.insertEmail("john@phoenix-trello.com");
        loginPage.insertPassword("12345678");
        loginPage.clickLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add_new_board")));
        Assert.assertEquals("http://localhost:4000/", driver.getCurrentUrl());
    }

    @Test
    public void loginWithEmptyFieldsShowsError() {
        goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        String urlBefore = driver.getCurrentUrl();
        loginPage.insertEmail("");
        loginPage.insertPassword("");
        loginPage.clickLogin();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        String urlAfter = driver.getCurrentUrl();
        Assert.assertEquals(urlBefore, urlAfter);
    }

    @Test
    public void loginWithOnlyEmailShowsError() {
        goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        String urlBefore = driver.getCurrentUrl();
        loginPage.insertEmail("john@phoenix-trello.com");
        loginPage.insertPassword("");
        loginPage.clickLogin();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        String urlAfter = driver.getCurrentUrl();
        Assert.assertEquals(urlBefore, urlAfter);
    }

    @Test
    public void loginWithOnlyPasswordShowsError() {
        goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        String urlBefore = driver.getCurrentUrl();
        loginPage.insertEmail("");
        loginPage.insertPassword("12345678");
        loginPage.clickLogin();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        String urlAfter = driver.getCurrentUrl();
        Assert.assertEquals(urlBefore, urlAfter);
    }

    @Test
    public void loginWithInvalidCredentialsShowsError() {
        goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        String urlBefore = driver.getCurrentUrl();
        loginPage.insertEmail("asdfaa");
        loginPage.insertPassword("1234567as8");
        loginPage.clickLogin();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        String urlAfter = driver.getCurrentUrl();
        Assert.assertEquals(urlBefore, urlAfter);
    }

    @Test
    public void loginWithInvalidEmailFormatShowsError() {
        goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clearAutofilledFields();
        loginPage.insertEmail("not-an-email");
        loginPage.insertPassword("12345678");
        loginPage.clickLogin();
        WebElement errorDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.error")));
        Assert.assertEquals("Invalid email format", errorDiv.getText());
    }

    // --- DASHBOARD PAGE TESTS ---

    private void loginAsValidUser() {
        goToLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.insertEmail("john@phoenix-trello.com");
        loginPage.insertPassword("12345678");
        loginPage.clickLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add_new_board")));
    }

    @Test
    public void createBoardWithValidData() {
        loginAsValidUser();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickAddBoard();
        dashboardPage.insertBoardName("Morning Routine");
        dashboardPage.clickCreateBoard();
        dashboardPage.clickBoardCard();
        dashboardPage.insertListName("Brushing Teeth");
        dashboardPage.clickCreateBoard();
        // Add assertion for board/list creation if possible
    }

    @Test
    public void createBoardWithEmptyNameShowsError() {
        loginAsValidUser();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickAddBoard();
        dashboardPage.insertBoardName("");
        dashboardPage.clickCreateBoard();
        Assert.assertTrue(driver.getPageSource().contains("Board name required")); //no error like this. if don't fill the Board name. I just can't create the the board. and "Please fill out this field" is shown above the input field
    }

    @Test
    public void createListWithEmptyNameShowsError() {
        loginAsValidUser();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickAddBoard();
        dashboardPage.insertBoardName("Test Board");
        dashboardPage.clickCreateBoard();
        dashboardPage.clickBoardCard();
        dashboardPage.insertListName("");
        dashboardPage.clickCreateBoard();
        Assert.assertTrue(driver.getPageSource().contains("List name required"));
    }

    @Test
    public void userProfileAndLogoutFlow() {
        loginAsValidUser();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickUserProfileIcon();
        dashboardPage.clickAddIcon();
        dashboardPage.clickAddIcon();
        dashboardPage.clickUserProfileIcon();
        dashboardPage.clickLogout();
        Assert.assertTrue(driver.getCurrentUrl().contains("sign_in"));
    }

    @Test
    public void addBoardAndListFlow() {
        loginAsValidUser();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.addBoard("dinner menu");
        dashboardPage.openFirstBoard();
        dashboardPage.addList("rice");
        dashboardPage.cancelAddList();
    }

    private void goToLoginPage() {
        driver.get("http://localhost:4000/sign_in");
    }
}