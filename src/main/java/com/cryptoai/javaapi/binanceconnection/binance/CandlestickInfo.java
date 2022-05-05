package com.cryptoai.javaapi.binanceconnection.binance;

import com.binance.api.client.domain.market.CandlestickInterval;
import com.cryptoai.javaapi.binanceconnection.binance.util.DateFormatUtil;

public class CandlestickInfo {

    private String symbol;

    private CandlestickInterval interval;

    private Integer candlestickLimit;

    private Long startTime;

    private Long endTime;

    public CandlestickInfo(String symbol, CandlestickInterval interval, String startDate, String endDate) {
        this.symbol = symbol.toUpperCase();
        this.interval = interval;
        this.startTime = DateFormatUtil.convertDateStringToLong(startDate);
        this.endTime = DateFormatUtil.convertDateStringToLong(endDate);
        // Set by default 500, the maximum number is 1000
        this.candlestickLimit = 500;
    }

    public String getSymbol() {
        return symbol;
    }

    public CandlestickInterval getInterval() {
        return interval;
    }

    public Integer getCandlestickLimit() {
        return candlestickLimit;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }
}
