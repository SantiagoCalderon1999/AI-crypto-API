package com.cryptoai.javaapi.binanceconnection.entity;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.Environment;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.State;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.StateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Analyzer {

    private static List<Candlestick> candlestickList;

    private static int currentStep;

    private static int globalStep;

    private static boolean isEpochOngoing;

    private FinanceSimulation financeSimulation;

    private static Logger logger = LoggerFactory.getLogger(Analyzer.class);

    public Analyzer(List<Candlestick> candlestickMap) {
        this.candlestickList = candlestickMap;
        this.currentStep = 0;
        this.globalStep = 0;
        this.isEpochOngoing = false;
        this.financeSimulation = new FinanceSimulation();
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }

    public FinanceSimulation getFinanceSimulation() {
        return financeSimulation;
    }

    public void setFinanceSimulation(FinanceSimulation financeSimulation) {
        this.financeSimulation = financeSimulation;
    }

    public List<Float> getClose(){

        List<Float> close = new ArrayList<>();
        for(Candlestick tempEntry: candlestickList){
            close.add(Float.parseFloat(tempEntry.getClose()));
        }

        return close;
    }

    public static StateUtil getCurrentObservation(){
        currentStep++;
        globalStep++;


        if (currentStep > candlestickList.size() - 10){
            isEpochOngoing = false;
            logger.info("=====> Stop");
        }

        List<Candlestick> currentCandlesticks = new ArrayList<>();

        for (int i = currentStep; i < currentStep + 5; i++){
            currentCandlesticks.add(candlestickList.get(i));
        }

        List<State> currentState = getStateFromCandleSticks(currentCandlesticks);
        return new StateUtil(currentState);
    }

    private static List<State> getStateFromCandleSticks(List<Candlestick> currentCandlesticks){

        List<State> state = new ArrayList<>();

        for(Candlestick tempEntry: currentCandlesticks){
            state.add(new State(Float.parseFloat(tempEntry.getClose()),
                    Float.parseFloat(tempEntry.getOpen()),
                    Float.parseFloat(tempEntry.getHigh()),
                    Float.parseFloat(tempEntry.getLow()),
                    Float.parseFloat(tempEntry.getVolume())));
        }

        return state;
    }

    public boolean isEpochFinished(){

        if (globalStep < 10)
        {
            this.isEpochOngoing = false;
        }

        return !this.isEpochOngoing;
    }

    public StateUtil initializeEpoch(){
        currentStep = 0;
        isEpochOngoing = true;

        financeSimulation.resetSimulation();
        return getCurrentObservation();
    }

    public Float[]  getArrayFromCandlestickByIndex(int index){
        Float[] candlestickArray = new Float[5];

        candlestickArray[0] = Float.parseFloat(candlestickList.get(index).getOpen());
        candlestickArray[1] = Float.parseFloat(candlestickList.get(index).getClose());
        candlestickArray[2] = Float.parseFloat(candlestickList.get(index).getHigh());
        candlestickArray[3] = Float.parseFloat(candlestickList.get(index).getLow());
        candlestickArray[4] = Float.parseFloat(candlestickList.get(index).getVolume());

        return candlestickArray;
    }

}
