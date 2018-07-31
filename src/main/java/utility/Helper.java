package utility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class Helper {
	public static MobileElement scrollToElementUsing_getChildByDescription(String scrollableList, String uiSelector,
			AppiumDriver<MobileElement> driver) {
		MobileElement element = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"" + scrollableList + "\")).getChildByDescription("
						+ "new UiSelector().className(\"" + uiSelector + "\")"));

		return element;
	}
}
