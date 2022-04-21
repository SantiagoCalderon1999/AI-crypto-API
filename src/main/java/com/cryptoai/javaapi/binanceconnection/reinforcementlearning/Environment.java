package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.entity.Analyzer;
import org.deeplearning4j.gym.StepReply;
import org.deeplearning4j.rl4j.mdp.MDP;
import org.deeplearning4j.rl4j.space.DiscreteSpace;
import org.deeplearning4j.rl4j.space.ObservationSpace;

import java.util.List;

public class Environment implements MDP<StateUtil, Integer, DiscreteSpace> {

    // Size is 3 as there are 3 actions, i.e. sell, buy and hold
    private DiscreteSpace actionSpace = new DiscreteSpace(3);

    private Analyzer stockAnalyzer;

    @Override
    public ObservationSpace<StateUtil> getObservationSpace() {
        return null;
    }

    @Override
    public DiscreteSpace getActionSpace() {
        return null;
    }

    @Override
    public StateUtil reset() {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public StepReply<StateUtil> step(Integer actionIndex) {

        final Action actionToTake = Action.getActionByIndex(actionIndex);

        Reward theReward = new Reward();
        double reward = theReward.calculateRewardForActionToTake(actionToTake);

        StateUtil observation = stockAnalyzer.getCurrentObservation();

        return new StepReply<>(
                observation,
                reward,
                false,
                ""
        );
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public MDP<StateUtil, Integer, DiscreteSpace> newInstance() {
        return null;
    }
}
