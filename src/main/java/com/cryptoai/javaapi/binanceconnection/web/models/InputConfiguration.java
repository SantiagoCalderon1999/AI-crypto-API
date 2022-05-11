package com.cryptoai.javaapi.binanceconnection.web.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ApiModel
public class InputConfiguration {

    @ApiModelProperty(value = "Cryptocurrency pair symbol", example = "ETHUSDT")
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
