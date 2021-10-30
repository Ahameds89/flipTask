package com.flipTask.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipTask.conf.Utilities;

public class TravelPage extends Utilities {
	WebDriver driver;

	public TravelPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//input[@name='tripTypeList'])[1]")
	public WebElement oneWayRdo;

	public By oneWay = By.xpath("(//input[@name='tripTypeList'])[1]");

	@FindBy(xpath = "(//div[@class='_1XFPmK'])[2]")
	public WebElement roundTripRdo;

	public By roundTrip = By.xpath("(//input[@name='tripTypeList'])[2]");

	@FindBy(name = "0-departcity")
	public WebElement fromInput;

	@FindBy(name = "0-arrivalcity")
	public WebElement toInput;

	@FindBy(name = "0-datefrom")
	public WebElement departDateInput;

	@FindBy(name = "0-dateto")
	public WebElement returnDateInput;

	@FindBy(xpath = "(//button[@class='_3CTB5G'])[2]")
	public WebElement calendarNextArrow;

	@FindBy(xpath = "(//button[contains(@class,'_2KpZ6l')])[2]")
	public WebElement addAdults;

	@FindBy(xpath = "(//div[contains(@class,'_19cAhQ ')])[1]")
	public WebElement adultsValue;

	@FindBy(xpath = "(//button[contains(@class,'_2KpZ6l')])[4]")
	public WebElement addChildren;

	@FindBy(xpath = "(//div[contains(@class,'_19cAhQ ')])[2]")
	public WebElement childrenValue;

	@FindBy(xpath = "//button[contains(@class,'aC49_F')]")
	public WebElement done;

	@FindBy(xpath = "(//input[@name='travel-cabin-class'])[1]")
	public WebElement economyRdo;

	@FindBy(xpath = "//button[contains(@class,'_2KpZ6l')]")
	public WebElement searchBtn;

	@FindBy(xpath = "(//span[@class='switch-handle'])[1]")
	public WebElement nonStopSwitch;

	@FindBy(xpath = "(//span[contains(@class,'c-switch switch')])[1]")
	public WebElement nonStopSwitchStatus;

	@FindBy(xpath = "(//div[@class='result-col-inner'])[1]//div[@class='price-group']//span[2]")
	public List<WebElement> fromSourcePriceList;

	@FindBy(xpath = "(//div[@class='result-col-inner'])[2]//div[@class='price-group']//span[2]")
	public List<WebElement> fromDestinationPriceList;

	@FindBy(xpath = "//button[contains(@class,'c-btn u-link')]")
	public WebElement bookBtn;

	public WebElement flightRdoButton(int index) {
		return driver.findElement(
				By.xpath("((//div[@class='result-col-inner'])[1]//div[@class='radio-list-item'])[" + index + "]"));
	}

	public void clickRoundTrip() {
		waitForElement(roundTripRdo, 5);
		do {
			clickByAction(roundTripRdo);
		} while (!roundTripSelected());
	}

	public void clickDone() {
		waitForElement(done, 5);
		clickByAction(done);
	}

	public void clickSearch() {
		waitForElement(searchBtn, 5);
		clickByAction(searchBtn);
	}

	public void clickNonStop() {
		waitForElement(nonStopSwitch, 5);
		clickByAction(nonStopSwitch);
	}

	public void clickBook() {
		waitForElement(bookBtn, 5);
		clickByAction(bookBtn);
	}

	public boolean oneWaySelected() {
		waitForElement(oneWay, 5);
		return oneWayRdo.isSelected();
	}

	public boolean roundTripSelected() {
		waitForElement(roundTrip, 5);
		return driver.findElement(roundTrip).isSelected();
	}

	public boolean economySelected() {
		return economyRdo.isSelected();
	}

	public WebElement cityInList(String city) {
		return driver.findElement(By.xpath("//span[contains(text(),'" + city + "')]"));
	}

	public WebElement calendarMonth(String month, String year) {
		return driver.findElement(By.xpath("//div[text()='" + month + " " + year + "']"));
	}

	public WebElement selectDate(String day, String month, String year) {
		return driver.findElement(
				By.xpath("//div[text()='" + month + " " + year + "']/ancestor::table//button[text()='" + day + "']"));
	}

	public void selectCalendarDate(String date) {
		String[] dateField = date.split("/");
		waitForElement(calendarNextArrow, 5);
		while (!checkCurrentCalendar(dateField[1],dateField[2])) {
			calendarNextArrow.click();
		}
		selectDate(dateField[0], dateField[1], dateField[2]).click();
	}
	
	boolean checkCurrentCalendar(String month,String year) {
		 boolean status;
		try {
			status = calendarMonth(month,year).isDisplayed();
		}catch(Exception e) {
			status=false;
		}
		return status;
	}

	public void enterFromCity(String city) throws InterruptedException {
		fromInput.click();
		clearInput(fromInput);
		fromInput.sendKeys(city.subSequence(0, 3));
		cityInList(city).click();
	}

	public void enterToCity(String city) throws InterruptedException {
		toInput.click();
		clearInput(toInput);
		toInput.sendKeys(city.subSequence(0, 3));
		Thread.sleep(500);
		cityInList(city).click();
	}

	public void addAdult() {
		addAdults.click();
	}

	public void addChild() {
		addChildren.click();
	}

	public boolean isNonStopSwitchoff() {
		return nonStopSwitchStatus.getAttribute("class").equals("c-switch switch-off");
	}

	public void getFromSourcePriceList() {
		System.out.println("--------Price list from source point-------");
		fromSourcePriceList.stream().map(element -> element.getText())
				.forEach(price -> System.out.println("Rs. " + price));
	}

	public void getFromDestinationPriceList() {
		System.out.println("--------Price list from destination point-------");
		fromDestinationPriceList.stream().map(element -> element.getText())
				.forEach(price -> System.out.println("Rs. " + price));
	}
	public void selectSourceFlight() {
		scrollToElementAndClick(flightRdoButton(fromSourcePriceList.size() - 1));
	}

	public void selectDestinationFlight() {
		scrollToElementAndClick(flightRdoButton(fromDestinationPriceList.size() - 1));
	}
}
