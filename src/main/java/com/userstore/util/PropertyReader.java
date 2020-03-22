package com.userstore.util;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    Properties properties;

    public PropertyReader(String filename) {
        properties = new Properties();
        try {
            properties.load(PropertyReader.class.getClassLoader().getResourceAsStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error in loading properties " + filename);
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
