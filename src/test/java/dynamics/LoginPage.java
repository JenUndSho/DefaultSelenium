package dynamics;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private ChromeDriver driver;

    public LoginPage(ChromeDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    @FindBy(xpath = "//a[@class='footer'][2]")
    private WebElement testPortalLink;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[@class='user']")
    private WebElement popUpUserName;

    @FindBy(xpath = "//td[@align='right']/a")
    private WebElement shoppingCart;

    @FindBy(xpath = "//input[@value='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//table[@class='form_all_underline']/tbody/tr[1]/td/table/tbody/tr/td[1]/a")
    private WebElement goMainPage;

    @FindBy(xpath = "//input[@name='keyword']")
    private WebElement searchField;

    @Step("Go to Test Portal Page")
    public LoginPage goToTestPortalPage(){
        this.testPortalLink.click();
        return this;
    }

    @Step("Fill in with email {0}")
    public LoginPage setEmail(String email){
        this.emailField.sendKeys(email);
        return this;
    }

    @Step("Fill in with password {0}")
    public LoginPage setPassword(String password){
        this.passwordField.sendKeys(password);
        return this;
    }

    @Step("Enter the account")
    public LoginPage enterAccount(){
        this.loginButton.click();
        return this;
    }

    @Step("Get pop-up user name")
    public String getPopUpUserName(){
        return this.popUpUserName.getAttribute("innerHTML");
    }

    @Step("Click on Shopping Cart Button")
    public LoginPage clickOnShoppingCart(){
        this.shoppingCart.click();
        return this;
    }

    @Step("Search for the results")
    public LoginPage clickOnSearchButton(){
        this.searchButton.click();
        return this;
    }

    @Step("Fill in the searching field with keyword {0}")
    public LoginPage writeBookName(String keyword){
        this.searchField.sendKeys(keyword);
        return this;
    }

}
