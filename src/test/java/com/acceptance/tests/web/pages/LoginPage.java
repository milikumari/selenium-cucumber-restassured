package com.acceptance.tests.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertEquals;

public class LoginPage extends BasePage {


      @FindBy(id = "SubmitLogin")
      WebElement signIn_Button;

      @FindBy(id = "email")
      WebElement userName_TextBox;

    @FindBy(id = "passwd")
    WebElement password_TextBox;

    @FindBy(className = "logout")
    private WebElement signOut_Link;

    @FindBy(css = ".lost_password > a")
    private WebElement forgotPassword_Link;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void checkHomePageIsDisplayed() {
        assertEquals("", driver.getTitle(), "My Store");
    }

    public QuickViewPage viewItemInQuickView(String firstItem) {

        selectItem(firstItem);
        return goToQuickViewPage();
    }

    private QuickViewPage goToQuickViewPage() {
        getPageTitle("Quick view Page");
        return new QuickViewPage(driver);
    }

    private void selectItem(String firstItem) {
    }


    public MyAccountPage loginAndNavigateToMyAccountPage(String userName, String password) {

        login( userName,  password);
        return new MyAccountPage(driver);
    }
    public CheckOutProcessPage loginAndNavigateToCheckOutProcessPage(String userName, String password) {

        login( userName,  password);
        return new CheckOutProcessPage(driver);
    }

    private void login(String userName, String password) {

        enterUserName(userName);
        enterPassword(password);
        clickOnSignInButton();
    }

    private void enterUserName(String userName) {
        enterText(userName_TextBox, userName, "user name text field");
    }

    private void enterPassword(String userName) {
        enterText(password_TextBox, userName, "password text field");
    }

    private void clickOnSignInButton() {
        focusAndClickOnElement(signIn_Button, "sign in button");
    }


}
