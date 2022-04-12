package com.cryptoai.javaapi;

import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.cryptoai.javaapi.binanceconnection.CandlestickRetrieval;

import java.util.Date;
import java.util.TreeMap;

public class BinanceConnectionCandlestickTest {

    public static void main(String[] args) {

        TreeMap<Long, Candlestick> candlestickMap = candleStickInitialization();

        // print the information retrieved from the API
        System.out.println(candlestickMap);

        Date startDate = new Date(candlestickMap.firstKey());

        Date endDate = new Date(candlestickMap.lastKey());

        // log start date and end date for comparing purposes
        System.out.println("\nStart Date: " + startDate);
        System.out.println(candlestickMap.get(candlestickMap.firstKey()));

        System.out.println("\nEnd Date: " + endDate);
        System.out.println(candlestickMap.get(candlestickMap.lastKey()));

    }

    private static TreeMap<Long, Candlestick> candleStickInitialization() {
        CandlestickRetrieval candlestickRetrieval = new CandlestickRetrieval();

        // set symbol of cryptocurrency pair
        //               interval between each Candlestick
        //               start time
        //               end time
        candlestickRetrieval.setSymbol("BTCUSDT");
        candlestickRetrieval.setInterval(CandlestickInterval.FIFTEEN_MINUTES);
        candlestickRetrieval.setStartTime("10-April-2022");
        candlestickRetrieval.setEndTime("12-April-2022");

        // retrieve candlesticks from Binance API
        candlestickRetrieval.retrieveBinanceCandlesticks();

        // save candlesticks using a TreeMap
        TreeMap<Long, Candlestick> candlestickMap = candlestickRetrieval.getCandlesticksCache();
        return candlestickMap;
    }

}
