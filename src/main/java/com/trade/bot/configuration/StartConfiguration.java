package com.trade.bot.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClientCandleStickInterval;

/**
 * @author Ozan Ay
 */
public class StartConfiguration {
    @JsonProperty ("logging")
    private Logging logging;
    
    @JsonProperty("candleStickBar")
    private TradeClientCandleStickInterval candleStickInterval;
    
    @JsonProperty ("tradeSymbol")
    private TradeSymbol tradeSymbol;
    
    @JsonProperty("indicatorFeature")
    private IndicatorFeature indicatorFeature;
    
    public Logging getLogging() {
        return logging;
    }
    
    public TradeClientCandleStickInterval getCandleStickInterval() {
        return candleStickInterval;
    }
    
    public TradeSymbol getTradeSymbol() {
        return tradeSymbol;
    }
    
    public IndicatorFeature getIndicatorFeature() {
        return indicatorFeature;
    }
}
