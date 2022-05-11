package com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.Observation;
import org.deeplearning4j.rl4j.space.Encodable;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.List;

public class StateUtil implements Encodable {

    private final List<Observation> inputs;


    public StateUtil(List<Observation> inputs) {
        this.inputs = inputs;
    }

    public List<Observation> getInputs() {
        return inputs;
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
        return Nd4j.create(createDataArray(inputs)); //
    }

    public INDArray getMatrix() {
        return Nd4j.create(new double[][] {
                createDataArray(inputs)
        });
    }

    @Override
    public Encodable dup() {
        return null;
    }

    public double[] createDataArray(List<Observation> dataList){

        double[] resultDataArray =  new double[NetworkUtil.NUMBER_OF_INPUTS];
        int counter = 0;

            for(Observation tempObservation : dataList){
                resultDataArray[counter*5+0] = tempObservation.getOpen();
                resultDataArray[counter*5+1] = tempObservation.getClose();
                resultDataArray[counter*5+2] = tempObservation.getHigh();
                resultDataArray[counter*5+3] = tempObservation.getLow();
                resultDataArray[counter*5+4] = tempObservation.getVolume();
                counter++;
            }

        return resultDataArray;
    }
}
