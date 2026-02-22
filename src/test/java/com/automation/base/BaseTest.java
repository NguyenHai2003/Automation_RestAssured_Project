package com.automation.base;

import com.automation.globals.ConfigsGlobal;
import com.automation.helpers.PropertiesHelpers;
import com.automation.listeners.TestListener;
import com.automation.utils.LogUtils;
import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeSuite
    public void beforeSuite() {
        LogUtils.info("Initializing Automation Framework");
        PropertiesHelpers.loadAllFiles();
        RestAssured.baseURI = ConfigsGlobal.BASE_URI;
        RestAssured.basePath = ConfigsGlobal.BASE_PATH;
        LogUtils.info("Base URI: " + RestAssured.baseURI);
        LogUtils.info("Base Path: " + RestAssured.basePath);
    }

    @BeforeMethod
    public void beforeMethod() {
        LogUtils.info("Starting Test Method");
    }

    @AfterSuite
    public void afterSuite() {
        LogUtils.info("Stopping Automation Framework");
    }
}
