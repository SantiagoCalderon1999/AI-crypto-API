package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.NetworkUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nd4j.linalg.api.ndarray.INDArray;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ObservationSpaceTest {

    ObservationSpace observationSpace ;

    @BeforeEach
    void setUp() {

        observationSpace = new ObservationSpace();

    }

    @Test
    void getLow() {
        INDArray lowArray = observationSpace.getLow();
        assertThat(lowArray.size(0)).isEqualTo(NetworkUtil.NUMBER_OF_INPUTS);
    }

    @Test
    void getHigh() {
        INDArray highArray = observationSpace.getHigh();
        assertThat(highArray.size(0)).isEqualTo(NetworkUtil.NUMBER_OF_INPUTS);
    }
}