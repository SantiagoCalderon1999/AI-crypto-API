package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.StateUtil;
import org.deeplearning4j.gym.StepReply;
import org.deeplearning4j.rl4j.mdp.MDP;
import org.deeplearning4j.rl4j.space.DiscreteSpace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Environment implements MDP<StateUtil, Integer, DiscreteSpace> {

    // Size is 3 as there are 3 actions, i.e. sell, buy and hold
    private final DiscreteSpace actionSpace = new DiscreteSpace(3);

    private final TrainingHelper trainingHelper;

    private final Reward reward;

    private final Logger logger = LoggerFactory.getLogger(Environment.class);

    @Autowired
    public Environment(TrainingHelper trainingHelper, Reward reward) {
        this.trainingHelper = trainingHelper;
        this.reward = reward;
    }

    @Override
    public org.deeplearning4j.rl4j.space.ObservationSpace<StateUtil> getObservationSpace() {
        return new ObservationSpace();
    }

    @Override
    public DiscreteSpace getActionSpace() {
        return actionSpace;
    }

    @Override
    public StateUtil reset() {
        logger.info("=====> Reset");
        trainingHelper.initializeEpoch();
        return trainingHelper.getCurrentObservation();
    }

    @Override
    public void close() {

    }

    public Float getLastValue(){
        return trainingHelper.getLastTrainingValue();
    }

    @Override
    public StepReply<StateUtil> step(Integer actionIndex) {

        Action actionToTake = Action.getActionByIndex(actionIndex);

        double rewardValue = reward.calculateRewardForActionToTake(actionToTake);

        StateUtil observation = trainingHelper.getCurrentObservation();

        return new StepReply<>(
                observation,
                rewardValue,
                isDone(),
                ""
        );
    }



    @Override
    public boolean isDone() {
        return trainingHelper.isEpochFinished();
    }

    @Override
    public MDP<StateUtil, Integer, DiscreteSpace> newInstance() {
        return new Environment(trainingHelper, reward);
    }

    public TrainingHelper getCryptoData() {
        return trainingHelper;
    }
}
