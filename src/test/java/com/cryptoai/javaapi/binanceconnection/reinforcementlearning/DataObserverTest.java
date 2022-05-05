package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.binance.api.client.domain.market.Candlestick;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

class DataObserverTest {



    @Test
    void getStateFromCandleSticks() {

        List<Candlestick> candlestickList = new ArrayList<>();

        Candlestick candlestick1 = new Candlestick();
        candlestick1.setClose("1000");
        candlestick1.setOpen("1100");
        candlestick1.setHigh("1200");
        candlestick1.setLow("900");
        candlestick1.setVolume("100000");

        Candlestick candlestick2 = new Candlestick();
        candlestick2.setClose("2000");
        candlestick2.setOpen("2100");
        candlestick2.setHigh("2200");
        candlestick2.setLow("1900");
        candlestick2.setVolume("200000");


        candlestickList.add(candlestick1);
        candlestickList.add(candlestick2);

        DataObserver dataObserver = new DataObserver();

        List<Observation> observation = dataObserver.getStateFromCandleSticks(candlestickList);

        assertThat(observation.size()).isEqualTo(2);

    }
}