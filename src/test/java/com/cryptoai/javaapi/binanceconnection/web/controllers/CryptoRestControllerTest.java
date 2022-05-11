package com.cryptoai.javaapi.binanceconnection.web.controllers;

import com.cryptoai.javaapi.binanceconnection.web.models.InputConfiguration;
import com.cryptoai.javaapi.binanceconnection.web.models.Result;
import com.cryptoai.javaapi.binanceconnection.binance.DataFactory;
import com.cryptoai.javaapi.binanceconnection.web.behavior.ResultsBehavior;
import com.cryptoai.javaapi.binanceconnection.reinforcementlearning.NetworkInitializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CryptoRestControllerTest {

    @Mock
    ResultsBehavior mockResultsBehavior;

    @InjectMocks
    CryptoRestController cryptoRestController;

    MockedStatic<NetworkInitializer> networkInitializerMockedStatic;

    MockedStatic<DataFactory> dataFactoryMockedStatic;

    @BeforeEach
    void setUp() {
        // given

    }

    @Test
    void getCryptoData() {

        //given
        String correctStringDate = "20-Apr-2022";

        dataFactoryMockedStatic =
                Mockito.mockStatic(DataFactory.class);
        dataFactoryMockedStatic.when(()-> DataFactory.newCryptoDataFromCandlesticks(anyString(), anyString())).
                thenAnswer((Answer<Void>) invocation -> null);

        networkInitializerMockedStatic =
                Mockito.mockStatic(NetworkInitializer.class);
        networkInitializerMockedStatic.when(()-> NetworkInitializer.initializeNetwork(anyLong(), anyInt())).
                thenAnswer((Answer<Void>) invocation -> null);

        List<Result> expectedResultList = Collections.EMPTY_LIST;
        given(mockResultsBehavior.computeResults()).willReturn(expectedResultList);

        InputConfiguration inputConfiguration = new InputConfiguration(
                "ETHUSDT",
                correctStringDate,
                123L,
                15000);

        // when
        ResponseEntity<List<Result>> resultListMethod = cryptoRestController.getCryptoData(inputConfiguration, null);

        // then
        dataFactoryMockedStatic.verify(() -> DataFactory.newCryptoDataFromCandlesticks(anyString(), anyString()));
        networkInitializerMockedStatic.verify(() -> NetworkInitializer.initializeNetwork(anyLong(), anyInt()));
        verify(mockResultsBehavior, times(1)).computeResults();

        assertNotNull(resultListMethod);


        networkInitializerMockedStatic.close();
        dataFactoryMockedStatic.close();

    }

    @Test
    void getNeuralNetworkItExists() throws IOException {

        // given a testing file using the 12345 id
        long correctId = 12345;

        // when
        byte[] result = cryptoRestController.getNeuralNetwork(correctId);

        // then
        assertNotNull(result);

    }

    @Test
    void getNeuralNetworkItDoesNotExists() throws IOException {

        // given a non-existent file with the id 99999
        long correctId = 99999;

        // when
        byte[] result = cryptoRestController.getNeuralNetwork(correctId);

        // then
        assertNull(result);

    }


}