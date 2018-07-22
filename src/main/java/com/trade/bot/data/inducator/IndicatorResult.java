package com.trade.bot.data.inducator;

/**
 * @author Ozan Ay
 */
public class IndicatorResult {
    private static final IndicatorResult EmptyResult = new IndicatorResult();
    private final double trigger;
    private final double fish;

    private IndicatorResult() {
        this.trigger = 0;
        this.fish = 0;
    }

    public static IndicatorResult emptyResult() {
        return EmptyResult;
    }

    public IndicatorResult(double trigger, double fish) {
        this.trigger = trigger;
        this.fish = fish;
    }

    public double getTrigger() {
        return trigger;
    }

    public double getFish() {
        return fish;
    }
}
