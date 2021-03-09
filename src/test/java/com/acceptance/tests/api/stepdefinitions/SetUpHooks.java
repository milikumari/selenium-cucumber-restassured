package com.acceptance.tests.api.stepdefinitions;

import com.acceptance.tests.api.RestAssuredExtension;
import com.acceptance.tests.utils.providers.ScenarioProvider;
import com.acceptance.tests.utils.providers.TestDataProvider;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static java.util.Objects.isNull;

public class SetUpHooks extends BaseSteps {

    TestDataProvider  testDataProvider ;

    public  SetUpHooks(ScenarioProvider scenarioProvider ){

           super(scenarioProvider);    }

    @Before

    public void TestSetup(Scenario scenario){
        if(isNull(testDataProvider)){
            testDataProvider= new TestDataProvider();
        }
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
        this.scenarioProvider.setScenario(scenario);

    }
}
