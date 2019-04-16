import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    @Test
    public void SuccessfulLoginTest() {

        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe");
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");

        String loginPageTitle = "LinkedIn: Log In or Sign Up ";

        Assert.assertEquals(driver.getTitle(), loginPageTitle);
        System.out.println(driver.getTitle());

        WebElement loginField = driver.findElement(By.xpath("//*[@id='login-email']"));
        loginField.sendKeys("xodylj@ukr.net");
        WebElement passField = driver.findElement(By.xpath("//*[@id='login-password']"));
        passField.sendKeys("Kosmetista1990");
        passField.sendKeys(Keys.ENTER);

        WebElement pageTitle = driver.findElement(By.xpath("//*[@id='nav-settings__dropdown-trigger']/div/span"));
        Assert.assertEquals(pageTitle.getText(), "Профиль");


        driver.quit();

    }
}
