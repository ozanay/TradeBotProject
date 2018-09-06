package com.trade.bot.data.client.binance;

import com.trade.bot.TradeData;
import com.trade.bot.data.client.TradeWebSocketClient;
import com.trade.bot.data.decisionmaker.CommercialDecisionMaker;

import java.util.concurrent.BlockingQueue;

/**
 * @author Ozan Ay
 */
public class BinanceWebSocketClientFactory {
    private BinanceWebSocketClientFactory() {}
    
    public static TradeWebSocketClient getClient(BlockingQueue<TradeData> blockingQueue) {
        return new BinanceWebSocketClient(blockingQueue);
    }
}
