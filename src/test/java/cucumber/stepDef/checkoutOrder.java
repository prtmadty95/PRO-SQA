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

public class checkoutOrder {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("Login into swag labs")
    public void login_into_swag_labs() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        WebDriverManager.chromedriver().setup();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //Assert login
        String loginPageAssert = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }
    @When("Product add to cart")
    public void product_add_to_cart() {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
    }
    @And("user click product cart")
    public void user_click_product_cart() {
        driver.findElement(By.xpath("//*[@id=\'shopping_cart_container\']/a/span")).click();
        //Assert page user checkout
        String cartPage = driver.findElement(By.xpath("//*[@id=\'header_container\']/div[2]/span")).getText();
        Assert.assertEquals(cartPage, "Your Cart");
    }
    @And("user click checkout button")
    public void user_click_checkout_button() {
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
    }

    @Then("user navigated to checkout information")
    public void user_navigated_to_checkout_information() {
        String checkOutinf = driver.findElement(By.xpath("//*[@id=\'header_container\']/div[2]/span")).getText();
        Assert.assertEquals(checkOutinf, "Checkout: Your Information");
    }
    @And("user input checkout information")
    public void user_input_checkout_information() {
        driver.findElement(By.id("first-name")).sendKeys("Aditya");
        driver.findElement(By.id("last-name")).sendKeys("Pratama");
        driver.findElement(By.id("postal-code")).sendKeys("44188");
    }
    @And("user click continue button")
    public void user_click_continue_button() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }
    @Then("user click finish button")
    public void user_click_finish_button() {
        //Assert checkout overview
        String overview = driver.findElement(By.xpath("//*[@id=\'header_container\']/div[2]/span")).getText();
        Assert.assertEquals(overview, "Checkout: Overview");

        driver.findElement(By.xpath("//button[@id='finish']")).click();

        //Assert Checkout finish
        String finishcheckout = driver.findElement(By.xpath("//*[@id=\'header_container\']/div[2]/span")).getText();
        Assert.assertEquals(finishcheckout, "Checkout: Complete!");
    }
}
