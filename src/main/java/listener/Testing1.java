package listener;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(listener.Listener.class)
public class Testing1 {
	
	
	
  @Test(priority=-1)
  public void A() 
  {
	  Reporter.log("A method is running", true);
  }
  @Test
  public void B()
  {
	  Assert.fail();
	  Reporter.log("B method is running", true);
  }
  @Test(dependsOnMethods = "B")
  public void C()
  {
	  Reporter.log("C method is running", true);
  }
}
