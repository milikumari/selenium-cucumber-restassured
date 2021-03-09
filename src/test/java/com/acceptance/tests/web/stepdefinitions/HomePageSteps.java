package com.acceptance.tests.web.stepdefinitions;

import com.acceptance.tests.utils.providers.WebDriverProvider;
import com.acceptance.tests.web.pages.HomePage;
import com.acceptance.tests.web.testDataModel.BasketItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;


public class HomePageSteps extends WebDriverBaseSteps {

    public HomePageSteps(WebDriverProvider webDriverProvider){
        super(webDriverProvider );
    }

   private HomePage homePage= new HomePage(webDriverProvider.getDriver());

    @Given("I am on online shopping website")
    public void i_am_on_online_shopping_website() {
        homePage.checkHomePageIsDisplayed();
    }
    @And("I view {string} item in Quick view")
    public void i_view_an_item_in_quick_view(String item) {
        homePage.viewItemInQuickView(item);
    }

    @And("I view and add following items into basket")
    public void i_view_and_add_following_item_into_basket(DataTable dataTable) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> values = dataTable.asMaps(String.class, String.class);
        List<BasketItem> items = values.stream().map(row -> objectMapper.convertValue(row, BasketItem.class)).collect(Collectors.toList());

    }

}
