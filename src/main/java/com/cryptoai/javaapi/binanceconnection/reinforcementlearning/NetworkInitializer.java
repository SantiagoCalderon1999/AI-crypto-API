package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.binance.CryptoData;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.NetworkUtil;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.StateUtil;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.CryptoStateUtil;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.rl4j.learning.sync.qlearning.discrete.QLearningDiscreteDense;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NetworkInitializer {

    private static final Logger logger = LoggerFactory.getLogger(NetworkInitializer.class);

    private static CryptoData cryptoData;

    private static Reward reward;

    @Autowired
    public NetworkInitializer(CryptoData cryptoData, Reward reward) {
        NetworkInitializer.cryptoData = cryptoData;
        NetworkInitializer.reward = reward;
    }

    public static void initializeNetwork(Long seed, int maxStep){

            logger.info("=====> Initialize neural network");

            // create random name to the network
            String randomNetworkName = "network-" + System.currentTimeMillis() + ".zip";



            // create training environment
            Environment mdp = new Environment(cryptoData, reward);

            QLearningDiscreteDense<StateUtil> dql = new QLearningDiscreteDense<>(
                    mdp,
                    NetworkUtil.buildDQNFactory(),
                    NetworkUtil.buildConfig(seed, maxStep)
            );

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

}
