package com.system.EcoEV.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;

@Slf4j
public class CommonUtils {
    public static String getCurrentDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static int calculateTotalDate(String date) {
        Date currentDate = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int diff = 0;
        try {
            Date todayDate = sdf.parse(getCurrentDate(currentDate));
            Date givenDate = sdf.parse(date);

            long difference_In_Time = Math.abs(todayDate.getTime() - givenDate.getTime());

            long difference_In_Days = difference_In_Time / (1000 * 60 * 60 * 24);

            diff = (int) difference_In_Days;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }


    public static String getMonthName(int mm) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (mm >= 1 && mm <= 12)
            month = months[mm - 1];
        return month;
    }

    public static boolean isLastDayOfMonth(String date) {
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)));
        int maxDayOfMonth = yearMonth.lengthOfMonth();
        log.info("{}", maxDayOfMonth);
        if (maxDayOfMonth == Integer.parseInt(date.substring(8, 10))) return true;
        return false;
    }
}
//yyyy-mm-dd
//0123456789



