package com.cryptoai.javaapi.binanceconnection.entity;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.State;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.StateUtil;

import java.util.ArrayList;
import java.util.List;

public class Analyzer {

    private List<Candlestick> candlestickList;

    private int currentStep;

    public Analyzer(List<Candlestick> candlestickMap) {
        this.candlestickList = candlestickMap;
        this.currentStep = 0;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public List<Float> getClose(){

        List<Float> close = new ArrayList<>();
        for(Candlestick tempEntry: candlestickList){
            close.add(Float.parseFloat(tempEntry.getClose()));
        }

        return close;
    }

    public StateUtil getCurrentObservation(){
        this.currentStep++;

        if (this.currentStep > candlestickList.size()-10){
            this.currentStep = 0;
        }

        List<Candlestick> currentCandlesticks = new ArrayList<>();

        for (int i = 0; i<5; i++){
            currentCandlesticks.add(candlestickList.get(i));
        }

        List<State> currentState = getStateFromCandleSticks(candlestickList);

        return new StateUtil(currentState);
    }

    private List<State> getStateFromCandleSticks(List<Candlestick> currentCandlesticks){

        List<State> state = new ArrayList<>();

        for(Candlestick tempEntry: currentCandlesticks){
            state.add(new State(Float.parseFloat(tempEntry.getClose()),
                    Float.parseFloat(tempEntry.getClose()),
                    Float.parseFloat(tempEntry.getClose()),
                    Float.parseFloat(tempEntry.getClose()),
                    Float.parseFloat(tempEntry.getClose())));
        }

        return state;
    }

}
