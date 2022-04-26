package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.entity.Analyzer;
import org.deeplearning4j.gym.StepReply;
import org.deeplearning4j.rl4j.mdp.MDP;
import org.deeplearning4j.rl4j.space.DiscreteSpace;
import org.deeplearning4j.rl4j.space.ObservationSpace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Environment implements MDP<StateUtil, Integer, DiscreteSpace> {

    // Size is 3 as there are 3 actions, i.e. sell, buy and hold
    private DiscreteSpace actionSpace = new DiscreteSpace(3);

    @Autowired
    private Analyzer analyzer;

    private Logger logger = LoggerFactory.getLogger(Environment.class);

    public Environment(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    @Override
    public ObservationSpace<StateUtil> getObservationSpace() {
        return new StockObservationSpace();
    }

    @Override
    public DiscreteSpace getActionSpace() {
        return actionSpace;
    }

    @Override
    public StateUtil reset() {
        analyzer.initializeEpoch();
        return analyzer.getCurrentObservation();
    }

    @Override
    public void close() {

    }

    @Override
    public StepReply<StateUtil> step(Integer actionIndex) {

        final Action actionToTake = Action.getActionByIndex(actionIndex);

        Reward theReward = new Reward();
        double reward = theReward.calculateRewardForActionToTake(actionToTake, analyzer);

        StateUtil observation = analyzer.getCurrentObservation();

        //logger.info("=====> is it done? " + isDone());

        return new StepReply<>(
                observation,
                reward,
                isDone(),
                ""
        );
    }



    @Override
    public boolean isDone() {
        return analyzer.isEpochFinished();
    }

    @Override
    public MDP<StateUtil, Integer, DiscreteSpace> newInstance() {
        return new Environment(analyzer);
    }
}
