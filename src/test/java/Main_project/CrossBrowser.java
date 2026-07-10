package Main_project;

import java.awt.print.Pageable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class CrossBrowser {
	
	Browser browser;
	Page page;
	Playwright  pw;
	@Parameters({"browsername"})
	@BeforeMethod
	public void Setup(@Optional("Chrome") String browsername)
	{
	    pw=Playwright.create();
		BrowserType bt = null;
		
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
		
		 browser=bt.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
		 page=browser.newPage();
		
	}
	
	@Test
	public void login()
	{
	 page.navigate("https://freelance-learn-automation.vercel.app/login");
	 String page_title=page.title();
		
		System.out.println(page_title);
		
		
	}
	@AfterMethod
	public void tearDown()
	{
		page.close();
		browser.close();
		pw.close();
	}

}
