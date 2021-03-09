package com.acceptance.tests.web.stepdefinitions;

import com.acceptance.tests.utils.providers.TestDataProvider;
import com.acceptance.tests.utils.providers.WebDriverProvider;
import com.acceptance.tests.web.pages.QuickViewPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class QuickViewPageSteps extends WebDriverBaseSteps {

    private TestDataProvider testDataProvider;
    private QuickViewPage quickViewPage = new QuickViewPage(webDriverProvider.getDriver());

    public QuickViewPageSteps(    TestDataProvider testDataProvider
            ,WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
        this.testDataProvider=testDataProvider;
    }

    @Given("change the size of the item to {string}")
    public void change_the_size_of_the_item(String size) {

        quickViewPage.changeSize(size);
    }

    @Given("add that item to basket")
    public void add_that_item_to_basket() {
    quickViewPage.addItemToBasket(this.testDataProvider);    }

    @Given("select continue shopping option")
    public void continue_shopping() {

        quickViewPage.selectContinueToShopping();
        quickViewPage.navigateToHomePage();
    }
    @Given("select proceeds to checkout")
    public void select_proceeds_to_checkout() {

        quickViewPage.selectProceedsToCheckout();
    }

}