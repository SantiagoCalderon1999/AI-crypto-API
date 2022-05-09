package com.cryptoai.javaapi.binanceconnection.binance;

import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.cryptoai.javaapi.binanceconnection.binance.util.ConstantsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class DataFactory {

    static CryptoData cryptoData;

    static CandlestickRetriever candlestickRetriever;

    @Autowired
    public DataFactory(CryptoData cryptoData, CandlestickRetriever candlestickRetriever) {
        DataFactory.cryptoData = cryptoData;
        DataFactory.candlestickRetriever = candlestickRetriever;
    }

    public static List<Candlestick> retrieveCandlesticks(String symbol, String startDate, String endDate) {
        // set symbol of cryptocurrency pair
        //               interval between each Candlestick
        //               start time
        //               end time
        CandlestickInfo candlestickInfo = new CandlestickInfo(
                                                symbol,
                                                CandlestickInterval.FIFTEEN_MINUTES,
                                                startDate,
                                                endDate);

        // retrieve candlesticks from Binance API
        return candlestickRetriever.retrieveBinanceCandlesticks(candlestickInfo);
    }

    public static void newCryptoDataFromCandlesticks(String symbol, String startDate)
    {
        String currentDate = new SimpleDateFormat(ConstantsUtil.DATE_FORMAT).format(new Date());
        cryptoData.setCandlestickList(DataFactory.retrieveCandlesticks(symbol, startDate, currentDate));
    }

}
