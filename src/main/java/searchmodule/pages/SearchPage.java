package searchmodule.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "search_form_input_homepage")
    private WebElement searchText;

    @FindBy(id = "search_button_homepage")
    private WebElement searchBtn;

    @FindBy(linkText = "Videos")
    private WebElement videosLink;

    @FindBy(xpath ="//div[@class='tile  tile--c--w  tile--vid  has-detail  opt--t-xxs']")
    private List<WebElement> allVideos;


    public SearchPage(WebDriver driver)
    {
        this.driver=driver;
        this.wait=new WebDriverWait(driver,30);
        PageFactory.initElements(driver,this);

    }

    public void goTo() {
        driver.get("https://duckduckgo.com/");
    }

    public void doSearch(String keyword){

        this.wait.until(ExpectedConditions.visibilityOf(searchText));
        searchText.sendKeys(keyword);
        searchBtn.click();

    }

    public void goToVideos(){
        wait.until(ExpectedConditions.visibilityOf(videosLink));
        videosLink.click();
    }

    public int getResult(){

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class='tile  tile--c--w  tile--vid  has-detail  opt--t-xxs']"),0));
        System.out.println((allVideos.size()));
        return allVideos.size();

    }






}
