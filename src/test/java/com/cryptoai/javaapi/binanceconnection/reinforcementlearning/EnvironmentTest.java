package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class EnvironmentTest {

    @InjectMocks
    Environment environment;

    @Mock
    Action action;

    @Mock
    Reward reward;

    @Mock
    FinanceSimulation financeSimulation;

    @Mock
    TrainingHelper trainingHelper;

    @BeforeEach
    void setUp() {

    }

    @Test
    void step() {

    }

}