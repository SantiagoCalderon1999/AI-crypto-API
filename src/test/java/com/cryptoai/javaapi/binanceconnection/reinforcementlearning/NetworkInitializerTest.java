package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.RewardConfig;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.StateUtil;
import org.deeplearning4j.rl4j.learning.sync.qlearning.discrete.QLearningDiscreteDense;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RewardConfig.class, TrainingHelper.class, FinanceSimulation.class})
@SpringBootTest
class NetworkInitializerTest {

    @Autowired
    FinanceSimulation financeSimulation;

    @Autowired
    TrainingHelper trainingHelper;

    @Autowired
    Reward reward;

}