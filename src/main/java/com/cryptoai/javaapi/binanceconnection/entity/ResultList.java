package com.cryptoai.javaapi.binanceconnection.entity;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import freemarker.template.SimpleDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOError;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ResultList{

    private CryptoData cryptoData;

    private List<Result> results;

    private FinanceSimulation financeSimulation;

    @Autowired
    public ResultList(FinanceSimulation financeSimulation) {

        results = new ArrayList<>();
        this.financeSimulation = financeSimulation;
    }

    public void setResults(CryptoData cryptoData){

        List<Float> closeNormalized = cryptoData.getNormalizedClose();
        List<Date> dates = cryptoData.getDates();
        List<Float> networkResult = financeSimulation.getTrainingResults();


        for(int i = 0; i < networkResult.size(); i++){
            results.add(new Result(formatDate(dates.get(i)), closeNormalized.get(i), networkResult.get(i)));
        }
    }

    private String formatDate(Date date){
        String output = date.toString();
        return (output.substring(4,16));
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
