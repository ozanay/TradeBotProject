package com.trade.bot.math;

import java.util.List;

/**
 * @author Ozan Ay
 */
public class MathUtil {
    public static double calculateWeightedMovingAverage(List<Double> values, int timePeriod) {
        if (timePeriod < 0 || timePeriod > values.size()) {
            throw new IllegalArgumentException("Invalid time period.");
        }

        double weightedMovingAverage = 0.0;
        if (timePeriod > 0) {
            double weightedAverageSum = 0.0;
            double weightSum = 0.0;
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
