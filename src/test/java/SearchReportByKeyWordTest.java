import Base.BaseClass;
import Page.CardMini;
import Page.Filter;
import Page.Main;
import Page.VideoTalksLibrary;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertTrue;


/*
 * Поиск докладов по ключевому слову
 */
@Execution(ExecutionMode.CONCURRENT)
public class SearchReportByKeyWordTest extends BaseClass {
    private final String searchText = "QA";

    @Epic("Checking the search for reports by keyword")
    //@Test
    public void searchReportByKeyWord() {
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
