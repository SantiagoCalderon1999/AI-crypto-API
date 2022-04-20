package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import ai.djl.modality.rl.env.RlEnv;
import ai.djl.ndarray.NDList;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.agent.Agent;

public interface Environment extends  AutoCloseable{

    void reset();

    NDList getObservation();

    ActionSpace getActionSpace();

    void step(NDList action);

    @Override
    void close() throws Exception;

}
