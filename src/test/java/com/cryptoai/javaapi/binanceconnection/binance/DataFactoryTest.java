package com.cryptoai.javaapi.binanceconnection.binance;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.binance.util.ConstantsUtil;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.TrainingHelper;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataFactory.class, TrainingHelper.class, CandlestickRetriever.class, FinanceSimulation.class})
@SpringBootTest
class DataFactoryTest {

    @Autowired
    CandlestickRetriever candlestickRetriever;

    @Autowired
    TrainingHelper trainingHelper;

    @Autowired
    FinanceSimulation financeSimulation;

    @Before
    public void setUp(){
        reset(DataFactory.class);

    }

    @Test
    void retrieveCandlesticks() {


        // given
        Calendar startCalendar = new GregorianCalendar(2022, 2, 12);
        String startDate = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT).format(startCalendar.getTime());
        Calendar endCalendar = new GregorianCalendar(2022, 2, 12);
        String endDate = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT).format(endCalendar.getTime());

        // when
        List<Candlestick> candlestickList = DataFactory.retrieveCandlesticks("ETHUSDT", startDate, endDate);

        // then
        assertNotNull(candlestickList);
    }

    @Test
    public void newCryptoDataTest() {
        // given
        Calendar startCalendar = new GregorianCalendar(2022, 2, 12);
        String startDate = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT).format(startCalendar.getTime());

        // when
        DataFactory.newCryptoDataFromCandlesticks("ETHUSDT", startDate);

        // then
        assertNotNull(trainingHelper.getCandlestickList());
    }
}