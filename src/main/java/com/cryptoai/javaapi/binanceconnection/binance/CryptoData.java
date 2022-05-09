package com.cryptoai.javaapi.binanceconnection.binance;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.DataObserver;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.Observation;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.StateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CryptoData {

    private static List<Candlestick> candlestickList;

    private static int currentStep;

    private static int globalStep;

    private static boolean isEpochOngoing;

    private static FinanceSimulation financeSimulation;

    private static final Logger logger = LoggerFactory.getLogger(CryptoData.class);

    @Autowired
    public CryptoData(FinanceSimulation financeSimulation) {
        candlestickList = new ArrayList<>();
        currentStep = 0;
        globalStep = 0;
        isEpochOngoing = false;
        CryptoData.financeSimulation = financeSimulation;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCandlestickList(List<Candlestick> candlestickList) {
        CryptoData.candlestickList = candlestickList;
    }

    @Autowired
    public void setFinanceSimulation(FinanceSimulation financeSimulation) {
        CryptoData.financeSimulation = financeSimulation;
    }

    public List<Float> getNormalizedClose(){

        Candlestick firstElement = candlestickList.get(0);
        float firstClose = Float.parseFloat(firstElement.getClose());
        List<Float> normalizedClose = new ArrayList<>();

        for(Candlestick tempEntry: candlestickList){
            normalizedClose.add(Float.parseFloat(tempEntry.getClose()) / firstClose * financeSimulation.getInitialAccountBalance());
        }

        return normalizedClose;
    }

    public List<Date> getDates(){
        List<Date> dates = new ArrayList<>();
        for(Candlestick tempEntry: candlestickList){
            dates.add(new Date(tempEntry.getCloseTime()));
        }

        return dates;
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

        DataObserver dataObserver = new DataObserver();

        List<Observation> currentObservation = dataObserver.getStateFromCandleSticks(currentCandlesticks);
        return new StateUtil(currentObservation);
    }



    public boolean isEpochFinished(){

        if (globalStep < 10)
        {
            isEpochOngoing = false;
        }

        return !isEpochOngoing;
    }

    public StateUtil initializeEpoch(){
        currentStep = 0;
        isEpochOngoing = true;

        financeSimulation.resetSimulation();
        return getCurrentObservation();
    }

    public float getCloseFromCandlestickByIndex(int index){
        float closePrice;
        closePrice = Float.parseFloat(candlestickList.get(index).getClose());
        return closePrice;
    }

}
