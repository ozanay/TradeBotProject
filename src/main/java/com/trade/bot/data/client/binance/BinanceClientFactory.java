package com.trade.bot.data.client.binance;

import com.trade.bot.data.client.TradeClient;

/**
 * @author Ozan Ay
 */
public class BinanceClientFactory {
    public static TradeClient getClient() {
        return new BinanceClient();
    }
}
