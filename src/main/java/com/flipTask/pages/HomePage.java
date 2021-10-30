package com.flipTask.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipTask.conf.Utilities;

public class HomePage extends Utilities {
	
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div._2QfC02>button")
	public WebElement closeLogin;
	
	By skipLogin = By.cssSelector("div._2QfC02>button");

	@FindBy(xpath = "//div[text()='Travel']")
	public WebElement travelOpt;
	
	public void clickCloseLogin() {
		waitForElement(skipLogin,5);
		clickByAction(closeLogin);
	}
	
	public void clickTravel() {
		waitForElement(travelOpt,3);
		clickUsingJS(travelOpt);
	}
}
