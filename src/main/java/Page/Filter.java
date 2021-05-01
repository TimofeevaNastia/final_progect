package Page;

import Base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Filter extends BaseClass {
    private final String cssSelectCountryInLocation="[data-value='%s']";
    private final By byLocation= By.id("filter_location");
    private final By byMoreFilters= By.cssSelector(".show-more");
    private final By byCategory= By.id("filter_category");
    private final By byLanguage= By.id("filter_language");
    private final By byInputSearch= By.cssSelector(".evnt-search-filter input");

    //нажатие на More Filters
    public  Filter clickShowMore(){
        click(byMoreFilters);
        return this;
    }
    //нажатие на Category
    public  Filter clickCategory(){
        click(byCategory);
        return this;
    }
    //нажатие на Location
    public  Filter clickLocation(){
        click(byLocation);
        return this;
    }
    //нажатие на Language
    public  Filter clickLanguage(){
        click(byLanguage);
        return this;
    }
    // выбор из списка
    public Filter select(String text) {
        click(By.cssSelector(String.format(cssSelectCountryInLocation,text)));
        return this;
    }
    // ввод в поле поиска
    public Filter inputSearchField(String text) {
        setValue(text, byInputSearch);
        return this;
    }

}
