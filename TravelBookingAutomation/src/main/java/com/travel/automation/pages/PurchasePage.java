package com.travel.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PurchasePage {
	
	private WebDriver driver;
	
	 private By nameField = By.id("inputName");
	 private By addressField = By.id("address");
	 private By cityField = By.id("city");
	 private By stateField = By.id("state");
	 private By zipCodeField = By.id("zipCode");
	 private By cardTypeDropdown = By.id("cardType");
	 private By creditCardNumberField = By.id("creditCardNumber");
	 private By creditCardMonthField = By.id("creditCardMonth");
	 private By creditCardYearField = By.id("creditCardYear");
	 private By nameOnCardField = By.id("nameOnCard");
	 private By rememberMeCheckbox = By.id("rememberMe");
	 private By purchaseFlightButton = By.cssSelector("input[type='submit']");
	 
	 public PurchasePage(WebDriver driver) {
		 this.driver = driver;
		 
	 }
	    public void enterName(String name) {
	        driver.findElement(nameField).clear();
	        driver.findElement(nameField).sendKeys(name);
	    }

	    public void enterAddress(String address) {
	        driver.findElement(addressField).clear();
	        driver.findElement(addressField).sendKeys(address);
	    }

	    public void enterCity(String city) {
	        driver.findElement(cityField).clear();
	        driver.findElement(cityField).sendKeys(city);
	    }

	    public void enterState(String state) {
	        driver.findElement(stateField).clear();
	        driver.findElement(stateField).sendKeys(state);
	    }

	    public void enterZipCode(String zip) {
	        driver.findElement(zipCodeField).clear();
	        driver.findElement(zipCodeField).sendKeys(zip);
	    }

	    public void enterCardNumber(String number) {
	        driver.findElement(creditCardNumberField).clear();
	        driver.findElement(creditCardNumberField).sendKeys(number);
	    }

	    public void enterCardMonth(String month) {
	        driver.findElement(creditCardMonthField).clear();
	        driver.findElement(creditCardMonthField).sendKeys(month);
	    }

	    public void enterCardYear(String year) {
	        driver.findElement(creditCardYearField).clear();
	        driver.findElement(creditCardYearField).sendKeys(year);
	    }

	    public void enterCardName(String cardName) {
	        driver.findElement(nameOnCardField).clear();
	        driver.findElement(nameOnCardField).sendKeys(cardName);
	    }

	    public void clickPurchaseFlight() {
	        driver.findElement(purchaseFlightButton).click();
	    }
	    
	    public void fillAndPurchase(
	            String name,
	            String address,
	            String city,
	            String state,
	            String zip,
	            String cardNumber,
	            String month,
	            String year,
	            String cardName) {

	        enterName(name);
	        enterAddress(address);
	        enterCity(city);
	        enterState(state);
	        enterZipCode(zip);
	        enterCardNumber(cardNumber);
	        enterCardMonth(month);
	        enterCardYear(year);
	        enterCardName(cardName);

	        clickPurchaseFlight();
	    }

	

}
