package Page;

import Base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Main extends BaseClass {
    private By byTabTalks=By.cssSelector(".talks-library-icon");
    private By byTabEvents=By.cssSelector(".events-icon");

    public WebDriver driver;

    public Main (WebDriver driver) {this.driver=driver;}

    //переход на вкладку Events
    public Events tabEvents() {
        click(byTabEvents);
        return new Events(driver);
    }

    //переход на вкладку Video
    public VideoTalksLibrary tabVideo() {
        click(byTabTalks);
        return new VideoTalksLibrary(driver);
    }
}
