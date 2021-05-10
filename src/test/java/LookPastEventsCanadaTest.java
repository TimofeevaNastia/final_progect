import Base.BaseClass;
import Helper.DateEvents;
import Page.CardMini;
import Page.Events;
import Page.Filter;
import Page.Main;
import io.qameta.allure.Epic;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/*
 * Просмотр прошедших мероприятий в Канаде
 */
@Execution(ExecutionMode.CONCURRENT)
public class LookPastEventsCanadaTest extends BaseClass {
    private final String country="Canada";
    private Boolean checkDate=false;

    @Epic("Checking cards of past events in Canada")
    @Test
    public void lookPastEventsCanadaTest() throws ParseException {
        logger.info("Переход на вкладку Events");
        Events events = new Main(driver).tabEvents();

        logger.info("Переход на вкладку Past Events");
        events.clickPastEvents().waitLoadCard();

        logger.info("Нажатие на Location и выбор страны");
        new Filter().clickLocation()
                .select(country);
        //ожидание прогрузки карточек
        waitLoadCard();

        logger.info("Проверка, что совпадает количетсво карточек, которое отображено, с количеством, указанным на вкладке Past Events");
        Integer c1=events.countCardsEvents();
        Integer c2=Integer.parseInt(events.countCardsEventsLabel());
        assertEquals(c1,c2);

        logger.info("Для проверки выбрана карточка № 1");
        CardMini cardMini =events.getCard(1);

        logger.info("Проверка, что дата проведения мероприятий меньше текущей даты");
        DateEvents dateEvents=new DateEvents(cardMini.getDateCard());
        Date current = new Date();//текущая дата
        logger.info("Текущая дата" + current);
        //получение даты начала мероприятия
        Date date1=dateEvents.getDateBegin();
        //получение даты конца мероприятия
        Date date2=dateEvents.getDateEnd();
        if (current.after(date1) && current.after(date2)) checkDate=true;
        assertTrue(checkDate);

    }

}
