package pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import app_base.Utilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Homepage {

	public Homepage(AndroidDriver<AndroidElement>driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	@AndroidFindBy(id="com.wrizto.samvita.healthcare:id/username")
	public WebElement  username_txt;
	 
	 
	@AndroidFindBy(id="com.wrizto.samvita.healthcare:id/password")
	public WebElement  password_txt;
	
	 

	@AndroidFindBy(id="com.wrizto.samvita.healthcare:id/btnSignIn")
	public WebElement  Signin_btn;
	
	 @AndroidFindBy(id="com.wrizto.samvita.healthcare:id/all_list_rb")
	 public WebElement all_btn;
	 
	 @AndroidFindBy(id="com.wrizto.samvita.healthcare:id/patient_details_card_view")
	 public List<WebElement> patientlist;
	
	 @AndroidFindBy(id="com.wrizto.samvita.healthcare:id/patient_name")
	 public List<WebElement> patientlistname;
	
	 @AndroidFindBy(xpath="//android.widget.Button[@text='MEASURE VITALS']")
	 public List<WebElement> Measure_vitals;
	 
	 public void Scroll_and_measurevital(String search) {
		 Utilities.scroll_clickmeasure(search, patientlistname, Measure_vitals);
		 
	 }
	
}
