package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import org.deeplearning4j.rl4j.space.Encodable;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.List;

public class StateUtil implements Encodable {

    private List<State> inputs;

    public StateUtil(List<State> inputs) {
        this.inputs = inputs;
    }

    @Override
    public double[] toArray() {
        return null;
    }

    @Override
    public boolean isSkipped() {
        return false;
    }

    @Override
    public INDArray getData() {
        return null; //
    }

    @Override
    public Encodable dup() {
        return null;
    }
}
