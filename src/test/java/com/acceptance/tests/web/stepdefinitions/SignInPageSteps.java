package com.acceptance.tests.web.stepdefinitions;

import com.acceptance.tests.utils.providers.TestDataProvider;
import com.acceptance.tests.utils.providers.WebDriverProvider;
import com.acceptance.tests.web.pages.HomePage;
import com.acceptance.tests.web.pages.LoginPage;
import io.cucumber.java.en.Given;


public class SignInPageSteps extends WebDriverBaseSteps {

    private HomePage homePage= new HomePage(webDriverProvider.getDriver());
    private LoginPage loginPage= new LoginPage(webDriverProvider.getDriver());
    private TestDataProvider testDataProvider;

    public SignInPageSteps(WebDriverProvider webDriverProvider,TestDataProvider testDataProvider){
        super(webDriverProvider );
        this.testDataProvider=testDataProvider;
    }

    @Given("user login with username {string} and password {string}")
    public void user_login_with_username_and_password(String userName, String password) {
        if(!TestDataProvider.isUserOnLoginPage){
        homePage.navigateToLoginPage();
        }
        testDataProvider.getUser().getUserName();
        testDataProvider.getUser().getPassword();
        loginPage.loginAndNavigateToMyAccountPage(userName, password) ;
    }
}
