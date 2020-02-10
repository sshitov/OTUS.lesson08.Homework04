package MIFTests;

import Helpers.DriverManagers.ChromeWebDriver;
import Helpers.MIFPageElements.MainPage;
import Helpers.TestSteps.Steps;
import org.junit.*;

import java.io.IOException;
import java.util.ArrayList;


public class BookInfoCrawler {
    ChromeWebDriver chromeWebDriver = new ChromeWebDriver();
    Steps step = new Steps();
    MainPage mainPage = new MainPage();

    @BeforeClass
    public static void setupWebDriver() {
        ChromeWebDriver.driverLoad();
    }

    @Before
    public void createWebDriver() {
        chromeWebDriver.create();
    }

    @After
    public void closeWebDriver() {
        ChromeWebDriver.quit();
    }

    @Test
    public void getBookCollection() throws IOException, InterruptedException {

        step.createCsvFile();

        step.openPage(mainPage.getBaseUrl() + mainPage.getAudioBook());

        ArrayList<String> bookLinkList = new ArrayList<>(step.getUrlList());

        for (String s : bookLinkList) {

           step.writeValuesToCsvFile(step.collectInfoOnThePage(s));
        }

    }

    @Test
    public void tst() throws IOException {
        step.openPage(mainPage.getBaseUrl() + mainPage.getAudioBook());

        step.createCsvFile();

    }
}
