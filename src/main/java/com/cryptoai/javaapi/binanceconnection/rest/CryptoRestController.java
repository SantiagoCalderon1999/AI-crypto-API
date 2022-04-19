package com.cryptoai.javaapi.binanceconnection.rest;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.entity.Analyzer;
import com.cryptoai.javaapi.binanceconnection.entity.Result;
import com.cryptoai.javaapi.binanceconnection.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.TreeMap;

@RestController
public class CryptoRestController {

    private CryptoService cryptoService;

    @Autowired
    public CryptoRestController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("/crypto/{symbol}/{startDate}")
    public Result getCryptoData(@PathVariable String symbol, @PathVariable String startDate){
        TreeMap<Long, Candlestick> candlestickMap = cryptoService.candleStickInitialization(symbol, startDate);
        Analyzer theAnalyzer = new Analyzer(candlestickMap);
        Result theResult = new Result(theAnalyzer.getClose());
        return theResult;
    }

}
