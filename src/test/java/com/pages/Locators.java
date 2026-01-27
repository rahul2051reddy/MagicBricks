package com.pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Locators {

	WebDriver driver;
	public void setup() {
		driver=new ChromeDriver();
		
		driver.get("https://www.magicbricks.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait;
		List<WebElement> list1=driver.findElements(By.tagName("a"));
		System.out.println(list1.size());
		String handle=driver.getWindowHandle();
		
//		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@onclick=\"fireDynamicPropSeeAllGTM(event,'Exclusive Owner see all','exclusiveOwner');seeAppPropertiesOpenUrl('exclusiveOwner');\"]"))).click();
//		    
		Wait<WebDriver> fluent=new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
		
		fluent.until(driver -> driver.findElement(By.xpath("//a[@oncick=\"fireDynamicPropSeeAllGTM(event,'Exclusive Owner see all','exclusiveOwner');seeAppPropertiesOpenUrl('exclusiveOwner');\"]"))).click();		
		
		
		
	    Set<String> a=driver.getWindowHandles();
		for(String h:a) {
			if(!h.equals(handle)) {
				driver.switchTo().window(h);
			}
		}
		List<WebElement> list=driver.findElements(By.tagName("a"));
		System.out.println(list.size());
		driver.switchTo().window(handle);
	}
	public static void main(String args[]) {
		Locators l=new Locators();
		l.setup();
	}
}