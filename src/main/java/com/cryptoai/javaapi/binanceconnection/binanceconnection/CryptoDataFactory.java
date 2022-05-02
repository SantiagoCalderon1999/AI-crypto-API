package com.cryptoai.javaapi.binanceconnection.binanceconnection;

import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.cryptoai.javaapi.binanceconnection.util.ConstantsUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CryptoDataFactory {

    public List<Candlestick> candleStickInitialization(String symbol, String startDate) {
        CandlestickRetriever candlestickRetriever = new CandlestickRetriever();

        // set symbol of cryptocurrency pair
        //               interval between each Candlestick
        //               start time
        //               end time
        candlestickRetriever.setSymbol(symbol);
        candlestickRetriever.setInterval(CandlestickInterval.FIFTEEN_MINUTES);
        candlestickRetriever.setStartTime(startDate);
        String currentDate = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT).format(new Date());
        candlestickRetriever.setEndTime(currentDate);

        // retrieve candlesticks from Binance API
        candlestickRetriever.retrieveBinanceCandlesticks();

        // save candlesticks using a List
        List<Candlestick> candlesticksList = candlestickRetriever.getCandlesticksList();
        return candlesticksList;
    }
}
