package Main_project;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.annotations.ITest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import Page_Object_Model.Login_page;
import Utils.ExtentManager;

public class Base_class {
	//protected final Logger logger = LogManager.getLogger(getClass());
	public Logger logger;
	public  Browser browser;
	public  Page page;
	public  Playwright pw;
	public Properties properties;
	protected ExtentReports extent;
	protected ExtentTest test;
	@Parameters({"os","browsername"})
	@BeforeMethod(groups= {"Sanity","Regression","Master"})
	public void Setup(@Optional ("windows") String os, @Optional ("chrome") String browsername,Method method) throws Exception
	{
		//Reporting
		extent= ExtentManager.getInstance();
		test=extent.createTest(method.getName());
		FileReader fReader = new FileReader("./src//test//resources//config.properties");
		properties=new Properties();
		properties.load(fReader);
	    pw=Playwright.create();
		BrowserType bt = null;
		//System.out.println("Inside Setup");
		
	 logger=LogManager.getLogger(this.getClass());
		
		if(browsername.equalsIgnoreCase("Chrome"))
		{
			bt=pw.chromium();
		}
		else if (browsername.equalsIgnoreCase("Firefox")) 
		{
			bt=pw.firefox();
		}
		else if (browsername.equalsIgnoreCase("Safari")) 
		{
			bt=pw.webkit();
		}
		
		 browser=bt.launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(5000));
		 page=browser.newPage();
		 page.navigate(properties.getProperty("appURL"));
	}
	/*@Test
	public void login()
	{
		page.navigate("https://freelance-learn-automation.vercel.app/login");
        Login_page loginpage = new Login_page(page);
        loginpage.setEmail("admin@email.com");
        loginpage.setPassword("admin@123");
        loginpage.clickonbutton();
        PlaywrightAssertions.assertThat(page).hasTitle("Learn Automation Courses");
        loginpage.clickmenubutton();
        loginpage.clicksignoutbutton();
		
		
	}*/
	@AfterMethod(groups= {"Sanity","Regression","Master"})
	public void tearDown(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.fail(result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.pass("Test passed");
		}
		else 
		{
			test.skip("Test Skipped");
		}
		extent.flush();
		page.close();
		browser.close();
		pw.close();
	}
    }