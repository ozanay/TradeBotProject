package com.trade.bot.util.math;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ozan Ay
 */
public class ExponentialMovingAverage {
    private double ema = Double.MIN_VALUE;
    private final double multiplier;
    private final int period;

    public ExponentialMovingAverage(int period) {
        this.period = period;
        this.multiplier = 2.0 / (period + 1);
    }

    public double calculateInitiallyThroughList(List<Double> values) {
        if (areValuesNotValid(values)) {
            throw new IllegalArgumentException("Values size cannot be less than period size.");
        }

        List<Double> iterativeValues = values.stream().limit(period).collect(Collectors.toList());
        ema = SimpleMovingAverage.calculateMostRecently(iterativeValues, period);
        if (values.size() > period) {
            for (int index = period; index < values.size(); index++) {
                double value = values.get(index);
                ema = calculateWithPreviousEma(value);
            }
        }

        return ema;
    }

    private boolean areValuesNotValid(List<Double> values) {
        return values == null || values.isEmpty() || values.size() < period;
    }

    public double calculateWithPreviousEma(double value) {
        if (ema == Double.MIN_VALUE) {
            throw new IllegalArgumentException("Initial calculation is necessary.");
        }

        return ema = (value - ema) * multiplier + ema;
    }
}
