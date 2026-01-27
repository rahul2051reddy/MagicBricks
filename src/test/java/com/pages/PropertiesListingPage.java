package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PropertiesListingPage extends BasePage {

    private WebDriverWait wait;

    @FindBy(xpath = "//div[@class='mb-srp__tabs__sortby--title']")
    private WebElement sortByDropdown;

    @FindBy(xpath = "//li[text()='Price - Low to High']")
    private WebElement lowToHighOption;

    public PropertiesListingPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void switchToListingTab() {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }

    public void clickSortLowToHigh() {
        switchToListingTab();

        
        PageFactory.initElements(driver, this);

        try {
            wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.elementToBeClickable(sortByDropdown)
            )).click();

            wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.elementToBeClickable(lowToHighOption)
            )).click();

            System.out.println("Clicked 'Sort by' dropdown and selected 'Price - Low to High'");

        } catch (Exception e) {
            System.out.println("Exception occurred while clicking sort option: " + e.getMessage());
        }
    }
}