package com.trade.bot.data.indicator;

import com.trade.bot.util.math.ExponentialMovingAverage;

import java.util.ArrayList;
import java.util.List;

import static com.trade.bot.util.UtilConstants.ZERO;

/**
 * @author Ozan Ay
 */
class MacdasFactor {
    private final ExponentialMovingAverage ema;
    private final int period;
    private final List<Double> valuesForEma = new ArrayList<>();

    MacdasFactor(int period) {
        this.ema = new ExponentialMovingAverage(period);
        this.period = period;
    }

    double calculate(double value) {
        if (value <= ZERO) {
            return ZERO;
        }

        if (hasInsufficientNumberOfValuesForEmaCalculation()) {
            valuesForEma.add(value);
            return ZERO;
        }

        return (isInitialEmaCalculation()) ? calculateInitialEma(value) : ema.calculateWithPreviousEma(value);
    }

    private double calculateInitialEma(double value) {
        valuesForEma.add(value);
        return ema.calculateInitiallyThroughList(valuesForEma);
    }

    private boolean hasInsufficientNumberOfValuesForEmaCalculation() {
        return valuesForEma.size() < period;
    }

    private boolean isInitialEmaCalculation() {
        return valuesForEma.size() == period;
    }
}
