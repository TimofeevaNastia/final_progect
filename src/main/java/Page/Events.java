package Page;

import Base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Events  extends BaseClass {
    private final String cssCards=".evnt-events-row>.evnt-events-column.cell-3";
    private final By byCountCardsEventsLabel= By.cssSelector(".evnt-tab-link.active .evnt-tab-counter.evnt-label");
    private final By byCardsEvents= By.cssSelector(cssCards);
    private final By byTabEvents= By.cssSelector(".evnt-tab-item.nav-item");

    public WebDriver driver;

    public Events (WebDriver driver) {this.driver=driver;}

    //получение количетсва карточек из закладки upcoming events
    public String countCardsEventsLabel() {
        return getText(byCountCardsEventsLabel);
    }

    //подсчет количества отображающихся карточек
    public Integer countCardsEvents() {
        return sizeListWebElements(byCardsEvents);
    }

    //клик на вкладку past events
    public Events clickPastEvents() {
         click(getListWebElements(byTabEvents).get(1));
         return this;
    }
    //клик на вкладку upcoming events
    public void clickUpcomingEvents() {
        click(getListWebElements(byTabEvents).get(0));
    }

    //получение карточки
    public CardMini getCard(int i) {
        String cssCard =":nth-of-type(%d)";
        return new CardMini(cssCards+String.format(cssCard,i));
    }



}
