package Utils;

import java.io.IOException;
import Utils.Excelutils;
import org.testng.annotations.DataProvider;

public class Data_Providers  {
//DATAPROVIDER 1
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
	String path1=".\\Test_Data\\Sharing_Details.xlsx";
	Excelutils xlutils=new Excelutils(path1);
	
	int totalrows=xlutils.getRowCount("Sheet1");
	int totalcols=xlutils.getCellCount("Sheet1", 1);
	//int totalcols=2;
	String loginData[][]=new String[totalrows][totalcols];
	for(int i=1;i<=totalrows;i++)
	{
		for(int j=0;j<totalcols;j++)
		{
			loginData[i-1][j]=xlutils.getCellData("Sheet1",i, j);
		}
	}
		
		
		return loginData;
	}
}
