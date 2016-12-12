package sample;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by pc on 11.12.2016.
 */
class DateHelper {

    //календарь на текущую дату
    private Calendar battleBegin = new GregorianCalendar();
    private Calendar battleSkip = new GregorianCalendar();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("Год yyy, месяц MM, день dd. Время hh:mm");

    String getFormattedStartDate() {
        // минус 1500 лет
        battleBegin.add(Calendar.YEAR, -1500);
        battleSkip.add(Calendar.YEAR, -1500);
        return "НАЧАЛО ВЕЛИКОГО СРАЖЕНИЯ: " + simpleDateFormat.format(battleBegin.getTime());
    }

    // пропустить 20 минут раунда
    void skipTime() {
        battleSkip.add(Calendar.MINUTE, 20);
    }

    // общее время сражения
    String getFormattedDiff() {
        long begin = battleBegin.getTimeInMillis();
        long skip = battleSkip.getTimeInMillis();
        Calendar battleEnd = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        battleEnd.setTimeInMillis(skip - begin);
        return "БИТВА ДЛИЛАСЬ: " + (battleEnd.get(Calendar.DAY_OF_YEAR) - 1) + " дней "
                + battleEnd.get(Calendar.HOUR_OF_DAY) + " часов, "
                + battleEnd.get(Calendar.MINUTE) + " минут\n";
    }
}
