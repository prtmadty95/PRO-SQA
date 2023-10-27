package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("login page swage labs")
    public void login_page_swage_labs() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        WebDriverManager.chromedriver().setup();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        //Assertion
        String loginPageAssert = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");
    }

    @When("user input correct username")
    public void user_input_correct_username() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }
    @And("user input correct password")
    public void user_input_correct_password() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }
    @And("user click login button")
    public void user_click_login_button() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }
    @Then("User navigated to products page")
    public void user_navigated_to_products_page() {
        String Product = driver.findElement(By.xpath("//*[@id=\'header_container\']/div[2]/span")).getText();
        Assert.assertEquals(Product, "Products");
        driver.close();
    }
    @And("user input invalid password")
    public void user_input_invalid_password() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce123");
    }
    @Then("User get error message")
    public void user_get_error_message() {
        String ErrorLogin = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3")).getText();
        Assert.assertEquals(ErrorLogin, "Epic sadface: Username and password do not match any user in this service");
        driver.close();
        driver.quit();

    }

}
