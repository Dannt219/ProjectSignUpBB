package testcases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;

import java.net.MalformedURLException;
import java.net.URL;

public class SignUpTest {
    private AppiumDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private String emailValid = "test@example.com";
    private String emailInvalid = "test@example";
    private String password = "12345678";
    private String passwordMismatched = "123123123";

    @BeforeClass
    public void setUp() throws MalformedURLException {
        String appiumServerUrl = "http://0.0.0.0:4723";
        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability("platformName", "Android");
        dc.setCapability("appium:automationName", "UiAutomator2");
        dc.setCapability("appium:app", System.getProperty("user.dir") + "/apps/appLogin.apk");

        driver = new AndroidDriver(new URL(appiumServerUrl), dc);

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        homePage.clickLoginButton();
        loginPage.clickSignUpTab();
    }

    @Test
    public void TC_01_EmptyAllFields() {
        signUpPage.enterEmail("");
        signUpPage.enterPassword("");
        signUpPage.enterConfirmPassword("");
        signUpPage.clickSignUpButton();
        signUpPage.assertEmailError("Please enter a valid email address");
        signUpPage.assertPasswordError("Please enter at least 8 characters");
        signUpPage.assertConfirmPasswordError("Please enter the same password");
    }

    @Test
    public void TC_02_InvalidEmail() {
        signUpPage.enterEmail(emailInvalid);
        signUpPage.enterPassword(password);
        signUpPage.enterConfirmPassword(password);
        signUpPage.clickSignUpButton();
        signUpPage.assertInvalidEmailError("Please enter a valid email address");
    }

    @Test
    public void TC_03_MismathchedPassword() {
        signUpPage.enterEmail(emailValid);
        signUpPage.enterPassword(password);
        signUpPage.enterConfirmPassword(passwordMismatched);
        signUpPage.clickSignUpButton();
        signUpPage.assertConfirmPasswordError("Please enter the same password");
    }

    @Test
    public void TC_04_ValidEmailAndPassword() {
        signUpPage.enterEmail(emailValid);
        signUpPage.enterPassword(password);
        signUpPage.enterConfirmPassword(password);
        signUpPage.clickSignUpButton();

    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}