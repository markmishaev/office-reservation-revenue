package com.wework.officereservationrevenue.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    /*
    Converts YYYY-MM-dd string to Date
     */
    public static Date convertYearAndMonthToDate(String yearAndMonth) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(yearAndMonth);
    }

    /*
    Calculates number of days in month
     */
    public static int getNumberOfDaysInMonth(Date monthInDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(monthInDate);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;

        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();
    }

    /*
        Get first date of next month
     */
    public static Date getFirstDateOfNextMonth(Date date) {

        Calendar nextMonthDate = Calendar.getInstance();
        nextMonthDate.setTime(date);
        // Add the Date as next month ahead
        nextMonthDate.add(Calendar.MONTH, 1);

        return getFirstDateOfMonth(nextMonthDate.getTime());
    }

    /*
        Get first date of this month
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /*
        Returns difference in days between two dates
     */
    public static long getNumberOfDaysBetweenTwoDates(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();

        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
