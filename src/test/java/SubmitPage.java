import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SubmitPage {
    private WebDriver driver;
    private WebElement loginErrorMessage;
    private WebElement passwordErrorMessage;

    public SubmitPage(WebDriver driver) {
        this.driver = driver;
    }

    private void initElements() {
        passwordErrorMessage = driver.findElement(By.xpath("//*[@id='error-for-password']"));
        loginErrorMessage = driver.findElement(By.xpath("//*[@id='error-for-username']"));
    }

    public boolean isLoginErrorMessageDisplayed() {
        return loginErrorMessage.isDisplayed();
    }
    public String getLoginErrorMessageText() {
        return loginErrorMessage.getText();
    }

    public boolean isPasswordErrorMessageDisplayed() {
        return passwordErrorMessage.isDisplayed();
    }
    public String getPasswordErrorMessageText() {
        return passwordErrorMessage.getText();
    }
}
