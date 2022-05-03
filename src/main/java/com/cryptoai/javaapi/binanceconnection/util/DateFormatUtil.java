package com.cryptoai.javaapi.binanceconnection.util;

import com.cryptoai.javaapi.binanceconnection.rest.WrongDateFormatException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    public static Long convertDateStringToLong(String endDateString) {

        // set the String date format
        SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT);
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
