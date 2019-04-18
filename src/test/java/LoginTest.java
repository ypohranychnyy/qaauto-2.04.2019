import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

import static java.lang.Thread.sleep;

public class LoginTest {
    @Test
    public void negativeLoginTest(){
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");

        WebElement loginField = driver.findElement(By.xpath("//input[@id='login-email']"));
        loginField.sendKeys("t@ukr.net");
        WebElement passField = driver.findElement(By.xpath("//input[@id='login-password']"));
        passField.sendKeys("Kosmetista1990");
        passField.sendKeys(Keys.ENTER);

        WebElement errorrorMessage = driver.findElement(By.xpath("//*[@id='error-for-password']"));

        Assert.assertEquals(errorrorMessage.getText(),"Hmm, that's not the right password. Please try again or request a new one.\n");

    }


    @Test
    public void negativeLoginTest2(){
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");

        WebElement loginField = driver.findElement(By.xpath("//input[@id='login-email']"));
        loginField.sendKeys("k@ukr.net");
        WebElement passField = driver.findElement(By.xpath("//input[@id='login-password']"));
        passField.sendKeys("12341");
        passField.sendKeys(Keys.ENTER);

        WebElement errorrorMessage = driver.findElement(By.xpath("//*[@id='error-for-username']"));
 //       Assert.assertEquals(ErrorrorMessage.getText(),"Please enter a valid email address.");
        Assert.assertEquals(errorrorMessage.getText(),"Hmm, we don't recognize that email. Please try again.");
    }

    @Test
    public void SuccessfulLoginTest() {

        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");

        String loginPageTitle = "LinkedIn: Log In or Sign Up ";

        Assert.assertEquals(driver.getTitle(), loginPageTitle);
        System.out.println(driver.getTitle());

        WebElement loginField = driver.findElement(By.xpath("//input[@id='login-email']"));
        loginField.sendKeys("xodylj@ukr.net");
        WebElement passField = driver.findElement(By.xpath("//input[@id='login-password']"));
        passField.sendKeys("Kosmetista1990");
        passField.sendKeys(Keys.ENTER);

        /*try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        WebElement profileMenuItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
        Assert.assertTrue(profileMenuItem.isDisplayed(), "Element is not displayed on homepage " + profileMenuItem);
        Assert.assertEquals(profileMenuItem.getText(), "Профиль");
        profileMenuItem.click();

//        WebElement profileMenu = driver.findElement(By.xpath("//ul[@id='nav-settings__dropdown-options']/li[2]/h5"));
        WebElement profileUserName = driver.findElement(By.xpath("//ul[@id='nav-settings__dropdown-options']//h3"));
        Assert.assertEquals(profileUserName.getText(), "Member1 Number");
//        Assert.assertEquals(profileMenu.getText(), "УЧЁТНАЯ ЗАПИСЬ");
        System.out.println(profileUserName.getText());

//        driver.quit();

    }
}
