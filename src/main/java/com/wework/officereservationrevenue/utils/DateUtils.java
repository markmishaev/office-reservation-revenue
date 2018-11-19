package com.wework.officereservationrevenue.utils;

import com.wework.officereservationrevenue.model.MonthRange;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /*
    Converts YYYY-MM-dd string to Date
     */

    public static MonthRange convertYearAndMonthToMonthRange(String yearAndMonth) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String startYearAndMonth = yearAndMonth + "-01";
        Date startDate = sdf.parse(startYearAndMonth);

        String endYearAndMonth = yearAndMonth + "-" + getNumberOfDaysInMonth(startDate);
        Date endDate = sdf.parse(endYearAndMonth);

        return new MonthRange(startDate, endDate);
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
        Returns difference in days between two dates
     */
    public static long getNumberOfDaysBetweenTwoDates(Date startDate, Date endDate) {

        LocalDate date1 = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return ChronoUnit.DAYS.between(date1, date2);
    }
}
