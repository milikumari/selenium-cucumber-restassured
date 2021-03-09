package com.acceptance.tests.web.pages;

import com.acceptance.tests.web.testDataModel.BasketItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;


public class BasePage {

    //To Do  adding loger infomation to help with debugging

    protected final Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());;
    private final static int defaultTimeout = Integer.parseInt(System.getProperty("default.timeout"));
    private final static int defaultPollingTimeout = Integer.parseInt(System.getProperty("default.timeout"));

    public WebDriver driver;
    protected WebDriverWait wait;
    private String pageUrl;

   protected HashMap<String, BasketItem> hashmap = new HashMap< String, BasketItem>();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, defaultTimeout, defaultPollingTimeout);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, defaultTimeout), this);
    }


    protected void focusAndClickOnElement(WebElement element, String elementName) {
        focusOnElement( element);
        element.click();
    }

    protected  void  clickUsingJavaScript(WebElement element){

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    protected  String  getTextUsingJavaScript(WebElement element){

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        return (String) executor.executeScript("return arguments[0].innerHTML;", element);
    }

    protected void clickOnElement(WebElement element, String elementName) {
        element.click();
    }

    protected void enterText(WebElement element, String value, String elementName) {
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            logMessage(elementName + "  was not clickable due to exception ; " + e.getMessage());
        }
    }

    protected void clickAndEnterText(WebElement element, String value, String elementName) {
     //   waitForElementToBeClickable(element);
        try {
            element.click();
            element.sendKeys(value);
        } catch (Exception e) {
            logMessage(elementName + "  was not clickable due to exception ; " + e.getMessage());
        }
    }


    protected  void doubleClickOnElement(WebElement element){

        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }

    protected  void  focusOnElement(WebElement element){

        new Actions(driver).moveToElement(element).perform();

        element.sendKeys("");

    }
    protected void selectByVisibleText(WebElement element, String visibleTextVale, String elementName) {
        logger.info("driver is going to select visible text");
        Select select = new Select(element);
        select.selectByVisibleText(visibleTextVale);
    }

    protected boolean isItemSelected(WebElement element, String value, String elementName) {
        return element.isSelected();
    }

    protected String getTextValueOfElement(WebElement element, String elementName) {
        return element.getText();
    }



    public  void  refreshPage(){
        driver.navigate().refresh();
    }


    protected void waitForElementToBeVisible(WebElement element, String elementDescription) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForTextToDisplay(WebElement element, String text) {

        WebDriverWait wait1 = new WebDriverWait(driver, defaultTimeout);

        wait1.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    protected void waitForElementToBePresent(WebElement element, String text) {


        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }



    protected String getPageTitle(String pageName) {
        return driver.getTitle();
    }


    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public Alert switchToAlert() {
        return driver.switchTo().alert();
    }
    public void load() {
        if (pageUrl.contains("http")) {
            driver.get(pageUrl);
        } else {
            driver.get(System.getProperty("apiBaseUrl") + pageUrl);
        }
    }
    public  void performMouseHover(WebElement element){
        logMessage("webdriver is going to perform mouse hover action");
        Actions action = new Actions(driver) ;
        action.moveToElement(element).build().perform();

    }

    protected void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait1 = new WebDriverWait(driver, 15);

        wait1.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected void waitForElementToBeClickable(WebElement element, String text) {
        WebDriverWait wait1 = new WebDriverWait(driver, defaultTimeout);

        wait1.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected  void logMessage(String logMessage){

        logger.info(logMessage);
    }

}


