package com.pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.testng.Assert;

import com.setup.ExcelUtility;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Demo {

	/* @EMICalculator @ExcelData
  Scenario: EMI Calculator using Excel data
    Given I am on the Home Page
    When I perform EMI calculation using Excel data
    Then I should see calculated EMI and Interest details*/
	
	
	/*
	 * 	@When("I perform EMI calculation using Excel data")
	public void user_calculates_emi_for_all_data_from_excel() throws IOException, InterruptedException {

		String excelPath = "C:\\Users\\rrahulre\\OneDrive - Capgemini\\Desktop\\YemiReddy Rahul Reddy\\src\\test\\resources\\Exceldata\\Emidata.xlsx";
		List<String[]> dataList = ExcelUtility.readEMIData(excelPath, "Sheet1");

		homePage.clickEmiCalculator();

	
		String mainWindow = driver.getWindowHandle();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if (driver.getTitle().contains("EMI Calculator")) {
				System.out.println("Switched to EMI Calculator tab");
				break;
			}
		}

		for (int i = 0; i < dataList.size(); i++) {
			String loanAmount = dataList.get(i)[0];
			String interestRate = dataList.get(i)[1];

			System.out.println("Row " + (i + 1) + " | Loan: " + loanAmount + " | Interest: " + interestRate);

			emiCalculatorPage.clearLoanAmount();
			emiCalculatorPage.clearInterestRate();
			emiCalculatorPage.enterLoanAmount(loanAmount);
			emiCalculatorPage.enterInterestRate(interestRate);
			emiCalculatorPage.clickYes();
			Thread.sleep(Duration.ofSeconds(10));
			emiCalculatorPage.clickNo();
			Thread.sleep(Duration.ofSeconds(5));
			emiCalculatorPage.clickRecalculate();
			Thread.sleep(Duration.ofSeconds(10));

			String emiValue = emiCalculatorPage.InterestAmount();

			Assert.assertNotNull(emiValue, "EMI value should not be null!");
			Assert.assertFalse(emiValue.isEmpty(), "EMI value is empty!");
			Assert.assertTrue(emiValue.matches("[0-9.,]+"), "EMI value is not numeric: " + emiValue);

			System.out.println("EMI/Interest Calculated: " + emiValue);

			ExcelUtility.writeEMIResult(excelPath, "Sheet1", i + 1, emiValue);
		}

		driver.switchTo().window(mainWindow);
		System.out.println("Switched back to main window.");
	}

	@Then("I should see calculated EMI and Interest details")
	public void verify_emi_calculation_result() {
		System.out.println(" EMI and Interest details validated and written to Excel successfully!");
	}

	 */
}
