package com.cryptoai.javaapi.binanceconnection.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultList {

    private CryptoData cryptoData;

    private List<Result> results;

    public ResultList() {

        results = new ArrayList<>();
    }

    public void setResults(CryptoData cryptoData){

        List<Float> closeNormalized = cryptoData.getNormalizedClose();

        List<Float> networkResult = cryptoData.getFinanceSimulation().getTrainingResults();

        System.out.println("It's arriving here");
        System.out.println("Close size: " + closeNormalized.size());
        System.out.println("Network Result: " + networkResult.size());

        for(int i = 1; i < networkResult.size(); i++){
            results.add(new Result(closeNormalized.get(i), networkResult.get(i)));
        }


    }

    public String listToJSON() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = objectMapper.writeValueAsString(results);
         return arrayToJson;
    }



}
