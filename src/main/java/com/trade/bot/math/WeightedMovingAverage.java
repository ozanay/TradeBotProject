package com.trade.bot.math;

import com.trade.bot.Price;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ozan Ay
 */
public class WeightedMovingAverage {
    private static final int FIRST = 0;

    public static double calculate(List<Price> prices, int timePeriod) {
        if (timePeriod < 0 || timePeriod > prices.size()) {
            throw new IllegalArgumentException("Invalid time period.");
        }

        double weightedMovingAverage = 0;
        if (timePeriod > 0) {
            List<Price> sortedPrices = prices.stream().sorted(Price::compareTo).collect(Collectors.toList());
            double weightedSum = 0;
            for (int i = timePeriod; i > 0; i--) {
                weightedSum += sortedPrices.remove(FIRST).getValue() * i;
            }
            weightedMovingAverage = weightedSum / (timePeriod  * (timePeriod + 1) / 2);
        }

        return weightedMovingAverage;
    }
}
