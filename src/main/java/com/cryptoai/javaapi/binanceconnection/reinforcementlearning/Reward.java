package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.entity.Analyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reward {

    private Logger logger = LoggerFactory.getLogger(Reward.class);

    public Reward() {

    }

    public double calculateRewardForActionToTake(Action action, Analyzer analyzer){

        switch(action){
            case SELL:
                    analyzer.getFinanceSimulation().sellSimulation(analyzer);
                break;
            case BUY:
                    analyzer.getFinanceSimulation().buySimulation(analyzer);
                break;
            case HOLD:
                    analyzer.getFinanceSimulation().holdSimulation(analyzer);
                break;
        }

        float netWorth = analyzer.getFinanceSimulation().getNetWorth();

        logger.info("Net worth: " + netWorth);

        float reward = netWorth / analyzer.getFinanceSimulation().getInitialAccountBalance();

        return reward;
    }

    private void performAction(){

    }
}
