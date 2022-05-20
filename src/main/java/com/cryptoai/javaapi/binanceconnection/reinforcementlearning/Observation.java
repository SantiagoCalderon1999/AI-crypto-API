package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

public class Observation {
    private Float open;
    private Float high;
    private Float low;
    private Float close;
    private Float volume;

    public Observation(Float open, Float high, Float low, Float close, Float volume) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public Float getOpen() {
        return open;
    }

    public Float getHigh() {
        return high;
    }

    public Float getLow() {
        return low;
    }

    public Float getClose() {
        return close;
    }

    public Float getVolume() {
        return volume;
    }

}
