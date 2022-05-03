package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FinanceSimulation {

    private final float BINANCE_TRADING_FEE = 0.001f;

    private final float initialAccountBalance = 1000f;

    private float currentAmountUSD;

    private float cryptoHeld;

    private float currentCryptoPrice;

    private List<Float> trainingResults;

    private final Logger logger = LoggerFactory.getLogger(FinanceSimulation.class);

    public FinanceSimulation() {

        this.cryptoHeld = 0;
        this.currentAmountUSD = initialAccountBalance;
        this.currentCryptoPrice = 0;
    }

    public void buySimulation(float currentCryptoPrice){
        this.currentCryptoPrice = currentCryptoPrice;

        float amountBought = currentAmountUSD;

        currentAmountUSD -= amountBought;

        cryptoHeld += amountBought / currentCryptoPrice;

    }

    public void sellSimulation(float currentCryptoPrice){
        this.currentCryptoPrice = currentCryptoPrice;

        float amountSold = cryptoHeld * currentCryptoPrice;

        currentAmountUSD += amountSold;

        cryptoHeld -= cryptoHeld; // Multiply by amount
    }

    public void holdSimulation(float currentCryptoPrice){

    }


    public float getNetWorth(){
        float netWorth = currentAmountUSD + cryptoHeld * currentCryptoPrice;
        this.trainingResults.add(netWorth);
        return netWorth;
    }

    public float getInitialAccountBalance() {
        return initialAccountBalance;
    }

    public void resetSimulation(){
        this.cryptoHeld = 0;
        this.currentAmountUSD = initialAccountBalance;
        this.currentCryptoPrice = 0;
        logger.info("=====> Restarting everything");
        this.trainingResults = new ArrayList<>();
    }

    public List<Float> getTrainingResults() {
        return trainingResults;
    }

    public void setTrainingResults(List<Float> trainingResults) {
        this.trainingResults = trainingResults;
    }
}
