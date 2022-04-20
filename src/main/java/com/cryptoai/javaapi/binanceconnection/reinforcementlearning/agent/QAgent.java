package com.cryptoai.javaapi.binanceconnection.reinforcementlearning.agent;

import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.training.Trainer;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.ActionSpace;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

import java.util.Arrays;

public class QAgent implements Agent{

    private Trainer trainer;
    private float rewardDiscount;

    public QAgent(Trainer trainer, float rewardDiscount) {
        this.trainer = trainer;
        this.rewardDiscount = rewardDiscount;
    }

    private Logger logger = LoggerFactory.getLogger(QAgent.class);

    @Override
    public NDList chooseAction(Environment env) {
        ActionSpace actionSpace = env.getActionSpace();
        NDArray actionReward = trainer.evaluate(env.getObservation()).singletonOrThrow().get(0);
        logger.info(Arrays.toString(actionReward.toArray()));
        int bestAction = Math.toIntExact(actionReward.argMax().getLong());
        return actionSpace.get(bestAction);
    }

    @Override
    public void trainBatch() {

    }
}
