package com.travel.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.travel.automation.base.BaseTest;
import com.travel.automation.pages.HomePage;
import com.travel.automation.pages.ChooseFlightPage;
import com.travel.automation.pages.PurchasePage;
import com.travel.automation.pages.ConfirmationPage;

public class FlightBookingTest extends BaseTest {

    @Test
    public void bookAFlightTest() {

        // Load homepage
        driver.get("https://blazedemo.com");

        // Home Page actions
        HomePage home = new HomePage(driver);
        home.selectDepartureCity("Boston");
        home.selectDestinationCity("London");
        home.clickFindFlights();

        // Choose Flight Page actions
        ChooseFlightPage choose = new ChooseFlightPage(driver);
        choose.selectFirstFlight();

        // Purchase Page actions
        PurchasePage purchase = new PurchasePage(driver);
        purchase.fillAndPurchase(
                "John Doe",
                "123 Main St",
                "New York",
                "NY",
                "10001",
                "4111111111111111",
                "12",
                "2027",
                "John Doe"
        );

        // Confirmation Page actions
        ConfirmationPage confirm = new ConfirmationPage(driver);

        String bookingId = confirm.getBookingId();
        String status = confirm.getStatus();

        // Assertions
        Assert.assertNotNull(bookingId, "Booking ID should not be null");
        Assert.assertTrue(status.contains("Pending"), "Status should contain 'Pending'");
    }
}
