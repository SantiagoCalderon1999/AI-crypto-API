package com.cryptoai.javaapi.binanceconnection.service;

import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.cryptoai.javaapi.binanceconnection.binanceconnection.CandlestickRetriever;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

@Service
public class CryptoService {


    public static TreeMap<Long, Candlestick> candleStickInitialization(String symbol, String startDate) {
        CandlestickRetriever candlestickRetriever = new CandlestickRetriever();

        // set symbol of cryptocurrency pair
        //               interval between each Candlestick
        //               start time
        //               end time
        candlestickRetriever.setSymbol(symbol);
        candlestickRetriever.setInterval(CandlestickInterval.FIFTEEN_MINUTES);
        candlestickRetriever.setStartTime(startDate);
        String currentDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
        candlestickRetriever.setEndTime(currentDate);

        // retrieve candlesticks from Binance API
        candlestickRetriever.retrieveBinanceCandlesticks();

        // save candlesticks using a TreeMap
        TreeMap<Long, Candlestick> candlestickMap = candlestickRetriever.getCandlesticksCache();
        return candlestickMap;
    }
}
