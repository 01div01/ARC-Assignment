package com.arc.assignment.PageComponent;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.arc.assignment.Helper.ExplicitWait;

public class AddCampuses {
	WebDriver driver;
	public static String campusName;
	private By campusesButton = By.id("Collections");
//	private By addCampusButton = By.xpath("//form[@action='./ProjectList.aspx']/div/section/div/div[2]/button");
//	private By addCampusButton = By.xpath("//button[@id='btnNewProject']");
	private By campusNmaeTextField = By.xpath("//input[@id='txtProjectName']");
	private By saveButton = By.xpath("//input[@id='btnSave']");
	

	public AddCampuses(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnCampusesButton() {
		driver.findElement(campusesButton).click();
	}

	public void clcikOnAddCampus() {
		driver.switchTo().frame("myFrame");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.getElementById('btnNewProject').click();");
	}

	public String addCampusName() {
		String currentwindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> i = allWindows.iterator();
		while (i.hasNext()) {
			String childwindow = i.next();
			if (!childwindow.equalsIgnoreCase(currentwindow)) {
				Random random = new Random();
				int randomTwoDigitNumber = random.nextInt(90) + 10;
				campusName = "Test_Name_" + randomTwoDigitNumber;
				driver.switchTo().window(childwindow);
				driver.findElement(campusNmaeTextField).sendKeys(campusName);
				driver.findElement(saveButton).click();
				driver.switchTo().window(currentwindow);
			} else {
				
			}
		}
		return campusName;
	}
	public String verifyCampusName()
	{
		String currentwindow = driver.getWindowHandle();
		System.out.println("Created Campus Name is : " + campusName);
		String campusText = "//summary[text()='"+campusName+"']";
//		By campusTextXpath = By.xpath(campusText);
//		ExplicitWait.waitForElementToBeVisible(campusTextXpath);
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("return document.readyState").toString().equals("complete");
		driver.switchTo().frame("myFrame");
		String actualValue=jse.executeScript("return document.querySelector(\"#angureport > app-root > div > div.container > div.arc-top_title_bar > div.pull-left > h2 > details > summary\").innerText;").toString();
		return actualValue;	
	}

}
