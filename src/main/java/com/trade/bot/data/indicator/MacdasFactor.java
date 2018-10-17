package com.trade.bot.data.indicator;

import com.trade.bot.util.math.ExponentialMovingAverage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ozan Ay
 */
class MacdasFactor {
    private final List<Double> valuesForEma = new ArrayList<>();
    private final ExponentialMovingAverage ema;
    private final int period;
    private boolean isInitialRun = true;

    MacdasFactor(int period) {
        this.ema = new ExponentialMovingAverage(period);
        this.period = period;
    }

    void warmUp(double value) {
        valuesForEma.add(value);
    }

    double calculate(double value) {
        return (isInitialRun) ? calculateInitialEma(value) : ema.calculateWithPreviousEma(value);
    }

    boolean isReady() {
        return valuesForEma.size() >= period;
    }

    private double calculateInitialEma(double value) {
        valuesForEma.add(value);
        double initialEma = this.ema.calculateInitiallyThroughList(valuesForEma);
        isInitialRun = false;
        return initialEma;
    }
}
