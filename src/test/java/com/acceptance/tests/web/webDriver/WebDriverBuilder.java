package com.acceptance.tests.web.webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class WebDriverBuilder {



    private  enum  Browser{
        None,
        Chrome,
//        Firefox,
//        Safari,
//        Gecko
    }

    private static WebDriverBuilder.Browser  getBrowser(){

        String  browser =System.getProperty("test.browser.name");

        if(browser.equalsIgnoreCase("chrome")){
            return  Browser.Chrome;
        }
//        else if(browser.equalsIgnoreCase("firefox")){
//            return  Browser.Firefox;
//        }else if(browser.equalsIgnoreCase("gecko")){
//            return  Browser.Gecko;
//        }else if(browser.equalsIgnoreCase("safari")){
//            return  Browser.Safari;
//        }
        else
            return Browser.None;

    }

    public static WebDriver buildLocalDriver() throws Exception {

        switch(getBrowser()){
            case Chrome:
                return getLocalChromeDriver( System.getProperty("user.dir")+System.getProperty("chrome.webdriver.path"));
            //case Gecko:
//            case Firefox:
//                return getLocalFireFoxDriver(System.getProperty("user.dir")+System.getProperty("chrome.webdriver.path"));
            case None:
            default:
                throw new Exception("Please set valid browser look like you have not set valid option");
        }
    }


    static ChromeDriver getLocalChromeDriver(String driverPath ){

        boolean ignoreSSL = Boolean.parseBoolean(System.getProperty("ignore.ssl"));
        ChromeDriverService service= new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(driverPath))
                .usingAnyFreePort()
                .build();
        ChromeOptions options =new ChromeOptions();
        options.setAcceptInsecureCerts(ignoreSSL);
        options.addArguments("--allow-insecure-localhost");
        return new ChromeDriver(service, options);

    }

    static FirefoxDriver getLocalFireFoxDriver(String driverPath) {
        System.setProperty("webdriver.gecko.driver", driverPath);
        return new FirefoxDriver();
    }

}
