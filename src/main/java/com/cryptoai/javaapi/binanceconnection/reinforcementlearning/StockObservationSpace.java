package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.NetworkUtil;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.StateUtil;
import org.deeplearning4j.rl4j.space.ObservationSpace;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class StockObservationSpace implements ObservationSpace<StateUtil> {

    private static final double[] LOWS = StockObservationSpace.createValueArray(NetworkUtil.LOW_VALUE);
    private static final double[] HIGHS = StockObservationSpace.createValueArray(NetworkUtil.HIGH_VALUE);

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int[] getShape() {
        return new int[]{1, NetworkUtil.NUMBER_OF_INPUTS};
    }

    @Override
    public INDArray getLow() {
        return Nd4j.create(LOWS);
    }

    @Override
    public INDArray getHigh() {
        return Nd4j.create(HIGHS);
    }

    private static double[] createValueArray(double value){
        double[] values = new double[NetworkUtil.NUMBER_OF_INPUTS];
        for (int i = 0; i < NetworkUtil.NUMBER_OF_INPUTS; i++){
            values[i] = value;
        }

        return values;
    }
}
