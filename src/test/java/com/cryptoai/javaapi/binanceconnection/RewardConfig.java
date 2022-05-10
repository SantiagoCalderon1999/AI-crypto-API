package com.cryptoai.javaapi.binanceconnection;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.TrainingHelper;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.Reward;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RewardConfig {

    @Bean
    Reward reward(TrainingHelper trainingHelper, FinanceSimulation financeSimulation){
        return new Reward(financeSimulation, trainingHelper);
    }
}
