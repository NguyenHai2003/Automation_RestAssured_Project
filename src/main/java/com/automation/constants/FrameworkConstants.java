package com.automation.constants;

import com.automation.helpers.SystemHelpers;
import java.io.File;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    public static final String PROJECT_PATH = SystemHelpers.getCurrentDir();
    public static final String REPORT_PATH = PROJECT_PATH + File.separator + "target" + File.separator + "allure-results";
    public static final String TEST_RESOURCES_PATH = PROJECT_PATH + File.separator + "src" + File.separator + "test" + File.separator + "resources";
    public static final String CONFIG_DIR_PATH = TEST_RESOURCES_PATH + File.separator + "config";
    public static final String COMMON_CONFIG_FILE_PATH = CONFIG_DIR_PATH + File.separator + "configs.properties";
    public static final String AUTHOR = "Automation Architect";
}
