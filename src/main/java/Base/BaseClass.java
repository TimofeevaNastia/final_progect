package Base;

import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class BaseClass {
    protected static WebDriver driver;
    public Logger logger = LogManager.getLogger(BaseClass.class);
    public ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Before
    public void setUp() {
        //WebDriverManager.chromedriver().setup();
        logger.info("Драйвер поднят");
        //если появляется капча
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("user-data-dir=~AppData\\Local\\Google\\Chrome\\User Data");
        //options.addArguments("--profile-directory=Default");
        //driver = new ChromeDriver();

        driver =initDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(cfg.url());
        driver.manage().window().maximize();

    }

    public WebDriver initDriver() {
        URL url;
        RemoteWebDriver rd;
        // url= new URL("https://127.0.0.1:4444/wd/hub");


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(System.getProperty("browser_name"));
        capabilities.setVersion(System.getProperty("browser_version"));
        //capabilities.setCapability("browserName", "chrome");
        //capabilities.setCapability("browserVersion", "89.0");
        /// capabilities.setCapability("enableVNC",true);
        // capabilities.setCapability("enableVideo",true);
        try {
            rd = new RemoteWebDriver(URI.create(cfg.urlHub()).toURL(),capabilities);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return rd;
        //WebDriverManager.chromedriver().setup();
        //return new ChromeDriver();
    }
    @After
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

    //Выбор значения из выпадающего списка, где field - раскрытие выпадащего списка, data - выбор значения в выпадающем списке
    public void setValueSelect(By field,By data){
        click(field);
        click(data);
    }

    //получение значения атрибута value
    public String getAttributeValue(By by){
        return driver.findElement(by).getAttribute("value");

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

    //oжидание прогрузки страницы
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
