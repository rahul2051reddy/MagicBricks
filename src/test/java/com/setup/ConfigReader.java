package com.setup;




import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties prop = new Properties();

    public static void loadConfig() {
        try (FileInputStream input = new FileInputStream("src/test/resources/PropertieFiles/config.properties")) {
            prop.load(input);
                    } catch (IOException e) {
            e.printStackTrace();
           
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}