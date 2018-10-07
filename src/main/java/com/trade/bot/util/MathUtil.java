package com.trade.bot.util;

import java.util.List;

/**
 * @author Ozan Ay
 */
public class MathUtil {
    private static final double ZERO = 0.0;
    public static double calculateWeightedMovingAverage(List<Double> values, int timePeriod) {
        if (timePeriod < 0 || timePeriod > values.size()) {
            throw new IllegalArgumentException("Invalid time period.");
        }

        double weightedMovingAverage = ZERO;
        if (timePeriod > 0) {
            double weightedAverageSum = ZERO;
            double weightSum = ZERO;
            int index = values.size() - 1;
            for (int weight = timePeriod; weight > 0; weight--) {
                weightedAverageSum += values.get(index) * weight;
                weightSum += weight;
                index--;
            }

            weightedMovingAverage = weightedAverageSum / weightSum;
        }

        return weightedMovingAverage;
    }
}
