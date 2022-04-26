package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.entity.Analyzer;

public class FinanceSimulation {

    private float BINANCE_TRADING_FEE = 0.001f;

    private float initialAccountBalance = 1000f;

    private float currentAmountUSD;

    private float cryptoHeld;

    private float currentCryptoPrice;

    public FinanceSimulation() {

        this.cryptoHeld = 0;
        this.currentAmountUSD = initialAccountBalance;
        this.currentCryptoPrice = 0;
    }

    public void buySimulation(Analyzer analyzer){
        int currentStep = analyzer.getCurrentStep();

        // Get close
        currentCryptoPrice = analyzer.getArrayFromCandlestickByIndex(currentStep)[1];

        float amountBought = currentAmountUSD;

        currentAmountUSD -= amountBought;

        cryptoHeld += amountBought / currentCryptoPrice;

    }

    public void sellSimulation(Analyzer analyzer){
        int currentStep = analyzer.getCurrentStep();

        // Get close
        currentCryptoPrice = analyzer.getArrayFromCandlestickByIndex(currentStep)[1];

        float amountSold = cryptoHeld * currentCryptoPrice;

        currentAmountUSD += amountSold;

        cryptoHeld -= cryptoHeld; // Multiply by amount

    }

    public void holdSimulation(Analyzer analyzer){

    }

    public float getNetWorth(){
        return currentAmountUSD + cryptoHeld * currentCryptoPrice;
    }

    public float getInitialAccountBalance() {
        return initialAccountBalance;
    }

    public void resetSimulation(){
        this.cryptoHeld = 0;
        this.currentAmountUSD = initialAccountBalance;
        this.currentCryptoPrice = 0;
    }
}
