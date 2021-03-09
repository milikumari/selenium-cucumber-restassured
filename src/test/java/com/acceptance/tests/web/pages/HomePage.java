package com.acceptance.tests.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HomePage extends  BasePage {

    @FindAll({
            @FindBy(xpath="//a[@title=\"Log in to your customer account\"]"),
            @FindBy(xpath="//a[contains(text(), \"Sign in\")]"),
    })
    WebElement login_Link;


    @FindBy(xpath="//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]/img")
    WebElement firstItemFromList;

    @FindBy(xpath="//*[@id=\"homefeatured\"]/li[2]/div/div[1]/div/a[1]/img")
    WebElement secondItemFromList;

    public HomePage( WebDriver driver ) {
        super(driver);
    }

    public  void checkHomePageIsDisplayed(){
        assertEquals("",driver.getTitle(),"My Store");
    }

    public  QuickViewPage viewItemInQuickView(String itemPlace){
        selectItem(itemPlace);
        return  goToQuickViewPage();
    }

    private QuickViewPage goToQuickViewPage() {
        getPageTitle("Quick view Page");
        return new QuickViewPage(driver);
    }

    private void selectItem(String itemPlace) {
        WebElement item = driver.findElement(By.xpath( "//*[@id=\"homefeatured\"]/li["+getItemIndex(itemPlace)+ "]/div/div[1]/div/a[1]/img"));
        WebElement quickViewItem = driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li["+getItemIndex(itemPlace)+"]/div/div[2]/div[2]/a[2]/span"));
        performMouseHover(item);
        clickOnElement(quickViewItem, "click on quick view item option");

    }

    private LoginPage clickOnSignInLink(){
      focusAndClickOnElement(login_Link, "click on login link");
      return new LoginPage(driver);
    }

    public  LoginPage navigateToLoginPage(){
        return clickOnSignInLink();
    }

    private  String getItemIndex(String itemPositionInList){

        switch (itemPositionInList.toLowerCase()){
            case "first":
                return "1";
            case "second":
                return "2";
            default:
                return  "1";

        }
    }

    private  WebElement getItemElementByName(String itemPositionInList) {
        WebElement element;
       return null;
    }
}

