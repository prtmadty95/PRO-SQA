package Swaglabs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class Login{
    @Test //tag running script
    public void success_login(){
        WebDriver driver;
        String baseUrl = "https://www.saucedemo.com/";
        WebDriverManager.chromedriver().setup();

        //apply chrome driver setup
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); //set Time out untuk driver menemukan element
        driver.manage().window().maximize();
        driver.get(baseUrl);
        String loginPageAssert = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");

        //Input Username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //Input Password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //Click Login Button
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //Assertion
        String dashboardProduct = driver.findElement(By.xpath("//*[@id=\'header_container\']/div[2]/span")).getText();
        Assert.assertEquals(dashboardProduct, "Products");

        //QUIT
        driver.quit();
    }

    @Test
    public void failed_login(){
        WebDriver driver;
        String baseUrl = "https://www.saucedemo.com/";
        WebDriverManager.chromedriver().setup();

        //apply chrome driver setup
        driver = new ChromeDriver();

        //set Time out untuk driver menemukan element
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        driver.get(baseUrl);
        String loginPageAssert = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");

        //Input Username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //Input Password
        driver.findElement(By.id("password")).sendKeys("secret_sauce23");
        //Click Login Button
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        //assert error login
        String ErrorLogin = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3")).getText();
        Assert.assertEquals(ErrorLogin, "Epic sadface: Username and password do not match any user in this service");

    }
}