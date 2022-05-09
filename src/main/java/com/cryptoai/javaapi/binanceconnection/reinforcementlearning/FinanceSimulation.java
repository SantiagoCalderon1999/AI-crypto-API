package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FinanceSimulation {

    private final float BINANCE_TRADING_FEE = 0.001f;

    private float initialAccountBalance = 1000f;

    private float currentAmountUSD;

    private float cryptoHeld;

    private float currentCryptoPrice;

    private List<Float> trainingResults;

    private final Logger logger = LoggerFactory.getLogger(FinanceSimulation.class);

    @Autowired
    public FinanceSimulation() {

        this.cryptoHeld = 0;
        this.currentAmountUSD = initialAccountBalance;
        this.currentCryptoPrice = 0;
        this.trainingResults = new ArrayList<>();
    }

    public float buySimulation(float currentCryptoPrice){
        this.currentCryptoPrice = currentCryptoPrice;

        float amountBought = currentAmountUSD;

        currentAmountUSD -= amountBought * (1 + BINANCE_TRADING_FEE);

        cryptoHeld += amountBought / currentCryptoPrice;

        return getNetWorth();
    }

    public float sellSimulation(float currentCryptoPrice){
        this.currentCryptoPrice = currentCryptoPrice;

        float amountSold = cryptoHeld * currentCryptoPrice;

        currentAmountUSD += amountSold * (1 - BINANCE_TRADING_FEE);

        cryptoHeld -= cryptoHeld; // Multiply by amount
        return getNetWorth();
    }

    public float holdSimulation(float currentCryptoPrice){
        return getNetWorth();
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


    public void setCurrentAmountUSD(float currentAmountUSD) {
        this.currentAmountUSD = currentAmountUSD;
    }

    public void setCryptoHeld(float cryptoHeld) {
        this.cryptoHeld = cryptoHeld;
    }

}
