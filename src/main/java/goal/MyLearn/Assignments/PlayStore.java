package goal.MyLearn.Assignments;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class PlayStore {
	public static MobileElement find;
	DesiredCapabilities caps;
	AppiumDriver<MobileElement> driver;

	@BeforeClass
	public void launchApp() {
		caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "MCCDU17103007715");
		// caps.setCapability("udid", "192.168.1.7:5566");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "7.0");
		caps.setCapability("appPackage", "com.android.vending");
		caps.setCapability("appActivity", "com.google.android.finsky.activities.MainActivity");
		caps.setCapability("noReset", "true");
		// caps.setCapability("newCommandTimeout", 120);
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Test(enabled = true, priority = 0, description = "Verify that Mobile App Play Store is launched and Search Box works when text is given in Search Box od App")
	public void verifySearch() {
		find = driver.findElementById("com.android.vending:id/search_box_idle_text");
		find.click();
		// or
		// driver.findElement(By.id("com.android.vending:id/search_box_idle_text")).click();
		// driver.findElementByAccessibilityId("Search").click();
		find = driver.findElementById("com.android.vending:id/search_box_text_input");
		find.sendKeys("Google");
		((AndroidDriver<MobileElement>) driver).pressKeyCode(66);
		// find
		// =driver.findElementById("com.android.vending:id/search_box_text_input");
		// find.sendKeys(Keys.ENTER);

	}

	@Test(enabled = false, priority = 2, description = "Verify that element ‘TOP CHARTS’ in PlayStore app by matching its exact visible text")
	public void verifyTextMatches() {

		MobileElement topCharts = driver
				.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Top Charts\")"));
		System.out.println("Element's Resource ID - " + topCharts.getAttribute("resourceId"));
		Assert.assertEquals("Top Charts", topCharts.getText(), "The text \"Top chart\" does not matches");
	}

	@Test(enabled = true, priority = 3, description = "Count all the elements that start with the character ‘T’.")
	public void countTextStartsWithT() {
		List<MobileElement> countWebElementStartsWith = driver
				.findElements(MobileBy.AndroidUIAutomator("new UiSelector().textStartsWith(\"T\")"));
		for (MobileElement element : countWebElementStartsWith) {
			System.out.println("Elements Displayed on screen which starts with \"T\" " + element.getText());
		}

	}

	@Test(enabled = true, priority = 4, description = "Count all the elements that contains character ‘TO’.")
	public void countTextContainsTO() {
		int count = 0;
		List<MobileElement> countWebElementContains = driver
				.findElements(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"TO\")"));
		// new UiSelector().textMatches(\"\\w+\\s{1}\\w+\")" searches for all
		// the elements where the visible text contains any two words (with a
		// single space between them)
		// new
		// UiSelector().resourceId(\"com.android.vending:id/search_box_idle_text\")
		// new UiSelector().resourceIdMatches(\".*:id/search_box_idle_text\")
		for (MobileElement element : countWebElementContains) {
			System.out.println("Elements which contains text \"TO\" - " + element.getText());
			count++;
		}
		Assert.assertTrue(count == new Integer(2), "Element count is not as expected");
	}

	@Test(description = "Select all the elements in the Top menu of the Playstore app", priority = 1)
	public void getMenuElements() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("mobile: keyevent", 82);
		List<String> MenuItems = new ArrayList<String>();
		MenuItems.add("HOME");
		MenuItems.add("GAMES");
		MenuItems.add("MOVIES");
		MenuItems.add("BOOKS");
		List<MobileElement> verifyTopMenuTexts = driver.findElements(
				MobileBy.AndroidUIAutomator("new UiSelector().resourceId(\"com.android.vending:id/title\")"));
		for (MobileElement element : verifyTopMenuTexts) {
			for (String expectedMenu : MenuItems) {
				System.out.println("Elements present in Top Menu - " + element.getText());
				Assert.assertEquals(element.getText(), expectedMenu);
			}
		}
	}

	@AfterClass
	public void closeAppiumDriver() {
		driver.close();
	}

}