package Helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
 * Вспомогательный класс для преобразования строковой даты проведения мероприятия к типу  Date
 */
public class DateEvents {
    private final Logger logger = LogManager.getLogger(DateEvents.class);
    private final String dateSEventsString;
    private final String[] date;
    private final String dayBegin;
    private final String monthBegin;
    private final String dayEnd;
    private final String monthEnd;
    private final String year;
    private final String[] dateBegin;
    private final String[] dateEnd;

    public DateEvents(String dateSEventsString) {
        this.dateSEventsString = dateSEventsString;
        date = dateSEventsString.split(" - "); // разбиение дат на две

        dateBegin = date[0].split(" ");// разделения по пробелу чтобы выделить день месяц и год
        //если второй даты нет,  дата начала = дата конца
        if (date.length == 1) {
            dateEnd = dateBegin;
            year = dateBegin[2];
        } else {
            dateEnd = date[1].split(" ");
            year = dateEnd[2];
        }
        //устанавливаем день даты конца
        dayEnd = dateEnd[0];
        //устанавливаем месяц даты конца
        monthEnd = dateEnd[1];
        //устанавливаем день даты начала
        dayBegin = dateBegin[0];
        //устанавливаем месяц даты начала
        //если месяца у первой даты нет, то месяц даты начала = месяц даты конца
        if (dateBegin.length == 1) monthBegin = monthEnd;
        else monthBegin = dateBegin[1];

    }

    public Date getDateBegin() throws ParseException {
        logger.info("Дата начала проведения мероприятия: "+dayBegin+" "+monthBegin+" "+year);
        return new SimpleDateFormat("d MMM yyyy", Locale.US).parse(dayBegin+" "+monthBegin+" "+year);
    }

    public Date getDateEnd() throws ParseException {
        logger.info("Дата окончания проведения мероприятия: "+dayEnd+" "+monthEnd+" "+year);

        return new SimpleDateFormat("d MMM yyyy", Locale.US).parse(dayEnd+" "+monthEnd+" "+year);
    }

}
