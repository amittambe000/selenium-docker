package com.newtours.tests;

import com.newtours.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.BaseTest;

public class BookFlightTest extends BaseTest {


    @Test
    public void registrationPageTest()
    {
        RegistrationPage registrationPage=new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("selenium","docker");
        registrationPage.enterUserCredentials("selenium","docker");
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "registrationPageTest")
    public void registrationConfirmationPageTest()
    {
        RegistrationConfirmationPage registrationConfirmationPage=new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.gotoFlightDetailsPage();
    }

    @Test(dependsOnMethods = "registrationConfirmationPageTest")
    @Parameters({"noOfPassengers"})
    public void flightDetailsPage(String noOfPassengers)
    {
        FlightDetailsPage flightDetailsPage=new FlightDetailsPage(driver);
        flightDetailsPage.selectPassengers(noOfPassengers);
        flightDetailsPage.gotoFindFlightsPage();
    }

    @Test(dependsOnMethods = "flightDetailsPage")
    public void findFlightPage()
    {
        FindFlightPage findFlightPage=new FindFlightPage(driver);
        findFlightPage.submitFindFlightPage();
        findFlightPage.gotoFlightConfirmationPage();
    }

    @Test(dependsOnMethods = "findFlightPage")
    @Parameters({"expectedPrice"})
    public void flightConfirmationPage(String expectedPrice)
    {
        FlightConfimationPage flightConfimationPage=new FlightConfimationPage(driver);
        String actualprice=flightConfimationPage.printConfimration();
        Assert.assertEquals(actualprice,expectedPrice);
    }


}
