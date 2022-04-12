package com.cryptoai.javaapi.binanceconnection;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public class CandlestickRetrieval {

    private String symbol;

    private CandlestickInterval interval;

    private Integer candlestickLimit;

    private Long startTime;

    private Long endTime;

    private TreeMap<Long, Candlestick> candlesticksCache;

    public CandlestickRetrieval(){

        // Set by default 500, the maximum number is 1000
        this.candlestickLimit = 500;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public CandlestickInterval getInterval() {
        return interval;
    }

    public void setInterval(CandlestickInterval interval) {
        this.interval = interval;
    }

    public TreeMap<Long, Candlestick> getCandlesticksCache() {
        return candlesticksCache;
    }

    public Integer getCandlestickLimit() {
        return candlestickLimit;
    }

    public void setCandlestickLimit(Integer candlestickLimit) {
        this.candlestickLimit = candlestickLimit;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(String startDateString) {
        this.startTime = convertDateStringToLong(startDateString);
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(String endDateString) {
        this.endTime = convertDateStringToLong(endDateString);
    }

    private Long convertDateStringToLong(String endDateString) {

        // set the String date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

        // parse Date from String format to Long
        Date theDate = null;
        try {
            theDate = dateFormat.parse(endDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return theDate.getTime();
    }

    public void retrieveBinanceCandlesticks(){
        // connect with binance API
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        BinanceApiRestClient client = factory.newRestClient();

        // get candlesticks bars
        List<Candlestick> candlestickBars = client.getCandlestickBars(symbol.toUpperCase(), interval, candlestickLimit, startTime, endTime);

        // save candlesticks using a TreeMap
        this.candlesticksCache = new TreeMap<>();

        for (Candlestick theCandlestickBar: candlestickBars){
            candlesticksCache.put(theCandlestickBar.getOpenTime(), theCandlestickBar);
        }

    }

}