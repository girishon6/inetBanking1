package com.inetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig rc=new ReadConfig();
	
	public String baseurl=rc.getApplication();
	public String uid=rc.getUsername();
	public String pwd=rc.getPassword();
	public static WebDriver driver;
	public String homepagetitle="Guru99 Bank Manager HomePage";
	public static Logger log;
	
	@Parameters("browsers")
	@BeforeClass
	public void setup(String br)
	{
		log=Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+rc.getChromepath());
			driver=new ChromeDriver();
		}
		else if(br.equals("msedge"))
		{
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+rc.getMsEdgepath());
			driver=new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseurl);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver,String tname) throws IOException
	{
	TakesScreenshot ts= (TakesScreenshot) driver;
	File source=ts.getScreenshotAs(OutputType.FILE);
	File target=new File(System.getProperty("user.dir")+"/screenshots/"+tname+".png");
	FileUtils.copyFile(source, target);
	System.out.println("Screenshot taken");
	}
	
	public String RandomString()
	{
		String random=RandomStringUtils.randomAlphabetic(8);
		return random;
	}
}
