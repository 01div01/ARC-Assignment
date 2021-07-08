package com.arc.assignment.PageComponent;

import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;
	private By userName = By.id("UserID");
	private By password = By.id("Password");
	private By loginButton = By.id("btnLogin");
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	public void enterUserName(String userNameText) {
		driver.findElement(userName).sendKeys(userNameText);
	}
	public void enterPassword(String passwordText)
	{
		driver.findElement(password).sendKeys(passwordText);
	}
	public void clickOnLoginButton()
	{
		driver.findElement(loginButton).click();
	}
}
