package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setupDriver(ITestContext iTestContext)
    {
        String host="localhost";
        DesiredCapabilities dc;

        if(System.getProperty("BROWSER") !=null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox"))
        {
            dc=DesiredCapabilities.firefox();
        }
        else {
            dc=DesiredCapabilities.chrome();
        }
        if(System.getProperty("HUB_HOST")!=null)
        {
            host=System.getProperty("HUB_HOST");
        }

        String completeURL="http://"+host+":4444/wd/hub";
        //name for zaleium dashboard
        dc.setCapability("name",iTestContext.getCurrentXmlTest().getName());

        try {

            this.driver=new RemoteWebDriver(new URL(completeURL),dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @AfterTest
    public void quitDriver()
    {
        driver.quit();
    }
}
