package Base;

import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class BaseClass {
    protected static WebDriver driver;
    public Logger logger = LogManager.getLogger(BaseClass.class);
    public ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @BeforeEach
    public void setUp() {
        logger.info("Драйвер поднят");
        //WebDriverManager.chromedriver().setup();
        //driver=new ChromeDriver();
        driver =initDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(cfg.url());

    }

    public WebDriver initDriver() {
        RemoteWebDriver rd;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(System.getProperty("browser_name"));
        capabilities.setVersion(System.getProperty("browser_version"));
        try {
            rd = new RemoteWebDriver(URI.create(cfg.urlHub()).toURL(),capabilities);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return rd;
    }
    @AfterEach
    public void setDown()  {

        if (driver != null) {
            driver.quit();
            logger.info("Драйвер закрыт");
        }

    }
    //ввод данных в поле (с предварительной очисткой), где text - вводимое значениее, by - локатор поля ввода
    public void setValue(String text, By by){
        WebElement element =  driver.findElement(by);
        element.click();
        element.clear();
        element.sendKeys(text);

    }

    //получение текста элемента
    public String getText(By by){
        return driver.findElement(by).getText();

    }
    //получение размера списка элементов
    public Integer sizeListWebElements(By by){
        return getListWebElements(by).size();
    }

    //получение  списка элементов
    public List<WebElement> getListWebElements(By by){
        return driver.findElements(by);
    }

    //клик на элемент
    public void click(By by){
       WebElement element = new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class).until(elementToBeClickable(by));
        element.click();
    }
    //клик на элемент
    public void click(WebElement element){
        WebElement element2 = new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class).until(elementToBeClickable(element));
        element2.click();
    }

    //ожидание прогрузки страницы
    public void waitLoadCard() {
        boolean wait = (boolean) new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver arg0) {
                boolean result = false;
                if (getListWebElements(By.cssSelector(".evnt-global-loader")).size()==0)
                    result=true;
                return result;
            }
        });
    }

}
