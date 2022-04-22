package com.cryptoai.javaapi.binanceconnection.rest;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.entity.Analyzer;
import com.cryptoai.javaapi.binanceconnection.entity.Result;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.NetworkInitializer;
import com.cryptoai.javaapi.binanceconnection.service.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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


        // throw exception if Date format is not right
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        dateFormat.setLenient(false);

        try{
            dateFormat.parse(startDate);
        } catch(ParseException e){
            throw new CryptoWrongDateFormatException("Wrong date format - " + startDate);
        }

        List<Candlestick> candlestickMap = cryptoService.candleStickInitialization(symbol, startDate);
        Analyzer theAnalyzer = new Analyzer(candlestickMap);
        Result theResult = new Result(theAnalyzer.getClose());

        NetworkInitializer.initializeNetwork();

        return theResult;
    }

}
