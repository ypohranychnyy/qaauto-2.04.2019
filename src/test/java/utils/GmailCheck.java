package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;

public class GmailCheck {

    public static void main(String[] args) {
        String username = "lnkdn.tst",
                password = "testLink!";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com/ServiceLogin?");
       WebElement loginField = driver.findElement(By.xpath("//*[@id='identifierId']"));
        loginField.sendKeys(username);
        loginField.sendKeys(Keys.ENTER);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement passwordField = driver.findElement(By.id("Passwd")); //FixMe locator
        passwordField.sendKeys(password);
        driver.findElement(By.id("signIn")).click();





    }


}
