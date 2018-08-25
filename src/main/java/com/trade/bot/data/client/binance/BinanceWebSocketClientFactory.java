package com.trade.bot.data.client.binance;

import com.trade.bot.TradeData;
import com.trade.bot.data.client.TradeWebSocketClient;

import java.util.concurrent.BlockingQueue;

/**
 * @author Ozan Ay
 */
public class BinanceWebSocketClientFactory {
    public static TradeWebSocketClient getClient(BlockingQueue<TradeData> tradeDataQueue) {
        return new BinanceWebSocketClient(tradeDataQueue);
    }
}
