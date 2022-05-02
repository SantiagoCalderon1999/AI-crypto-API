package com.cryptoai.javaapi.binanceconnection.rest;

import com.binance.api.client.domain.market.Candlestick;
import com.cryptoai.javaapi.binanceconnection.entity.Result;
import com.cryptoai.javaapi.binanceconnection.binanceconnection.CryptoDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CryptoRestControllerTest {

    @Mock
    CryptoDataFactory cryptoDataFactory;

    @InjectMocks
    CryptoRestController cryptoRestController;

    @BeforeEach
    void setUp() {
        // given

    }

    @Test
    void getCryptoData() {

        //given
        Random rnd = new Random();
        Date correctRandomDate = new Date(Math.abs(System.currentTimeMillis() - rnd.nextLong()));
        String correctStringDate = new SimpleDateFormat("dd-MMM-yyyy").format(correctRandomDate);
        List<Candlestick> candlestickList = new ArrayList<>();
        given(cryptoDataFactory.candleStickInitialization(anyString(), anyString())).willReturn(candlestickList);

        // when
        List<Result> view = cryptoRestController.getCryptoData(anyString(), correctStringDate, anyLong(), anyInt());

        // then
        then(cryptoDataFactory).should().candleStickInitialization(anyString(), anyString());

    }
}