import Base.BaseClass;
import Page.Events;
import Page.Main;
import io.qameta.allure.Epic;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
 * Просмотр предстоящих мероприятий
 */
public class LookComingEventsTest extends BaseClass {

    @Epic("Проверка карточек предстоящих мероприятий")
    @Test
    public void lookComingEventsTest(){
        logger.info("Переход на вкладку Events");
        Events events = new Main(driver).tabEvents();
        waitLoadCard();
        logger.info("Проверка, что совпадает количетсво карточек, которое отображено, с количеством, указанным на вкладке upcoming");
        Integer c1=events.countCardsEvents();
        Integer c2=Integer.parseInt(events.countCardsEventsLabel());
        assertEquals(c1,c2);

    }
}
