import Base.BaseClass;
import Page.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
 * Фильтрация докладов по категориям
 */
public class FilterReportInCategoryTest  extends BaseClass {
    private final String category="Testing";
    private final String location="Belarus";
    private final String language="ENGLISH";
    @Test
    public void filterReportInCategoryTest() {
        logger.info("Переход на вкладку Video-Talks Library");
        VideoTalksLibrary talksLibrary = new Main(driver).tabVideo() ;
        waitLoadCard();
        Filter filter = new Filter();
        logger.info("Клик на More Filters");
        filter.clickShowMore();

        logger.info("Выбор Category");
        filter.clickCategory().select(category);

        logger.info("Выбор Location");
        filter.clickLocation().select(location);

        logger.info("Выбор Language ");
        filter.clickLanguage().select(language);

        waitLoadCard();

        logger.info("Для проверки выбрана карточка № 1");
        Card card =talksLibrary.clickCard(1);
        waitLoadCard();

        logger.info("Проверка языка карточки");
        assertEquals(language, card.getLang());

        logger.info("Проверка места проведения мероприятий");
        assertTrue(card.getLocation().contains(location));

        logger.info("Проверка категории");
        assertEquals(category,card.getCategory());
    }
}
