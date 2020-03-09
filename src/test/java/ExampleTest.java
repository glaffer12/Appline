import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ExampleTest {

    WebDriver driver;

    @Before
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void bodyTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("http://www.rgs.ru");
        WebElement Menu  = driver.findElement(By.xpath("//a[@data-toggle = \"dropdown\" and @class = \"hidden-xs\"]"));
        Menu.click();
        WebElement dms = driver.findElement(By.xpath("//a[@href=\"https://www.rgs.ru/products/private_person/health/dms/generalinfo/index.wbp\"]"));
        dms.click();
        Assert.assertEquals("ДМС 2020 | Рассчитать стоимость добровольного медицинского страхования и оформить ДМС в Росгосстрах",driver.getTitle());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement request = driver.findElement(By.xpath("//a[@data-form=\"insuranceApplication\" and @style]"));
        request.click();
        Assert.assertTrue("Заявка на добровольное медицинское страхование", true);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement surname = driver.findElement(By.xpath("//input[@name=\"LastName\"]"));
        surname.click();
        surname.sendKeys("Солодов");
        WebElement name = driver.findElement(By.xpath("//input[@name=\"FirstName\"]"));
        name.click();
        name.sendKeys("Артём");
        WebElement fname = driver.findElement(By.xpath("//input[@name=\"MiddleName\"]"));
        fname.click();
        fname.sendKeys("Николаевич");
        WebElement list = driver.findElement(By.xpath("//select[@name=\"Region\"]"));
        list.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Select select = new Select(list);
        select.selectByVisibleText("Москва");
        WebElement phoneNumber = driver.findElement(By.xpath("//input[contains(@data-bind, 'value: Phone')]"));
        phoneNumber.click();
        phoneNumber.sendKeys("9150453404");
        WebElement mail = driver.findElement(By.xpath("//input[contains(@data-bind, 'value: Email')]"));
        mail.click();
        mail.sendKeys("qwertyqwerty");
        WebElement date = driver.findElement(By.xpath("//input[contains(@data-bind, 'value: ContactDate')]"));
        date.click();
        date.sendKeys("31122020");
        WebElement addInf = driver.findElement(By.xpath("//textarea[contains(@data-bind, 'value: Comment')]"));
        addInf.click();
        addInf.sendKeys("No comments");
        WebElement ok = driver.findElement(By.xpath("//input[contains(@data-bind,'IsProcessingPersonalData')]"));
        ok.click();
        Assert.assertEquals("Артём",name.getAttribute("value"));
        Assert.assertEquals("Николаевич",fname.getAttribute("value"));
        Assert.assertEquals("Солодов",surname.getAttribute("value"));
        Assert.assertEquals("77",list.getAttribute("value"));
        Assert.assertEquals("+7 (915) 045-34-04",phoneNumber.getAttribute("value"));
        Assert.assertEquals("qwertyqwerty",mail.getAttribute("value"));
        Assert.assertEquals("No comments",addInf.getAttribute("value"));
        Assert.assertEquals("31.12.2020",date.getAttribute("value"));
        WebElement send = driver.findElement(By.xpath("//button[contains(text(),\"Отправить\")]"));
        send.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),\"Введите адрес электронной почты\")]")).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
