package Page_Object_Model;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Login_page  {
	 
	   //Page page; 
	
	
	   private Locator emailLocator;
       private Locator passLocator;
       private Locator submitbtnLocator;
       private Locator menuLocator;
       private Locator SignoutLocator;
	   
	    public Login_page(Page page) {
	    	
	    	//super(page);
	    	
	    	//this.page=page;
	    	emailLocator=page.getByPlaceholder("Enter Email");
			passLocator=page.getByPlaceholder("Enter Password");
			submitbtnLocator=page.getByText("Sign in").nth(1);
			menuLocator=page.getByAltText("menu");
			SignoutLocator=page.getByText("Sign out");
			
		}
		
		public void setEmail(String email)
	    {
	    	emailLocator.fill(email);
	    }
		public void setPassword(String password)
		{
			passLocator.fill(password);
		}
		public void clickonbutton()
		{
			submitbtnLocator.click();
		}
		public void clickmenubutton()
		{
			menuLocator.click();
		}
		public void clicksignoutbutton()
		{
			SignoutLocator.click();
		}
		
	    }

