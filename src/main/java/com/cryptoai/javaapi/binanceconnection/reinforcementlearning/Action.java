package com.cryptoai.javaapi.binanceconnection.reinforcementlearning;

import java.util.List;

public enum Action {

    SELL,

    BUY,

    HOLD;

    private static final List<Action> VALUES = List.of(values());

    public static Action getActionByIndex(final int index) {
        return VALUES.get(index);
    }
}
