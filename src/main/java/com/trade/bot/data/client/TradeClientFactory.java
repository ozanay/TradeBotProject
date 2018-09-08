package com.trade.bot.data.client;

import com.trade.bot.TradeData;
import com.trade.bot.data.client.binance.BinanceClientFactory;

import java.util.concurrent.BlockingQueue;

/**
 * @author Ozan Ay
 */
public class TradeClientFactory {
    private TradeClientFactory() {}
    
    public static TradeClient create(BlockingQueue<TradeData> tradeDataQueue) {
        return BinanceClientFactory.getClient(tradeDataQueue);
    }
}
