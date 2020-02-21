package com.mavenit.selenium.pages;

import com.mavenit.selenium.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Homepage extends DriverFactory
{
    public void doSearch(String searchTerm)
    {
        driver.findElement(By.id("searchTerm")).sendKeys(searchTerm);
        driver.findElement(By.id("searchTerm")).sendKeys(Keys.ENTER);
    }
    public String getCurrentUrl()
    {
        return driver.getCurrentUrl();
    }
    public void getTheProduct()
    {
        String searchTerm="Nike";
        doSearch(searchTerm);
    }
    }
