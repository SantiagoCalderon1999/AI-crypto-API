package com.cryptoai.javaapi.binanceconnection.binance.util;

import com.cryptoai.javaapi.binanceconnection.binance.util.DateFormatUtil;
import com.cryptoai.javaapi.binanceconnection.web.exception.WrongDateFormatException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateFormatUtilTest {

    @Test
    void convertDateStringToLong() {

        String incorrectStringDate = "1-october-2019";
        Long expectedReturnValue = 1569888000000l;

        assertThat(DateFormatUtil.convertDateStringToLong(incorrectStringDate)).isEqualTo(expectedReturnValue);

    }

    @Test
    void convertDateStringToLongExceptionHandling(){
        String incorrectStringDate = "22-abril-2022";

        assertThrows(WrongDateFormatException.class,
                ()-> DateFormatUtil.convertDateStringToLong(incorrectStringDate));
    }

}