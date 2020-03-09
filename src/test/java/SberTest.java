import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SberTest {

    WebDriver driver;

    @Before
    public void BeforeTest() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void BodyTest() {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://www.sberbank.ru/ru/person");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement regionChoose = driver.findElement(By.xpath("//a[contains(@aria-label,'Текущий регион:')]"));
        regionChoose.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement nn = driver.findElement(By.xpath("//a[contains(text(),\"Нижегородская область\")]"));
        nn.click();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class =\"footer__social\"]")).isDisplayed());
    }

    @After
    public void AfterTest() {
        driver.quit();
    }
}
