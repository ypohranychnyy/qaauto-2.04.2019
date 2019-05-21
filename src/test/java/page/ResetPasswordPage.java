package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

public class ResetPasswordPage extends BaseTest {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return usernameField.isDisplayed();
    }

    public ResetPasswordLinkSentPage submitUsername(String email) {
        usernameField.sendKeys(email);

        String messageSubject = "here's the link to reset your password";
        String messageTo = "lnkdn.tst@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";
        utils.GMailService gMailService = new utils.GMailService();
        gMailService.connect();
        findAccountButton.click();
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);
        return new ResetPasswordLinkSentPage(driver);
    }
}
