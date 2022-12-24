package com.inetBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}
	
	@FindBy(name="uid")
	WebElement username;
	 
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(name="btnLogin")
	WebElement login;
	
	@FindBy(name="btnReset")
	WebElement reset;
	
	@FindBy(linkText = "Log out")
	WebElement logout;
	
	public void setUname(String uname)
	{
		username.sendKeys(uname);
	}
	
	public void setPwd(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	public void clickSubmit()
	{
		login.click();
	}
	
	public void clickLogout()
	{
		logout.click();
	}

}
