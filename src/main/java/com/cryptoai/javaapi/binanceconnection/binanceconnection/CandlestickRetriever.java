package com.cryptoai.javaapi.binanceconnection.binanceconnection;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.cryptoai.javaapi.binanceconnection.util.DateFormatUtil;

import java.util.List;

public class CandlestickRetriever {

    private String symbol;

    private CandlestickInterval interval;

    private final Integer candlestickLimit;

    private Long startTime;

    private Long endTime;

    private List<Candlestick> candlesticksList;

    public CandlestickRetriever(){

        // Set by default 500, the maximum number is 1000
        this.candlestickLimit = 500;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setInterval(CandlestickInterval interval) {
        this.interval = interval;
    }

    public List<Candlestick> getCandlesticksList() {
        return candlesticksList;
    }

    public void setStartTime(String startDateString) {
        this.startTime = DateFormatUtil.convertDateStringToLong(startDateString);
    }

    public void setEndTime(String endDateString) {
        this.endTime = DateFormatUtil.convertDateStringToLong(endDateString);
    }



    public void retrieveBinanceCandlesticks(){
        // connect with binance API
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        BinanceApiRestClient client = factory.newRestClient();

        // get candlesticks bars
        candlesticksList = client.getCandlestickBars(symbol.toUpperCase(),
                interval,
                candlestickLimit,
                startTime,
                endTime);
    }

}
