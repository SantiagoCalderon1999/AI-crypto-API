package com.cryptoai.javaapi.binanceconnection.entity;

import java.util.List;

public class Result {

    private List<Float> close;

    public Result(List<Float> close) {
        this.close = close;
    }

    public List<Float> getClose() {
        return close;
    }

    public void setClose(List<Float> close) {
        this.close = close;
    }
}
