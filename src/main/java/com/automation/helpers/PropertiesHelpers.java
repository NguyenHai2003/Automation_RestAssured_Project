package com.automation.helpers;

import com.automation.constants.FrameworkConstants;
import com.automation.utils.LogUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.LinkedList;

public class PropertiesHelpers {

    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;

    public static Properties loadAllFiles() {
        LinkedList<String> files = new LinkedList<>();
        // Add all config files here
        files.add(FrameworkConstants.CONFIG_FILE_PATH);

        try {
            properties = new Properties();

            for (String f : files) {
                linkFile = f;
                file = new FileInputStream(linkFile);
                properties.load(file);
                file.close();
            }
            return properties;
        } catch (IOException e) {
            LogUtils.error("Error loading config file: " + linkFile);
            e.printStackTrace();
            return new Properties();
        }
    }

    public static String getValue(String key) {
        if (properties == null) {
            properties = loadAllFiles();
        }
        String value = properties.getProperty(key);
        return value != null ? value : "";
    }
}
