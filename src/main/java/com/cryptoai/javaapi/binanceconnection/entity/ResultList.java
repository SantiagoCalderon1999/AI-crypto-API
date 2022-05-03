package com.cryptoai.javaapi.binanceconnection.entity;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ResultList{

    private final CryptoData cryptoData;

    private final List<Result> results;

    private final FinanceSimulation financeSimulation;

    @Autowired
    public ResultList(FinanceSimulation financeSimulation, CryptoData cryptoData) {

        results = new ArrayList<>();
        this.financeSimulation = financeSimulation;
        this.cryptoData = cryptoData;
    }

    public void setResults(){

        List<Float> closeNormalized = cryptoData.getNormalizedClose();
        List<Date> dates = cryptoData.getDates();
        List<Float> networkResult = financeSimulation.getTrainingResults();


        for(int i = 0; i < networkResult.size(); i++){
            results.add(new Result(formatDate(dates.get(i)), closeNormalized.get(i), networkResult.get(i)));
        }
    }

    public List<Result> computeResults(){
        setResults();
        return getResults();
    }

    private String formatDate(Date date){
        String output = date.toString();
        return (output.substring(4,16));
    }

    public List<Result> getResults() {
        return results;
    }

}
