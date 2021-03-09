package com.acceptance.tests.web.stepdefinitions;

import com.acceptance.tests.utils.providers.TestDataProvider;
import com.acceptance.tests.utils.providers.WebDriverProvider;
import com.acceptance.tests.web.testDataModel.BasketItem;
import com.acceptance.tests.web.pages.CheckOutProcessPage;
import com.acceptance.tests.web.pages.LoginPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckOutProcessPageSteps extends WebDriverBaseSteps {

    private CheckOutProcessPage checkOutProcessPage = new CheckOutProcessPage(webDriverProvider.getDriver());
    private LoginPage loginPage = new LoginPage(webDriverProvider.getDriver());
    private TestDataProvider testDataProvider;


    public CheckOutProcessPageSteps(TestDataProvider testDataProvider, WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
        this.testDataProvider = testDataProvider;
    }

    @Then("I make payment using {string} as payment option")
    public void i_make_payment_using_as_payment_option(String string) {
        checkOutProcessPage.proceedsToCheckoutWithDefaultAddressSelected();
    }

    @When("I validate my basket items are correct")
    public void i_validate_my_basket_items_are_correct(DataTable dataTable) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> values = dataTable.asMaps(String.class, String.class);
        List<BasketItem> expectedBasketItems = values.stream().map(row -> objectMapper.convertValue(row, BasketItem.class)).collect(Collectors.toList());

        double expectedProductTotal = expectedBasketItems.stream().mapToDouble(i -> Double.parseDouble(i.getTotal())).sum();
        //added this to get to return 2digits after decimals
        expectedProductTotal = (double) Math.round(expectedProductTotal * 100) / 100;

        checkOutProcessPage.validateMyBasketDetails(expectedBasketItems, String.valueOf(expectedProductTotal));
        checkOutProcessPage.navigateToAddressVerificationPage();
        TestDataProvider.isUserOnLoginPage=false;
    }

    @When("login using {string} and password {string}")
    public void login_using_password(String userName, String password) {
        testDataProvider.getUser().getPassword();
        testDataProvider.getUser().getUserName();
        loginPage.loginAndNavigateToCheckOutProcessPage(userName, password);
    }
}