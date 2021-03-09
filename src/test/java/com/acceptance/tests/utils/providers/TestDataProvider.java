package com.acceptance.tests.utils.providers;


import com.acceptance.tests.api.model.PageInfo;
import com.acceptance.tests.api.model.ResponseBodyTestData;
import com.acceptance.tests.web.testDataModel.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import java.io.File;

public  class TestDataProvider {

    public TestDataProvider(){
    }

    private   PageInfo pageInfo;
    public static  boolean isUserOnLoginPage=true;

    public  static String ORDER_MESSAGE;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public ResponseBodyTestData getResponseBodyTestData() {
        return responseBodyTestData;
    }

    public void setResponseBodyTestData(ResponseBodyTestData responseBodyTestData) {
        this.responseBodyTestData = responseBodyTestData;
    }

    private ResponseBodyTestData responseBodyTestData;

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }


    public ResponseOptions<Response> getResponse() {
        return response;
    }

    public void setResponse(ResponseOptions<Response> response) {
        this.response = response;
    }

    private ResponseOptions<Response> response;

    public User getUser(){

        User user = new User();
        String testEnv= System.getProperty("test.env");
        ObjectMapper mapper = new ObjectMapper();
        try {
                user= mapper.readValue(new File(System.getProperty("user.dir")+"/src/test/resources/testData/"+testEnv+"/testData.json"),User.class);
             }catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public PageInfo getPageInfo(String jsonLocation){
        jsonLocation="myCustomer.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            if(pageInfo==null) {
                System.err.println("test-1");
                pageInfo= mapper.readValue(new File(System.getProperty("user.dir")+"/src/test/resources/testData/"+jsonLocation),PageInfo.class);
            } }catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

}

