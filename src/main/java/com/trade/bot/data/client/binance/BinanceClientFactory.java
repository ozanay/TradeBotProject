package com.trade.bot.data.client.binance;

import com.trade.bot.TradeData;
import com.trade.bot.data.client.TradeClient;

import java.util.concurrent.BlockingQueue;

/**
 * @author Ozan Ay
 */
public class BinanceClientFactory {
    private BinanceClientFactory() {}
    
    public static TradeClient getClient(BlockingQueue<TradeData> tradeDataQueue) {
        return new BinanceClient(tradeDataQueue);
    }
}
