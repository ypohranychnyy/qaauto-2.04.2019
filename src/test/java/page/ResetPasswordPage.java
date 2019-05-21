package page;

import org.apache.commons.lang3.StringUtils;
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
        String resetasswordLink = message.substring(message.indexOf("href=\"") + 1,
                message.indexOf("\" style=\"cursor:pointer;color:#008CC9;-webkit-text-size-adjust:100%;display:inline-block;text-decoration:none;-ms-text-size-adjust:100%;\">Reset my password"));
        String bar = StringUtils.substringBetween("ABC[ This is to extract ]",
                "href=\"",
                "\" style=\"cursor:pointer;color:#008CC9;-webkit-text-size-adjust:100%;display:inline-block;text-decoration:none;-ms-text-size-adjust:100%;\">Reset my password")
                .replace("amp","");


        System.out.println(resetasswordLink);
        driver.get(resetasswordLink);

        return new ResetPasswordLinkSentPage(driver);
    }
}
