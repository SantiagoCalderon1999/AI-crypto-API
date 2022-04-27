package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.entity.CryptoData;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.NetworkUtil;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.StateUtil;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.CryptoStateUtil;
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

    public static CryptoData initializeNetwork(CryptoData cryptoData, Long seed, int maxStep){

            logger.info("=====> Initialize neural network");

            // create random name to the network
            String randomNetworkName = "network-" + System.currentTimeMillis() + ".zip";



            // create training environment
            Environment mdp = new Environment(cryptoData);

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
            /*
            cryptoData.initializeEpoch();
            logger.info("Testing: " + randomNetworkName);

            evaluateNetwork(cryptoData, randomNetworkName);
*/
        return mdp.getCryptoData();
    }

    private static void evaluateNetwork(CryptoData cryptoData, String randomNetworkName){
        MultiLayerNetwork multiLayerNetwork = NetworkUtil.loadNetwork(randomNetworkName);

        while(cryptoData.isEpochFinished()){
            StateUtil state = cryptoData.getCurrentObservation();
            INDArray output = multiLayerNetwork.output(state.getMatrix(), false);
            double[] data = output.data().asDouble();
            int maxValueIndex = CryptoStateUtil.getMaxValueIndex(data);

        }
    }
}
