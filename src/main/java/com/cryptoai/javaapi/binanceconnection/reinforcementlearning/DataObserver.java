package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.binance.api.client.domain.market.Candlestick;

import java.util.ArrayList;
import java.util.List;

public class DataObserver {

    

    public List<Observation> getStateFromCandleSticks(List<Candlestick> currentCandlesticks){

        List<Observation> observation = new ArrayList<>();

        for(Candlestick tempEntry: currentCandlesticks){
            observation.add(new Observation(
                    Float.parseFloat(tempEntry.getOpen()),
                    Float.parseFloat(tempEntry.getHigh()),
                    Float.parseFloat(tempEntry.getLow()),
                    Float.parseFloat(tempEntry.getClose()),
                    Float.parseFloat(tempEntry.getVolume())));
        }

        return observation;
    }

}
