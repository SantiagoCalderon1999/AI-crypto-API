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

    public static Long initializeNetwork(Long seed, int maxStep){

            logger.info("=====> Initialize neural network");

            // create random name to the network
            Long randomId =  System.currentTimeMillis();
            String randomNetworkName = "network-" + randomId + ".zip";

            // create training environment
            Environment mdp = new Environment(trainingHelper, reward);

            NetworkIteration networkIteration = new NetworkIteration(seed, maxStep, mdp);

            QLearningDiscreteDense<StateUtil> bestDql = networkIteration.train();


            // save network
            try {
                bestDql.getNeuralNet().save("src/main/resources/" + randomNetworkName);
                logger.info("=====> Saved neural network");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return randomId;
    }


}
