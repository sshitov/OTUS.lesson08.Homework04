package Helpers.MIFPageElements;

import Helpers.DriverManagers.ChromeWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookPage {

    ChromeWebDriver chromeWebDriver = new ChromeWebDriver();

    @FindBy(css = "h1 span")
    WebElement bookName;

    @FindBy(css = ".position-top .author.active")
    WebElement bookAuthor;

    @FindBy(css = ".l-book-buy-panel-top [data-type='audiobook']")
    WebElement bookPrice;

    @FindBy(css = "#about .description")
    WebElement bookDescription;

    public String getBookName() {
        return bookName.getAttribute("innerText");
    }

    public String getBookAuthor(){
        return bookAuthor.getAttribute("innerText");
    }

    public String getBookPrice(){
        return bookPrice.getAttribute("data-price");
    }

    public String getDescription(){
        return bookDescription.getAttribute("innerText");
    }
}
