package Page;

import Base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VideoTalksLibrary  extends BaseClass {
    public WebDriver driver;
    private final String cssCards=".evnt-talks-column.cell-6";
    private final By byCards=By.cssSelector(cssCards);

    public VideoTalksLibrary (WebDriver driver) {this.driver=driver;}

    //получение карточки
    public CardMini getCard(int i) {
        String cssCard =":nth-of-type(%d)";
        return new CardMini(cssCards+String.format(cssCard,i));
    }

    //открытие карточки
    public Card clickCard(int i) {
        String cssCard =":nth-of-type(%d)";
        String cssCardFormat =cssCards+String.format(cssCard,i);
        click(By.cssSelector(cssCardFormat));
        return new Card();
    }
    //подсчет количества отображающихся карточек
    public Integer countCardsTalks() {
        return sizeListWebElements(byCards);
    }

}
