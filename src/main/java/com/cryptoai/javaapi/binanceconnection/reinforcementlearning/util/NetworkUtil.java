package com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.rl4j.learning.configuration.QLearningConfiguration;
import org.deeplearning4j.rl4j.network.configuration.DQNDenseNetworkConfiguration;
import org.deeplearning4j.rl4j.network.dqn.DQNFactoryStdDense;
import org.nd4j.linalg.learning.config.RmsProp;

import java.io.File;
import java.io.IOException;

public class NetworkUtil {

    public static int NUMBER_OF_INPUTS = 25;

    public static double LOW_VALUE = -1;

    public static double HIGH_VALUE = 1;

    public static QLearningConfiguration buildConfig(Long seed, int maxStep){
        return QLearningConfiguration.builder()
                .seed(seed)
                .maxEpochStep(10000)
                .maxStep(maxStep)
                .expRepMaxSize(150000)
                .batchSize(128)
                .targetDqnUpdateFreq(500)
                .updateStart(10)
                .rewardFactor(0.01)
                .gamma(0.99)
                .errorClamp(1.0)
                .minEpsilon(0.1f)
                .epsilonNbStep(1000)
                .doubleDQN(true)
                .build();
    }

    public static DQNFactoryStdDense buildDQNFactory(){
        DQNDenseNetworkConfiguration build = DQNDenseNetworkConfiguration.builder()
                .l2(0.001)
                .updater(new RmsProp(0.000025))
                .numHiddenNodes(300)
                .numLayers(2)
                .build();

        return new DQNFactoryStdDense(build);
    }

    /*public static MultiLayerNetwork loadNetwork(String networkName){

        try {
            return MultiLayerNetwork.load(new File(networkName), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

}
