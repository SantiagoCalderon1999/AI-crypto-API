package com.cryptoai.javaapi.binanceconnection;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.TrainingHelper;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CryptoDataConfig {

        @Bean
        TrainingHelper cryptoData(FinanceSimulation financeSimulation){
            return new TrainingHelper(financeSimulation);
        }
    }
