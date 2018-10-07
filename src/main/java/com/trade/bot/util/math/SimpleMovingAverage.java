package com.trade.bot.util.math;

import java.util.List;

import static com.trade.bot.util.UtilConstants.ZERO;

/**
 * @author Ozan Ay
 */
public class SimpleMovingAverage {
    public static double calculateMostRecently(List<Double> values, int period) {
        double sumOfPeriodValues = calculateMostRecentlySumOfPeriodValues(values, period);
        return sumOfPeriodValues / period;
    }

    private static double calculateMostRecentlySumOfPeriodValues(List<Double> values, int period) {
        double sum = ZERO;
        int lastElementIndex = values.size() - 1;
        for (int indexFromLast = 0; indexFromLast < period; indexFromLast++) {
            sum += values.get(lastElementIndex - indexFromLast);
        }

        return sum;
    }
}
