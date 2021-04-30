package Page;

import Base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Main extends BaseClass {
    private By byTab = By.cssSelector(".events-icon [href='/events']");
    public WebDriver driver;

    public Main (WebDriver driver) {this.driver=driver;}
    //переход на вкладку Events
    public Events tabEvents() {
        click(byTab);
        return new Events(driver);
    }
}
