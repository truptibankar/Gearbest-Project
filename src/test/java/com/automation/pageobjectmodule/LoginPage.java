package com.automation.pageobjectmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {

	WebDriver driver;
	
	By signin_button = By.xpath("//span[@class='headUser_linkLogin']");
	By register_button= By.xpath("//a[@class='headUser_btnReg']");
	By register_tab = By.xpath("//a[@class = 'authTab_link active']");
	By close_window_poppup = By.xpath("//span[@class='layui-layer-setwin']");
	By email = By.id("email");
	By password = By.id("password");
	By retypepassword = By.id("password2");
	By agreebutton = By.id("js-regAgree");
	By submitbutton = By.id("js-btnSubmit");
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void closepopupofhomepage() {
		driver.findElement(close_window_poppup).click();
	}
	 
	public void mouseovertosignin() {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(signin_button)).perform();
		
	}
	public void clickedonregisterbutton() {
		driver.findElement(register_button).click();
	}
	
	public void clickonregisteredtab() {
		driver.findElement(register_tab).click();
	}
	
	
	public void enteredemailId(String emailId) {
		WebElement emailid = driver.findElement(email);
		emailid.sendKeys(emailId);
	}
	
	public void enterdpwd(String pwd) {
		WebElement passwordbox = driver.findElement(password);
		passwordbox.sendKeys(pwd);
	}
	
	public void enterdretypepwd(String repwd) {
		WebElement repasswordbox = driver.findElement(retypepassword);
		repasswordbox.sendKeys(repwd);
	}
	
	public void clickonsubmit()
	{
		driver.findElement(submitbutton).click();
	}

     
	
	
	
}
