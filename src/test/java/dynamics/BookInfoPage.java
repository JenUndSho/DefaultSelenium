package dynamics;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookInfoPage {
    private ChromeDriver driver;

    public BookInfoPage(ChromeDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[@class='error_message']")
    private WebElement errorMessage;

    @FindBy(xpath = "//table[@class='form_all_underline']/tbody/tr[1]/td/table/tbody/tr/td[1]/a")
    private WebElement goMainPage;

    @FindBy(xpath = "//table[@align='center']/tbody/tr/td[2]/p[text()]")
    private WebElement bookName;

    @FindBy(xpath = "//p/a/img")
    private WebElement addToCartButton;

    @FindBy(xpath = "//td[@align='right']/a")
    private WebElement shoppingCart;

    @Step("Get error message")
    public String getErrorMessage(){
        return this.errorMessage.getAttribute("innerHTML");
    }

    @Step("Go to main Page")
    public BookInfoPage goToMainPage(){
        this.goMainPage.click();
        return this;
    }

    @Step("Get book name")
    public String getBookName(){
        String book = this.bookName.getAttribute("innerHTML");
        book = book.substring(0, book.indexOf(" <"));
        return book;
    }

    @Step("Add the book to the Shopping Cart")
    public BookInfoPage addToCart(){
        this.addToCartButton.click();
        return this;
    }

    @Step("Click on Shopping Cart button")
    public BookInfoPage clickOnShoppingCart(){
        this.shoppingCart.click();
        return this;
    }
}
