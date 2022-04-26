package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.entity.CryptoData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reward {

    private Logger logger = LoggerFactory.getLogger(Reward.class);
    private float REWARD_CONSTANT = 10;

    public Reward() {

    }

    public double calculateRewardForActionToTake(Action action, CryptoData cryptoData){

        switch(action){
            case SELL:
                    cryptoData.getFinanceSimulation().sellSimulation(cryptoData);
                break;
            case BUY:
                    cryptoData.getFinanceSimulation().buySimulation(cryptoData);
                break;
            case HOLD:
                    cryptoData.getFinanceSimulation().holdSimulation(cryptoData);
                break;
        }

        float netWorth = cryptoData.getFinanceSimulation().getNetWorth();

        logger.info("Net worth: " + netWorth);

        float reward = ((netWorth / cryptoData.getFinanceSimulation().getInitialAccountBalance()) - 1) * REWARD_CONSTANT;

        return reward;
    }

    private void performAction(){

    }
}
