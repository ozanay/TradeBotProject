package com.trade.bot.data.client;

/**
 * @author Ozan Ay
 */
public class TradeClientFactory {
    public TradeClient create() {
        return BinanceClient.getInstance();
    }
}
