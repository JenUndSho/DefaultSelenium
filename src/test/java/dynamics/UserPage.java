package dynamics;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage {
    private ChromeDriver driver;

    public UserPage(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    @FindBy(xpath = "//tr[@class='grey_bg'][1]/td/a")
    private WebElement logoutButton;

    @FindBy(xpath = "//table[@class='form_all_underline']/tbody/tr[1]/td/table/tbody/tr/td[1]/a")
    private WebElement goMainPage;

    @FindBy(xpath = "//table[@class='form_all_underline']/tbody/tr[5]/td/table/tbody/tr/td[1]/table/tbody/tr/td/a[text()]")
    private WebElement bookNameLink;

    @Step("Logout")
    public UserPage pressLogoutButton(){
        this.logoutButton.click();
        return this;
    }

    @Step("Go to main page")
    public UserPage goToMainPage(){
        this.goMainPage.click();
        return this;
    }

    @Step("Click on book name link")
    public UserPage clickOnBookNameLink(){
        this.bookNameLink.click();
        return this;
    }
}
