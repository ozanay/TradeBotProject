package com.trade.bot.data.client;

import com.trade.bot.data.client.binance.BinanceClientFactory;

/**
 * @author Ozan Ay
 */
public class TradeClientFactory {
    private TradeClientFactory() {}
    
    public static TradeClient create() {
        return BinanceClientFactory.getClient();
    }
}
