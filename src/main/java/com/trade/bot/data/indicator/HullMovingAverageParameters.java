package com.trade.bot.data.indicator;

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
