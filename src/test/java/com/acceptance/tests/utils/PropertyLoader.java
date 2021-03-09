package com.acceptance.tests.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public class PropertyLoader {

    private static final Logger logger = LogManager.getLogger(PropertyLoader.class);
    private Properties props;

    private static void parseProperties(InputStream streams)  {
        Properties props = new Properties();
        try {
            props.load(streams);
        } catch (Exception e) {

            logger.error(e.getStackTrace());
        }

        props.stringPropertyNames().forEach(prop -> {
            if (isNull(System.getProperty(prop))) {
                System.setProperty(prop, props.getProperty(prop));
                System.getProperty(prop);
            } else {
                logger.info("System property for your prop " + prop + " is already set");
            }
        });
        System.getProperty("webdriver.local.run");
    }

    public  static void loadConfig() {
        InputStream propertiesStream = getPropertiesInputStream();

        if (nonNull(propertiesStream)) {
            parseProperties(propertiesStream);
        }
        System.getProperty("webdriver.local.run");
    }

    private static InputStream getPropertiesInputStream()  {

        InputStream propsStream = null;
        try {

            //   final InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
            propsStream = ClassLoader.getSystemResourceAsStream("config.properties");

        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return propsStream;

    }


}