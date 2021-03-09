package com.acceptance.tests.api.stepdefinitions;

import com.acceptance.tests.api.RestAssuredExtension;
import com.acceptance.tests.api.model.RequestBodyTestData;
import com.acceptance.tests.utils.providers.ScenarioProvider;
import com.acceptance.tests.utils.providers.TestDataProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeleteSteps  extends BaseSteps{
    private TestDataProvider testDataProvider;

    public DeleteSteps(TestDataProvider testDataProvider, ScenarioProvider scenarioProvider) {
        super(scenarioProvider);
       this.testDataProvider = testDataProvider;
    }

    @Given("I Perform DELETE operation for {string}")
    public void i_perform_delete_operation(String url) throws JsonProcessingException {
        ResponseOptions<Response> response = RestAssuredExtension.DeleteOps(url);
        testDataProvider.setResponse(response);
    }
    }
