import Base.BaseClass;
import Page.CardMini;
import Page.Events;
import Page.Main;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
 * Просмотр карточек мероприятий
 */
public class LookCardEventsTest extends BaseClass {
    private final String lang = "Ru";
    private final String title = "DevOps Architecture Webinar";
    private final String date = "17 Dec 2020";
    private final String status = "Registration closed";
    private final int countSpeakers = 1;

    @Epic("Checking event cards on the Events tab")
    @Test
    public void lookCardEventsTest(){
        logger.info("Переход на вкладку Events");
        Events events = new Main(driver).tabEvents() ;

        logger.info("Переход на вкладку Past Events");
        events.clickPastEvents().waitLoadCard();

        logger.info("Для проверки выбрана карточка № 2");
        CardMini cardMini =events.getCard(2);

        logger.info("Проверка, что отображаются карточки");
        assertTrue(events.countCardsEvents()>0);

        logger.info("Проверка языка");
        assertEquals(lang, cardMini.getLangCard());

        logger.info("Проверка заголовка карточки");
        assertEquals(title, cardMini.getTitleCard());

        logger.info("Проверка даты карточки");
        assertEquals(date, cardMini.getDateCard());

        logger.info("Проверка статуса карточки");
        assertEquals(status, cardMini.getStatusCard());

        logger.info("Проверка количества спикеров карточки");
        assertEquals(countSpeakers, cardMini.getCountSpeakers());
    }
}
