package Page;

import Base.BaseClass;
import org.openqa.selenium.By;

public class Card  extends BaseClass {
    private final String cssLang= " .language"; // css названия языка
    private final String cssTitle= " h1"; // css заголовка карточки
    private final String cssDate= " .date"; // css даты карточки
    private final String cssStatus= " .status"; // css статуса карточки
    private final String cssSpeaker= " .evnt-speaker"; // css спикеров
    private final String cssCard;

    public Card(String cssCard) {
        this.cssCard=cssCard;
    }
    //получение сокращенного названия языка карточки
    public String getLangCard() {
        return getText(By.cssSelector(cssCard+cssLang));
    }

    //получение заголовка карточки
    public String getTitleCard() {
        return getText(By.cssSelector(cssCard+cssTitle));
    }

    //получение заголовка карточки
    public String getDateCard() {
        return getText(By.cssSelector(cssCard+cssDate));
    }

    //получение статуса карточки
    public String getStatusCard() {
        return getText(By.cssSelector(cssCard+cssStatus));
    }

    //получение статуса карточки
    public int getCountSpeakers() {
        return getListWebElements(By.cssSelector(cssCard+cssSpeaker)).size();
    }

}
