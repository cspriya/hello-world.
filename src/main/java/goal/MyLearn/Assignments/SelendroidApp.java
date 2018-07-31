package goal.MyLearn.Assignments;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class SelendroidApp {
	public static MobileElement find;
	DesiredCapabilities caps;
	AppiumDriver<MobileElement> driver;
	public static List<MobileElement> listView;
	String settingAppPackageName = "com.android.settings";
	String settingAppActivityName = "com.android.settings.HWSettings";
	String selendroidAppPackageName = "io.selendroid.testapp";
	String selendroidAppActivityName = "io.selendroid.testapp.HomeScreenActivity";

	@BeforeSuite
	public void launchApp() {
		caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "MCCDU17103007715");
		// caps.setCapability("udid", "192.168.1.7:5566");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "7.0");
		caps.setCapability("appPackage", "io.selendroid.testapp");
		caps.setCapability("appActivity", "io.selendroid.testapp.HomeScreenActivity");
		caps.setCapability("noReset", "true");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		// caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
		// selendroidAppPackageName);
		// caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
		// selendroidAppActivityName);
		// caps.setCapability("newCommandTimeout", 120);
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		// com.android.systemui:id/info_center_container
	}

	@BeforeClass
	public void reportGeneration() {

	}

	@Test(enabled = false, priority = 0, description = "Open the Selendroid application from android mobile and get the title of the application")
	public void getApplicationTitle() {
		find = driver.findElementById("android:id/title");
		Assert.assertEquals("selendroid-test-app", find.getText(), "The Title of the page does not matches");
	}

	@Test(enabled = false, priority = 0, description = "Open the Selendroid application from android mobile and get the title of the application")
	public void createNewUser() {
		driver.findElementById("io.selendroid.testapp:id/startUserRegistration").click();
		driver.findElementById("io.selendroid.testapp:id/inputUsername").sendKeys("PriyaVerma");
		driver.findElementById("io.selendroid.testapp:id/inputEmail").sendKeys("p@demo.com");
		driver.findElementById("io.selendroid.testapp:id/inputPassword").sendKeys("Passw0rd");
		driver.findElementById("io.selendroid.testapp:id/inputName").clear();
		driver.findElementById("io.selendroid.testapp:id/inputName").sendKeys("Priya Verma");
		// driver.findElementById("android:id/text1").click();
		MobileElement spinner = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"android:id/content\")).getChildByText("
						+ "new UiSelector().className(\"android.widget.TextView\"), \"Ruby\")"));
		spinner.click();
		find = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Java\")"));
		find.click();
		find = driver.findElementByClassName("android.widget.CheckBox");
		find.click();
		find = driver.findElementById("io.selendroid.testapp:id/btnRegisterUser");
		find.click();
		find = driver.findElementById("io.selendroid.testapp:id/buttonRegisterUser");
		find.click();
	}

	@Test(enabled = false, priority = 2, description = "Click Broswer icon and enter Name in search box from Selendroid application")
	public void clickBrowserTabAndSelectVehicleWithName() {
		find = driver.findElementById("io.selendroid.testapp:id/buttonStartWebview");
		find.click();
		find = driver.findElementById("name_input");
		find.sendKeys(Keys.END);
		find.clear();
		driver.findElementById("name_input").sendKeys("Hello Priya Verma!Welcome- to Appium automation on Android app");
		find = driver.findElementByXPath("//android.widget.Spinner[@index='2']");
		// find = driver.findElement(MobileBy.AndroidUIAutomator("new
		// UiSeelector().text()(\"Volvo\")"));
		find.click();
		find = driver.findElementByXPath("//android.widget.CheckedTextView[@index='1']");
		// find = driver.findElement(MobileBy.AndroidUIAutomator("new
		// UiSeelector().text()(\"Mercedes\")"));
		find.click();
		find = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Send me your name!\")"));
		find.click();
	}

	@Test(enabled = false, priority = 3, description = "Click Broswer icon and select 'Webdriver Test File' and select 'FormPage' , Fill the full form and submit")
	public void clickBrowserTabAndFillFormPage() {
		find = driver.findElementById("io.selendroid.testapp:id/buttonStartWebview");
		find.click();
		find = driver.findElementByXPath("//android.widget.Spinner[@index='0']");
		find.click();
		find = driver.findElementByXPath("//android.widget.TextView[@index='2']");
		find.click();
		// Email Address input text box
		driver.findElementById("email").sendKeys("P@demo.com");
		// Checkbox enabled and unchecked
		find = driver.findElementById("checky");
		if (find.isEnabled() && !find.isSelected()) {
			find.click();
		}
		// Checkbox enabled and checked
		find = driver.findElementById("checkedchecky");
		if (find.isEnabled() && find.isSelected()) {
			find.click();
		}
		// Checkbox disabled and unchecked
		find = driver.findElementById("disabledchecky");
		if (!find.isEnabled() && !find.isSelected()) {
			System.out.println("True");
		}
		// Checkbox checked but disabled
		find = driver.findElementById("randomly_disabled_checky");
		if (!find.isEnabled() && find.isSelected()) {
			System.out.println("True");
		}
		// Select 4th position element from dropdown 1
		find = driver.findElementByXPath("//android.widget.Spinner[@index='6']");
		find.click();
		listView = new ArrayList<MobileElement>();
		for (int i = 0; i <= 3; i++) {
			listView.add(driver.findElementByXPath("//android.widget.CheckedTextView[@index=" + i + "]"));
		}
		listView.get(3).click();
		// Select 2nd and 4th position element from dropdown 2 checkbox and
		// de-select 1st
		// and 3rd position from dropdown 2 checkbox and click OK
		find = driver.findElementByXPath("//android.widget.Spinner[@index='7']");
		find.click();
		listView = new ArrayList<MobileElement>();
		for (int i = 0; i <= 3; i++) {
			listView.add(driver.findElementByXPath("//android.widget.CheckedTextView[@index=" + i + "]"));
		}
		System.out.println("Check Size++" + listView.size());
		for (int i = 0; i <= listView.size() - 1; i++) {
			if (listView.get(i).isSelected()) {
				listView.get(i).click();
			} else {
				listView.get(i).click();
			}
		}
		driver.findElementById("android:id/button1").click();
		// Check if all the checkbox elements in the dropdown 4 are not selected
		// and then select all the elements
		find = driver.findElementByXPath("//android.widget.Spinner[@index='9']");
		find.click();
		listView = new ArrayList<MobileElement>();
		for (int i = 0; i <= 3; i++) {
			listView.add(driver.findElementByXPath("//android.widget.CheckedTextView[@index=" + i + "]"));
		}
		for (int i = 0; i <= listView.size() - 1; i++) {
			if (!listView.get(i).isSelected()) {
				listView.get(i).click();
			}
		}
		find = driver.findElementByXPath("//android.widget.Button[@index='0']");
		find.click();
		// List all bullet elements in ArrayList and vetify if it is enabled and
		// if enabled then click each..
		listView = new ArrayList<MobileElement>();
		listView.add(driver.findElementById("cheese"));
		listView.add(driver.findElementById("peas"));
		listView.add(driver.findElementById("cheese_and_peas"));
		listView.add(driver.findElementById("nothing"));
		listView.add(driver.findElementById("randomly_disabled_nothing"));
		for (int i = 0; i <= listView.size() - 1; i++) {
			if (listView.get(i).isEnabled()) {
				listView.get(i).click();
			}
		}

		// take all Input text box in a ArrayList and access all and verify if
		// input boxes are editable.
		listView = driver.findElementsByXPath("//android.view.View[@index='4']/android.widget.EditText");
		System.out.println("Check input boxes size" + listView.size());
		for (int i = 0; i <= listView.size() - 1; i++) {
			if (listView.get(i).isEnabled()) {
				listView.get(i).sendKeys(Keys.END);
				listView.get(i).clear();
				listView.get(i).sendKeys("Testing Input Boxes enabled or not...");
			}
		}
		// take all Input text box in a ArrayList and access all and verify if
		// input boxes are editable.
		listView = driver.findElementsByXPath("//android.view.View[@index='6']/android.widget.EditText");
		for (int i = 0; i <= listView.size() - 1; i++) {
			if (listView.get(i).isEnabled()) {
				listView.get(i).sendKeys(Keys.END);
				listView.get(i).clear();
				listView.get(i).sendKeys("Testing Name ID...");
			}
		}
		find = driver.findElementById("io.selendroid.testapp:id/goBack");
		find.click();
	}

	@Test(enabled = false, priority = 4, description = "Get the text elements from DOM")
	public void getTextDOM() {
		// Select items from DOM and get text of all
		find = driver.findElementById("io.selendroid.testapp:id/buttonStartWebview");
		find.click();
		find = driver.findElementByXPath("//android.widget.Spinner[@index='0']");
		find.click();
		find = driver.findElementByXPath("//android.widget.TextView[@index='3']");
		find.click();
		listView = driver.findElementsByXPath("//android.widget.ListView[@index='0']/android.view.View");
		for (int i = 0; i <= listView.size() - 1; i++) {
			System.out.println(listView.get(i).getText());
			System.lineSeparator();
		}
	}

	@Test(enabled = false, priority = 5, description = "Click 'End Button' and Exit from Selendroid application")
	public void verifyToastMessage() {
		find = driver.findElement(By.xpath("//*[@content-desc = 'showToastButtonCD']"));
		find.click();
		//// while (driver.findElement(By.partialLinkText("Hello selendroid
		//// toast!")).isDisplayed() == true) {
		// Assert.assertTrue(true);
		// }
		// Assert.assertTrue(false);
		// List<MobileElement> toast =
		// driver.findElementsByXPath("//android.widget.Button[@text='Hello
		// selendroid toast!']");
		// System.out.println("Test" + toast.size());
		// WebDriverWait wait = new WebDriverWait(driver, 20);
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//[contains(@text,
		// 'Hello selendroid toast!')]")));
		// I can find element here
		String toast = "Hello selendroid toast!";
		WebElement el2 = driver.findElement(By.xpath(".//*[contains(@text,'" + toast + "')]"));
		System.out.println(driver.getPageSource());
		Assert.assertEquals(el2.getAttribute("text"), toast);
		find = driver.findElementByXPath("//*[@content-desc = 'showPopupWindowButtonCD']");
		find.click();

		driver.findElementById("io.selendroid.testapp:id/visibleButtonTest").click();
		String actualText = driver.findElementById("io.selendroid.testapp:id/visibleTextView").getText();
		Assert.assertEquals(actualText, "Text is sometimes displayed");

	}

	@Test(enabled = false, priority = 6, description = "Touch Actions")
	public void verifyTouchActions() {
		driver.findElementById("io.selendroid.testapp:id/touchTest").click();
		MobileElement element = driver.findElementByXPath("//android.widget.LinearLayout[@index='6']");
		// TouchActions action = new TouchActions(driver);
		// action.singleTap(element);
		// action.perform();
		// **********not working
		String actualText = driver.findElementById("io.selendroid.testapp:id/gesture_type_text_view").getText();
		Assert.assertEquals(actualText, "SINGLE TAP CONFIRMED");
	}

	@Test(enabled = false, priority = 7, description = "Click on 'En button' and accept the alert")
	public void verifyAlerts() {
		find = driver.findElementByXPath("//android.widget.Button[@index='1']");
		find.click();
		find = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"This will end the activity\")"));
		String actualText = find.getText();
		if (actualText.equals("This will end the activity")) {
			find = driver.findElementByXPath("//android.widget.Button[@index='0']");
			find.click();
		} else {
			find = driver.findElementByXPath("//android.widget.Button[@index='1']");
			find.click();
		}
	}

	@Test(enabled = true, priority = 8, description = "Click Broswer icon and select 'Webdriver Test File' and select 'FormPage' , verify all the clicks on 'TestClickPage1t'")
	public void clickBrowserTabAndTestClickPage1() {
		find = driver.findElementById("io.selendroid.testapp:id/buttonStartWebview");
		find.click();
		find = driver.findElementByXPath("//android.widget.Spinner[@index='0']");
		find.click();
		find = driver.findElementByXPath("//android.widget.TextView[@index='11']");
		find.click();
		int elementSize = driver.findElementsByClassName("android.view.View").size();
		System.out.println("Tes++" + elementSize);
		listView = new ArrayList<MobileElement>();
		for (int i = 0; i <= elementSize - 1; i++) {
			listView.addAll(driver.findElementsByXPath("//android.view.View[@index='" + i + "']"));
		}
		System.out.println("Test" + listView);
		// Navigate to next page by navigating link and get back to previous
		// page
		listView.get(2).click();
		find = driver.findElementByXPath("//android.view.View[@index='6']");
		find.click();
		listView.get(10).click();
		find = driver.findElementByXPath("//android.view.View[@index='6']");
		find.click();
	}

	@Test(enabled = false, priority = 8, description = "Click 'End Button' and Exit from Selendroid application")
	public void closeApp() {
		find = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"EN Button\")"));
		find.click();
		find = driver.findElementById("android:id/button1");
		find.click();
	}

	@AfterClass
	public void genearteReport() {

	}

	@AfterSuite
	public void closeAppiumDriver() {
		driver.quit();
	}
}
