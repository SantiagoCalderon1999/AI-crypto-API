package com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.Observation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StateUtilTest {

    @Test
    void createDataArray() {
        List<Observation> observationList = new ArrayList<>();

        Observation observation1 = new Observation(1000f,1200f,900f,1100f,100000f);
        Observation observation2 = new Observation(2000f,2200f,1900f,2100f,200000f);
        Observation observation3 = new Observation(3000f,3200f,2900f,3100f,300000f);
        Observation observation4 = new Observation(4000f,4200f,3900f,4100f,400000f);
        Observation observation5 = new Observation(5000f,5200f,4900f,5100f,500000f);

        observationList.add(observation1);
        observationList.add(observation2);
        observationList.add(observation3);
        observationList.add(observation4);
        observationList.add(observation5);

        StateUtil stateUtil = new StateUtil(observationList);

        double[] testArray = stateUtil.createDataArray(observationList);

        assertThat(testArray.length).isEqualTo(5 * observationList.size());

    }
}