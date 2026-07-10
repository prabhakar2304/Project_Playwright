package Main_project;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.assertions.PlaywrightAssertions;

import Page_Object_Model.Registration_page;

public class Registration extends Base_class
{
	@Test(groups={"Regression","Master"})
	public void registration()
	{    
		String hobbie[]= {"Playing","Swimming"};
		logger.info("*****Starting Registration Test case*****");
		try 
		{
			Registration_page regpage=new Registration_page(page);
			
			regpage.signUpLink();
			logger.info("*****Click on sign up for Registration Link*****");
			regpage.enterName("Prabhakar");
			logger.info("*****Entering the Name Details*****");
			regpage.enterEmail("testuser_" + System.currentTimeMillis() + "@gmail.com");
			logger.info("*****Entering the Email Details*****");
			regpage.enterPass("Password@123");
			logger.info("*****Entering the Password Details*****");
			regpage.selectCheckboxByText("JavaScript");
			logger.info("*****Selecting the CheckBox Option*****");
			regpage.selectCheckboxByText("JMeter");
			logger.info("*****Selecting the other CheckBox Option*****");
			regpage.selectGender("Male");
			logger.info("*****Selecting the RadioButton Option*****");
			regpage.selectCountryByValue("Goa");
			logger.info("*****Selecting the Drop Down Option*****");
			regpage.selectMulitpleHobbies(hobbie);
			logger.info("*****Selecting the Mutltiple Drop Down Option*****");
			regpage.signUpButton();
			logger.info("*****Clicking on SignupButton*****");
			PlaywrightAssertions.assertThat(page.getByText("Signup successfully, Please login!")).containsText("Please login!");
		} 
		
		catch (Exception e) 
		{
			logger.error("Test Case Failed....");
			logger.debug("Debug logs....");
			Assert.fail(e.getMessage());
		}
		logger.info("*****Ending of Registration Test case*****");
	}

}
