package Main_project;



import java.awt.Dialog;
import java.awt.event.ItemEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.security.PublicKey;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.core.appender.rolling.action.IfAccumulatedFileCount;
import org.apache.logging.log4j.core.tools.picocli.CommandLine.ParameterIndexGapException;
import org.apache.logging.log4j.core.tools.picocli.CommandLine.RunFirst;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle.ClickOptions;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.KeyboardModifier;
import com.microsoft.playwright.Page;

public class End_to_End 
{

	public static void main(String[] args)
	{
		
		Playwright pw=Playwright.create();
		BrowserType bType=pw.chromium();
		Browser br=bType.launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-fullscreen")).setSlowMo(1000));
		BrowserContext cntxt=br.newContext();
		Page page=cntxt.newPage();
		page.navigate("https://testautomationpractice.blogspot.com/");
		page.locator("#name").fill("Prabhakar");
		page.locator("#email").fill("Prabhakarsingh@email.com");
		page.locator("#phone").fill("7248467818");
		page.getByLabel("Address:").fill("Nothing Area 51");
		page.locator("#male").check();//click also work but recommended to use check() only
		page.locator("//input[@value='sunday']").check();//click also work but recommended to use check() only
		page.locator("#country").selectOption("India");
		String Colors[]={"Blue","Yellow"};
		page.locator("#colors").selectOption(Colors);
		String Animals[]={"Cheetah","Lion"};
		page.getByLabel("Sorted List:").selectOption(Animals);
		
		//Date picker 1 using loop
		
		String targetmonthLocator="June";
		String targetyearLocator="2026";
		String targetdateLocator="26";
		page.locator("//input[@id='datepicker']").click();
		Locator previousButton=page.locator("//span[@class='ui-icon ui-icon-circle-triangle-w']");
		Locator nextButton=page.locator("//span[@class='ui-icon ui-icon-circle-triangle-e']");
		Locator currentyearLocator = page.locator("//span[@class='ui-datepicker-year']");
		Locator currentmonthLocator = page.locator("//span[@class='ui-datepicker-month']");
		//int count=0;
		while(!(currentmonthLocator.textContent().trim().equals(targetmonthLocator) && currentyearLocator.textContent().trim().equals(targetyearLocator)))
		{ 
			page.waitForTimeout(2000);
			page.locator("//span[@class='ui-icon ui-icon-circle-triangle-w']").click();
			
			
		}
		
		page.locator("td")
	    .filter(new Locator.FilterOptions().setHasText("26"))
	    .locator("a")
	    .click();
		
	//	page.locator("//a[text()='15']").click();
	//	page.getByRole(AriaRole.GRIDCELL, new Page.GetByRoleOptions().setName("15")).click();
	//	page.locator("//a[text()='" + targetdateLocator + "']").click();
	//	System.out.println(currentmonthLocator + " " + currentyearLocator + " " + targetdateLocator);
		
		//Date picker 2 using javascript

		page.evaluate("document.getElementById('txtDate').value='30-12-2000'");
		
		//Another date format using FILL date format first check while entering date
		
		page.locator("//div[@class='date-picker-box']//input[1]").fill("2000-12-30");
		page.getByPlaceholder("End Date").fill("2020-01-01");
		page.locator("//button[@class='submit-btn']").click();
		String str=page.locator("#result").textContent();
		System.out.println(str);
	
		
		//FileUploading with input and tag
//SINGLE		
	    page.locator("//input[@id='singleFileInput']").setInputFiles(Paths.get(System.getProperty("user.dir")+"/Files/file_example_PNG.png"));
	    page.locator("//button[text()='Upload Single File']").click();
	    String newstr=page.locator("//p[@id='singleFileStatus']").innerText();
	    System.out.println(newstr);
//MULTIPLE	    
	    Path[] Files= { Paths.get(System.getProperty("user.dir")+"/Files/file_example_PNG.png"),
	    		        Paths.get(System.getProperty("user.dir")+"/Files/random.txt") };
	    page.locator("//input[@id='multipleFilesInput']").setInputFiles(Files);
		page.locator("//button[text()='Upload Multiple Files']").click();
		String s=page.locator("//p[@id='multipleFilesStatus']").innerText();
		System.out.println(s);                                          
	/*	//FileUploading without input and tag(with the help of filechooser)
//SINGLE
		FileChooser fChooser=page.waitForFileChooser(()->page.locator("//input[@id='singleFileInput']").click());
		Path[] Files= { Paths.get(System.getProperty("user.dir")+"/Files/file_example_PNG.png")};
		fChooser.setFiles(Files);
		page.locator("//button[text()='Upload Single File']").click();
//MULTIPLE		
		FileChooser anotherfChooser=page.waitForFileChooser(()->page.locator("//input[@id='multipleFilesInput']").click());
		Path[] Files1= { Paths.get(System.getProperty("user.dir")+"/Files/file_example_PNG.png"),
				       Paths.get(System.getProperty("user.dir")+"/Files/random.txt")};
		anotherfChooser.setFiles(Files1);
		page.locator("//button[text()='Upload Multiple Files']").click(); */
		
		
		
		Locator tablerow=page.locator("//table[@name='BookTable']//tr");
		Locator tablecolumn=page.locator("//table[@name='BookTable']//tbody//tr[1]//th");
		//Fetch all the table 1st way
		//tablerow.locator(":scope").allInnerTexts().forEach(e-> System.out.println(e));
			
	
		//Fetch all the table 2nd way
		
		
			for (int i = 0; i < tablerow.count(); i++) {

			    Locator cells = tablerow.nth(i).locator("th, td");

			    for (int j = 0; j < cells.count(); j++) {
			        System.out.printf("%-25s", cells.nth(j).textContent().trim());
			    }
			    System.out.println();
			}
			
			page.locator("#input1").fill("Prabhakar");
			page.locator("#btn1").click();
			page.locator("#input2").fill("Prabhakar12345");
			page.locator("#btn2").click();
			page.locator("#input3").fill("Prabhakar55555SINGH55555");
			page.locator("#btn3").click();
			
		//Fetch all the rows 
		//Locator loc1=tablerow.locator(":scope", new Locator.LocatorOptions().setHasText("Master In Selenium"));
		//System.out.println(loc1.textContent());
			
	    //Fetching any cell value
		//String value = page.locator("//table[@name='BookTable']//tr").nth(1).locator("td") .nth(0) .textContent();
		//System.out.println(value);
	    //Locator loc=tablerow.locator(":scope", new Locator.LocatorOptions().setHasText("Master In Selenium")).locator("//td[4]");
		//System.out.println(loc.textContent());
			    
		Locator shadowLocator=page.locator("#shadow_host");
		shadowLocator.locator("input[type='text']").fill("ABC");
		shadowLocator.locator("input[type='checkbox']").check();
		shadowLocator.locator("input[type='file']").setInputFiles(Paths.get(System.getProperty("user.dir")+"/Files/file_example_PNG.png"));
		
		page.locator(".wikipedia-search-input").fill("nothing");
		page.locator("input[type='submit']").click();
		Locator searchLocator = page.locator("//div[@class='wikipedia-search-results']//div");
		System.out.println(searchLocator.count());
		//One WAY
		/*for(int i=0;i<=searchLocator.count();i++)
		{
			String textString=searchLocator.nth(i).textContent();
			if(textString.equalsIgnoreCase("Nothing Else Matters"))
			{
				searchLocator.nth(i).click();
			}
		}*/
		//One more WAY
		
		Page newpage=cntxt.waitForPage(()->{searchLocator.filter(new Locator.FilterOptions().setHasText("Nothing Else Matters")).click();});
		//searchLocator.filter(new Locator.FilterOptions().setHasText("Nothing Else Matters")).first().click();
		newpage.locator(".mw-page-title-main").first().textContent();
		page.waitForTimeout(1000);
		page.bringToFront();
		page.waitForTimeout(1000);
		newpage.bringToFront();
		page.waitForTimeout(1000);
		page.bringToFront();
		//Dynamic button
		//ONE WAY TO HANDLE DYNAMIC BUTTON
		Locator startbuttonLocator = page.getByRole(
			    AriaRole.BUTTON,
			    new Page.GetByRoleOptions().setName("START")
			);
		startbuttonLocator .click();
		
		Locator stopbuttonLocator= page.getByRole(
			    AriaRole.BUTTON,
			    new Page.GetByRoleOptions().setName("STOP")
			);
		stopbuttonLocator.click();	
		
		//ANOTHER WAY TO HANDLE DYNAMIC BUTTON
		/*page.locator("xpath=//button[starts-with(@class,'ST')").click();
		page.waitForTimeout(1000);
		page.locator("xpath=//button[starts-with(@name,'ST')").click();*/
		
		//Alerts 
		page.onDialog(anything->{
			String alertmsg=anything.message();
			if(alertmsg.equalsIgnoreCase("I am an alert box!"))
			{
				anything.accept();
			}
			if(alertmsg.equalsIgnoreCase("Press a button!"))
			{
				anything.accept();
			}
			if(alertmsg.equalsIgnoreCase("Please enter your name:"))
			{
				anything.accept("Prabhakar Singh");
			}
	
		});
		
		page.locator("#alertBtn").click();
		page.locator("#confirmBtn").click();
		page.locator("#promptBtn").click();
		//OPening a new tab
		Page newtabPage =cntxt.waitForPage(()->page.locator("//button[@onclick='myFunction()']").click());
		String newtabString=newtabPage.locator(".title").first().textContent();
		System.out.println(newtabString);
		page.bringToFront();
		//Mouse Hover action with the help of a locator 
		page.waitForTimeout(500);
		page.locator("//button[text()='Point Me']").hover();
		page.waitForTimeout(500);
		//Double click action with help of a locator
		//Locator newLocator=page.locator("#field2");
		PlaywrightAssertions.assertThat(page.locator("#field2")).isEmpty();
		page.locator("//button[text()='Copy Text']").dblclick();
		page.waitForTimeout(1000);
		PlaywrightAssertions.assertThat(page.locator("#field2")).hasValue("Hello World!");
		String textString=page.locator("#field2").textContent();
		System.out.println(textString);
		//DRAG AND DROP
		//ONE WAY
		//Locator dragLocator=page.locator("#draggable");
		//Locator dropLocator=page.locator("#droppable");
		//dragTo(dropLocator);
		//SeCOND WAY
		page.locator("#draggable").hover();
		page.mouse().down();
		page.locator("#droppable").hover();
		page.mouse().up();
	
		//SLIDER Operation
		//String pricerangebeforeupdate=page.locator("#amount").innerText();
		//System.out.println(pricerangebeforeupdate);
		
		Locator sliderLocator=page.locator("//div[@id='slider-range']//span[1]");
		sliderLocator.focus();
		for(int i=0;i<10;i++)
		{
			page.keyboard().press("ArrowRight");
		}
		//PlaywrightAssertions.assertThat(page.locator("#amount").textContent().matches(pricerangebeforeupdate));
		/*String pricerangeafterupdate=page.locator("#amount").textContent();
		System.out.println(pricerangeafterupdate);
		PlaywrightAssertions.assertThat(page.locator("#amount")).containsText(pricerangebeforeupdate);*/
		Locator drpdwnLocator=page.locator("//div[text()='Item 5']");
		page.locator("#comboBox").click();
		page.locator("//div[text()='Item 5']").click();
		page.waitForTimeout(1000);
		//CLICK on link
		String href = page.locator("//a[text()='Dell']").getAttribute("href");
		System.out.println(href);

		Page newPage123 = cntxt.newPage();
		newPage123.navigate(href);

		System.out.println(newPage123.title());

		newPage123.close();
		//PlaywrightAssertions.assertThat(newpagelinkPage).hasTitle("titlenewlinkString");
		//page.goBack();--> to pressback button or go on back page
		page.bringToFront();
		page.waitForTimeout(1000);
		page.close();
		br.close();
	
	}

	}


/*Interview tip

For Playwright, remember these methods because they're used frequently in real-world automation:

locator("tr").count() → Count rows
locator("td").nth(index) → Access a specific cell
filter(new Locator.FilterOptions().setHasText(...)) → Find a row by its text
locator("button").click() → Click an element within a specific row
Nested locators like rows.nth(i).locator("td").nth(j) → Traverse table rows and columns */
