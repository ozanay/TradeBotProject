package com.trade.bot.data.inducator;

import com.trade.bot.TradeData;

import java.util.List;

/**
 * @author Ozan Ay
 */
public interface Indicator {
    void apply(List<TradeData> tradeData);
}
