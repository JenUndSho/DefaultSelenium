package dynamics;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {
     private ChromeDriver driver;

     public ShoppingCartPage(ChromeDriver driver){
         PageFactory.initElements(driver, this);
         this.driver = driver;
     }


    @FindBy(xpath = "//span[@class='error_message']")
    private WebElement errorMessage;

    @FindBy(xpath = "//table[@class='form_all_underline']/tbody/tr[1]/td/table/tbody/tr/td/a/img")
    private WebElement goMainPage;

    @FindBy(xpath = "//tbody/tr[2]/td[4]")
    private WebElement price;

    @FindBy(xpath = "//tbody/tr[2]/td[6]")
    private WebElement discount;

    @FindBy(xpath = "//tbody/tr[2]/td[7]")
    private WebElement totalPrice;

    @Step("Get error message")
    public String getErrorMessage(){
        return this.errorMessage.getAttribute("innerHTML");
    }

    @Step("Go to main page")
    public ShoppingCartPage goToMainPage(){
        this.goMainPage.click();
        return this;
    }

    @Step("Get price of one book")
    public double getPriceOfOneBook(){
        return Double.parseDouble(this.price.getAttribute("innerHTML"));
    }

    @Step("Get discount in percentage")
    public double getDiscount(){
        return Double.parseDouble(this.discount.getAttribute("innerHTML"));
    }

    @Step("Get total price of order")
    public double getTotalPrice(){
        return Double.parseDouble(this.totalPrice.getAttribute("innerHTML"));
    }

}
