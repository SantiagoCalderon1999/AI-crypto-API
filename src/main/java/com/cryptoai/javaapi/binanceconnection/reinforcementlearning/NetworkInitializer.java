package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.NetworkUtil;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.StateUtil;
import org.deeplearning4j.rl4j.learning.sync.qlearning.discrete.QLearningDiscreteDense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NetworkInitializer {

    private static final Logger logger = LoggerFactory.getLogger(NetworkInitializer.class);

    private static TrainingHelper trainingHelper;

    private static Reward reward;

    @Autowired
    public NetworkInitializer(TrainingHelper trainingHelper, Reward reward) {
        NetworkInitializer.trainingHelper = trainingHelper;
        NetworkInitializer.reward = reward;
    }

    public static void initializeNetwork(Long seed, int maxStep){

            logger.info("=====> Initialize neural network");

            // create random name to the network
            String randomNetworkName = "network-" + System.currentTimeMillis() + ".zip";

            // create training environment
            Environment mdp = new Environment(trainingHelper, reward);

            QLearningDiscreteDense<StateUtil> dql = createQLearningDiscreteDense(seed, maxStep, mdp);

            // train network
            dql.train();
            logger.info("=====> Finished training");
            mdp.close();


            // save network
            try {
                dql.getNeuralNet().save(randomNetworkName);
                logger.info("=====> Saved neural network");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static QLearningDiscreteDense<StateUtil> createQLearningDiscreteDense(Long seed, int maxStep, Environment mdp) {
        QLearningDiscreteDense<StateUtil> dql = new QLearningDiscreteDense<>(
                mdp,
                NetworkUtil.buildDQNFactory(),
                NetworkUtil.buildConfig(seed, maxStep)
        );
        return dql;
    }

}
