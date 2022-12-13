package CB;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

public class CBTesting 
{
	WebDriver driver;
	@Parameters("BrowserName")
	
  @Test
  public void BrowserTest(String bname) 
  {
	  if (bname.equals("edge"))
	  {
		  System.setProperty("webdriver.edge.driver", "D:\\My Folder\\Suraj\\My Documents\\edgedriver_win64\\msedgedriver.exe");
		  driver=new EdgeDriver();
		  driver.get("https://www.google.com/");
	  }
	  else if (bname.equals("chrome"))
	  {
		  System.setProperty("webdriver.chrome.driver", "D:\\My Folder\\Suraj\\My Documents\\chromedriver_win32\\chromedriver.exe");
		  driver=new ChromeDriver();
		  driver.get("https://www.youtube.com/");
	  }
	  
	  driver.manage().window().maximize();
  }
}
