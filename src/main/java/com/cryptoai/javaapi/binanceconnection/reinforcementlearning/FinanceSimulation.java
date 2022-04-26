package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.entity.CryptoData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FinanceSimulation {

    private float BINANCE_TRADING_FEE = 0.001f;

    private float initialAccountBalance = 1000f;

    private float currentAmountUSD;

    private float cryptoHeld;

    private float currentCryptoPrice;

    private List<Float> trainingResults;

    private Logger logger = LoggerFactory.getLogger(FinanceSimulation.class);

    public FinanceSimulation() {

        this.cryptoHeld = 0;
        this.currentAmountUSD = initialAccountBalance;
        this.currentCryptoPrice = 0;
    }

    public void buySimulation(CryptoData cryptoData){
        int currentStep = cryptoData.getCurrentStep();

        // Get close
        currentCryptoPrice = cryptoData.getArrayFromCandlestickByIndex(currentStep)[1];

        float amountBought = currentAmountUSD;

        currentAmountUSD -= amountBought;

        cryptoHeld += amountBought / currentCryptoPrice;

    }

    public void sellSimulation(CryptoData cryptoData){
        int currentStep = cryptoData.getCurrentStep();

        // Get close
        currentCryptoPrice = cryptoData.getArrayFromCandlestickByIndex(currentStep)[1];

        float amountSold = cryptoHeld * currentCryptoPrice;

        currentAmountUSD += amountSold;

        cryptoHeld -= cryptoHeld; // Multiply by amount

    }

    public void holdSimulation(CryptoData cryptoData){

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
