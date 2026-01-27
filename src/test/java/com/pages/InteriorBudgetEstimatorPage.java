package com.pages;

import org.openqa.selenium.WebDriver;
import java.util.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InteriorBudgetEstimatorPage extends BasePage {



    @FindBy(xpath = " //button[@class='cta cta-filled']")  
    private WebElement submitButton;



    public InteriorBudgetEstimatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }



    @FindBy(xpath="//div[@class=\"mb-form-ui__error\"]") 
    private List<WebElement> errorMessages;

    public  List<String> getErrorMessages() {
        List<String> texts = new ArrayList<>();
        for (WebElement e : errorMessages) {
            texts.add(e.getText().trim());
        }
        return texts;
    }
    public void clickSubmitWithoutFilling() {
        click(submitButton);
    }

    
}