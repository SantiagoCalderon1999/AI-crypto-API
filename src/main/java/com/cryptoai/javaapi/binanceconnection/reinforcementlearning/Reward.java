package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.entity.CryptoData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Reward {

    private final Logger logger = LoggerFactory.getLogger(Reward.class);

    private final FinanceSimulation financeSimulation;

    private final CryptoData cryptoData;

    @Autowired
    public Reward(FinanceSimulation financeSimulation, CryptoData cryptoData) {

        this.financeSimulation = financeSimulation;
        this.cryptoData = cryptoData;
    }

    public double calculateRewardForActionToTake(Action action){

        int currentStep = cryptoData.getCurrentStep();

        float currentCryptoPrice = cryptoData.getCloseFromCandlestickByIndex(currentStep);

        switch(action){
            case SELL:
                financeSimulation.sellSimulation(currentCryptoPrice);
                break;
            case BUY:
                financeSimulation.buySimulation(currentCryptoPrice);
                break;
            case HOLD:
                financeSimulation.holdSimulation(currentCryptoPrice);
                break;
        }

        float netWorth = financeSimulation.getNetWorth();

        logger.info("Net worth: " + netWorth);

        float REWARD_CONSTANT = 10;

        return ((netWorth / financeSimulation.getInitialAccountBalance()) - 1) * REWARD_CONSTANT;
    }

}
