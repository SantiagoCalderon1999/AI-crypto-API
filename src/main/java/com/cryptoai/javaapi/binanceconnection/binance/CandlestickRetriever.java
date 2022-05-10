package com.cryptoai.javaapi.binanceconnection.binance;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CandlestickRetriever {

    private List<Candlestick> candlesticksList;

    public CandlestickRetriever() {
    }

    public  List<Candlestick> retrieveBinanceCandlesticks(CandlestickInfo candlestickInfo){
        // connect with binance API
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        BinanceApiRestClient client = factory.newRestClient();

        // get candlesticks bars
        candlesticksList = client.getCandlestickBars(
                                            candlestickInfo.getSymbol(),
                                            candlestickInfo.getInterval(),
                                            candlestickInfo.getCandlestickLimit(),
                                            candlestickInfo.getStartTime(),
                                            candlestickInfo.getEndTime());

        return candlesticksList;
    }

}
