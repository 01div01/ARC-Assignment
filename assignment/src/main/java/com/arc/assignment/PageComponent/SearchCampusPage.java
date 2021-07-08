package com.arc.assignment.PageComponent;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.arc.assignment.Helper.ExplicitWait;

public class SearchCampusPage {
	WebDriver driver;
	private By campusesButton = By.id("Collections");
	ArrayList campusValue;
	By loader = By.xpath("//div[@id='IndProjectList']/i");
	public SearchCampusPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnCampusesButton() {
		driver.findElement(campusesButton).click();
	}

	public void sendTextInSearchField() {
		driver.switchTo().frame("myFrame");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementById('txtSearchValue').value='Test_Name';");
		jse.executeScript("document.getElementById('btnSearch').click();");
	}

	public int getRowCount() {
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ExplicitWait.waitForElementToBeVisible(loader);
		ExplicitWait.waitForInvisibilityOfElement(loader);
		List<WebElement> rows = driver.findElements(By.xpath("//div[@id='listGrid']/div[2]/table/tbody/tr/td[3]/a"));
		campusValue =new ArrayList();
		for (int i = 0; i < rows.size(); i++) {
			String value = driver.findElement(By.xpath("(//div[@id='listGrid']/div[2]/table/tbody/tr/td[3]/a)["+(i+1)+"]"))
					.getText();
			campusValue.add(value);
			
		}
		String campusNameCreated = AddCampuses.campusName;
		System.out.println("Index of "+campusNameCreated +" is "+(campusValue.indexOf(campusNameCreated)+1));

		return rows.size();
	}

}
