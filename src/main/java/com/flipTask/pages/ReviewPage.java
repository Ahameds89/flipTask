package com.flipTask.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipTask.conf.Utilities;

public class ReviewPage extends Utilities {
	
	WebDriver driver;

	public ReviewPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//button[contains(@class,'_2KpZ6l')]")
	public WebElement continueBtn;
}
