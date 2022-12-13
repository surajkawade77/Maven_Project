package neoStoxTestclasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import NeoStoxUtility.Utility;
import neoStoxBase.Base;
import neoStoxPOMclasses.NeoStoxHomePage;
import neoStoxPOMclasses.NeoStoxLoginPage;
import neoStoxPOMclasses.NeoStoxPasswordPage;

public class ValidateNeoStoxUserName extends Base
{
	NeoStoxLoginPage login;
	NeoStoxPasswordPage password;
	NeoStoxHomePage home;
	
	@BeforeClass
	public void launchNeoStox() throws InterruptedException
	{
	launchBrowser();//form base class
	login= new NeoStoxLoginPage(driver);
	password= new NeoStoxPasswordPage(driver);
	home= new NeoStoxHomePage(driver);
	}
	@BeforeMethod
	public void logintoNeoStox() throws InterruptedException 
	{
		Utility.wait(driver, 1000);
	login.sendMobNum(driver, Utility.readDataFromExcel(0, 0));
	login.clickOnSignInButton(driver);
	Utility.wait(driver, 1000);
	
	password.enterPassword(driver, Utility.readDataFromExcel(0, 1));
	Thread.sleep(1000);
	password.clickOnSubmitButton(driver);
	Thread.sleep(1000);
	home.handlePopUp(driver);
	}
	
	@Test
	 public void validateUserID() throws IOException 
	{
	Assert.assertEquals(home.getActualUserName(driver), 
	Utility.readDataFromExcel(1, 0),"TC is failed Actual and expected User Name are not matching");
	Utility.screenshot(driver, home.getActualUserName(driver));
	}
	@AfterMethod
	public void logOutFromNeoStox()
	{
			
	home.logOut(driver);
	}
	@AfterClass
	public void closeBrowser()
	{
	Reporter.log("closing browser", true);
	driver.close();
	}


  
}
