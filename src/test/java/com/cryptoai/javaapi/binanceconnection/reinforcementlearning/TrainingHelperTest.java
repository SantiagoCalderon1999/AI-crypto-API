package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.binance.util.ConstantsUtil;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util.StateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.plaf.nimbus.State;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TrainingHelperTest {

    @Mock
    FinanceSimulation financeSimulation;

    @InjectMocks
    TrainingHelper trainingHelper;

    List<Candlestick> testingList;

    int TESTING_CANDLE_SIZE = 6;

    String[] closeValue = new String[TESTING_CANDLE_SIZE];


    @BeforeEach
    void setUp() {

        testingList = new ArrayList<>();

        closeValue[0] = "5540";
        closeValue[1] = "5440";
        closeValue[2] = "5302";
        closeValue[3] = "5200";
        closeValue[4] = "5123";
        closeValue[5] = "4923";

        testingList.add(CandlestickCreationHelper.createCandlestick("5440",
                "5440",
                "5200",
                "5600",
                "15000",
                1577836800000L));
        testingList.add(CandlestickCreationHelper.createCandlestick( "5440",
                "5440",
                "5200",
                "5600",
                "15000",
                1580515200000L));
        testingList.add(CandlestickCreationHelper.createCandlestick("5302",
                "5440",
                "5200",
                "5600",
                "15000",
                1583020800000L));
        testingList.add(CandlestickCreationHelper.createCandlestick("5200",
                "5440",
                "5200",
                "5600",
                "15000",
                1585699200000L));
        testingList.add(CandlestickCreationHelper.createCandlestick("5123",
                "5440",
                "5200",
                "5600",
                "15000",
                1588291200000L));
        testingList.add(CandlestickCreationHelper.createCandlestick("4923",
                "5440",
                "5200",
                "5600",
                "15000",
                1590969600000L));

        trainingHelper.setCandlestickList(testingList);
    }

    @Test
    void getNormalizedClose() {
        //given
        float initialValue = 1000f;
        given(financeSimulation.getInitialAccountBalance()).willReturn(initialValue);


        // when
        List<Float> normalizedValues = trainingHelper.getNormalizedClose();

        // then
        assertThat(normalizedValues.get(0)).isEqualTo(initialValue);
    }

    @Test
    void getDates() throws ParseException {

        // when
        List<Date> returnedValues = trainingHelper.getDates();
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        // then
        List<Date> expectedList = new ArrayList<>();
        expectedList.add(isoFormat.parse("2020-01-01T00:00:00"));
        expectedList.add(isoFormat.parse("2020-02-01T00:00:00"));
        expectedList.add(isoFormat.parse("2020-03-01T00:00:00"));
        expectedList.add(isoFormat.parse("2020-04-01T00:00:00"));
        expectedList.add(isoFormat.parse("2020-05-01T00:00:00"));
        expectedList.add(isoFormat.parse("2020-06-01T00:00:00"));


        for (int i = 0; i < expectedList.size(); i++){
            assertThat(returnedValues.get(i)).isEqualTo(expectedList.get(i));
        }

    }


    @Test
    void initializeEpoch() {
        //given
        float expectedValue = 5440f;

        // when
        StateUtil returnedStateUtil = trainingHelper.initializeEpoch();

        // then
        for (int i = 1; i <= 5; i++){
            assertThat(returnedStateUtil.getInputs().get(i-1).getClose()).isEqualTo(Float.parseFloat(closeValue[i]));
        }


    }

    @Test
    void getCloseFromCandlestickByIndex() {
        //given

        // when
        float returnedValue = trainingHelper.getCloseFromCandlestickByIndex(1);

        // then
        assertThat(returnedValue).isEqualTo(Float.parseFloat(closeValue[1]));
    }
}