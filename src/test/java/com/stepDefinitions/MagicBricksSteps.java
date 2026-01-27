package com.stepDefinitions;

import com.pages.*;


import com.setup.DriverFactory;
import com.setup.ExcelUtility;
import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.*;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static org.junit.Assert.*;

public class MagicBricksSteps {

	private WebDriver driver = DriverFactory.getDriver();
	private HomePage homePage = new HomePage(driver);
	private SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
	private EMICalculator emiCalculatorPage = new EMICalculator(driver);
	private PropertiesListingPage propertiesListingPage = new PropertiesListingPage(driver);
	private InteriorBudgetEstimatorPage interiorEstimatorPage = new InteriorBudgetEstimatorPage(driver);
	// ---------------------- Background ----------------------

	@Given("User navigates to {string}")
	public void user_navigates_to(String url) {
		DriverFactory.getDriver().get(url);
	}

	@Given("User verifies that the MagicBricks homepage is loaded successfully")
	public void user_verifies_that_the_magic_bricks_homepage_is_loaded_successfully() {
		String title = driver.getTitle();
		assertTrue("Home page is not loaded!", title.contains("MagicBricks"));
	}

//-------------------------------------- Scenario-1 ---------------------------------------------------------------

	/*
	 * Created by: YEMIREDDY RAHUL REDDY 
	 * Reviewed by:KOTTE RAVINDER 
	 * Motive : To ensure the search functionality works and extract structural insights from
	 * the search results page.
	 */

	@And("User checks the homepage")
	public void user_verifies_that_the_magic_bricks_homepage_is_loaded_successfully1() {
		String title = driver.getTitle();
		assertTrue("Home page not loaded properly!", title.contains("MagicBricks"));
		System.out.println("Home Page loaded successfully with title: " + title);
	}

	@When("User clicks on the Search button")
	public void user_clicks_on_the_search_button() {
		homePage.clickSearchButton();
		System.out.println("Clicked on Search button");
	}

	@Then("Search results page should be displayed")
	public void search_results_page_should_be_displayed() {
		searchResultsPage = new SearchResultsPage(driver);
		String pageTitle = driver.getTitle();
		System.out.println("Search Results Page displayed: " + pageTitle);
	}

	@Then("User prints all unique HTML tag names on the search results page")
	public void user_prints_all_unique_html_tag_names_on_the_search_results_page() throws InterruptedException {
		
		//searchResultsPage = new SearchResultsPage(driver);
		

		int totalTags = searchResultsPage.getAllTagNames();

		if (totalTags==0) {
			System.out.println("No anchor tags found on the search results page!");
		} else {
			System.out.println(" Found " + totalTags + " anchor tags:");
//			for (String tag : tagNames) {
//				System.out.println(" - <" + tag + ">");
//			}
		}
	}

//-------------------------------------- Scenario-2 ---------------------------------------------------------------

	/*
	 * Created by: YEMIREDDY RAHUL REDDY 
	 * Reviewed by:KOTTE RAVINDER 
	 * Motive : To validate EMI calculations using dynamic input from an external Excel source.
	 */

	@When("I enter loan amount {string} and interest rate {string}")
	public void enter_loan_and_interest(String loanAmount, String interestRate) throws InterruptedException {
	    homePage.clickEmiCalculator();

	    String mainWindow = driver.getWindowHandle();
	    for (String handle : driver.getWindowHandles()) {
	        driver.switchTo().window(handle);
	        if (driver.getTitle().contains("EMI Calculator")) {
	            System.out.println("Switched to EMI Calculator tab");
	            break;
	        }
	    }

	    emiCalculatorPage.clearLoanAmount();
	    emiCalculatorPage.clearInterestRate();
	    emiCalculatorPage.enterLoanAmount(loanAmount);
	    emiCalculatorPage.enterInterestRate(interestRate);
//	    emiCalculatorPage.clickYes();
//	    Thread.sleep(Duration.ofSeconds(10));
	    emiCalculatorPage.clickNo();
	    Thread.sleep(Duration.ofSeconds(5));
	    emiCalculatorPage.clickRecalculate();
	    Thread.sleep(Duration.ofSeconds(10));

	    String emiValue = emiCalculatorPage.InterestAmount();

	    Assert.assertNotNull(emiValue, "EMI value should not be null!");
	    Assert.assertFalse(emiValue.isEmpty(), "EMI value is empty!");
	    Assert.assertTrue(emiValue.matches("[0-9.,]+"), "EMI value is not numeric: " + emiValue);

	    System.out.println("EMI/Interest Calculated: " + emiValue);

	    driver.switchTo().window(mainWindow);
	    System.out.println("Switched back to main window.");
	}
//-------------------------------------- Scenario-3 ---------------------------------------------------------------

	/*
	 * Created by: YEMIREDDY RAHUL REDDY Reviewed by:KOTTE RAVINDER Motive : To
	 * confirm property listings are accessible and sortable by price for better
	 * user experience.
	 */

	@Given("I am on the Home Page")
	public void i_am_on_the_home_page() {
		homePage = new HomePage(DriverFactory.getDriver());
		System.out.println("On Home Page");
	}

	@When("I click on the {string} tab")
	public void i_click_on_the_tab(String tabName) throws InterruptedException {
		homePage.clickSeeAllPropertiesTab();
		System.out.println("Clicked '" + tabName + "' tab");
	}

	@Then("I should be redirected to the Property Listings page")
	public void i_should_be_redirected_to_the_property_listings_page() {
		propertiesListingPage = new PropertiesListingPage(DriverFactory.getDriver());
		propertiesListingPage.switchToListingTab();
		System.out.println("Redirected to Property Listings Page");
	}

	@When("I select {string} from the sort by dropdown")
	public void i_select_from_the_sort_by_dropdown(String sortOption) throws InterruptedException {
		propertiesListingPage.clickSortLowToHigh();
		System.out.println("Sorted properties by 'Price - Low to High'");
	}

//-------------------------------------- Scenario-4 ---------------------------------------------------------------

	/*
	 * Created by: YEMIREDDY RAHUL REDDY 
	 * Reviewed by:KOTTE RAVINDER 
	 * Motive : To verify form validation by testing submission with missing mandatory inputs.
	 */

	@When("I click on \"Interior Budget Estimator\"")
	public void i_click_on_interior_budget_estimator() throws InterruptedException {
		// Click the menu on Home Page
		homePage.clickInteriorBudgetEstimator();
		Thread.sleep(2000);

		// Switch to newly opened tab
		Set<String> allWindows = driver.getWindowHandles();
		List<String> windowList = new ArrayList<>(allWindows);
		driver.switchTo().window(windowList.get(windowList.size() - 1));

		System.out.println("Switched to Interior Budget Estimator tab: " + driver.getTitle());
	}

	@And("I submit the estimator form without filling any details")
	public void i_submit_the_estimator_form_without_filling_any_details() {
		interiorEstimatorPage.clickSubmitWithoutFilling();
	}

	@Then("I should see appropriate validation error messages")
	public void i_should_see_appropriate_validation_error_messages() {
		List<String> messages = interiorEstimatorPage.getErrorMessages();
		for (String msg : messages) {
			System.out.println("Validation message: " + msg);
		}
		assertFalse("No validation messages found!", messages.isEmpty());
		

	}

}