package com.trade.bot.data.indicator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ozan Ay
 */
public enum IndicatorEnum {
    @JsonProperty("mavilim")
    MAVILIM("mavilim"),
    @JsonProperty("hma")
    HULL_MOVING_AVERAGE("hma");
    
    private final String id;
    
    IndicatorEnum(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
}
