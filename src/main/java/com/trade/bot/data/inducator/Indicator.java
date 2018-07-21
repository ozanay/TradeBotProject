package com.trade.bot.data.inducator;

import com.trade.bot.TradeData;

import java.util.List;

/**
 * @author Ozan Ay
 */
public interface Indicator {
    AppliedIndicatorResult apply(List<TradeData> tradeData);
}
