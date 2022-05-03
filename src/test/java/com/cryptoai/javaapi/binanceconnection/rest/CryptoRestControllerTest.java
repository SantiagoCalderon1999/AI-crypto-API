package com.cryptoai.javaapi.binanceconnection.rest;

import com.cryptoai.javaapi.binanceconnection.entity.Result;
import com.cryptoai.javaapi.binanceconnection.binanceconnection.DataFactory;
import com.cryptoai.javaapi.binanceconnection.entity.ResultList;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.NetworkInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CryptoRestControllerTest {

    @Mock
    ResultList mockResultList;

    @InjectMocks
    CryptoRestController cryptoRestController;

    @BeforeEach
    void setUp() {
        // given

    }

    @Test
    void getCryptoData() {

        //given
        String correctStringDate = "20-Apr-2022";

        MockedStatic<DataFactory> dataFactoryMockedStatic =
                Mockito.mockStatic(DataFactory.class);
        dataFactoryMockedStatic.when(()-> DataFactory.newCryptoDataFromCandlesticks(anyString(), anyString())).
                thenAnswer((Answer<Void>) invocation -> null);

        MockedStatic<NetworkInitializer> networkInitializerMockedStatic =
                Mockito.mockStatic(NetworkInitializer.class);
        networkInitializerMockedStatic.when(()-> NetworkInitializer.initializeNetwork(anyLong(), anyInt())).
                thenAnswer((Answer<Void>) invocation -> null);

        List<Result> expectedResultList = new ArrayList<>();
        given(mockResultList.computeResults()).willReturn(expectedResultList);

        // when
        List<Result> resultListMethod = cryptoRestController.getCryptoData(
                "ETHUSDT",
                correctStringDate,
                123L,
                15000);

        // then
        dataFactoryMockedStatic.verify(() -> DataFactory.newCryptoDataFromCandlesticks(anyString(), anyString()));
        networkInitializerMockedStatic.verify(() -> NetworkInitializer.initializeNetwork(anyLong(), anyInt()));
        verify(mockResultList, times(1)).computeResults();

        assertThat(resultListMethod).isEqualTo(expectedResultList);

    }


}