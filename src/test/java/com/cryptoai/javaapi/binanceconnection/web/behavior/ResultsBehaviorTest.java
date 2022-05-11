package com.cryptoai.javaapi.binanceconnection.web.behavior;

import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.FinanceSimulation;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.TrainingHelper;
import com.cryptoai.javaapi.binanceconnection.web.models.Result;
import com.sun.net.httpserver.Authenticator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ResultsBehaviorTest {

    @Mock
    TrainingHelper trainingHelper;

    @Mock
    FinanceSimulation financeSimulation;

    @InjectMocks
    ResultsBehavior resultsBehavior;

    @Test
    void computeResults() {

        // given
        List<Float> closePrices = new ArrayList<>();
        closePrices.add(1200f);
        closePrices.add(1300f);

        List<Date> dates = new ArrayList<>();
        dates.add((new GregorianCalendar(2020, 1, 1)).getTime());
        dates.add((new GregorianCalendar(2020, 2, 1)).getTime());

        List<Float> networkPrices = new ArrayList<>();
        networkPrices.add(1200f);
        networkPrices.add(1400f);

        given(trainingHelper.getNormalizedClose()).willReturn(closePrices);
        given(trainingHelper.getDates()).willReturn(dates);
        given(financeSimulation.getTrainingResults()).willReturn(networkPrices);

        // when
        List<Result> results = resultsBehavior.computeResults();

        // then
        assertThat(results.size()).isEqualTo(2);


    }
}