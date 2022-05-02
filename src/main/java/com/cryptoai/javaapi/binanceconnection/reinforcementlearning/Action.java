package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import java.util.List;
import java.util.Random;

public enum Action {

    SELL,

    BUY,

    HOLD;

    private static final List<Action> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    public static Action getActionByIndex(final int index) {
        return VALUES.get(index);
    }
}
