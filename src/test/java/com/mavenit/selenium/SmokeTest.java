package com.mavenit.selenium;

import com.mavenit.selenium.driver.DriverFactory;
import com.mavenit.selenium.pages.Homepage;
import com.mavenit.selenium.pages.ResultsPage;
import com.mavenit.selenium.pages.TrolleyPage;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class SmokeTest
{
    Homepage homepage=new Homepage();
    ResultsPage resultsPage=new ResultsPage();
    TrolleyPage trolleyPage=new TrolleyPage();
    DriverFactory factory=new DriverFactory();
    String searchTerm=null;

    @Test
    public void searchTest()
    {
        searchTerm="puma";

        homepage.doSearch(searchTerm);
        //To Get Current URL
        String url = homepage.getCurrentUrl();
        assertThat(url, endsWith("puma"));
        factory.waitTime(50);
        //To check the products are same
        List<String> actualProductList=resultsPage.getAllProducts();
        for (String product:actualProductList)
        {
            assertThat(product,containsString(searchTerm));
        }
        //To check results page title
        String actualTitle = resultsPage.getSearchTitle();
        assertThat(actualTitle, is(equalToIgnoringCase(searchTerm)));
    }
    @Test
    public void basketTest()
    {
        searchTerm="nike";
        homepage.doSearch(searchTerm);
        factory.waitTime(50 );
        String selectedProductName=resultsPage.selectAnyProduct();
        factory.waitTime(50);
        trolleyPage.addToTrolley();
        trolleyPage.goToTrolley();
        String actual=trolleyPage.getProductInTrolley();
        assertThat(actual, is(equalToIgnoringCase(selectedProductName)));
    }
    @Test
    public void selectMultiple()
    {
        searchTerm="Nike";
        homepage.doSearch(searchTerm);
        String selectedProductName=resultsPage.selectAnyProduct();
        int noOfQuantity=trolleyPage.noOfQuantity("2");
        trolleyPage.addToTrolley();
        trolleyPage.goToTrolley();
        double actualResult=trolleyPage.getActualResult();
        double expectedResult=trolleyPage.setExpectedResult(noOfQuantity);
        assertThat(actualResult,is(equalTo(expectedResult)));
    }
    @Test
    public void continueShopping()
    {
        homepage.getTheProduct();
        resultsPage.selectAnyProduct();
        trolleyPage.selectingProduct("1");
        trolleyPage.goToContinueShopping();
        homepage.getTheProduct();
        resultsPage.selectAnyProduct();
        trolleyPage.selectingProduct("2");
        trolleyPage.goToTrolley();
        double actualResult=trolleyPage.getActualResult();
        double expectedResult=trolleyPage.expectedResultContinue();
        assertThat(actualResult,is(equalTo(expectedResult)));
    }


} 