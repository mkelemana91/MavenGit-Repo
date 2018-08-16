package Group.Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonTest {

    //Create Instance of WebDriver
    private WebDriver driver;
    private int x = 0;

    //Before all methods run, initialize the browser driver
    @BeforeClass
    private void setUp() {
        driver = new ChromeDriver();
    }

    //Before each test method, open the browser
    @BeforeMethod
    private void openHomePage() {
        driver.get("http://www.amazon.com");
    }

    //After all methods run, close the browser and end the session
    @AfterClass
    private void tearDown() {
        driver.quit();
    }

    //Write test cases on below method
    @Test(invocationCount = 3)
    private void test() {
        new Select(driver.findElement(By.id("searchDropdownBox"))).selectByVisibleText("Books");

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Selenium");
        driver.findElement(By.xpath("//*[@value='Go']")).click();

        WebElement firstItemElement = driver.findElement(By.className("s-access-title"));
        String firstItem = firstItemElement.getText();
        firstItemElement.click();

        assert driver.findElement(By.id("productTitle")).getText().equals(firstItem);

        driver.findElement(By.id("add-to-cart-button")).click();
        ++x;
        if (x == 1) {
            assert driver.findElement(By.id("hlb-ptc-btn-native")).getText().equals("Proceed to checkout (" + x + " item)");
        } else {
            assert driver.findElement(By.id("hlb-ptc-btn-native")).getText().equals("Proceed to checkout (" + x + " items)");
        }

        driver.findElement(By.className("nav-logo-link")).click();
    }


}
