import Base.BaseClass;
import Page.Events;
import Page.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LookComingEventsTest extends BaseClass {
    private Logger logger = LogManager.getLogger(LookComingEventsTest.class);

    @Test
    public void goEvents(){
        logger.info("Переход на вкладку Events");
        Events events = new Main(driver).tabEvents() ;
        logger.info("Проверка, что совпадает количетсво карточек, которое отображено, с количеством, указанным на вкладке upcoming");
        Integer c1=events.countCardsEvents();
        Integer c2=Integer.parseInt(events.countCardsEventsLabel());
        assertEquals(c1,c2);

    }
}
