package Helpers.MIFPageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage {

    protected String baseUrl = "https://www.mann-ivanov-ferber.ru/";
    protected String audioBook = "books/allbooks/?booktype=audiobook";

    protected By booksOldStateInList = By.cssSelector(".c-continuous-list .lego-book");

    @FindBy(css = ".c-continuous-list .lego-book")
    protected List<WebElement> bookList;

    @FindBy(css = ".page-loader div")
    protected WebElement loader;

    public WebElement getLoader() {
        return loader;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getAudioBook() {
        return audioBook;
    }

    public List<WebElement> getBookList(){
        return bookList;
    }

    public String getBookURL(WebElement webElement) {
        return webElement.findElement(By.cssSelector("[href]")).getAttribute("href");
    }

    public By getBooksOldStateInList() {
        return booksOldStateInList;
    }

}
