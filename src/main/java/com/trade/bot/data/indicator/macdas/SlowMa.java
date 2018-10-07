package com.trade.bot.data.indicator.macdas;

import com.trade.bot.util.math.ExponentialMovingAverage;

/**
 * @author Ozan Ay
 */
public class SlowMa implements MacdasFactor {
    private final int period;
    private final ExponentialMovingAverage ema;

    public SlowMa(int period) {
        this.period = period;
        this.ema = new ExponentialMovingAverage(period);
    }

    @Override
    public double calculate(double value) {
        return 0;
    }
}
