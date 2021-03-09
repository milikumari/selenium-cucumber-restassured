package com.acceptance.tests.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                publish=false,
                glue = { "com.acceptance.tests.api.stepdefinitions"},
                tags = "(@cucumberTest or @api ) and not @webui"
                ,features =  "src/test/resources/features/api"
                ,plugin={"json:target/report/cucumber-json.json","html:target/report/cucumber-html.html",
                "com.acceptance.tests.utils.reportHelper.ExtentCucumberAdapter:",
                "pretty"}
        )



public class ApiTestRunner   {

//    @BeforeClass
//    public static   void  setUp(){
//     File file= null;
//
//     try {
//         FileUtils.deleteDirectory(new File( System.getProperty("user.dir")+"/logs/"));
//
//     }catch (Exception e){
//
//         e.printStackTrace();
//         System.err.println("exception occurred while deleting file  from path: " + file +  "\n" + e.getMessage());
//        }
//    }

}
