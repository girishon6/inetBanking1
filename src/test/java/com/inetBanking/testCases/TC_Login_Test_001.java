package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetBanking.pageObjects.LoginPage;



public class TC_Login_Test_001 extends BaseClass {
	
	@Test
	public void testLogin() throws IOException
	{
		
		log.info("opened baseURL");
		
		LoginPage lp=new LoginPage(driver);
		log.info("Object created for loginpage");
		
		lp.setUname(uid);
		log.info("Enter username");
		
		lp.setPwd(pwd);
		log.info("Enter password");
		
		lp.clickSubmit();
		log.info("Clicked on submit");
		
		if(driver.getTitle().equals(homepagetitle))
		{
			Assert.assertTrue(true);
			log.info("Title matched as expected");
		}
		else
		{
			captureScreen(driver, "testLogin");
			Assert.assertTrue(false);
			log.info("Title did not matched");
		}
	}

}
