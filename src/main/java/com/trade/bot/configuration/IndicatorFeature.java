package com.trade.bot.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.trade.bot.data.indicator.IndicatorEnum;

/**
 * @author Ozan Ay
 */
public class IndicatorFeature {
    @JsonProperty ("name")
    private IndicatorEnum indicatorEnum;
    
    @JsonProperty("parameters")
    private ObjectNode parameters;
    
    public IndicatorEnum getIndicatorEnum() {
        return indicatorEnum;
    }
    
    public ObjectNode getParameters() {
        return parameters;
    }
}
