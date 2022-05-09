package com.cryptoai.javaapi.binanceconnection.binance;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.RewardConfig;
import com.cryptoai.javaapi.binanceconnection.binance.util.ConstantsUtil;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DataFactory.class, CryptoData.class, CandlestickRetriever.class, FinanceSimulation.class})
@SpringBootTest
class DataFactoryTest {

    @Autowired
    CandlestickRetriever candlestickRetriever;

    @Autowired
    CryptoData cryptoData;

    @Autowired
    FinanceSimulation financeSimulation;

    DataFactory dataFactory;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    void retrieveCandlesticks() {
        // given
        Calendar startCalendar = new GregorianCalendar(2022, 2, 12);
        String startDate = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT).format(startCalendar.getTime());
        Calendar endCalendar = new GregorianCalendar(2022, 2, 12);
        String endDate = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT).format(endCalendar.getTime());

        // when
        List<Candlestick> candlestickList = dataFactory.retrieveCandlesticks("ETHUSDT", startDate, endDate);

        // then
        assertNotNull(candlestickList);
    }

}