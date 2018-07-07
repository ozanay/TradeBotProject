package com.trade.bot.client;

/**
 * @author Ozan Ay
 */
public class TradeClientFactory {
    public TradeClient createClient() {
        return BinanceClient.getInstance();
    }
}
