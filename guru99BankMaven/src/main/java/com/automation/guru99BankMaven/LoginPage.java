package com.automation.guru99BankMaven;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends TestBaseGuru99 implements Constants 
{	
	//public static WebDriver driver;
	public static Alert alert;
	@FindBy(name="uid")
	public static WebElement userName;
	
	@FindBy(name="password")
	public static WebElement password;
	
	@FindBy(name="btnLogin")
	public static WebElement loginBtn;
	
	@FindBy(name="btnReset")
	public static WebElement resetBtn;
	
	@FindBy(css="a[href*='Logout.php']")
	public static WebElement logOutBtn;
	
	public static WebDriverWait wait;
	public static void launchLoginPage()
	{
		driver.get(baseURL);		
	}
	
	public static void enterUN(String uName)
	{
		PageFactory.initElements(driver , LoginPage.class);
		userName.clear();
		userName.sendKeys(uName);
	}
	
	public static void enterPwd(String pwd)
	{
		PageFactory.initElements(driver,LoginPage.class);
		password.clear();
		password.sendKeys(pwd);
	}
	
	public static void clickLogin()
	{
		PageFactory.initElements(driver, LoginPage.class);
		loginBtn.click();
	}
	
	public static void clickReset()
	{
		PageFactory.initElements(driver, LoginPage.class);
		resetBtn.click();
	}
	
	public static void performLogin() throws IOException
	{
			TestBaseGuru99.openXL();
		int rowNum = sheet.getLastRowNum();
		for(int i=1;i<=rowNum;i++)
		{
		driver.get(baseURL);
		enterUN(TestBaseGuru99.getCellData(i, 2));
		enterPwd(TestBaseGuru99.getCellData(i, 3));
		clickLogin();
		//wait = new WebDriverWait(driver,30);
		//boolean result =wait.until(ExpectedConditions.alertIsPresent()) != null;
		 //boolean result =ExpectedConditions.alertIsPresent() != null;
		 //System.out.println(result);
		//if(result==true)
		//if(ExpectedConditions.alertIsPresent() != null)
		try
		{	//wait = new WebDriverWait(driver,15);
			System.out.println("Alert Displayed");
			alert = driver.switchTo().alert();
			System.out.println("Switched to Alert");
			String errorMsg = alert.getText();
			alert.accept();
			System.out.println("accepted alert");
			sheet.getRow(i).createCell(4).setCellValue("Fail");	
			writeXL();
			sheet.getRow(i).createCell(5).setCellValue(errorMsg);
			writeXL();
			System.out.println("Row  : "+ i + "Err msg written into XL");
		}
		catch (Exception e)
		{
		System.out.println(e.getMessage());
		}
		finally 
		{	
			 //System.out.println(result);
			if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
			{
				sheet.getRow(i).createCell(4).setCellValue("Pass");
				sheet.getRow(i).createCell(5).setCellValue("SuccessFully Logged In");
				writeXL();
				driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
				logOutBtn.click();
				System.out.println("Clicked on logout");
			}
		}
		
		/*if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			sheet.getRow(i).createCell(4).setCellValue("Pass");
			sheet.getRow(i).createCell(5).setCellValue("SuccessFully Logged In");
			writeXL();
			logOutBtn.click();
			System.out.println("Clicked on logout");
		}
		else
		{	wait = new WebDriverWait(driver,15);
			Assert.assertTrue(wait.until(ExpectedConditions.alertIsPresent())!=null);
			driver.switchTo().alert();
			String errorMsg = alert.getText();
			alert.accept();
			sheet.getRow(i).getCell(4).setCellValue("Fail");	
			writeXL();
			sheet.getRow(i).createCell(5).setCellValue(errorMsg);
			writeXL();
			
		}*/
		}
	}
}
