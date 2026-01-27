package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "C:\\Users\\rrahulre\\OneDrive - Capgemini\\Desktop\\YemiReddy Rahul Reddy\\src\\test\\resources\\Features\\MagicBrick.feature",     // Path to your feature files
    glue = "com.stepDefinitions",               
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",      
        "json:target/cucumber.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    monochrome = true,                            
    publish = true                                
)
public class TestRunner {
    
}