package com.mavenit.selenium.pages;

import com.mavenit.selenium.driver.DriverFactory;
import com.mavenit.selenium.utils.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class ResultsPage extends DriverFactory
{
    public List<String> getAllProducts()
    {
        List<String> productNamesList=new ArrayList<>();
        List<WebElement> productWebElements = driver.findElements(By.cssSelector("a[data-test='component-product-card-title']"));

        for (WebElement indProduct : productWebElements) {
            String actual = indProduct.getText();
            productNamesList.add(actual);
        }
        return productNamesList;
    }
    public String getSearchTitle()
    {
        String actualTitle = driver.findElement(By.className("search-title__term")).getText();
        return actualTitle;
    }
    public String selectAnyProduct()
    {
        waitTime(50);
        List<WebElement> productWebElements=isProductAvailable();
        int productSize=productWebElements.size()-1;
        int randomNumber=new Helpers().randomNumberGenerator(productSize);
        WebElement selectedElement = productWebElements.get(randomNumber);
        String selectedProductName = selectedElement.getText();
        selectedElement.click();
        return selectedProductName;


    }
    public List<WebElement> isProductAvailable()
    {
        waitTime(50);
        List<WebElement> productWebElements = driver.findElements(By.cssSelector("a[data-test='component-product-card-title']"));
        if (productWebElements.size() == 0) {
            fail("No Products found with: " + "nike");
        }
        return productWebElements;
    }
}
