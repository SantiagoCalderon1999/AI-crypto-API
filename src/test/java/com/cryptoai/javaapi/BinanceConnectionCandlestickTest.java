package com.cryptoai.javaapi;

import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.cryptoai.javaapi.binanceconnection.binanceconnection.CandlestickRetriever;

import java.text.SimpleDateFormat;
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
        CandlestickRetriever candlestickRetriever = new CandlestickRetriever();

        // set symbol of cryptocurrency pair
        //               interval between each Candlestick
        //               start time
        //               end time
        candlestickRetriever.setSymbol("BTCUSDT");
        candlestickRetriever.setInterval(CandlestickInterval.FIFTEEN_MINUTES);
        candlestickRetriever.setStartTime("16-April-2022");
        String currentDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
        candlestickRetriever.setEndTime(currentDate);

        // retrieve candlesticks from Binance API
        candlestickRetriever.retrieveBinanceCandlesticks();

        // save candlesticks using a TreeMap
        TreeMap<Long, Candlestick> candlestickMap = null;
        return candlestickMap;
    }

}
