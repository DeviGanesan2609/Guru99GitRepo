package com.automation.guru99BankMaven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBaseGuru99 implements Constants
{
	public static FileInputStream fis;
	public static WebDriver driver;
	public static Workbook wb;
	public static Sheet sheet;
	public static int rows;
	public static String data;
	
	
/*    public TestBaseGuru99(WebDriver driver)
    {
    	this.driver = driver;
    }*/
    
	
    public static void initializeBrowser(String browserToOpen)
    {
    	if(browserToOpen.equalsIgnoreCase("firefox"))
    	{
    		driver = new FirefoxDriver();	
    		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
    		//driver.get(baseURL);
    	}
    	else
    		System.out.println("Please provide Valid Browser Type");
    }
    
    public static void openXL() throws IOException
    {
    fis = new FileInputStream(dataFile)	;
    wb = new XSSFWorkbook(fis);
    sheet = wb.getSheet(sheetName);
  
    }
    
    public static void writeXL() throws IOException
    {
    	FileOutputStream fout = new FileOutputStream(dataFile);
    	wb.write(fout);
    	
    }
    
    public static String getCellData(int rowNum,int colNum) 
    {
    	String cellData = sheet.getRow(rowNum).getCell(colNum).toString();
    	return cellData;
    }
    
    @AfterClass()
    public static void closeBrowser()
    {
    	driver.quit();
    }
    
}
