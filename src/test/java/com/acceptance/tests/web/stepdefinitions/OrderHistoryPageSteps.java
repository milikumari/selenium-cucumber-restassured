package com.acceptance.tests.web.stepdefinitions;

import com.acceptance.tests.utils.providers.TestDataProvider;
import com.acceptance.tests.utils.providers.WebDriverProvider;
import com.acceptance.tests.web.pages.OrderHistoryPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Random;

public class OrderHistoryPageSteps extends WebDriverBaseSteps {

    private OrderHistoryPage orderHistoryPage = new OrderHistoryPage(webDriverProvider.getDriver());
    private TestDataProvider testDataProvider;

    public OrderHistoryPageSteps(TestDataProvider testDataProvider, WebDriverProvider webDriverProvider) {
        super((webDriverProvider));
        this.testDataProvider = testDataProvider;
    }

    @Given("select latest order from orders history")
    public void select_latest_order_from_orders_history() throws InterruptedException {
        orderHistoryPage.selectLatestOrder();
    }

    @When("I expect could of dress to be red")
    public void i_expect_could_of_dress_to_be_red() {
        System.err.println("");
    }

    @When("user add message")
    public void user_add_message() {
        String myMessage = "add this message for my order: " + generateRandomString();
        orderHistoryPage.addMessageForSelectedOrder(myMessage);
        TestDataProvider.ORDER_MESSAGE = myMessage;
    }

    @When("message should be saved")
    public void message_should_be_saved() {
        orderHistoryPage.checkMyMessageIsAdded(TestDataProvider.ORDER_MESSAGE);
    }

    @Then("colour of my order should be {string}")
    public void colour_of_my_order_should_be_(String colour) {
        orderHistoryPage.validateDressColour(colour);
    }

    private String generateRandomString() {

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 7;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
