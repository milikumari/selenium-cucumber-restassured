package com.acceptance.tests.web.pages;

import com.acceptance.tests.web.testDataModel.BasketItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
// TO Do -> This class will be  split into  BasketSummaryPage, AddressPage,ShipmentPage, OrderConfirmationPage
//relevant pageObjects and methods will be moved when this class is refactored
public class CheckOutProcessPage extends BasePage{


@FindAll({
        @FindBy(xpath = "//*[@id=\"center_column\"]/p[2]/a[1]/span"),
        @FindBy(css="div#center_column  a[title='Proceed to checkout'] > span")
})
    WebElement proceedsToCheckout_summary_Button;

    @FindAll({
            @FindBy(xpath="//*[@id=\"center_column\"]/form/p/button/span")
            })
    WebElement proceedsToCheckout_Address_Button;


    @FindBy(xpath="//button[@name=\"processCarrier\"]")
    WebElement proceedsToCheckout_Shipping_Button;

    @FindBy(xpath="//*[@id=\"cgv\"]")
    WebElement termsOfService_Shipping_CheckBox;

    @FindBy(xpath="//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")
    WebElement payByWire;

    @FindBy(xpath="//*[@id=\"cart_navigation\"]/button/span")
    WebElement iConfirmMyOrder_Button;
    public CheckOutProcessPage(WebDriver driver ) {
        super(driver);
    }
    @FindBy(xpath="//*[@id=\"center_column\"]/div/p/strong")
    WebElement orderConfirmation_InfoText;

    @FindBy(xpath="//*[@title=\"Log me out\"]")
    WebElement signOut_Link;

    @FindBy(xpath="//*[@title=\"Log in to your customer account\"]")
    WebElement signIn_link;

    @FindBy(xpath = "//div[@id=\"order-detail-content\"]/table/tbody/tr")
    List<WebElement> addedProductList;

    @FindBy(xpath = "//tr[2][starts-with(@id,\"product_\")]/td[2]/p/a")
    WebElement productDescription2;


    public void proceedsToCheckoutWithDefaultAddressSelected() {
        //TO DO
        //these steps will be refactored  and moved into different steps and proper method/objectPage names later on
        // also helper methods should be used
        clickOnElement(proceedsToCheckout_Address_Button, "select proceeds to checkout button");
        clickOnElement(termsOfService_Shipping_CheckBox, "select terms of service");
        clickOnElement(proceedsToCheckout_Shipping_Button, "select proceeds to checkout button");
        clickOnElement(payByWire, "select pay by wire option");
        clickOnElement(iConfirmMyOrder_Button,"click on  'i confirm my order' button");
        assertEquals("expected order confirmation message not found","Your order on My Store is complete.", orderConfirmation_InfoText.getText());
        clickOnElement(signOut_Link,"click on sign-out link");
        assertEquals("check sign in link is displayed on the page","Sign in", signIn_link.getText());

    }

    public void navigateToAddressVerificationPage() {

        clickOnElement(proceedsToCheckout_summary_Button, "click on proceeds to Checkout button on Summary section");

    }

    public void validateMyBasketDetails(List<BasketItem> expectedBasketItems, String expectedProductTotal) {
//This is messy code but still works :-) need to be refactored and  decorated
      List<BasketItem> actualBasketItems= new ArrayList<BasketItem>();

        WebElement productColourAndSize=null;
        WebElement quantity=null;
        WebElement price=null;
        WebElement productDescription = null;
        for(int i =1; i<= addedProductList.size();i++)
    {
        BasketItem basketItem = new BasketItem();

        productDescription= driver.findElement(By.xpath("//tr["+i+"][starts-with(@id,\"product_\")]/td[2]/p/a"));
        quantity =driver.findElement(By.xpath("//tr["+i+"][starts-with(@id,\"product_\")][starts-with(@class,\"cart_item\")]/td[5]/input[@class=\"cart_quantity_input form-control grey\"]"));;
        price= driver.findElement(By.xpath("//tr["+i+"][starts-with(@id,\"product_\")][starts-with(@class,\"cart_item\")]/td[4]/span/span"));
        productColourAndSize =driver.findElement(By.xpath( "//tr["+i+"][starts-with(@id,\"product_\")][starts-with(@class,\"cart_item\")]/td[2]/small/a"));;

        System.err.println( "productDescription->"+ i + getTextUsingJavaScript(productDescription));

        basketItem.setSize(productColourAndSize.getText());
        basketItem.setName(getTextUsingJavaScript(productDescription));
        basketItem.setQuantity(getTextUsingJavaScript(quantity));
        basketItem.setColour(getTextUsingJavaScript(productColourAndSize));
        basketItem.setPrice(getTextUsingJavaScript(price).replace("$",""));

        actualBasketItems.add(basketItem);

        WebElement actualProductTotal=  driver.findElement(By.xpath("//td[@class=\"price\"][@id=\"total_product\"]"));
        WebElement shipment=  driver.findElement(By.xpath("//td[@class=\"price\"][@id=\"total_shipping\"]"));
        WebElement basketTotalWithoutTax=  driver.findElement(By.xpath("//*[@id=\"total_price_without_tax\"]"));
        WebElement tax=  driver.findElement(By.xpath("//*[@id=\"total_tax\"]"));
        WebElement basketTotalIncludingTax=  driver.findElement(By.xpath("//*[@id=\"total_price\"]"));
        System.err.println("expectedProductTotal="+ expectedProductTotal);
        System.err.println("actualProductTotal="+ getTextUsingJavaScript(actualProductTotal).replace("$",""));

        assertTrue("actual amount did not match with expected amount",expectedProductTotal.equals(actualProductTotal.getText().replace("$","")));

    }
        assertEquals("total number of basket item did not match expected",expectedBasketItems.size(),actualBasketItems.size());


    }
}
