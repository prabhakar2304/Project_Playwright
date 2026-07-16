package Main_project;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import Page_Object_Model.Adding_category;

public class Addingcategory extends Base_class
{ 
	public String categorynameString="Selenium";
	public String updateString="SeleniumwithJava";
	@Test(groups= {"Sanity","Regression","Master"})
	public void Addingcategory()
	{
		try 
		{    	
		Adding_category addcategory=new Adding_category(page);
		addcategory.setEmail(prop.getProperty("email"));
		addcategory.setPassword(prop.getProperty("password"));
		addcategory.clickonbutton();
		addcategory.clickmanageoption();
		Page newpage=brwsrContext.waitForPage(()-> addcategory.clickmanagecategory());
		Adding_category manageCategoryPage = new Adding_category(newpage);
		newpage.onDialog(popup->{
			String msg=popup.message();
			if(msg.equalsIgnoreCase("Enter a Category Name"))  {
			popup.accept(categorynameString);	
			}
			else if(msg.equalsIgnoreCase("Update the category")) {
			popup.accept(updateString);	
			}
			else{popup.dismiss();	
			}
		});
		manageCategoryPage.clickaddnewcategory();	
		manageCategoryPage.clickonupdate(categorynameString);	
		manageCategoryPage.clickondelete(updateString);
		manageCategoryPage.clickondeletepopupbtn();
		manageCategoryPage.clickmenubutton();
		manageCategoryPage.clicksignoutbutton();
		} 
		
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
