package com.cryptoai.javaapi.binanceconnection.web.controllers;

import com.cryptoai.javaapi.binanceconnection.binance.CryptoData;
import com.cryptoai.javaapi.binanceconnection.web.models.Result;
import com.cryptoai.javaapi.binanceconnection.web.models.ResultList;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.NetworkInitializer;
import com.cryptoai.javaapi.binanceconnection.binance.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CryptoRestController {

    private ResultList resultList;
    private CryptoData cryptoData;

    @Autowired
    public CryptoRestController(ResultList resultList, CryptoData cryptoData){
        this.resultList = resultList;
        this.cryptoData = cryptoData;
    }

    @GetMapping("/crypto/{symbol}/{startDate}/{seed}/{maxStep}")
    public List<Result> getCryptoData(@PathVariable String symbol,
                                      @PathVariable String startDate,
                                      @PathVariable Long seed,
                                      @PathVariable int maxStep){

        DataFactory.newCryptoDataFromCandlesticks(symbol, startDate);

        NetworkInitializer.initializeNetwork(seed, maxStep);

        return resultList.computeResults();
    }

}
