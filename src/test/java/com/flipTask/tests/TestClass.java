package com.flipTask.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.flipTask.conf.BaseClass;
import com.flipTask.pages.HomePage;
import com.flipTask.pages.ReviewPage;
import com.flipTask.pages.TravelPage;

public class TestClass extends BaseClass {
	public TravelPage travel;
	public HomePage home;
	public ReviewPage review;
	SoftAssert assertion = new SoftAssert();

	@BeforeClass
	public void testInit() {
		travel = new TravelPage(driver);
		home = new HomePage(driver);
		review = new ReviewPage(driver);
	}

	@Test
	@Parameters({"departDate","returnDate"})
	public void validateRoundTripBooking(String departDate,String returnDate) throws InterruptedException {
		driver.get("https://www.flipkart.com/");
		home.clickCloseLogin();
		home.clickTravel();
		travel.waitForElement(travel.oneWay, 5);
		assertion.assertTrue(travel.oneWaySelected());
		travel.clickRoundTrip();
		assertion.assertTrue(travel.roundTripSelected());
		travel.enterFromCity("Kolkata");
        travel.enterToCity("Chennai");
        travel.selectCalendarDate(departDate);
        travel.selectCalendarDate(returnDate);
        travel.addAdult();
        assertion.assertEquals(travel.adultsValue.getText(), "2");
        travel.addChild();
        assertion.assertEquals(travel.childrenValue.getText(), "1");
        assertion.assertTrue(travel.economySelected());
        travel.clickDone();
        travel.clickSearch();
        travel.waitForElement(travel.nonStopSwitch,30);
        assertion.assertTrue(travel.isNonStopSwitchoff());
        travel.clickNonStop();
        assertion.assertFalse(travel.isNonStopSwitchoff());
        Thread.sleep(10000);
        travel.getFromSourcePriceList();
        travel.getFromDestinationPriceList();
        travel.selectSourceFlight();
        travel.selectDestinationFlight();
        travel.clickBook();
        review.waitForElement(review.continueBtn, 10);
        System.out.println(driver.getTitle());
        assertion.assertEquals(driver.getTitle(),prop.getProperty("reviewPageTitle"));
        assertion.assertAll();
	}

}
