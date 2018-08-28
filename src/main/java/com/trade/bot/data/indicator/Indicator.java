package com.trade.bot.data.indicator;

import com.trade.bot.TradeData;

/**
 * @author Ozan Ay
 */
public interface Indicator {
    IndicatorResult apply(TradeData tradeData);

    void warmUp(TradeData tradeData);
}
