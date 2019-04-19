import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    @Test
    public void negativeLoginTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("t@ukr.net", "Kosmetista1990");
        WebElement errorrorMessage = driver.findElement(By.xpath("//*[@id='error-for-password']"));
        Assert.assertEquals(errorrorMessage.getText(), "Hmm, that's not the right password. Please try again or request a new one.\n");
//        driver.quit();
    }

    @Test
    public void negativeLoginTest2() {
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32\\chromedriver.exe"); //working station
//         System.setProperty("webdriver.chrome.driver", "/Users/yuriy/Documents/Webdriver/chromedriver"); //for MacOS
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("k@ukr.net", "12341");
        WebElement errorrorMessage = driver.findElement(By.xpath("//*[@id='error-for-username']"));
        //       Assert.assertEquals(ErrorrorMessage.getText(),"Please enter a valid email address.");
        Assert.assertEquals(errorrorMessage.getText(), "Hmm, we don't recognize that email. Please try again.");
        //        driver.quit();
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

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("xodylj@ukr.net", "Kosmetista1990");
        /*
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isProfileMenuItemDisplayed());
        homePage.clickOnProfileMenuItem();
        Assert.assertEquals(homePage.getProfileUserNameText(), "Member1 Number",
                "Wromg profile username is displayed");
//        driver.quit();
    }
}
