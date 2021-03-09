package com.acceptance.tests.web.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

import static java.lang.Thread.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderHistoryPage extends BasePage{



    @FindBy(xpath="//*[contains(text(), \"FOLLOW YOUR ORDER'S STATUS STEP-BY-STEP\")]")
    WebElement followYourOrderStatus_TextSection;

    @FindBy(xpath="//a[contains(text(), \"Sign in\")]")
    WebElement latestOrder_Link;

    @FindBy(xpath="//th[text()=\"Date\"][@class=\"item footable-sortable footable-sorted-desc\"]")
    WebElement orderSortedInAscendingOrderByDate_Link;

    @FindBy(xpath="//th[text()=\"Date\"][@class=\"item footable-sortable footable-sorted\"]")
    WebElement orderSortedInDescendingOrderByDate_Link;

    @FindBy(xpath="//th[text()=\"Date\"][@class=\"item footable-sortable\"]")
    WebElement defaultDate_Link;

    @FindBy(xpath="//th[text()=\"Date\"]")
    WebElement Date_Link;

    @FindBy(xpath ="//*[@id=\"order-list\"]/tbody/tr[1]/td[1]/a")
    WebElement firstOrder_Link;

    @FindBy(xpath ="//*[@id=\"sendOrderMessage\"]/p/textarea[@name=\"msgText\"]")
    WebElement messageBox_TextBox;

    @FindBy(xpath = "//*[@id=\"sendOrderMessage\"]/div/button/span")
    WebElement send_Button;

    @FindBy(xpath="//*[@id=\"block-order-detail\"]/p")
    WebElement successMessage_Text;

    @FindBy(xpath="//*[@id=\"block-order-detail\"]/div[5]/table/tbody/tr[1]/td[2]")
    WebElement myLastMessageForOrder;

    @FindBy(xpath = "//*[@id=\"order-detail-content\"]/table/tbody/tr[1]/td[2]/label")
    WebElement productNameColourAndSize;
    public OrderHistoryPage(WebDriver driver ) {
        super(driver);
      //  checkOrderHistoryPageIsDisplayed();
    }


    public  void checkOrderHistoryPageIsDisplayed(){
        assertEquals("","Order history - My Store",driver.getTitle());
    }

    public void selectLatestOrder()  {
        sortOrderByDateInDescendingOrder();
        selectFirstOrderFromList();

    }

    private void selectFirstOrderFromList() {
        clickOnElement(firstOrder_Link,"");

    }

    private void sortOrderByDateInDescendingOrder() {
       // int i=0;
        while( !Date_Link.getAttribute("class").equalsIgnoreCase("item footable-sortable footable-sorted-desc")){
          //  System.err.println(Date_Link.getAttribute("class"));
            Date_Link.click();
           // i++;
           // System.err.println(" driving is going to click : "+ i  +   " times now");
        }
        //System.err.println("my order number is ->  "+ firstOrder_Link.getText());
        clickOnElement(firstOrder_Link,"");

    }

    private  void AddMessage(String message){
        clickAndEnterText(messageBox_TextBox,message,"add comment in the message box");
        clickOnElement(send_Button,"send button");
        assertEquals("Message successfully sent", successMessage_Text.getText());
    }

    public void addMessageForSelectedOrder(String message) {
        AddMessage(message);
    }

    public void checkMyMessageIsAdded(String myMessage) {

        assertTrue("",myLastMessageForOrder.getText().equals(myMessage));
    }

    public void validateDressColour(String expectedColour) {
//StringUtils.substringBetween(s,":",",").trim()
        String actualColour= StringUtils.substringBetween(productNameColourAndSize.getText(),":",",").trim();

        assertTrue("expected color: " +expectedColour + " but found: " +actualColour, expectedColour.equalsIgnoreCase(actualColour));
    }
}
