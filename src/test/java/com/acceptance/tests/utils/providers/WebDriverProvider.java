package com.acceptance.tests.utils.providers;


import org.openqa.selenium.WebDriver;

public class WebDriverProvider {

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;

}
