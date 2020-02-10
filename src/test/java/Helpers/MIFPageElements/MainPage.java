package Helpers.MIFPageElements;

import Helpers.DriverManagers.ChromeWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage {

    ChromeWebDriver chromeWebDriver = new ChromeWebDriver();

    protected String baseUrl = "https://www.mann-ivanov-ferber.ru/";
    protected String audioBook = "books/allbooks/?booktype=audiobook";

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getAudioBook() {
        return audioBook;
    }

    @FindBy(css = ".c-continuous-list .lego-book")
    List<WebElement> bookList;

    @FindBy(css = "[href]")
    WebElement bookURL;

    public List<WebElement> getBookList(){
        return bookList;
    }

    public String getBookURL() {
        return bookURL.getAttribute("href");
    }

}
