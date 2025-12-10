package com.travel.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChooseFlightPage {
	
	private WebDriver driver;
	
	public ChooseFlightPage(WebDriver driver) {
		this.driver = driver;
	}

	public void selectFlightByIndex(int rowIndex) {
		By chooseBtn = By.xpath("/html/body/div[2]/table/tbody/tr["+(rowIndex+1)+"]/td[1]/input");
		driver.findElement(chooseBtn).click();
	}
	
	public void selectFirstFlight() {
		selectFlightByIndex(0);
	}
}
