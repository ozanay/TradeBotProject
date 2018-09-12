package com.trade.bot.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ozan Ay
 */
public class HullMovingAverageParameters {
    @JsonProperty("period")
    private int period;
    
    public int getPeriod() {
        return period;
    }
}
