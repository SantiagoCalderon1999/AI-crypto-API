package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.config.NetworkConfig;
import org.deeplearning4j.rl4j.space.ObservationSpace;
import org.nd4j.linalg.api.ndarray.INDArray;

public class StockObservationSpace implements ObservationSpace<StateUtil> {

    private static final double[] LOWS = StockObservationSpace.createValueArray(NetworkConfig.LOW_VALUE);
    private static final double[] HIGHS = StockObservationSpace.createValueArray(NetworkConfig.HIGH_VALUE);

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int[] getShape() {
        return new int[0];
    }

    @Override
    public INDArray getLow() {
        return null;
    }

    @Override
    public INDArray getHigh() {
        return null;
    }

    private static double[] createValueArray(double value){
        double[] values = new double[NetworkConfig.NUMBER_OF_INPUTS];
        for (int i = 0; i <NetworkConfig.NUMBER_OF_INPUTS; i++){
            values[i] =value;
        }

        return values;
    }
}
