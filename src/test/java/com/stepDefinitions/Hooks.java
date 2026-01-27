package com.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.setup.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {

    WebDriver driver;

    @Before
    public void setup() {
        System.out.println("Launching browser...");
        DriverFactory.initializeDriver();
        driver = DriverFactory.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            driver = DriverFactory.getDriver();

            if (driver != null) {
                
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());

                // Save a local copy inside "Screenshots" folder
                String screenshotName = scenario.getName().replaceAll(" ", "_");
                Path screenshotPath = Paths.get("Screenshots", screenshotName + ".png");
                Files.createDirectories(screenshotPath.getParent());

                File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Files.copy(file.toPath(), screenshotPath);

                System.out.println("Screenshot saved for scenario: " + scenario.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Closing browser...");
        DriverFactory.quitDriver();
    }
}