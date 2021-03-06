import Base.BaseClass;
import Page.CardMini;
import Page.Events;
import Page.Main;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/*
 * Просмотр карточек мероприятий
 */
@Execution(ExecutionMode.CONCURRENT)
public class LookCardEventsTest extends BaseClass {
    private final String lang = "Ru";
    private final String title = "DevOps Architecture Webinar";
    private final String date = "17 Dec 2020";
    private final String status = "Registration closed";
    private final int countSpeakers = 1;

    @Epic("Проверка карточек на закладке Events")
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
