package com.trade.bot.data.indicator.macdas;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ozan Ay
 */
class MacdasParameters {
    @JsonProperty("fastPeriod")
    private int fastPeriod;

    @JsonProperty("slowPeriod")
    private int slowPeriod;

    @JsonProperty("signalPeriod")
    private int signalPeriod;

    public int getFastPeriod() {
        return fastPeriod;
    }

    public int getSlowPeriod() {
        return slowPeriod;
    }

    public int getSignalPeriod() {
        return signalPeriod;
    }
}
