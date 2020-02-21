package com.mavenit.selenium;

import com.mavenit.selenium.driver.DriverFactory;
import cucumber.api.java.After;
import cucumber.api.java.Before;
//import org.junit.After;
//import org.junit.Before;


public class Hooks
{
    DriverFactory factory=new DriverFactory();


    @Before
    public void setUp()
    {
        factory.openBrowser();
        factory.navigateTo("https://www.argos.co.uk");
        factory.manageSize();
    }

    @After
    public void tearDown()
    {
        factory.closeBrowser();
    }
}

