package com.cryptoai.javaapi.binanceconnection;

import com.cryptoai.javaapi.binanceconnection.binance.CandlestickRetriever;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.TrainingHelper;
import com.cryptoai.javaapi.binanceconnection.binance.DataFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataFactoryConfig {

        @Bean
        DataFactory dataFactory(TrainingHelper trainingHelper, CandlestickRetriever candlestickRetriever){
            return new DataFactory(trainingHelper, candlestickRetriever);
        }
    }
