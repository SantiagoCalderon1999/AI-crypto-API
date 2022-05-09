package com.cryptoai.javaapi.binanceconnection;

import com.cryptoai.javaapi.binanceconnection.binance.CandlestickRetriever;
import com.cryptoai.javaapi.binanceconnection.binance.CryptoData;
import com.cryptoai.javaapi.binanceconnection.binance.DataFactory;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataFactoryConfig {

        @Bean
        DataFactory dataFactory(CryptoData cryptoData, CandlestickRetriever candlestickRetriever){
            return new DataFactory(cryptoData, candlestickRetriever);
        }
    }
