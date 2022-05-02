package com.cryptoai.javaapi.binanceconnection.rest;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.entity.CryptoData;
import com.cryptoai.javaapi.binanceconnection.entity.Result;
import com.cryptoai.javaapi.binanceconnection.entity.ResultList;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.NetworkInitializer;
import com.cryptoai.javaapi.binanceconnection.binanceconnection.CryptoDataFactory;
import com.cryptoai.javaapi.binanceconnection.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CryptoRestController {

    private CryptoDataFactory cryptoDataFactory;

    private ResultList resultList;

    private CryptoData cryptoData;

    @Autowired
    public CryptoRestController(ResultList resultList,
                                CryptoDataFactory cryptoDataFactory,
                                CryptoData cryptoData){
        this.resultList = resultList;
        this.cryptoDataFactory = cryptoDataFactory;
        this.cryptoData = cryptoData;
    }

    @GetMapping("/crypto/{symbol}/{startDate}/{seed}/{maxStep}")
    public List<Result> getCryptoData(@PathVariable String symbol,
                                      @PathVariable String startDate,
                                      @PathVariable Long seed,
                                      @PathVariable int maxStep){

        if (DateFormatUtil.hasErrors(startDate)){
            throw new WrongDateFormatException("Wrong date format - " + startDate);
        }

        List<Candlestick> candlestickList = cryptoDataFactory.candleStickInitialization(symbol, startDate);

        cryptoData.setCandlestickList(candlestickList);

        NetworkInitializer.initializeNetwork(seed, maxStep);

        resultList.setResults(cryptoData);

        return resultList.getResults();
    }

}
