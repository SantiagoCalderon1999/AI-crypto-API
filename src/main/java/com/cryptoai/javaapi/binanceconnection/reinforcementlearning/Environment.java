package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.entity.CryptoData;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.StateUtil;
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

    private CryptoData cryptoData;

    private Logger logger = LoggerFactory.getLogger(Environment.class);

    public Environment(CryptoData cryptoData) {
        this.cryptoData = cryptoData;
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
        logger.info("=====> Reset");
        cryptoData.initializeEpoch();
        return cryptoData.getCurrentObservation();
    }

    @Override
    public void close() {

    }

    @Override
    public StepReply<StateUtil> step(Integer actionIndex) {

        final Action actionToTake = Action.getActionByIndex(actionIndex);

        Reward theReward = new Reward();
        double reward = theReward.calculateRewardForActionToTake(actionToTake, cryptoData);

        StateUtil observation = cryptoData.getCurrentObservation();

        return new StepReply<>(
                observation,
                reward,
                isDone(),
                ""
        );
    }



    @Override
    public boolean isDone() {
        return cryptoData.isEpochFinished();
    }

    @Override
    public MDP<StateUtil, Integer, DiscreteSpace> newInstance() {
        return new Environment(cryptoData);
    }

    public CryptoData getCryptoData() {

        logger.info("=====> Printing Finance data: " + cryptoData.getFinanceSimulation().getTrainingResults());

        return cryptoData;
    }
}
