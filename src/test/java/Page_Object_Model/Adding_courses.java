package Page_Object_Model;

import java.awt.print.Pageable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Adding_courses extends Base_page_class {
	 private Locator emailLocator;
     private Locator passLocator;
     private Locator submitbtnLocator;
     private Locator managebtnLocator;
     private Locator manageCoursesLocator;
     private Locator addnewcourseLocator;
     private Locator choosefileLocator;
     private Locator coursenameLocator;
     private Locator descriptionLocator;
     private Locator instructorLocator;
     private Locator priceLocator;
     private Locator startdateLocator;
     private Locator enddateLocator;
     private Locator categoryLocator;
     private Locator selectcategoryLocator;
     private Locator savebtnLocator;
     private Locator tableLocator;
     private Locator columnLocator;
     private Locator rowLocator;
     private Locator cellLocator;
     private String coursenameString;
     private Locator menuLocator;
     private Locator SignoutLocator;
     
     public Adding_courses(Page page)
     {
    	 super(page);
    	  //this.page=page;
    	    emailLocator=page.getByPlaceholder("Enter Email");
			passLocator=page.getByPlaceholder("Enter Password");
			submitbtnLocator=page.getByText("Sign in").nth(1);
			managebtnLocator=page.locator("span:has-text('Manage')");
			manageCoursesLocator=page.getByText("Manage Courses");
			addnewcourseLocator=page.getByAltText("add");
			choosefileLocator=page.locator("//input[@id='thumbnail']");
			coursenameLocator=page.locator("#name");
			descriptionLocator=page.locator("#description");
			instructorLocator=page.locator("#instructorNameId");
			priceLocator=page.locator("#price");
			startdateLocator=page.locator("[name='startDate']");
			enddateLocator=page.locator("[name='endDate']");
			categoryLocator=page.getByAltText("select category");
			//selectcategoryLocator=page.locator(".menu-items");
			savebtnLocator=page.locator("//button[text()='Save']");
			tableLocator=page.locator("//table[@class='courses-table table table-borderless']");
			columnLocator=tableLocator.locator("thead tr th");
		    rowLocator=tableLocator.locator("tbody tr");
		    cellLocator=tableLocator.locator("tbody tr td");
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
		public void clickmanagecourse()
		{
			manageCoursesLocator.click();
		}
		public void clickaddnewcourse()
		{
			addnewcourseLocator.click();
		}
		public void selectfile(Path path)
		{
			choosefileLocator.setInputFiles(path);
		}
		public void entercoursename(String coursenameString)
		{
			coursenameLocator.fill(coursenameString);
		}
		public void enterdescription(String description)
		{
			descriptionLocator.fill(description);
		}
		public void enterinstructorname(String instuctorname)
		{
			instructorLocator.fill(instuctorname);
		}
		public void enterprice(String price)
		{
			priceLocator.fill(price);
		}
		public void enterstartdate(String sdate)
		{
			startdateLocator.fill(sdate);
		}
		public void enterenddate(String edate)
		{
			enddateLocator.fill(edate);
		}
		public void clickcategorydrpdwn()
		{
			categoryLocator.click();
		}
		public void selectcategory(String categoryname)
		{
			selectcategoryLocator=page.locator(".menu-items").filter(new Locator.FilterOptions().setHasText(categoryname));
			selectcategoryLocator.getByText(categoryname).click();
		}
		public void clickonsavebtn()
		{
			savebtnLocator.click();
		}
		public void selectrowtodelete(String coursenameString)
		{
			Locator row = rowLocator.locator(":scope",new Locator.LocatorOptions().setHasText(coursenameString)); //one way 
			row.getByText("Delete").click();
			/*Locator row1 = rowLocator.filter(new Locator.FilterOptions().setHasText("Anyone"));// another way
			row1.getByText("Delete").click();*/
		}
		public void searchagaincoursenameexist(String coursenameString)
		{
			Locator searchanddelete = cellLocator.filter(new Locator.FilterOptions().setHasText(coursenameString));
			if(searchanddelete.isVisible())
			{
			searchanddelete.getByText("Delete").click();
			}
			else {
			  System.out.println("It is already Deleted from the table");
			}
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
