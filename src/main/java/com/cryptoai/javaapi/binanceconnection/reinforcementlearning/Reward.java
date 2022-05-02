package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.entity.CryptoData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Reward {

    private Logger logger = LoggerFactory.getLogger(Reward.class);
    private float REWARD_CONSTANT = 10;

    private FinanceSimulation financeSimulation;

    @Autowired
    public Reward(FinanceSimulation financeSimulation) {

        this.financeSimulation = financeSimulation;
    }

    public double calculateRewardForActionToTake(Action action, CryptoData cryptoData){

        switch(action){
            case SELL:
                financeSimulation.sellSimulation(cryptoData);
                break;
            case BUY:
                financeSimulation.buySimulation(cryptoData);
                break;
            case HOLD:
                financeSimulation.holdSimulation(cryptoData);
                break;
        }

        float netWorth = financeSimulation.getNetWorth();

        logger.info("Net worth: " + netWorth);

        float reward = ((netWorth / financeSimulation.getInitialAccountBalance()) - 1) * REWARD_CONSTANT;

        return reward;
    }

    private void performAction(){

    }
}
