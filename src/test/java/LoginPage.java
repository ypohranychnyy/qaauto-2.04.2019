import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;
    private WebElement loginField;
    private WebElement passField;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    public void initElements() {
        loginField = driver.findElement(By.xpath("//input[@id='login-email']"));
        passField = driver.findElement(By.xpath("//input[@id='login-password']"));
    }

    public void login(String userEmail, String userPassword) {
        loginField.sendKeys(userEmail);
        passField.sendKeys(userPassword);
        passField.sendKeys(Keys.ENTER);
    }

}
