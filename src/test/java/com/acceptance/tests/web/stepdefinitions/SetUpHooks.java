package com.acceptance.tests.web.stepdefinitions;

import com.acceptance.tests.utils.PropertyLoader;
import com.acceptance.tests.utils.providers.TestDataProvider;
import com.acceptance.tests.utils.providers.WebDriverProvider;

import com.acceptance.tests.web.webDriver.WebDriverBuilder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.isNull;

public class SetUpHooks {

    private WebDriverProvider webDriverProvider;
    TestDataProvider testDataProvider ;

    private WebDriver driver;

    public SetUpHooks(TestDataProvider testDataProvider,WebDriverProvider webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
        driver = webDriverProvider.getDriver();
        this.testDataProvider=testDataProvider;
    }

    @Before
    public void beforeScenario(Scenario scenario) throws Exception {
        //This can be moved to runner classes so its not set again for each scenario
        PropertyLoader.loadConfig();
        buildWebDriver(scenario.getSourceTagNames());
    }

    @After

    public  void CloseBrowserAfterScenario(Scenario scenario){
        try {
            appendImageToStepWhenTestScenarioIsFailed(scenario);
        }
        finally {
            driver.close();
            driver.quit();
        }
    }

    private void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    private void buildWebDriver(final Collection<String> scenarioTagList) throws Exception {

        webDriverProvider.setDriver(WebDriverBuilder.buildLocalDriver());
        this.driver = webDriverProvider.getDriver();
        driver.get(System.getProperty("webUrl"));
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(System.getProperty("implicite.wait")),TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    private void appendImageToStepWhenTestScenarioIsFailed(Scenario scenario) {

        //String scenarioName= scenario.getName().replaceAll("\\s","-");
        //SimpleDateFormat sdf= new SimpleDateFormat("dd-MMM-yyy_hh_mm");
       // String s= sdf.format(new Date());
        if (scenario.isFailed()) {
            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(bytes, "image/png", "Image");
        }
    }

}