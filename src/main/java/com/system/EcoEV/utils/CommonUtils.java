package com.system.EcoEV.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Slf4j
public class CommonUtils {
    public static String  getCurrentDate(Date date){
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    public static int calculateTotalDate(String date){
        Date currentDate=new Date();

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
    }




