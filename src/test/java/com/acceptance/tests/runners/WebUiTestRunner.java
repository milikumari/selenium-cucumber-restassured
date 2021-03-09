package com.acceptance.tests.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.commons.io.FileUtils;
import org.junit.runner.RunWith;
import org.junit.BeforeClass;


import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions
              (
                publish=false,
                glue = { "com.acceptance.tests.web.stepdefinitions"},
                tags = "@cucumberTest  and not @api"
                ,features =  "src/test/resources/features"
               ,plugin={"json:target/report/cucumber-json.json","html:target/report/cucumber-html.html",
                    "com.acceptance.tests.utils.reportHelper.ExtentCucumberAdapter:",
                     "pretty"}
                )

public class WebUiTestRunner {
    // uncomment if you want to delete older logs before running new tests
//    @BeforeClass
//    public static void setUp() {
//        File file = null;
//
//        try {
//            FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + "/test-output/"));
//        } catch (Exception e) {
//
//            e.printStackTrace();
//            System.err.println("exception occurred while deleting file  from path: " + file + "\n" + e.getMessage());
//
//        }
//    }


}
