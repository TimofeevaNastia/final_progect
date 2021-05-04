package Page;

import Base.BaseClass;
import org.openqa.selenium.By;

public class Filter extends BaseClass {
    private final String cssSelect="[data-value='%s']";
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
    //Выбор из списка
    public  Filter select(String value){
        String css=String.format(cssSelect,value);
        click(By.cssSelector(css));
        return this;
    }

    //Ввод в поле поиска
    public  Filter inputSearchField(String value){
        setValue(value,byInputSearch);
        return this;
    }

}
