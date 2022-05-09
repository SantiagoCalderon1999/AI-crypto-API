package com.cryptoai.javaapi.binanceconnection;

import com.cryptoai.javaapi.binanceconnection.binance.CandlestickRetriever;
import com.cryptoai.javaapi.binanceconnection.binance.CryptoData;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CandlestickRetrieverConfig {

        @Bean
        CandlestickRetriever candlestickRetriever(){
            return new CandlestickRetriever();
        }
    }
