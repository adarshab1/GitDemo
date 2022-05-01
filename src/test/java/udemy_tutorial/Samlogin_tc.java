package udemy_tutorial;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import app_base.Utilities;
import app_base.base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObject.Homepage;

public class Samlogin_tc extends base{

	@DataProvider(name="inputData")
	public Object[][] getData()
	{
		Object[][] obj = new Object[][]
				{
			{"F200test1"}
				};
				return obj;
	}
	@Test(dataProvider="inputData")
	public  void Sam_Log_in(String username) throws IOException, InterruptedException {
		startServer();
		AndroidDriver<AndroidElement> driver=capabilities("SamvithaApp");//Samvita Kit App version 3.0
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//driver.findElementById("com.wrizto.samvita.healthcare:id/username").sendKeys("F200test1");
		Homepage homepage=new Homepage(driver);
		homepage.username_txt.sendKeys(username);
		
		driver.hideKeyboard();
		homepage.password_txt.sendKeys("wenzins");
		homepage.Signin_btn.click();
		
		homepage.all_btn.click();
		Utilities u = new Utilities(driver);
		u.scrolltoText("Akazuki6 a");
		homepage.Scroll_and_measurevital("Akazuki6 a");
       
		service.stop();
	}
	@BeforeTest
	public void killNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	
	}
}
