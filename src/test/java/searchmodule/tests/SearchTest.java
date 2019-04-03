package searchmodule.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import searchmodule.pages.SearchPage;
import tests.BaseTest;

public class SearchTest extends BaseTest {


    @Test
    @Parameters({"keyword"})
    public void Search(String keyword){
        SearchPage searchPage=new SearchPage(driver);
        searchPage.goTo();
        searchPage.doSearch(keyword);
        searchPage.goToVideos();
        int size=searchPage.getResult();
        Assert.assertTrue(size>0);
    }


}
