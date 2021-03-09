package com.acceptance.tests.api.stepdefinitions;

import com.acceptance.tests.api.RestAssuredExtension;
import com.acceptance.tests.api.model.RequestBodyTestData;
import com.acceptance.tests.api.model.ResponseBodyTestData;
import com.acceptance.tests.utils.providers.ScenarioProvider;
import com.acceptance.tests.utils.providers.TestDataProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PutSteps extends BaseSteps{
    private TestDataProvider testDataProvider;
   //private ScenarioProvider scenarioProvider;

    public PutSteps(TestDataProvider testDataProvider , ScenarioProvider scenarioProvider) {
        super(scenarioProvider );
         this.testDataProvider= testDataProvider;

    }

    @Given("I Perform PUT operation for {string} with body")
    public void i_perform_put_operation_for_with_body(String url, DataTable dataTable) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

       // ObjectMapper objectMapper2 = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Map<String, String>> values = dataTable.asMaps(String.class, String.class);
        List<RequestBodyTestData> requestBodyData = values.stream().map(row -> objectMapper.convertValue(row, RequestBodyTestData.class)).collect(Collectors.toList());
        ResponseOptions<Response> response = RestAssuredExtension.PUTOpsWithBody(url, objectMapper.writeValueAsString(requestBodyData.get(0)));
        testDataProvider.setResponse(response);
        testDataProvider.setResponseBodyTestData(testDataProvider.getResponse().getBody().as(ResponseBodyTestData.class));
    }

    }
