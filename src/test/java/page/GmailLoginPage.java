package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class GmailLoginPage extends BasePage{

    @FindBy(xpath = "//*[@id='identifierId']")
    private WebElement userEmailField;

    @FindBy(xpath = "//*[@id='password']//input")
    private WebElement passwordField;


    public GmailLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isPageLoaded() {
        return userEmailField.isDisplayed();
    }

    public void submitEmail(String email) {
        userEmailField.sendKeys(email);
        userEmailField.sendKeys(Keys.ENTER);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public GmailPage submitPassword(String password) {
        passwordField.sendKeys(password);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        passwordField.sendKeys(Keys.ENTER);
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new GmailPage(driver);
    }

}
