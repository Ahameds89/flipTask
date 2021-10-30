package com.flipTask.conf;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	FileInputStream input;
	public Properties prop = new Properties();

	@BeforeSuite
	public void init() throws IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		input = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\expectedValues.properties");
		prop.load(input);
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
