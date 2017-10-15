package com.automation.guru99BankMaven;

import java.io.IOException;

import org.testng.annotations.Test;


public class LoginPageTest extends LoginPage
{	
	@Test(priority=1)
	public static void lauchBrowser()
	{
		initializeBrowser("firefox");
		launchLoginPage();
	}
	
    @Test(priority=2,enabled=true)
    public static void verifyLogin() throws IOException
    {	
    	
    	performLogin();
    }

   
}
