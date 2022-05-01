package app_base;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilities {
	AndroidDriver<AndroidElement> driver;
	
	public Utilities(AndroidDriver<AndroidElement> driver) {
		this.driver=driver;
	}
	
public void scrolltoText(String text) {
	//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
	driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
	 }

public static  void scroll_clickmeasure(String text,List<WebElement> ele1,List<WebElement> ele2) {
	
	 int count = ele1.size();
    for(int i=0;i<=count;i++) {
  	  String text1=ele1.get(i).getText();
        System.out.println(text1);
  	  if(text1.equalsIgnoreCase(text)) {
  		  System.out.println("*******");
      	  ele2.get(i).click();
      	  System.out.println(text);
      	  break;
  	  }//com.wrizto.samvita.healthcare:id/more_bt
        }
	 }
}
