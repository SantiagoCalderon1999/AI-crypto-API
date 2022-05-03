package com.cryptoai.javaapi.binanceconnection.binanceconnection;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.entity.CryptoData;
import com.cryptoai.javaapi.binanceconnection.util.ConstantsUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DataFactoryTest {

    @Mock
    CryptoData cryptoData;

    @InjectMocks
    DataFactory dataFactory;

    @Test
    void retrieveCandlesticks() {
        Calendar startCalendar = new GregorianCalendar(2022, 2, 12);
        String startDate = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT).format(startCalendar.getTime());
        Calendar endCalendar = new GregorianCalendar(2022, 2, 12);
        String endDate = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT).format(endCalendar.getTime());

        List<Candlestick> candlestickList = DataFactory.retrieveCandlesticks("ETHUSDT", startDate, endDate);

        assertNotNull(candlestickList);
    }

}