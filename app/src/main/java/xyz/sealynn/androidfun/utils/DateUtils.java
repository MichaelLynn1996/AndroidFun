package xyz.sealynn.androidfun.utils;

import java.util.Calendar;

/**
 * Created by SeaLynn0 on 2018/5/20 13:29
 * <p>
 * Email：sealynndev@gmail.com
 */
public final class DateUtils {
    private static Calendar calendar = Calendar.getInstance();

    private DateUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String getPercentsOfTheYearPassed() {
        return (int) Math.floor((float) getDaysOfTheYearPassed() / (float) getDaysOfTheYear() * 100) + "%";
    }

    public static int getDaysOfTheYearPassed() {
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static int getDaysOfTheYear() {
        int year = calendar.get(Calendar.YEAR);

        if (year % 4 == 0)
            return 366;
        else return 365;
    }

    public static String getYear() {
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public static int getIntOfTheYearPassed(){
        return (int) Math.floor((float) getDaysOfTheYearPassed() / (float) getDaysOfTheYear() * 100);
    }
}