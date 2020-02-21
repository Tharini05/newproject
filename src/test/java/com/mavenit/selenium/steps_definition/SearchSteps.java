package com.mavenit.selenium.steps_definition;

import com.mavenit.selenium.driver.DriverFactory;
import com.mavenit.selenium.pages.Homepage;
import com.mavenit.selenium.pages.ResultsPage;
import com.mavenit.selenium.pages.TrolleyPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchSteps
{
    Homepage homepage=new Homepage();
    ResultsPage resultsPage=new ResultsPage();
    TrolleyPage trolleyPage=new TrolleyPage();
    DriverFactory factory=new DriverFactory();

    String selectedProductName;
    int noOfQuantity;

    @Given("^Iam in home page$")
    public void iam_in_home_page()
    {
        String actual=homepage.getCurrentUrl();
        assertThat(actual,endsWith("co.uk/"));
    }

    @When("^I search for product nike$")
    public void i_search_for_product_nike()
    {
        homepage.doSearch("nike");

    }

    @Then("^I should see the nike product$")
    public void i_should_see_the_nike_product()
    {
        factory.waitTime(50);
        String actualTitle=resultsPage.getSearchTitle();
        List<String> allProductsNames=resultsPage.getAllProducts();
        assertThat(actualTitle,is(equalToIgnoringWhiteSpace("nike")));
        for (String item:allProductsNames)
        {
            assertThat(item,containsString("Nike"));
        }
    }
    @When("^It should randomly select one product$")
    public void it_should_randomly_select_one_product()
    {
        factory.waitTime(50);
        selectedProductName=resultsPage.selectAnyProduct();
    }

    @Then("^Add it to trolley$")
    public void add_it_to_trolley()
    {
        factory.waitTime(90);
        trolleyPage.addToTrolley();
        factory.waitTime(50);
        trolleyPage.goToTrolley();
    }

    @Then("^Check the selected page is in trolley page$")
    public void check_the_selected_page_is_in_trolley_page()
    {
        factory.waitTime(70);
        String actual=trolleyPage.getProductInTrolley();
        assertThat(actual, is(equalToIgnoringCase(selectedProductName)));
    }

    @When("^Select Multiple product$")
    public void select_Multiple_product()
    {
        factory.waitTime(50);
        noOfQuantity=trolleyPage.noOfQuantity("2");
    }

    @Then("^Get the actual and expected result$")
    public void get_the_actual_and_expected_result()
    {
        double actualResult=trolleyPage.getActualResult();
        double expectedResult=trolleyPage.setExpectedResult(noOfQuantity);
        assertThat(actualResult,is(equalTo(expectedResult)));
    }
    @When("^I search for first product and randomly added to trolley$")
    public void i_search_for_first_product_and_randomly_added_to_trolley()
    {
        homepage.getTheProduct();
        resultsPage.selectAnyProduct();
        trolleyPage.selectingProduct("1");
    }

    @When("^Continue shopping for next product$")
    public void continue_shopping_for_next_product()
    {
        factory.waitTime(40);
         trolleyPage.goToContinueShopping();
    }

    @When("^I search for second product and randomly added to trolley$")
    public void i_search_for_second_product_and_randomly_added_to_trolley()
    {
        factory.waitTime(50);
        homepage.getTheProduct();
        resultsPage.selectAnyProduct();
        trolleyPage.selectingProduct("2");
    }

    @Then("^Go to trolley$")
    public void go_to_trolley()
    {
        trolleyPage.goToTrolley();
    }

    @Then("^Get the actual and expected result for continue shopping$")
    public void get_the_actual_and_expected_result_for_continue_shopping()
    {
        double actualResult=trolleyPage.getActualResult();
        double expectedResult=trolleyPage.expectedResultContinue();
        assertThat(actualResult,is(equalTo(expectedResult)));
    }
}
