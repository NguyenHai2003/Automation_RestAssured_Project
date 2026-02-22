package com.automation.globals;

import com.automation.helpers.PropertiesHelpers;

public class ConfigsGlobal {
    public static final String ENV = PropertiesHelpers.getActiveEnv();
    public static final String BASE_URI = PropertiesHelpers.getValue("BASE_URI");
    public static final String BASE_PATH = PropertiesHelpers.getValue("BASE_PATH");
    public static final String USERNAME = PropertiesHelpers.getValue("USERNAME");
    public static final String PASSWORD = PropertiesHelpers.getValue("PASSWORD");
    public static final String AUTHOR = PropertiesHelpers.getValue("AUTHOR");
}
