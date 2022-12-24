package com.inetBanking.testCases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;



public class TC_Login_TDD_001 extends BaseClass{
	
	@Test(dataProvider = "tddlogindata")
	public void loginTest(String usr, String pwd) throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		
		log.info("Username given");
		lp.setUname(usr);
		log.info("Username given");
		lp.setPwd(pwd);
		log.info("Clicked on submit");
		lp.clickSubmit();
		Thread.sleep(3000);
		
		if(isAlertOccured())
		{	
			log.warn("Login failed");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		else
		{
			log.info("Login pass");
			Assert.assertTrue(true);
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			
		}
	}
	
	public boolean isAlertOccured() // method to check if alert pop up is present or not
	{
		try 
		{
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e)
		{
			return false;
		}
		
		
		
	}
	
	@DataProvider(name="tddlogindata")
	public String[][] getLoginData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetBanking/testData/testdata.xlsx";
		int rowcount=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String login[][]= new String[rowcount][colcount];
		
		for(int i=1;i<=rowcount;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				login[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return login;
	}

}
