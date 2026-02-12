package com.parallel.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParallelTest {

	WebDriver driver;

	@Test(priority = 1)
	public void testChrome() throws InterruptedException {
		System.out.println("The thread ID for Chrome is "+ Thread.currentThread().getId());
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.bstackdemo.com/");
		driver.manage().window().maximize();
		Assert.assertEquals(driver.getTitle(), "StackDemo");
	}

	@Test(priority = 2)
	public void testEdge() throws InterruptedException {
		System.out.println("The thread ID for Edge is "+ Thread.currentThread().getId());
		//WebDriverManager.edgedriver().clearDriverCache().setup();
		//WebDriverManager.edgedriver().browserVersion("144.0.3719.104").setup();
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.get("https://www.bstackdemo.com/");
		driver.manage().window().maximize();
		Assert.assertEquals(driver.getTitle(), "StackDemo"); 
	}

	@AfterMethod
	public void close() {
		if (driver != null) {
			driver.quit();
		}
	} 
}
