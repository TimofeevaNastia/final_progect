package Page;

import Base.BaseClass;
import org.openqa.selenium.By;

public class Card extends BaseClass {

    private final String cssLang= " .language"; // css названия языка
    private final String cssCard=".evnt-card-wrapper";
    private final String cssLocation=" .location";
    private final String cssCategory=" .evnt-talk-topic";

    //получение названия языка карточки
    public String getLang() {
        return getText(By.cssSelector(cssCard+cssLang));
    }

    //получение местоположения проведения мероприятий
    public String getLocation() {
        return getText(By.cssSelector(cssLocation));
    }

    //получение категории
    public String getCategory() {
        return getText(By.cssSelector(cssCategory));
    }


}
