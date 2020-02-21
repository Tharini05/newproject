package com.mavenit.selenium.pages;

import com.mavenit.selenium.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TrolleyPage extends DriverFactory
{
    Homepage homepage=new Homepage();
    ResultsPage resultsPage=new ResultsPage();
    public void addToTrolley()
    {
        waitTime(60);
        driver.findElement(By.cssSelector("button[data-test='component-att-button']")).click();
    }
    public void goToTrolley()
    {
        waitTime(50);
        driver.findElement(By.cssSelector(".xs-row a[data-test='component-att-button-basket']")).click();
    }
    public String getProductInTrolley()
    {
        waitTime(50);
        return driver.findElement(By.cssSelector(".ProductCard__content__9U9b1.xsHidden.lgFlex .ProductCard__titleLink__1PgaZ")).getText();
    }
    public void goToContinueShopping()
    {
        waitTime(150);
        driver.findElement(By.cssSelector("button[data-test=\"component-att-button-continue\"]")).click();
    }
    public int noOfQuantity(String noOfQuantityPassed)
    {
        String valueGiven=noOfQuantityPassed;
        WebElement NoOfQuantity = driver.findElement(By.cssSelector(".xs-4--none select[data-test=\"select\"]"));
        Select selectedNumber = new Select(NoOfQuantity);
        selectedNumber.selectByValue(valueGiven);
        WebElement valueSelected = selectedNumber.getFirstSelectedOption();
        String value = valueSelected.getText();
        int noOfProducts = Integer.parseInt(value);
        return noOfProducts;
    }
    public double getActualResult()
    {
        String actualValue=driver.findElement(By.cssSelector(".Summary__totalInformation__2hwn3 .Summary__subTotalLabel__2GphY")).getText();
        double totalValue=(actualValue.contains("£"))?Double.parseDouble(actualValue.replace("£","")):0.0;
        return totalValue;
    }
    public double setExpectedResult(int noOfQuantity)
    {
        int noOfProducts=noOfQuantity;
        String productsAmount=driver.findElement(By.cssSelector(".ProductCard__unitPrice__rTWTs span[data-e2e=\"product-unit-price\"]")).getText();
        double unitPrice=(productsAmount.contains("£"))?Double.parseDouble(productsAmount.replace("£","")):0.0;

        double expectedResult=resultCalculation(unitPrice,noOfProducts);

        return expectedResult;
    }
    public double resultCalculation(double unitPrice,int noOfProducts)
    {
        if (noOfProducts!=0)
            return unitPrice+resultCalculation(unitPrice,noOfProducts-1);
        else
            return 0;
    }
    public double expectedResultContinue()
    {
        double totalIndProduct,grossTotal=0.0;
        double unitPrice;
        waitTime(50);
        List<WebElement> productsInBasket=driver.findElements(By.cssSelector(".xs-12--none.md-6--none.lg-7--none.undefined li[data-e2e=\"basket-productcard\"]"));
        for (WebElement indiProduct : productsInBasket)
        {
            String nameOfProduct=indiProduct.findElement(By.cssSelector(".ProductCard__content__9U9b1.xsHidden.lgFlex .ProductCard__titleLink__1PgaZ")).getText();
            waitTime(50);
            //Selection of number of products
            WebElement noOfQuantity=indiProduct.findElement(By.cssSelector(".ProductCard__quantityContainer__2gY5E .ProductCard__quantitySelect__2y1R3"));
            Select selectedNo=new Select(noOfQuantity);
            WebElement valueSelected=selectedNo.getFirstSelectedOption();
            String presentValue=valueSelected.getText();
            int noOfProduct=Integer.parseInt(presentValue);

            //Selection of unit price
            if(noOfProduct>=2)
            {
                String productsUnitAmount=indiProduct.findElement(By.cssSelector(".ProductCard__unitPrice__rTWTs span[data-e2e=\"product-unit-price\"]")).getText();
                unitPrice=(productsUnitAmount.contains("£"))?Double.parseDouble(productsUnitAmount.replace("£"," ")):0.0;
                totalIndProduct=resultCalculation(unitPrice,noOfProduct);
            }
            else
            {
                String productValue=indiProduct.findElement(By.cssSelector(".ProductCard__pricesContainer__dA7SA .ProductCard__price__1vkg0")).getText();
                double productPrice=(productValue.contains("£"))?Double.parseDouble(productValue.replace("£"," ")):0.0;
                totalIndProduct=resultCalculation(productPrice,noOfProduct);
            }
            grossTotal=grossTotal+totalIndProduct;
        }
        return grossTotal;
    }
    public void selectingProduct(String numOfProducts)
    {
        int noOfQuantity=noOfQuantity(numOfProducts);
        waitTime(50);
        addToTrolley();
    }

}
