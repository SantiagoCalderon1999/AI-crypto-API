package com.cryptoai.javaapi.binanceconnection.reinforcementlearning.config;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.rl4j.learning.configuration.QLearningConfiguration;
import org.deeplearning4j.rl4j.network.configuration.DQNDenseNetworkConfiguration;
import org.deeplearning4j.rl4j.network.dqn.DQNFactoryStdConv;
import org.deeplearning4j.rl4j.network.dqn.DQNFactoryStdDense;
import org.nd4j.linalg.learning.config.RmsProp;

import java.io.File;
import java.io.IOException;

public class NetworkConfig {

    public static int NUMBER_OF_INPUTS = 4;

    public static double LOW_VALUE = -1;

    public static double HIGH_VALUE =1;

    public NetworkConfig() {
    }

    public QLearningConfiguration buildConifg(){
        return QLearningConfiguration.builder()
                .seed(123L)
                .maxEpochStep(200)
                .maxStep(15000)
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

    public DQNFactoryStdDense buildDQNFactory(){
        DQNDenseNetworkConfiguration build = DQNDenseNetworkConfiguration.builder()
                .l2(0.001)
                .updater(new RmsProp(0.000025))
                .numHiddenNodes(300)
                .numLayers(2)
                .build();

        return new DQNFactoryStdDense(build);
    }

    public MultiLayerNetwork loadNetwork(String networkName){

        try {
            return MultiLayerNetwork.load(new File(networkName), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}