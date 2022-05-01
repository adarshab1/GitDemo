package app_base;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base {
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement>  driver;
		
 	public AppiumDriverLocalService startServer()
	{
		boolean flag=	checkIfServerIsRunnning(4723);
		if(!flag)
		{
			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
			return service;
			
		}
		
	public static boolean checkIfServerIsRunnning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
				
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
			} finally {
				serverSocket = null;
			}
			return isServerRunning;
		}
	public static void startEmulator() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(6000);
	}

	public static  AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException
		{
		//System.getProperty("user.dir");    E:\\simple data provider\\udemy_framework
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\app_base\\global.properties");
	 	  Properties prop=new Properties();
		  prop.load(fis);
			
		//AndroidDriver<AndroidElement> driver;
		  File appDir = new File("src");
	      File app = new File(appDir, (String) prop.get(appName));
	      DesiredCapabilities capabilities = new DesiredCapabilities();
//	  String device=(String) prop.get("device");
	     String device= System.getProperty("deviceName");
	  if(device.contains("emulator"))
	  {
	  startEmulator();
	  }
	     capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
	    
	     capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
	     capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,14);
	     capabilities.setCapability(MobileCapabilityType.FULL_RESET,false);
	     capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
	     capabilities.setCapability("adbExecTimeout",90000);
		    
	     capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
	     capabilities.setCapability("appPackage","com.wrizto.samvita.healthcare");
	     capabilities.setCapability("appActivity","com.wrizto.samvita.healthcare.LoginActivity");
			
	     driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	    driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 5000);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    
		    return driver;
		}
	
		public static void getScreenshot(String s) throws IOException
		{
		File scrfile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile,new File(System.getProperty("user.dir")+"\\"+s+".png"));
		
		}
		
		

	}


