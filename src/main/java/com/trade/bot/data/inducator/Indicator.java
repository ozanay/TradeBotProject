package com.trade.bot.data.inducator;

import com.trade.bot.TradeData;

import java.util.List;

/**
 * @author Ozan Ay
 */
public interface Indicator {
    IndicatorResult apply(List<TradeData> tradeData);
}
