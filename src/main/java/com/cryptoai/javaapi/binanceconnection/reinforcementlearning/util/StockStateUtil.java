package com.cryptoai.javaapi.binanceconnection.reinforcementlearning.util;

public class StockStateUtil {

    public static int getMaxValueIndex(final double[] values) {
        int maxAt = 0;

        for (int i = 0; i < values.length; i++) {
            maxAt = values[i] > values[maxAt] ? i : maxAt;
        }

        return maxAt;
    }
}
