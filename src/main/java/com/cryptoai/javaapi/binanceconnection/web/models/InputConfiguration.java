package com.cryptoai.javaapi.binanceconnection.web.models;

import lombok.*;

@Getter
@Setter
public class InputConfiguration {

    String currencyPairSymbol;

    String startDate;

    Long seed;

    int maxStep;

    public InputConfiguration() {
    }

    public InputConfiguration(String currencyPairSymbol, String startDate, Long seed, int maxStep) {
        this.currencyPairSymbol = currencyPairSymbol;
        this.startDate = startDate;
        this.seed = seed;
        this.maxStep = maxStep;
    }

}
