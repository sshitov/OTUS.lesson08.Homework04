package Helpers.MIFPageElements;

import Helpers.DriverManagers.ChromeWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookPage {

    @FindBy(css = "h1 span")
    protected WebElement bookName;

    @FindBy(css = ".position-top .author.active")
    protected WebElement bookAuthor;

    @FindBy(css = ".l-book-buy-panel-top [data-type='audiobook']")
    protected WebElement bookPrice;

    public String getBookName() {
        return bookName.getAttribute("innerText");
    }

    public String getBookAuthor(){
        return bookAuthor.getAttribute("innerText");
    }

    public String getBookPrice(){
        return bookPrice.getAttribute("data-price");
    }

    public String getPageUrl() {
        return ChromeWebDriver.getDriver().getCurrentUrl();
    }
}
