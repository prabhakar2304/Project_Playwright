package Utils;

import java.io.StringReader;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Browser.NewContextOptions;

public class ExtentManager {
	
	private static ExtentReports extent;
	public String reportnameString;
    public static ExtentReports getInstance() {
    	if (extent == null)
    	{
    		/*SimpleDateFormat dtDateFormat=new SimpleDateFormat("YYYY-MM-DD_HH-MM-SS");
    		Date date=new Date();
    		String currentDatetimeStampString=dtDateFormat.format(date);*/
    		
    		String timestampString=new SimpleDateFormat("YYYY-MM-DD_HH-MM-SS").format(new java.util.Date());
    		
			String	reportnameString= "Reportname" + timestampString + ".html";
			
    		ExtentSparkReporter reporter = new ExtentSparkReporter(".\\test-output\\" + reportnameString);
    		extent = new ExtentReports();
    		extent.attachReporter(reporter);
    		reporter.config().setDocumentTitle("Learn Automation Courses Page");
    		reporter.config().setReportName("Automation Test");
    		reporter.config().setTheme(Theme.DARK);
    	}
    
    	
		return extent;
    	
    }
}
