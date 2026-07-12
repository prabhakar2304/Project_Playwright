package Page_Object_Model;


import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Registration_page extends Base_page_class {
	
	Locator newUserLinkLocator;
    Locator nameLocator; 
    Locator emailLocatorReg_page;
    Locator passLocatorrReg_page;
    //Locator clickCheckBoxLocator;
   // Locator clickCheckBoxLocator1;
    Locator clickMaleRadioBtnLocator;
    Locator clickFemaleRadioBtnLocator;
    Locator selectStateLocator;
    Locator selectMultipleHobbiesLocator;
    Locator clickSignUpBtnLocator;
    
	 public Registration_page(Page page)
	 {
		 super(page);
        // this.page=page;
    	 newUserLinkLocator=page.getByText("New user? Signup");
		 nameLocator=page.locator("[type='text']");
		 emailLocatorReg_page=page.locator("[name='email']");
		 passLocatorrReg_page=page.getByTitle("Password must be atleast 6 characters");
		 //clickCheckBoxLocator=page.getByLabel("checkboxText");
		 //clickCheckBoxLocator1=page.getByText("JavaScript");
		 clickMaleRadioBtnLocator=page.locator("[value='Male']");
		 clickFemaleRadioBtnLocator=page.locator("[value='Female']");
		 selectStateLocator=page.locator("#state");
		 selectMultipleHobbiesLocator=page.locator("#hobbies");
		 clickSignUpBtnLocator=page.locator("[type='submit']");
	 }
	
	   public void signUpLink()
	   {
		   newUserLinkLocator.click();
	   }
	   public void enterName(String name)
	   {
		   nameLocator.fill(name);
	   }
	   public void enterEmail(String email)
	   {
		   emailLocatorReg_page.fill(email);
	   }
	   public void enterPass(String pass)
	   {
		 passLocatorrReg_page.fill(pass);
	   }
	  /* public void checkBoxBtn(String text) 
	   {
		 if(text.equalsIgnoreCase("Selenium"))
		 {
		     clickCheckBoxLocator.click();
		 }
		 else if (text.equalsIgnoreCase("JavaScript")) 
		 {
			 clickCheckBoxLocator1.click();
		}
	   }*/
	   /*public void checkBoxBtn(String text)
	   {
		   clickCheckBoxLocator.check();
	   }*/
	   
	    public void selectCheckboxByText(String checkboxText) {
	        // Strategy A: Best practice if checkboxes have visible text labels associated with them
	        page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(checkboxText)).check();
	    }
	   public void selectGender(String gender)
	   {
		   if(gender.equalsIgnoreCase("Male"))
		   {
			   clickMaleRadioBtnLocator.check(); 
		   }
		   else if (gender.equalsIgnoreCase("Female"))
		   {
			   clickFemaleRadioBtnLocator.check();
		   }
	   }
	   public void selectCountryByValue(String value)
	   {
		   selectStateLocator.selectOption(value);
	   }
	   public void selectMulitpleHobbies(String hobbies[])
	   {
		   selectMultipleHobbiesLocator.selectOption(hobbies);
	   }
	   public void signUpButton()
	   {
		   clickSignUpBtnLocator.click();
	   }

}
