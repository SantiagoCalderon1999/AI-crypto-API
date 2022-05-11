package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.NetworkUtil;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.StateUtil;
import org.deeplearning4j.rl4j.learning.sync.qlearning.discrete.QLearningDiscreteDense;

public class NetworkIteration {

    private Long seed;
    private int maxStep;
    private Environment mdp;

    public NetworkIteration(Long seed, int maxStep, Environment mdp) {
        this.mdp = mdp;
        this.seed = seed;
        this.maxStep = maxStep;
    }

    public QLearningDiscreteDense<StateUtil> createQLearningDiscreteDense() {
        QLearningDiscreteDense<StateUtil> dql = new QLearningDiscreteDense<>(
                mdp,
                NetworkUtil.buildDQNFactory(),
                NetworkUtil.buildConfig(seed, maxStep)
        );
        return dql;
    }
}
