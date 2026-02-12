package com.parallel.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParallelTestWithDataProviders {

	public WebDriver localDriver;

	@DataProvider(name = "Parallel Execution With DataProvider", parallel = true)
	public Object[][] getData() {
		return new Object[][] {
			{"user1", "pass1"},
			{"user2", "pass2"},
			{"user3", "pass3"}
		};
	}

	@BeforeMethod
	public void setup() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		localDriver = new ChromeDriver();
		DriverManager.setDriver(localDriver);
		DriverManager.getDriver().get("https://the-internet.herokuapp.com/login");
		DriverManager.getDriver().manage().window().maximize();
		Thread.sleep(3000);
	}

	@Test(dataProvider = "Parallel Execution With DataProvider")
	public void testLogin(String username, String password) {
		DriverManager.getDriver().findElement(By.id("username")).sendKeys(username);
		DriverManager.getDriver().findElement(By.id("password")).sendKeys(password);
		DriverManager.getDriver().findElement(By.cssSelector("button[type='submit']")).click();
	}

	@AfterMethod
	public void tearDown() {
		DriverManager.quitDriver();
	}

}
