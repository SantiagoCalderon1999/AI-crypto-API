package com.cryptoai.javaapi.binanceconnection.entity;

import com.binance.api.client.domain.market.Candlestick;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Analyzer {

    private TreeMap<Long, Candlestick> candlestickMap;

    public Analyzer(TreeMap<Long, Candlestick> candlestickMap) {
        this.candlestickMap = candlestickMap;
    }

    public TreeMap<Long, Candlestick> getCandlestickMap() {
        return candlestickMap;
    }

    public void setCandlestickMap(TreeMap<Long, Candlestick> candlestickMap) {
        this.candlestickMap = candlestickMap;
    }

    public List<Float> getClose(){

        List<Float> close = new ArrayList<>();

        for(Map.Entry<Long, Candlestick> tempTreeMapEntry: candlestickMap.entrySet()){
            String tempClose = tempTreeMapEntry.getValue().getClose();
            close.add(Float.parseFloat(tempClose));
        }

        return close;
    }

}
