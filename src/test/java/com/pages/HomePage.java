package com.pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
	@FindBy(id="tabRENT")
	private WebElement rent;

    @FindBy(id = "keyword")
   private WebElement cityInput;
  
   @FindBy(xpath="//div[@id='owner-properties']//a[@class='mb-home__section__title--anchor-see-all push-right'][normalize-space()='See all Properties']")
  private WebElement seeAllPropertiesTab;
    
    @FindBy(xpath="//div[@aria-label='3 / 6']//span[@class='mb-home__tool-advice__card--link'][normalize-space()='View now']")
    private WebElement interiorBudgetEstimatorLink;

   
    @FindBy(id = "propertyType")
    private WebElement propertyTypeDropdown;
    
    @FindBy(xpath="//div[text()='EMI Calculator']")
    private WebElement emiCalculator;
    
    @FindBy(xpath = "//div[@class='mb-search__btn']")
    private WebElement searchButton;


    @FindBy(xpath = "//a[text()='MB Advice']")
    private WebElement mbAdviceMenu;

    public HomePage(WebDriver driver) {
        super(driver);
    }

   public void clickRent() {
   	click(rent);
    }
   public void enterCity(String city) {
       type(cityInput, city);
   }
   
   public void clickInteriorBudgetEstimator() {
   	wait.until(ExpectedConditions.elementToBeClickable(interiorBudgetEstimatorLink)).click();
   }
   
    public void selectPropertyType(String type) {
        type(propertyTypeDropdown, type);
    }

    public void clickSearchButton() {
        click(searchButton);
    }
     
   public void clickSeeAllPropertiesTab() throws InterruptedException {
	   Thread.sleep(2000);
   	click(seeAllPropertiesTab);
   }
    
    public void clickEmiCalculator() throws InterruptedException {
    	Thread.sleep(2000);
        click(emiCalculator);

    }

    public void clickMBAdviceMenu() {
        click(mbAdviceMenu);
    }
}