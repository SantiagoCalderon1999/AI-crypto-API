package com.cryptoai.javaapi.binanceconnection.web.behavior;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.TrainingHelper;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import com.cryptoai.javaapi.binanceconnection.web.models.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ResultsBehavior {

    private final TrainingHelper trainingHelper;

    private final List<Result> results;

    private final FinanceSimulation financeSimulation;

    @Autowired
    public ResultsBehavior(FinanceSimulation financeSimulation, TrainingHelper trainingHelper) {

        results = new ArrayList<>();
        this.financeSimulation = financeSimulation;
        this.trainingHelper = trainingHelper;
    }

    public void setResults(){

        List<Float> closeNormalized = trainingHelper.getNormalizedClose();
        List<Date> dates = trainingHelper.getDates();
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
