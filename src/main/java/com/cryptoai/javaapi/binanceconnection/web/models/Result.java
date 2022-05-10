package com.cryptoai.javaapi.binanceconnection.web.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class Result {

    private String date;

    private float closeNormalized;

    private float networkResult;

    public Result( String date, float closeNormalized, float networkResult) {
        this.date = date;
        this.closeNormalized = closeNormalized;
        this.networkResult = networkResult;
    }
}
