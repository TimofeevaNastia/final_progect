import Base.BaseClass;
import Page.Card;
import Page.Events;
import Page.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LookCardEventsTest extends BaseClass {
    private final Logger logger = LogManager.getLogger(LookComingEventsTest.class);
    private final String lang = "Ru";
    private final String title = "DevOps Architecture Webinar";
    private final String date = "17 Dec 2020";
    private final String status = "Registration closed";
    private final int countSpeakers = 1;
    private Card card;

    @Test
    public void lookCardEventsTest(){
        logger.info("Переход на вкладку Events");
        Events events = new Main(driver).tabEvents() ;
        card=events.getCard(2);

        logger.info("Переход на вкладку Past Events");
        events.clickPastEvents();

        logger.info("Для проверки выбрана карточка № 2");
        card=events.getCard(2);

        logger.info("Проверка, что отображаются карточки");
        assertTrue(events.countCardsEvents()>0);

        logger.info("Проверка языка");
        assertEquals(lang,card.getLangCard());

        logger.info("Проверка заголовка карточки");
        assertEquals(title,card.getTitleCard());

        logger.info("Проверка даты карточки");
        assertEquals(date,card.getDateCard());

        logger.info("Проверка статуса карточки");
        assertEquals(status,card.getStatusCard());

        logger.info("Проверка количества спикеров карточки");
        assertEquals(countSpeakers,card.getCountSpeakers());

    }
}
