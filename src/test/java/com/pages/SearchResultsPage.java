package com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class SearchResultsPage extends BasePage {
	WebDriver driver;
	WebDriverWait wait;

	public SearchResultsPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public int getAllTagNames() throws InterruptedException {
		//Set<String> tagSet = new HashSet<>();
//		try {
//			
			List<WebElement> allElements = driver.findElements(By.tagName("a"));
			Thread.sleep(Duration.ofSeconds(20));
//			for (WebElement element : allElements) {
//				tagSet.add(element.getTagName());
//			}
//		} catch (Exception e) {
//			System.out.println("Error while fetching tag names: " + e.getMessage());
//		}
		return allElements.size();
	}
}
