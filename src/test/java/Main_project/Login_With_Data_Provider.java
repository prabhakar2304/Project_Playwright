package Main_project;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.assertions.PlaywrightAssertions;

import Page_Object_Model.Login_page;
import Utils.Data_Providers;

public class Login_With_Data_Provider extends Base_class {
	
	@Test(dataProvider="LoginData",dataProviderClass=Data_Providers.class)
	public void login_DDT(String username,String password)
	{
		logger.info("*****Starting Login_Logout Test case*****");
     
		try {
			
			
	         Login_page loginpage = new Login_page(page);
	         
	         loginpage.setEmail(username);
	         
	         logger.info("*****Entering the Email Details*****");
	         
	         loginpage.setPassword(password);
	         
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


