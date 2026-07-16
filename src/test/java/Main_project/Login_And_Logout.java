package Main_project;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import Page_Object_Model.Login_page;



public class Login_And_Logout extends Base_class{

		@Test(groups= {"Sanity","Master"})
		public void login()
		{
			logger.info("*****Starting Login_Logout Test case*****");
			/*Browser browser=Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
			Page page=browser.newPage(); 
			page.navigate("https://freelance-learn-automation.vercel.app/login");*/
			
		try {
			
		
         Login_page loginpage = new Login_page(page);
         
         loginpage.setEmail(prop.getProperty("email"));
         
         logger.info("*****Entering the Email Details*****");
         
         loginpage.setPassword(prop.getProperty("password"));
         
         logger.info("*****Entering the Password Details*****");
         
         loginpage.clickonbutton();
         
         logger.info("*****Clicking on Signin button*****");
       
         PlaywrightAssertions.assertThat(page).hasTitle("Learn Automation Courses");
         
         logger.info("*****Checking the assertions*****");
        
         loginpage.clickmenubutton();
         
         logger.info("*****Clicking on menu button*****");
        
         loginpage.clicksignoutbutton();
        
         logger.info("*****Clicking on Signout button*****"); 
		}
		catch (Exception e) 
		{
			logger.error("Test Case Failed....");
			logger.debug("Debug logs....");
			Assert.fail(e.getMessage());
		}
		logger.info("*****Ending of Login_Logout Test case*****");
	}

}

