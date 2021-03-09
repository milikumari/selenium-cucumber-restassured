package com.acceptance.tests.api.stepdefinitions;

import com.acceptance.tests.utils.providers.ScenarioProvider;
import com.acceptance.tests.utils.providers.TestDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseSteps {
    protected  final ScenarioProvider scenarioProvider;
    private final Logger logger;
    public  BaseSteps(ScenarioProvider scenarioProvider ){
        this.scenarioProvider= scenarioProvider;
        this.logger= LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }

    protected  void  log(String logMessage){
        logger.debug("This log is related to this scenario :-> "+scenarioProvider.getScenario().getName().replace("\\s","_"));
        logger.info(logMessage);
    //    logger.debug(logMessage);

    }

    protected  void  addToReport(String reportMessageInfo, String message){
        scenarioProvider.getScenario().log(reportMessageInfo + "\n : ");
        scenarioProvider.getScenario().log(message);
        scenarioProvider.getScenario().log(  "\n : ");

    }
}
