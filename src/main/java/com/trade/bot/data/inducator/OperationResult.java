package com.trade.bot.data.inducator;

/**
 * @author Ozan Ay
 */
public class OperationResult {
    private final double min;
    private final double max;
    private final double average;

    OperationResult(double min, double max, double average) {
        this.min = min;
        this.max = max;
        this.average = average;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getAverage() {
        return average;
    }
}
