package com.cryptoai.javaapi.binanceconnection.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    public static boolean hasErrors(String date){
        DateFormat dateFormat = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT);
        dateFormat.setLenient(false);

        try{
            dateFormat.parse(date);
        } catch(ParseException e){
            return true;
        }

        return false;
    }

    public static Long convertDateStringToLong(String endDateString) {

        // set the String date format
        SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT);

        // parse Date from String format to Long
        Date theDate = new Date();
        try {
            theDate = dateFormat.parse(endDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return theDate.getTime();
    }
}
