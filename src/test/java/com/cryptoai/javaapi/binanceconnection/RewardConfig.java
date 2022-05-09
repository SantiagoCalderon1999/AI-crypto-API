package com.cryptoai.javaapi.binanceconnection;

import com.cryptoai.javaapi.binanceconnection.binance.CryptoData;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.Reward;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RewardConfig {

    @Bean
    Reward reward(CryptoData cryptoData, FinanceSimulation financeSimulation){
        return new Reward(financeSimulation, cryptoData);
    }
}
