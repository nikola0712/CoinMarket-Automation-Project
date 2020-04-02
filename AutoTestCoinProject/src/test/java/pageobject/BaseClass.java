package pageobject;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass
{
    WebDriver driver;

    @BeforeClass
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Projects\\chromedriver\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://coinmarketcap.com/");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown()
    {
        driver.close();
        driver.quit();
    }
}
