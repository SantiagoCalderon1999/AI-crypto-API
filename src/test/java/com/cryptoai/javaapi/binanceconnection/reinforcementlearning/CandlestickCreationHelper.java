package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.binance.api.client.domain.market.Candlestick;

public class CandlestickCreationHelper {

    public static Candlestick createCandlestick(String closePrice, String openPrice, String lowPrice, String highPrice, String volume, long closeTime){
        Candlestick candlestick = new Candlestick();

        candlestick.setClose(closePrice);
        candlestick.setOpen(openPrice);
        candlestick.setLow(lowPrice);
        candlestick.setHigh(highPrice);
        candlestick.setVolume(volume);

        candlestick.setCloseTime(closeTime);

        return candlestick;
    }
}
