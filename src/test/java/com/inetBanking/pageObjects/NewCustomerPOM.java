package com.inetBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class NewCustomerPOM {

	WebDriver rdriver;
	
	 public NewCustomerPOM(WebDriver ldriver)
	{
		rdriver=ldriver;
		PageFactory.initElements(rdriver,this);
	}
	
	@FindBy(linkText = "New Customer")
	WebElement newCust;
	
	@FindBy(xpath="//input[@name='name']")
	WebElement custName;
	
	@FindBy(how=How.NAME, using="rad1")
	WebElement Gender;
	
	@FindBy(how=How.ID_OR_NAME, using="dob")
	WebElement dob;
	
	@FindBy(how=How.NAME, using="addr") 
	WebElement address;
	
	@FindBy(name="city")
	WebElement city;
	
	@FindBy(name="state") 
	WebElement state;
	
	@FindBy(name="pinno")
	WebElement pin;
	
	@FindBy(name="telephoneno")
	WebElement telno;
	
	@FindBy(name="emailid")
	WebElement email;
	
	@FindBy(name="password")
	WebElement pwd;
	
	@FindBy(name="sub")
	WebElement submit;
	
	@FindBy(name="res")
	WebElement reset;
	
	public void addNewCutomer()
	{
		newCust.click();
	}
	
	public void EnterCustName(String name)
	{
		custName.sendKeys(name);
	}
	
	public void GenderName(String gender)
	{
		Gender.sendKeys(gender);
	}
	
	public void Enterdob(String dd,String mm,String yyyy)
	{
		dob.sendKeys(dd);
		dob.sendKeys(mm);
		dob.sendKeys(yyyy);
	}
		
	public void EnterAddress(String addr)
	{
		address.sendKeys(addr);
	}
	
	public void EnterCity(String cityname)
	{
		city.sendKeys(cityname);
	}
	
	public void EnterState(String statename)
	{
		state.sendKeys(statename);
	}
	
	public void EnterPin(String pinno)
	{
		pin.sendKeys(pinno);
	}
	
	public void EnterTelNo(int telnumber)
	{
		telno.sendKeys(String.valueOf(telnumber));
	}
	
	public void EnterEmail(String emailid)
	{
		email.sendKeys(emailid);
	}
	
	public void Enterpwd(String pswd)
	{
		pwd.sendKeys(pswd);
	}
	
	public void ClickSubmit()
	{
		submit.click();
	}
	
	public void ClickReset()
	{
		reset.click();
	}

	
}
