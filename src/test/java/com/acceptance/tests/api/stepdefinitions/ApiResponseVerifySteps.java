package com.acceptance.tests.api.stepdefinitions;

import com.acceptance.tests.api.model.PageInfo;
import com.acceptance.tests.utils.providers.ScenarioProvider;
import com.acceptance.tests.utils.providers.TestDataProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Fail;

import static java.util.Objects.nonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ApiResponseVerifySteps extends BaseSteps {

    private TestDataProvider testDataProvider;

    public ApiResponseVerifySteps(TestDataProvider testDataProvider, ScenarioProvider scenarioProvider) {
        super(scenarioProvider);
        this.testDataProvider = testDataProvider;
    }

    @Then("Response should be {string}")
    public void response_should_be_success(String responseStatus) throws Exception {

        switch (responseStatus.toLowerCase()) {
            case "created":
                assertTrue("expected created response not returned", testDataProvider.getResponse().getStatusCode() == 201);
                break;
            case "bad request":
                assertTrue("expected bad request response not returned", testDataProvider.getResponse().getStatusCode() == 400);
                break;
            case "success":
                assertTrue("expected success response not returned", testDataProvider.getResponse().getStatusCode() == 200);
            break;
                case "deleted":
                assertTrue("expected deleted response not returned", testDataProvider.getResponse().getStatusCode() == 204);
           break;
            default:
                Fail.fail("currently this methods support , created, success, deleted, bad response. Please add support for " +responseStatus +"response");
        }

        //static  scenario may not work well if test are executed in parallel
        if( !testDataProvider.getResponse().getBody().asString().isEmpty()){
            addToReport("response body is \n", getPrettyString());
            log("\n  API response bosy is:" +  "\n "+ "https://reqres.in/"+getPrettyString());
        }
    }

    private String getPrettyString() throws Exception {

        //will add try catch block later
        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(testDataProvider.getResponse().getBody().asString(), Object.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    @Then("total number of pages should be {string}")
    public void total_number_of_pages_should_be(String totalPages) {
        PageInfo pageInfo = testDataProvider.getResponse().getBody().as(PageInfo.class);
        assertEquals("expected number of page total do not match actual", pageInfo.getPage(), totalPages);
    }

    @Then("I should see the response body has name as {string}")
    public void i_should_see_the_body_has_name_as(String name) {
       assertEquals( "expected name not found",name, testDataProvider.getResponseBodyTestData().getName());
       assertTrue("id should not be null",(nonNull(testDataProvider.getResponseBodyTestData().getId())));
    }
}