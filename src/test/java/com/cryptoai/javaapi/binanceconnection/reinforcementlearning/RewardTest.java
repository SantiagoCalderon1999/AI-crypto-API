package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.cryptoai.javaapi.binanceconnection.RewardConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.within;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {FinanceSimulation.class, TrainingHelper.class, RewardConfig.class})
@SpringBootTest
class RewardTest {

    @Autowired
    FinanceSimulation financeSimulation;

    @MockBean
    TrainingHelper trainingHelper;

    @Autowired
    Reward reward;

    @BeforeEach
    void setUp() {
        financeSimulation.resetSimulation();
    }

    @Test
    void calculateRewardForHoldingAction() {

        //given
        Action actionToTake = Action.getActionByIndex(2);
        System.out.println(actionToTake);
        when(trainingHelper.getCurrentStep()).thenReturn(0);
        when(trainingHelper.getCloseFromCandlestickByIndex(0)).thenReturn(1000f);


        // when
        double rewardValue = reward.calculateRewardForActionToTake(actionToTake);

        // then
        assertThat(rewardValue).isEqualTo(0);

    }

    @Test
    void calculateRewardForBuyingAction() {

        //given
        Action actionToTake = Action.getActionByIndex(1);
        given(trainingHelper.getCurrentStep()).willReturn(0);
        given(trainingHelper.getCloseFromCandlestickByIndex(0)).willReturn(1000f);

        // when
        double rewardValue = reward.calculateRewardForActionToTake(actionToTake);

        // then
        assertThat(rewardValue).isCloseTo(-0.01, Assertions.offset(0.0001d));
    }

    @Test
    void calculateRewardForSellingAction() {

        //given
        Action actionToTake = Action.getActionByIndex(0);
        System.out.println(actionToTake);
        given(trainingHelper.getCurrentStep()).willReturn(0);
        given(trainingHelper.getCloseFromCandlestickByIndex(0)).willReturn(1000f);
        financeSimulation.setCurrentAmountUSD(0f);
        financeSimulation.setCryptoHeld(1);

        // when
        double rewardValue = reward.calculateRewardForActionToTake(actionToTake);

        // then
        assertThat(rewardValue).isCloseTo(-0.01, Assertions.offset(0.0001d));
    }
}