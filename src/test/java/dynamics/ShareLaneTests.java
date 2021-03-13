package dynamics;

import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class ShareLaneTests {

    public ChromeDriver driver;
    public static LoginPage loginPage;
    public static TestPortalPage testPortalPage;
    public static ShoppingCartPage shoppingCartPage;
    public static BookInfoPage bookInfoPage;
    public static UserPage userPage;

    public  String expectedName;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/Users/eshovkovyi/IdeaProjects/DefaultSelenium/src/main/resources/chromedriver");

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        testPortalPage = new TestPortalPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        bookInfoPage = new BookInfoPage(driver);
        userPage = new UserPage(driver);


    }

    public void implicitWait(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public void login(){

        loginPage.goToTestPortalPage();
        testPortalPage.goToCreateAccount()
                .pressCreateAccountButton();

        expectedName = testPortalPage.getUserName();

        String email = testPortalPage.getEmail();
        String password = testPortalPage.getPassword();
        testPortalPage.goToMainPage();

        implicitWait();

        loginPage.setEmail(email)
                .setPassword(password)
                .enterAccount();

        implicitWait();

    }

    @BeforeMethod
    public void openSite(){
        driver.get("https://main.sharelane.com/cgi-bin/main.py");
    }


    @Epic("Test ShareLane site https://main.sharelane.com/cgi-bin/main.py")
    @Feature("Login tests")
    @Description("Verify on entering valid login and password, the customer can login and name on TestPortal should be " +
            "the same as pop-up name on UserPage")
    @Severity(SeverityLevel.NORMAL)
    @Story("Test the Login Functionality on site with valid credentials")

    @Test
    public void loginUser(){

        login();
        String actualName = loginPage.getPopUpUserName();
        assertThat(actualName).isEqualTo(expectedName);
        userPage.pressLogoutButton()
                .goToMainPage();

    }

    @AfterClass
    public void quit(){
        driver.quit();
    }


    @Epic("Test ShareLane site https://main.sharelane.com/cgi-bin/main.py")
    @Feature("Shopping cart tests")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify on clicking on Shopping Cart button without being logged in, user has to be notified about logging")
    @Story("Test the Shopping Cart on site without being logged in")

    @Test
    public void testShoppingCartWithoutBeingLoggedIn() throws Exception{

        loginPage.clickOnShoppingCart();
        String actualError = shoppingCartPage.getErrorMessage();
        shoppingCartPage.goToMainPage();

        String expectedError = "must log in";
        assertThat(actualError).contains(expectedError);

    }

    @Epic("Test ShareLane site https://main.sharelane.com/cgi-bin/main.py")
    @Feature("Search tests")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Verify on clicking the Search button, user should get an error due to null data entering")
    @Story("Test the Search button with no input data")

    @Test
    public void testSearchButtonWithNoKeyword() throws Exception{

        loginPage.clickOnSearchButton();
        String actualError = bookInfoPage.getErrorMessage();
        bookInfoPage.goToMainPage();

        String expectedError = "keyword";
        assertThat(actualError).contains(expectedError);

    }

    @Epic("Test ShareLane site https://main.sharelane.com/cgi-bin/main.py")
    @Feature("Search tests")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify on clicking the Search button after inputting the keyword, user should get a preview info about selected book")
    @Story("Test the Search results with valid input data")

    @Test
    public void testSearchResult(){

        String keyword = "The Alchemist";

        loginPage.writeBookName(keyword)
                .clickOnSearchButton();

        String bookName = bookInfoPage.getBookName();
        bookInfoPage.goToMainPage();

        assertThat(bookName).isEqualTo(keyword);

    }

    @Epic("Test ShareLane site https://main.sharelane.com/cgi-bin/main.py")
    @Feature("Book info tests")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the price of a book added to the Shopping Cart, total price should be less than price without discount")
    @Story("Test the total price of the selected book in Shopping Cart")

    @Test(expectedExceptions = AssertionError.class)
    public void testTotalPriceOfBook() throws Exception{
        SoftAssertions softAssert = new SoftAssertions();

        login();

        userPage.clickOnBookNameLink();
        bookInfoPage.addToCart()
                .clickOnShoppingCart();

        double price = shoppingCartPage.getPriceOfOneBook();
        double discount = shoppingCartPage.getDiscount();

        double expectedTotalPrice = price - 0.1*price*discount;
        double actualTotalPrice = shoppingCartPage.getTotalPrice();

        shoppingCartPage.goToMainPage();
        userPage.pressLogoutButton()
                .goToMainPage();

        softAssert.assertThat(actualTotalPrice).isEqualTo(expectedTotalPrice);
        softAssert.assertAll();

    }

}
