package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class addtocart {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("Login into the swag aplication")
    public void login_into_the_swag_aplication() {
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
    @When("All Products page swag")
    public void all_products_page_swag() {
        //Assertion on product page
        String productPage = driver.findElement(By.xpath("//*[@id=\'header_container\']/div[2]/span")).getText();
        Assert.assertEquals(productPage, "Products");
    }
    @And("user click product")
    public void user_click_product() {
        driver.findElement(By.xpath("//*[@id=\'item_4_title_link\']/div")).click();
    }

    @And("user click Add to Cart button")
    public void user_click_add_to_cart_button() {
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
    }

    @Then("product added to user cart")
    public void product_added_to_user_cart() {
        driver.findElement(By.xpath("//*[@id=\'shopping_cart_container\']/a/span")).click();
        driver.quit();
    }
}
