package com.acceptance.tests.web.stepdefinitions;

import com.acceptance.tests.utils.providers.TestDataProvider;
import com.acceptance.tests.utils.providers.WebDriverProvider;
import com.acceptance.tests.web.pages.HomePage;
import com.acceptance.tests.web.pages.LoginPage;
import com.acceptance.tests.web.pages.MyAccountPage;
import com.acceptance.tests.web.pages.OrderHistoryPage;
import io.cucumber.java.en.Given;

public class MyAccountPageSteps extends WebDriverBaseSteps {

    private TestDataProvider testDataProvider;
    private HomePage homePage = new HomePage(webDriverProvider.getDriver());
    private MyAccountPage myAccountPage = new MyAccountPage(webDriverProvider.getDriver());
    private OrderHistoryPage orderHistoryPage = new OrderHistoryPage(webDriverProvider.getDriver());


    public MyAccountPageSteps(TestDataProvider testDataProvider, WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
        this.testDataProvider = testDataProvider;
    }

    @Given("view existing orders")
    public void view_previous_order() {
        myAccountPage.navigateToOrderHistoryPage();
    }

}
