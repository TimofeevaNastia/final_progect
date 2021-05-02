import Base.BaseClass;
import Page.CardMini;
import Page.Filter;
import Page.Main;
import Page.VideoTalksLibrary;
import io.qameta.allure.Epic;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/*
 * Поиск докладов по ключевому слову
 */
public class SearchReportByKeyWord extends BaseClass {
    private final String searchText = "QA";

    @Epic("Checking the search for reports by keyword")
    @Test
    public void filterReportInCategoryTest() {
        logger.info("Переход на вкладку Video-Talks Library");
        VideoTalksLibrary talksLibrary = new Main(driver).tabVideo();
        waitLoadCard();

        logger.info("Ввод в поле поиска");
        new Filter().inputSearchField(searchText);

        waitLoadCard();

        int countCard = talksLibrary.countCardsTalks();

        for (int i = 1; i <= countCard; i++) {
            CardMini cardMini = talksLibrary.getCard(i);
            assertTrue(cardMini.getTitleCard().contains(searchText));
        }
    }
}
