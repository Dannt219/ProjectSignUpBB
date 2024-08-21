package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SignUpPage {
    private AppiumDriver driver;

    // Constructor
    public SignUpPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @FindBy(xpath = "//android.widget.EditText[@content-desc=\"input-email\"]")
    private WebElement emailField;

    @FindBy(xpath = "//android.widget.EditText[@content-desc=\"input-password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//android.widget.EditText[@content-desc=\"input-repeat-password\"]")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"button-SIGN UP\"]/android.view.ViewGroup")
    private WebElement signUpButton;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Please enter a valid email address\"]")
    private WebElement emailError;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Please enter at least 8 characters\"]")
    private WebElement passwordError;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Please enter the same password\"]")
    private WebElement confirmPasswordError;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Please enter a valid email address\"]")
    private WebElement invalidEmailError;

    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickSignUpButton() {
        signUpButton.click();
    }

    public void assertEmailError(String expectedError) {
        String actualError = emailError.getText();
        Assert.assertEquals(actualError, expectedError, "Please enter a valid email address");
    }

    public void assertPasswordError(String expectedError) {
        String actualError = passwordError.getText();
        Assert.assertEquals(actualError, expectedError, "Password error message did not match expected");
    }

    public void assertConfirmPasswordError(String expectedError) {
        String actualError = confirmPasswordError.getText();
        Assert.assertEquals(actualError, expectedError, "Confirm password error message did not match expected");
    }

    public void assertInvalidEmailError(String expectedError) {
        String actualError = invalidEmailError.getText();
        Assert.assertEquals(actualError, expectedError, "Confirm password error message did not match expected");
    }
}