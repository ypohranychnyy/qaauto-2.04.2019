import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    private WebElement loginField;
    private WebElement passField;
    private WebElement loginPage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        loginField = driver.findElement(By.xpath("//input[@id='login-email']"));
        passField = driver.findElement(By.xpath("//input[@id='login-password']"));
    }

    public HomePage login(String userEmail, String userPassword) {
        loginField.sendKeys(userEmail);
        passField.sendKeys(userPassword);
        passField.sendKeys(Keys.ENTER);
        return new HomePage((driver));
    }

    public boolean isLoginPageLoaded() {
        return driver.getCurrentUrl().equals("https://www.linkedin.com/")
                && driver.getTitle().equals("LinkedIn: Log In or Sign Up ")
                && loginField.isDisplayed();
    }
    public HomePage loginToHomePage(String userEmail, String userPassword) {
        loginField.sendKeys(userEmail);
        passField.sendKeys(userPassword);
        passField.sendKeys(Keys.ENTER);
        return new HomePage(driver);

    }
    public void loginToLoginPage(String userEmail, String userPassword) {
        loginField.sendKeys(userEmail);
        passField.sendKeys(userPassword);
        passField.sendKeys(Keys.ENTER);
    }
    public SubmitPage loginToSubmitPage(String userEmail, String userPassword) {
        loginField.sendKeys(userEmail);
        passField.sendKeys(userPassword);
        passField.sendKeys(Keys.ENTER);
        return new SubmitPage(driver);
    }


}
