package com.trade.bot.util.math;

import java.util.List;
import java.util.stream.Collectors;

import static com.trade.bot.util.UtilConstants.ZERO;

/**
 * @author Ozan Ay
 */
public class ExponentialMovingAverage {
    private double ema = ZERO;
    private final double multiplier;
    private final int period;

    public ExponentialMovingAverage(int period) {
        this.period = period;
        this.multiplier = 2.0 / (period + 1);
    }

    public double calculateThrough(List<Double> values) {
        if (areValuesValid(values)) {
            return ZERO;
        }

        List<Double> iterativeValues = values.stream().limit(period).collect(Collectors.toList());
        ema = SimpleMovingAverage.calculateMostRecently(iterativeValues, period);
        for (int index = period; index < values.size(); index++) {
           double value = values.get(index);
           ema = calculateWithPreviousEma(value);
        }

        return ema;
    }

    private boolean areValuesValid(List<Double> values) {
        return values.isEmpty() || values.size() < period;
    }

    public double calculateWithPreviousEma(double value) {
        return (value - ema) * multiplier + ema;
    }
}
