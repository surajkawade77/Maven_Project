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
import NeoStoxUtility.Utility2;
import neoStoxBase.Base;
import neoStoxBase.BaseNew;
import neoStoxPOMclasses.NeoStoxHomePage;
import neoStoxPOMclasses.NeoStoxLoginPage;
import neoStoxPOMclasses.NeoStoxPasswordPage;
import neoStoxPOMclasses.NeoStoxSignInPage;

public class ValidateNeoStoxAccBalance2 extends BaseNew
{
	NeoStoxLoginPage login;
	NeoStoxPasswordPage password;
	NeoStoxHomePage home;
	NeoStoxSignInPage signIn;

	@BeforeClass
	public void launchNeoStox() throws IOException, InterruptedException 
	{
	launchBrowser();//form base class
	login= new NeoStoxLoginPage(driver);
	password= new NeoStoxPasswordPage(driver);
	home= new NeoStoxHomePage(driver);
	signIn= new NeoStoxSignInPage(driver);
	}
	
	
	@BeforeMethod
	public void logintoNeoStox() throws InterruptedException, IOException 
	
	{
	signIn.clickOnSignInButton(driver);
	Thread.sleep(1000);
	login.sendMobNum(driver, Utility2.readDataFrompropertyFile("mobnum"));
	login.clickOnSignInButton(driver);
	Utility2.wait(driver, 1000);
	password.enterPassword(driver, Utility2.readDataFrompropertyFile("password"));
	Thread.sleep(1000);
	password.clickOnSubmitButton(driver);
	Thread.sleep(1000);
	home.handlePopUp(driver);
	}
	
	@Test
	 public void validateAccBalance() throws IOException 
	{
	Assert.assertNotNull(home.getAccBalance(driver),"TC failed Unable to fetch account Balance");
	Utility2.screenshot(driver, "ACCBalance");
	}
	
	
	@Test(priority = -1)
	 public void validateUserID() throws IOException 
	{
	Assert.assertEquals(home.getActualUserName(driver), 
	Utility2.readDataFrompropertyFile("username"),"TC is failed Actual and expected User Name are not matching");
	Utility2.screenshot(driver, home.getActualUserName(driver));
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
