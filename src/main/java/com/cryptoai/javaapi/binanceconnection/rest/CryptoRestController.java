package com.cryptoai.javaapi.binanceconnection.rest;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.entity.CryptoData;
import com.cryptoai.javaapi.binanceconnection.entity.ResultList;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.NetworkInitializer;
import com.cryptoai.javaapi.binanceconnection.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class CryptoRestController {

    private CryptoService cryptoService;

    @Autowired
    public CryptoRestController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("/crypto/{symbol}/{startDate}/{seed}")
    public String getCryptoData(@PathVariable String symbol, @PathVariable String startDate, @PathVariable Long seed){


        // throw exception if Date format is not right
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        dateFormat.setLenient(false);

        try{
            dateFormat.parse(startDate);
        } catch(ParseException e){
            throw new CryptoWrongDateFormatException("Wrong date format - " + startDate);
        }

        List<Candlestick> candlestickMap = cryptoService.candleStickInitialization(symbol, startDate);
        CryptoData theCryptoData = new CryptoData(candlestickMap);

        CryptoData cryptoData = NetworkInitializer.initializeNetwork(theCryptoData, seed);

        ResultList theResultList = new ResultList();


        theResultList.setResults(cryptoData);

        String stringResult;

        try {
            stringResult = theResultList.listToJSON();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stringResult;
    }

}
