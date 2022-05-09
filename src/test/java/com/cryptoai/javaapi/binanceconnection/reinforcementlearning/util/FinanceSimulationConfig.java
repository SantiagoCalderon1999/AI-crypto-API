package com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinanceSimulationConfig {

    @Bean
    FinanceSimulation financeSimulation(){
        return new FinanceSimulation();
    }

}
