package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.entity.Analyzer;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.config.NetworkConfig;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.config.StockStateUtil;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.rl4j.learning.sync.qlearning.discrete.QLearningDiscreteDense;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class NetworkInitializer {

    private static Logger logger = LoggerFactory.getLogger(NetworkInitializer.class);

    public NetworkInitializer() {
    }

    public static void initializeNetwork(Analyzer analyzer){

        Thread thread = new Thread(()->{
            logger.info("=====> Initialize neural network");

            // create random name to the network
            String randomNetworkName = "network-" + System.currentTimeMillis() + ".zip";



            // create training environment
            Environment mdp = new Environment(analyzer);

            QLearningDiscreteDense<StateUtil> dql = new QLearningDiscreteDense<>(
                    mdp,
                    NetworkConfig.buildDQNFactory(),
                    NetworkConfig.buildConfig()
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

            analyzer.initializeEpoch();
            System.out.println("Testing: " + randomNetworkName);

            evaluateNetwork(analyzer, randomNetworkName);
        });

        thread.start();

    }

    private static void evaluateNetwork(Analyzer analyzer, String randomNetworkName){
        MultiLayerNetwork multiLayerNetwork = NetworkConfig.loadNetwork(randomNetworkName);

        while(analyzer.isEpochFinished()){
            StateUtil state = analyzer.getCurrentObservation();
            INDArray output = multiLayerNetwork.output(state.getMatrix(), false);
            double[] data = output.data().asDouble();
            int maxValueIndex = StockStateUtil.getMaxValueIndex(data);

        }
    }
}
