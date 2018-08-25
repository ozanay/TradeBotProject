package com.trade.bot.data.client;

import com.trade.bot.TradeSymbol;

/**
 * @author Ozan Ay
 */
public interface TradeWebSocketClient {
    void subscribeEvent(TradeSymbol tradeSymbol);
}
