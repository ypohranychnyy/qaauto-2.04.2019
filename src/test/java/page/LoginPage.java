package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id='layout-main']/div//form/a")
    private WebElement resetPasswordLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public <GenericPage> GenericPage login(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (driver.getCurrentUrl().contains("/feed/")) {
            return (GenericPage) PageFactory.initElements(driver, HomePage.class); //InitElements at page.HomePage may be deleted
        }
        if (driver.getCurrentUrl().contains("/login-submit")) {
            return (GenericPage) new LoginSubmitPage(driver);//creates a new object class
        } else return (GenericPage) new LoginPage(driver);

    }

    public boolean isPageLoaded() {
        return driver.getCurrentUrl().equals("https://www.linkedin.com/")
                && driver.getTitle().contains("LinkedIn: Log In or Sign Up")
                && signInButton.isDisplayed();
    }


    public ResetPasswordPage resetPassword() {
        resetPasswordLink.click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResetPasswordPage(driver);
    }

}