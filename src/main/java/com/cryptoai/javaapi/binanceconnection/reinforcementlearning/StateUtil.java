package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.config.NetworkConfig;
import org.deeplearning4j.rl4j.space.Encodable;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

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
        return Nd4j.create(createDataMatrix(inputs)); //
    }

    public INDArray getMatrix() {
        return Nd4j.create(new double[][] {
                createDataMatrix(inputs)
        });
    }

    @Override
    public Encodable dup() {
        return null;
    }

    private double[] createDataMatrix(List<State> dataList){

        double[] resultDataMatrix =  new double[NetworkConfig.NUMBER_OF_INPUTS];
        int counter = 0;
            for(State tempState: dataList){
                resultDataMatrix[counter*5+0] = tempState.getOpen();
                resultDataMatrix[counter*5+1] = tempState.getClose();
                resultDataMatrix[counter*5+2] = tempState.getHigh();
                resultDataMatrix[counter*5+3] = tempState.getLow();
                resultDataMatrix[counter*5+4] = tempState.getVolume();
                counter++;
            }

        return resultDataMatrix;
    }
}
