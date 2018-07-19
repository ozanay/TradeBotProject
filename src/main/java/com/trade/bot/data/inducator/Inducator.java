package com.trade.bot.data.inducator;

import com.trade.bot.TradeData;

import java.util.List;

/**
 * @author Ozan Ay
 */
public interface Inducator {
    AppliedInducatorResult apply(List<TradeData> tradeData);
}
