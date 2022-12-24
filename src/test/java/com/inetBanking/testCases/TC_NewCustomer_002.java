package com.inetBanking.testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.pageObjects.NewCustomerPOM;

public class TC_NewCustomer_002 extends BaseClass {
	
	@Test
	public void testNewCust() throws InterruptedException, IOException
	{
		
		LoginPage lp = new LoginPage(driver);
		NewCustomerPOM nc=new NewCustomerPOM(driver);
		
		log.info("Entered Username");
		lp.setUname(uid);
		log.info("Entered Password");
		lp.setPwd(pwd);
		log.info("Clicked Submit");
		lp.clickSubmit();
		Thread.sleep(3000);
		
		log.info("Adding New Customer name");
		nc.addNewCutomer();
		Thread.sleep(5000);
		nc.EnterCustName("Girish");
		nc.GenderName("male");
		nc.Enterdob("01","12","2020");
		Thread.sleep(3000);
		nc.EnterAddress("Kanchveer nagar");
		nc.EnterCity("Belgaum");
		nc.EnterState("karnataka");
		nc.EnterPin("591124");
		nc.EnterTelNo(789257350);
		nc.EnterEmail(RandomString()+"@gmail.com");
		nc.Enterpwd(pwd);
		nc.ClickSubmit();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Customer Registered Successfully!!!"))
		{
			Assert.assertTrue(true);
			
		}
		else
		{
			captureScreen(driver,"testNewCust");
			Assert.assertTrue(false);
		}
		lp.clickLogout();
	}
}
