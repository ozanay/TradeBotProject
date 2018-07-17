package com.trade.bot;

import com.trade.bot.data.DataProcessorExecutor;
import com.trade.bot.data.DataProcessorExecutorFactory;
import com.trade.bot.data.client.BinanceWebSocketClient;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BotRunner {
    public static void main(String[] args) {
        BlockingQueue<TradeData> tradeDataQueue = new LinkedBlockingQueue<>();
        new BinanceWebSocketClient(tradeDataQueue).subscribeEvent();
        DataProcessorExecutor dataProcessorExecutor = DataProcessorExecutorFactory.instance.create(tradeDataQueue);
        dataProcessorExecutor.start();
    }
}
