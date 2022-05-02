package com.cryptoai.javaapi.binanceconnection.binanceconnection;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.cryptoai.javaapi.binanceconnection.util.ConstantsUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

public class CandlestickRetriever {

    private String symbol;

    private CandlestickInterval interval;

    private Integer candlestickLimit;

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
        this.startTime = convertDateStringToLong(startDateString);
    }

    public void setEndTime(String endDateString) {
        this.endTime = convertDateStringToLong(endDateString);
    }

    private Long convertDateStringToLong(String endDateString) {

        // set the String date format
        SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT);

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
        candlesticksList = client.getCandlestickBars(symbol.toUpperCase(),
                interval,
                candlestickLimit,
                startTime,
                endTime);


    }

}
