package com.acceptance.tests.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertEquals;

public class MyAccountPage extends  BasePage {

   // @FindBy(xpath="//span[text()=\"Order history and details\"]")
            @FindBy(xpath="//div[@id='center_column']/div/div/ul/li/a/span")
    WebElement viewOrderHistory_Link;


    public MyAccountPage(WebDriver driver ) {
        super(driver);
      //  checkMyAccountPageIsDisplayed();
    }
    public  void checkMyAccountPageIsDisplayed(){
        assertEquals("","My account - My Store",driver.getTitle());
    }



    public OrderHistoryPage navigateToOrderHistoryPage() {

        return  selectViewOrderHistory();
    }

    private OrderHistoryPage selectViewOrderHistory() {
        clickOnElement(viewOrderHistory_Link, "view order history link");
        return new OrderHistoryPage(driver);
    }
}
