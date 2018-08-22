package com.trade.bot.math;

import java.util.List;

import static com.trade.bot.Constants.FIRST;

/**
 * @author Ozan Ay
 */
public class WeightedMovingAverage {
    public static double calculate(List<Double> values, int timePeriod) {
        if (timePeriod < 0 || timePeriod > values.size()) {
            throw new IllegalArgumentException("Invalid time period.");
        }

        double weightedMovingAverage = 0.0;
        if (timePeriod > 0) {
            double weightedSum = 0.0;
            int index = 0;
            for (int weight = timePeriod; weight > 0; weight--) {
                weightedSum += values.get(index) * weight;
                index++;
            }

            weightedMovingAverage = weightedSum / (timePeriod  * (timePeriod + 1) * 0.5);
        }

        return weightedMovingAverage;
    }
}
