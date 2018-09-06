package com.trade.bot.data.client;

import com.trade.bot.TradeData;
import com.trade.bot.data.client.binance.BinanceWebSocketClientFactory;
import com.trade.bot.data.decisionmaker.CommercialDecisionMaker;

import java.util.concurrent.BlockingQueue;

/**
 * @author Ozan Ay
 */
public class TradeWebSocketClientFactory {
    private TradeWebSocketClientFactory() {}
    
    public static TradeWebSocketClient create(BlockingQueue<TradeData> blockingQueue) {
        return BinanceWebSocketClientFactory.getClient(blockingQueue);
    }
}
