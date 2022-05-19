package com.cryptoai.javaapi.binanceconnection.web.models;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
public class InputConfiguration {

    String currencyPairSymbol;

    String startDate;

    Long seed;

    int stepsPerTraining;

    @Size(max = 1, min = 0)
    Long minGamma;

    @Size(max = 1, min = 0)
    Long maxGamma;

    Long stepQuantityGamma;

    public InputConfiguration() {
    }

    public InputConfiguration(String currencyPairSymbol, String startDate, Long seed, int stepsPerTraining) {
        this.currencyPairSymbol = currencyPairSymbol;
        this.startDate = startDate;
        this.seed = seed;
        this.stepsPerTraining = stepsPerTraining;
    }

}
