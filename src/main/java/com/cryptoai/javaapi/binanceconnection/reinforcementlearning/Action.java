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

    /**
     * Gets an action based on provided index.
     *
     * @param index Index based on which action is selected.
     * @return Returns one of Action values.
     */
    public static Action getActionByIndex(final int index) {
        return VALUES.get(index);
    }
}
