package com.trade.bot.data.inducator;

/**
 * @author Ozan Ay
 */
public class AppliedIndicatorResult {
    private static final AppliedIndicatorResult EmptyResult = new AppliedIndicatorResult();
    private final double trigger;
    private final double fish;

    private AppliedIndicatorResult() {
        this.trigger = 0;
        this.fish = 0;
    }

    public static AppliedIndicatorResult emptyResult() {
        return EmptyResult;
    }

    public AppliedIndicatorResult(double trigger, double fish) {
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
