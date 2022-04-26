package com.cryptoai.javaapi.binanceconnection.entity;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.Observation;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.StateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CryptoData {

    private static List<Candlestick> candlestickList;

    private static int currentStep;

    private static int globalStep;

    private static boolean isEpochOngoing;

    private static FinanceSimulation financeSimulation;

    private static Logger logger = LoggerFactory.getLogger(CryptoData.class);

    public CryptoData(List<Candlestick> candlestickMap) {
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

    public List<Float> getNormalizedClose(){

        Candlestick firstElement = candlestickList.get(0);
        float firstClose = Float.parseFloat(firstElement.getClose());
        List<Float> normalizedClose = new ArrayList<>();

        for(Candlestick tempEntry: candlestickList){
            normalizedClose.add(Float.parseFloat(tempEntry.getClose()) / firstClose * getFinanceSimulation().getInitialAccountBalance());
        }

        return normalizedClose;
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

        List<Observation> currentObservation = getStateFromCandleSticks(currentCandlesticks);
        return new StateUtil(currentObservation);
    }

    private static List<Observation> getStateFromCandleSticks(List<Candlestick> currentCandlesticks){

        List<Observation> observation = new ArrayList<>();

        for(Candlestick tempEntry: currentCandlesticks){
            observation.add(new Observation(Float.parseFloat(tempEntry.getClose()),
                    Float.parseFloat(tempEntry.getOpen()),
                    Float.parseFloat(tempEntry.getHigh()),
                    Float.parseFloat(tempEntry.getLow()),
                    Float.parseFloat(tempEntry.getVolume())));
        }

        return observation;
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
