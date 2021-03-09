package com.acceptance.tests.web.stepdefinitions;

import com.acceptance.tests.utils.providers.ScenarioProvider;
import com.acceptance.tests.utils.providers.WebDriverProvider;

public class WebDriverBaseSteps {

    protected WebDriverProvider webDriverProvider;
   // protected ScenarioProvider scenarioProvider;

    public WebDriverBaseSteps(WebDriverProvider webDriverProvider){

        this.webDriverProvider= webDriverProvider;
    }
}
