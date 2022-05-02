package com.cryptoai.javaapi.binanceconnection.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
}
