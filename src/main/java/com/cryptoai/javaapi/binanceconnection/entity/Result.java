package com.cryptoai.javaapi.binanceconnection.entity;

import java.util.Date;

public class Result {

    private String date;

    private float closeNormalized;

    private float networkResult;


    public Result(String date, float closeNormalized, float networkResult) {
        this.date = date;
        this.closeNormalized = closeNormalized;
        this.networkResult = networkResult;
    }

    public float getCloseNormalized() {
        return closeNormalized;
    }

    public void setCloseNormalized(float closeNormalized) {
        this.closeNormalized = closeNormalized;
    }

    public float getNetworkResult() {
        return networkResult;
    }

    public void setNetworkResult(float networkResult) {
        this.networkResult = networkResult;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
