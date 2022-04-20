package com.cryptoai.javaapi.binanceconnection.reinforcementlearning.agent;

import ai.djl.ndarray.NDList;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.Environment;

public interface Agent {

    NDList chooseAction(Environment env);

    void trainBatch();
}
