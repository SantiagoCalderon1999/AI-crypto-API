package com.cryptoai.javaapi.binanceconnection.binance.util;

import com.cryptoai.javaapi.binanceconnection.web.exception.WrongDateFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatUtil {

    public static Long convertDateStringToLong(String endDateString) {

        // set the String date format
        SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        // parse Date from String format to Long
        Date theDate = new Date();

        try {
            theDate = dateFormat.parse(endDateString);
        } catch (ParseException e) {
            throw new WrongDateFormatException("Wrong date format - " + endDateString);
        }

        return theDate.getTime();
    }
}
