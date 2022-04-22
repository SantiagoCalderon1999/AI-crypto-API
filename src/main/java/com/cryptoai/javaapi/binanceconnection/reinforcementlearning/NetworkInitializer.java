package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.config.NetworkConfig;
import org.deeplearning4j.rl4j.learning.sync.qlearning.discrete.QLearningDiscreteDense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class NetworkInitializer {

    private static Logger logger = LoggerFactory.getLogger(NetworkInitializer.class);

    public NetworkInitializer() {
    }

    public static void initializeNetwork(){

        Thread thread = new Thread(()->{
            logger.error("=====> Initialize neural network");

            // create random name to the network
            String randomNetworkName = "network-" + System.currentTimeMillis() + ".zip";



            // create training environment
            Environment env = new Environment();
            QLearningDiscreteDense<StateUtil> dql = new QLearningDiscreteDense<>(
                    env,
                    NetworkConfig.buildDQNFactory(),
                    NetworkConfig.buildConfig()
            );

            // train network
            dql.train();
            env.close();

            // save network

            try {
                dql.getNeuralNet().save(randomNetworkName);
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        thread.start();

    }
}
