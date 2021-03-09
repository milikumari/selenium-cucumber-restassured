package com.acceptance.tests.api.stepdefinitions;

import com.acceptance.tests.api.RestAssuredExtension;
import com.acceptance.tests.api.model.PageInfo;
import com.acceptance.tests.utils.providers.ScenarioProvider;
import com.acceptance.tests.utils.providers.TestDataProvider;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

public class GetSteps extends BaseSteps{
    private TestDataProvider testDataProvider;

    public GetSteps(TestDataProvider testDataProvider , ScenarioProvider scenarioProvider) {
        super(scenarioProvider);
          this.testDataProvider= testDataProvider;
    }

    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) throws Throwable {
        log("going to run Get request");

        testDataProvider.setResponse( RestAssuredExtension.GetOps(url, scenarioProvider.getScenario()));
        addToReport("\n  API request url is:" +  "\n ", "https://reqres.in/"+url);
       log("\n  API request url is:" +  "\n "+ "https://reqres.in/"+url);
        PageInfo pageInfo =testDataProvider.getResponse().body().as(PageInfo.class);
        pageInfo.getData();
        testDataProvider.setPageInfo(pageInfo);
    }
}
