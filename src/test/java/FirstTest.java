import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstTest {
    public AndroidDriver driver;

    @BeforeTest
    public void  setUp() throws MalformedURLException {
        String appiumServerUrl = "http://0.0.0.0:4723";

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "Android");
        dc.setCapability("appium:automationName", "UiAutomator2");
        dc.setCapability("appium:app", System.getProperty("user.dir") + "/apps/ApiDemos.apk");

        driver = new AndroidDriver(new URL(appiumServerUrl), dc);
    }

    @Test
    public void test(){
        driver.findElement(AppiumBy.accessibilityId("Accessibility")).click();
    }
    @AfterTest
    public void close(){
        driver.quit();
    }
}
