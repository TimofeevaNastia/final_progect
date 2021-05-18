import Base.BaseClass;
import Helper.DateEvents;
import Page.CardMini;
import Page.Events;
import Page.Main;
import io.qameta.allure.Epic;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertTrue;


/*
 * Валидация дат предстоящих мероприятий
 */
@Execution(ExecutionMode.CONCURRENT)
public class ValidDateComingEventsTest extends BaseClass {
    private Boolean checkDate = false;

    @Epic("Checking the dates of upcoming events")
    //@Test
    public void validDateComingEventsTest() throws ParseException, InterruptedException {

        logger.info("Переход на вкладку Events");
        Thread.sleep(5000);
        Events events = new Main(driver).tabEvents();
        logger.info("Переход на вкладку Past Events");
        events.clickUpcomingEvents();
        logger.info("Проверка, что отображаются карточки");
        assertTrue(events.countCardsEvents() > 0);

        logger.info("Для проверки выбрана карточка № 1");
        CardMini cardMini = events.getCard(1);

        logger.info("Проверка, что дата проведения мероприятий больше или равны текущей дате");
        DateEvents dateEvents = new DateEvents(cardMini.getDateCard());
        Date current = new Date();//текущая дата
        logger.info("Текущая дата" + current);
        //получение даты начала мероприятия
        Date date1 = dateEvents.getDateBegin();
        //получение даты конца мероприятия
        Date date2 = dateEvents.getDateEnd();
        if (date1.after(current) && date2.after(current) || ((date1.compareTo(current) == 0) || date2.compareTo(current) == 0))
            checkDate = true;
        assertTrue(checkDate);
    }
}
