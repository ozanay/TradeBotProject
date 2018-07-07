package com.trade.bot.data.client;

/**
 * @author Ozan Ay
 */
public class TradeClientFactory {
    public TradeClient createClient() {
        return BinanceClient.getInstance();
    }
}
