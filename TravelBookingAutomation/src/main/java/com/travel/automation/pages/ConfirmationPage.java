package com.travel.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

    private WebDriver driver;

    // Locators
    private By confirmationId = By.xpath("//table/tbody/tr[1]/td[2]");
    private By status = By.xpath("//table/tbody/tr[2]/td[2]");
    private By amountCharged = By.xpath("//table/tbody/tr[3]/td[2]");
    private By cardNumber = By.xpath("//table/tbody/tr[4]/td[2]");
    private By expiration = By.xpath("//table/tbody/tr[5]/td[2]");

    // Constructor
    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Getters
    public String getBookingId() {
        return driver.findElement(confirmationId).getText();
    }

    public String getStatus() {
        return driver.findElement(status).getText();
    }

    public String getAmountCharged() {
        return driver.findElement(amountCharged).getText();
    }

    public String getCardNumber() {
        return driver.findElement(cardNumber).getText();
    }

    public String getExpirationDate() {
        return driver.findElement(expiration).getText();
    }
}
