package com.automation.constants;

import com.automation.helpers.SystemHelpers;
import java.io.File;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    public static final String PROJECT_PATH = SystemHelpers.getCurrentDir();
    public static final String REPORT_PATH = PROJECT_PATH + File.separator + "target" + File.separator + "allure-results";
    public static final String CONFIG_FILE_PATH = PROJECT_PATH + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "configs.properties";
    public static final String AUTHOR = "Automation Architect";
}
