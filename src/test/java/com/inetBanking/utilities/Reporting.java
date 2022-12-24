package com.inetBanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{	
		//configure the htmlreporter with dynamic report-name, specify the output folder where the report is saved and load xml (extent-config)
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.MM.SS").format(new Date());//time stamp
		String repName="Test Report-"+timestamp+".html";
		htmlreporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);//specify the location of report
		htmlreporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");// load the extent configuration file
		
		//configure the extent
		extent= new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user", "Girish");
		
		//configure the htmlreporter
		htmlreporter.config().setDocumentTitle("InetBanking Test Project");
		htmlreporter.config().setReportName("Functional Test Automation report");
		htmlreporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlreporter.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr)
	{	
		//logger concept is same for onTestSuccess onTestSkipped and onTestFailure
		//logger comes in picture,First createTest then log the status, create markuphelper with green color. 
		logger = extent.createTest(tr.getName());//Create a new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr)
	{
		//logger concept is same for onTestSuccess onTestSkipped and onTestFailure
		logger=extent.createTest(tr.getName());
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
		
		//get screenshot path where the screenshot is saved
		String screenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotPath);
		
		if(f.exists())
		{
			try 
			{   //new logger for fail where we are 
				logger.fail("Screenshot is below:"+logger.addScreenCaptureFromPath(screenshotPath));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		//logger concept is same for onTestSuccess onTestSkipped and onTestFailure
		logger=extent.createTest(tr.getName()); //create a new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext tr)
	{
		extent.flush();
	}
}
