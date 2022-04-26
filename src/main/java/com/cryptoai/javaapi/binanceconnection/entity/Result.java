package com.cryptoai.javaapi.binanceconnection.entity;

public class Result {

    private float closeNormalized;

    private float networkResult;

    public Result(float closeNormalized, float networkResult) {
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
}
