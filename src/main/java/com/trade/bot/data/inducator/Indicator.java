package com.trade.bot.data.inducator;

import com.trade.bot.TradeData;

/**
 * @author Ozan Ay
 */
public interface Indicator {
    IndicatorResult apply(TradeData tradeData);

    boolean warmUp(TradeData tradeData);
}
