package com.acceptance.tests.web.pages;

import com.acceptance.tests.utils.providers.TestDataProvider;
import com.acceptance.tests.web.testDataModel.BasketItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class QuickViewPage extends BasePage {

    @FindBy(id = "group_1")
    WebElement selectSize_DropDownList;

    @FindBy(xpath = "//*[@id=\"add_to_cart\"]/button/span")
    WebElement addToCard_Button;
    @FindBy(css = "a[title='Proceed to checkout'] > span")

    WebElement proceedsToCheckout_Button;
    @FindBy(css = "span[title='Continue shopping'] > span")
    WebElement continueWithShopping_Button;

    @FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")
    WebElement itemAddedSuccess_InfoText;

    @FindBy(xpath = "/html/body/div/div[2]/div/div[1]/a[1]/i")
    WebElement homeIcon_link;

    @FindBy(xpath="//span[@id=\"our_price_display\"]")
    WebElement itemPrice_TextValue;

    public QuickViewPage(WebDriver driver) {
        super(driver);
    }


    public QuickViewPage selectContinueToShopping() {
        clickOnElement(continueWithShopping_Button, "click on continue shopping button");
        // clickUsingJavaScript(proceedsToCheckout_Button);
        return new QuickViewPage(driver);
    }

    public CheckOutProcessPage selectProceedsToCheckout() {
        clickUsingJavaScript(proceedsToCheckout_Button);
        refreshPage();

        System.err.println(driver.getTitle());
        return new CheckOutProcessPage(driver);
    }

    public void changeSize(String size) {
        selectSize(size);
    }


    private void selectSize(String size) {
        selectByVisibleText(selectSize_DropDownList, size, "select size from dropdown list");
    }

    public void addItemToBasket(TestDataProvider testDataProvider) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<Map<String, String>> values = dataTable.asMaps(String.class, String.class);
//        List<BasketItem> expectedBasketItem = values.stream().map(row -> objectMapper.convertValue(row, BasketItem.class)).collect(Collectors.toList());

        BasketItem basketItem= new BasketItem();
        HashMap<String, BasketItem> hashmap = new HashMap< String, BasketItem>();

//basketItem.setName();
//basketItem.setColour();
//basketItem.setQuantity();
   basketItem.setPrice(itemPrice_TextValue.getText().replace("$",""));
        clickAddToCartButton();
        // checkItemIsAddedSuccessfully();
        //hashmap.put("firstItem",basketItem);

        getTextUsingJavaScript(itemAddedSuccess_InfoText);
    }

    private void clickAddToCartButton() {
        // assertTrue(addToCard_Button.isDisplayed());
        //System.err.println(addToCard_Button.getText());
        clickUsingJavaScript(addToCard_Button);
    }

    private void checkItemIsAddedSuccessfully() {
        waitForElementToBePresent(itemAddedSuccess_InfoText, "waiting for product added message to be visible");

        assertTrue(itemAddedSuccess_InfoText.isDisplayed());

        System.err.println(itemAddedSuccess_InfoText.getText());
    }

    public HomePage navigateToHomePage() {
        return clickOnHomeIcon();
    }

    private HomePage clickOnHomeIcon() {
        clickOnElement(homeIcon_link, "click on home page icon link");
        return new HomePage(driver);
    }
}
