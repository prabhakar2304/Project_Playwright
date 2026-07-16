package Main_project;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import Page_Object_Model.Adding_courses;

public class Addingcourse extends Base_class{
	Path path=Path.of(System.getProperty("user.dir"),"Files","file_example_PN.jpeg");
	String coursenameString="AI Testing";
	@Test(groups= {"Sanity","Regression","Master"})
	public void Addingcourse()
	{
		try 
		{
			Adding_courses addcourse=new Adding_courses(page);
			addcourse.setEmail(prop.getProperty("email"));
			addcourse.setPassword(prop.getProperty("password"));
			addcourse.clickonbutton();
			addcourse.clickmanageoption();
			addcourse.clickmanagecourse();
			addcourse.clickaddnewcourse();
			page.onDialog(dialog->{
				String msg=dialog.message();
			    System.out.println(msg);
			    dialog.accept();});
			try {
				System.out.println("Path: " + path.toAbsolutePath());
				System.out.println("Exists: " + path.toFile().exists());
				addcourse.selectfile(path);
			} catch ( Exception e) 
			{
				Assert.fail(e.getMessage());
			}
			/*page.onDialog(dialog->{
				String msg=dialog.message();
			    System.out.println(msg);
			    dialog.accept();});
			addcourse.selectfile(path);*/
			addcourse.entercoursename(coursenameString);
			addcourse.enterdescription("AI Knowledge ETE");
			addcourse.enterinstructorname("Prabhakar Singh");
			addcourse.enterprice("10000");
			addcourse.enterstartdate("08/30/2026");
			addcourse.enterenddate("12/30/2026");
			addcourse.clickcategorydrpdwn();
			addcourse.selectcategory("TestNG");
			addcourse.clickonsavebtn();
			addcourse.selectrowtodelete(coursenameString);
			addcourse.searchagaincoursenameexist(coursenameString);
			addcourse.clickmenubutton();
			addcourse.clicksignoutbutton();
			
		}
		catch(Exception e)
		{
			Assert.fail(e.getMessage());
			//System.out.print(e.getMessage());
		}
	}

}
