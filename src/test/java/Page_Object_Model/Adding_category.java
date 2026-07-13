package Page_Object_Model;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Adding_category extends Base_page_class {
	
	 private Locator emailLocator;
     private Locator passLocator;
     private Locator submitbtnLocator;
     private Locator managebtnLocator;
     private Locator clickmanagecategoryLocator;
     private Locator clicknewcategoryLocator;
     private Locator tableLocator;
     private Locator columnLocator;
     private Locator rowLocator;
     private Locator cellLocator;
     private Locator clickpopupdeleteLocator;
     private Locator menuLocator;
     private Locator SignoutLocator;
     
     public Adding_category(Page page)
     {
    	    super(page);
 	        emailLocator=page.getByPlaceholder("Enter Email");
			passLocator=page.getByPlaceholder("Enter Password");
			submitbtnLocator=page.getByText("Sign in").nth(1);
			managebtnLocator=page.locator("span:has-text('Manage')");
			clickmanagecategoryLocator=page.locator("//a[text()='Manage Categories']//img");
			clicknewcategoryLocator=page.locator("//div[@class='manage-btns']//button");
			tableLocator=page.locator("//table[@class='category-table table table-borderless']"); 
			columnLocator=tableLocator.locator("thead tr th");
		    rowLocator=tableLocator.locator("tbody tr");
		    cellLocator=tableLocator.locator("tbody tr td");
		    clickpopupdeleteLocator=page.locator("//div[@class='modal-footer']//button[2]");
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
		public void clickmanageoption()
		{
			managebtnLocator.click();
		}
        public void clickmanagecategory()
        {
        	clickmanagecategoryLocator.click();
        }
        public void clickaddnewcategory()
        {
        	clicknewcategoryLocator.click();
        }
        public void clickonupdate(String Categoryname)
        {
        	Locator row =rowLocator.filter(new Locator.FilterOptions().setHasText(Categoryname));
        	row.getByText("Update ").click();
        }
        public void clickondelete(String Categoryname)
        {
        Locator row1=rowLocator.locator(":scope",new Locator.LocatorOptions().setHasText(Categoryname));
        row1.getByText("Delete ").click();
        }
        public void clickondeletepopupbtn()
        {
        	clickpopupdeleteLocator.click();
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
