package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.CryptoDataConfig;
import com.cryptoai.javaapi.binanceconnection.RewardConfig;
import com.cryptoai.javaapi.binanceconnection.binance.CryptoData;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.StateUtil;
import org.deeplearning4j.gym.StepReply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class EnvironmentTest {

    @InjectMocks
    Environment environment;

    @Mock
    Action action;

    @Mock
    Reward reward;

    @Mock
    CryptoData cryptoData;

    @BeforeEach
    void setUp() {

    }

    @Test
    void step() {

    }
}