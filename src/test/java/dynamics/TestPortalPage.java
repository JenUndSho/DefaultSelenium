package dynamics;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestPortalPage {
    private ChromeDriver driver;

    public TestPortalPage(ChromeDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//table//tbody/tr[2]/td[@align='left']//p//a")
    private WebElement accountCreatorlLink;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement createAccountButton;

    @FindBy(xpath = "//table//tbody/tr[2]/td[1]")
    private WebElement name;

    @FindBy(xpath = "//table//tbody/tr[2]/td[3]/span/b")
    private WebElement email;

    @FindBy(xpath = "//table//tbody/tr[2]/td[4]")
    private WebElement password;

    @FindBy(xpath = "//body//a[3]")
    private WebElement goMainPage;

    @Step("Click <<Account Creator>> button")
    public TestPortalPage goToCreateAccount(){
        this.accountCreatorlLink.click();
        return this;
    }

    @Step("Click <<Create new user account>> button")
    public TestPortalPage pressCreateAccountButton(){
        this.createAccountButton.click();
        return this;
    }

    @Step("Get User name")
    public String getUserName(){
        return "Hello " + this.name.getAttribute("innerHTML");
    }

    @Step("Get email")
    public String getEmail(){
        return this.email.getAttribute("innerHTML");
    }

    @Step("Get password")
    public String getPassword(){
        return this.password.getAttribute("innerHTML");
    }

    @Step("Go to main page")
    public TestPortalPage goToMainPage(){
        this.goMainPage.click();
        return this;
    }

}
