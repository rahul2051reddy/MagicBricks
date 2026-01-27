package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EMICalculator extends BasePage {

    private WebDriverWait wait;

    public EMICalculator(WebDriver driver) {
        super(driver);
        // Wait for up to 15 seconds for elements to be visible/clickable
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(id = "amountRequiredEmiCal")
    WebElement loanAmountField;

    @FindBy(id = "interestRateEmiCal")
    WebElement interestRateField;

    @FindBy(xpath = "//label[@for='emiPropFinalizedNo']")
    WebElement propertyNoOption;

    @FindBy(xpath = "//label[@for='emiPropFinalizedYes']")
    WebElement propertyYesOption;

    @FindBy(xpath = "//a[text()='Recalculate Your EMI']")
    WebElement recalculateButton;

    @FindBy(xpath = "//span[@id='InterestAmount']")
    WebElement interestAmountLabel;

    
    public void clearLoanAmount() {
        wait.until(ExpectedConditions.visibilityOf(loanAmountField)).clear();
    }

   
    public void clearInterestRate() {
        wait.until(ExpectedConditions.visibilityOf(interestRateField)).clear();
    }

   
    public void enterLoanAmount(String amount) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loanAmountField));
        element.sendKeys(amount);
    }

    
    public void enterInterestRate(String interestRate) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(interestRateField));
        element.sendKeys(interestRate);
    }

    
    public void clickNo() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(propertyNoOption));
        element.click();
    }

    
    public void clickYes() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(propertyYesOption));
        element.click();
    }

   
    public void clickRecalculate() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(recalculateButton));
        element.click();
    }

   
    public String InterestAmount() {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(interestAmountLabel));
        return element.getText().trim();
    }
}