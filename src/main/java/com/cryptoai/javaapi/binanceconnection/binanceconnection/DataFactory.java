package com.cryptoai.javaapi.binanceconnection.binanceconnection;

import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.cryptoai.javaapi.binanceconnection.entity.CryptoData;
import com.cryptoai.javaapi.binanceconnection.util.ConstantsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class DataFactory {

    static CryptoData cryptoData;

    @Autowired
    public DataFactory(CryptoData cryptoData) {
        DataFactory.cryptoData = cryptoData;
    }

    public static List<Candlestick> retrieveCandlesticks(String symbol, String startDate, String endDate) {
        CandlestickRetriever candlestickRetriever = new CandlestickRetriever();

        // set symbol of cryptocurrency pair
        //               interval between each Candlestick
        //               start time
        //               end time
        candlestickRetriever.setSymbol(symbol);
        candlestickRetriever.setInterval(CandlestickInterval.FIFTEEN_MINUTES);
        candlestickRetriever.setStartTime(startDate);
        candlestickRetriever.setEndTime(endDate);

        // retrieve candlesticks from Binance API
        candlestickRetriever.retrieveBinanceCandlesticks();

        return candlestickRetriever.getCandlesticksList();
    }

    public static void newCryptoDataFromCandlesticks(String symbol, String startDate)
    {
        String currentDate = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT).format(new Date());
        cryptoData.setCandlestickList(DataFactory.retrieveCandlesticks(symbol, startDate, currentDate));
    }
}
