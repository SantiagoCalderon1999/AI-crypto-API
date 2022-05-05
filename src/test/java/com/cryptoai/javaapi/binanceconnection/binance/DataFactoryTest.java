package com.cryptoai.javaapi.binanceconnection.binance;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.binance.util.ConstantsUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DataFactoryTest {

    @Mock
    CryptoData cryptoData;

    @Mock
    CandlestickRetriever candlestickRetriever;

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