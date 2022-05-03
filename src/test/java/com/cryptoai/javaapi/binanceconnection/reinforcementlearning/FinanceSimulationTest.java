package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinanceSimulationTest {

    FinanceSimulation financeSimulation;

    @BeforeEach
    void setUp() {
        financeSimulation = new FinanceSimulation();
        financeSimulation.resetSimulation();
    }

    @Test
    void buySimulation() {
        float netWorth = financeSimulation.buySimulation(1000);
        assertThat(Math.round(netWorth)).isEqualTo(Math.round(1000 * 0.999));
    }

    @Test
    void sellSimulation() {
        float netWorth = financeSimulation.sellSimulation(1000);
        assertThat(netWorth).isEqualTo(1000);
    }
}