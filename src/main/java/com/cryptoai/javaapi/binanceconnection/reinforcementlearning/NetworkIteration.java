package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.NetworkUtil;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.StateUtil;
import org.deeplearning4j.rl4j.learning.sync.qlearning.discrete.QLearningDiscreteDense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkIteration {

    private final Logger logger = LoggerFactory.getLogger(NetworkInitializer.class);

    private Long seed;
    private int maxStep;
    private Environment mdp;
    float bestValue;

    public NetworkIteration(Long seed, int maxStep, Environment mdp) {
        this.mdp = mdp;
        this.seed = seed;
        this.maxStep = maxStep;
        this.bestValue = 0;
    }

    public QLearningDiscreteDense<StateUtil> createQLearningDiscreteDense() {
        QLearningDiscreteDense<StateUtil> dql = new QLearningDiscreteDense<>(
                mdp,
                NetworkUtil.buildDQNFactory(),
                NetworkUtil.buildConfig(seed, maxStep)
        );
        return dql;
    }

    public QLearningDiscreteDense<StateUtil> train(){

        QLearningDiscreteDense<StateUtil> bestDql = trainingIteration();

        return bestDql;
    }

    public QLearningDiscreteDense<StateUtil> trainingIteration(){

        QLearningDiscreteDense<StateUtil> dql = createQLearningDiscreteDense();

        dql.train();
        logger.info("=====> Finished training");
        mdp.close();

        float currentValue = mdp.getLastValue();

        if (currentValue > bestValue){
            bestValue = currentValue;
        }

        return dql;
    }

}
