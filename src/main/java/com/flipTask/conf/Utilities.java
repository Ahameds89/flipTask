package com.flipTask.conf;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	WebDriver driver;
	JavascriptExecutor js;
	Actions actions;

	public Utilities(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
		actions = new Actions(driver);
	}

	public void clearInput(WebElement element) {
		clickUsingJS(element);
		element.sendKeys(Keys.CONTROL + "A");
		element.sendKeys(Keys.DELETE);
	}

	public void scrollToElement(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		clickUsingJS(element);
	}

	public void clickByAction(WebElement element) {
		actions.moveToElement(element).click().build().perform();
	}

	public void waitForElement(By by, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitForElement(WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForElementToBeClickable(WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void clickUsingJS(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

}
