package com.trade.bot;

import com.trade.bot.data.DataProcessorExecutor;
import com.trade.bot.data.DataProcessorExecutorFactory;
import com.trade.bot.data.client.BinanceWebSocketClient;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BotRunner {
    public static void main(String[] args) {
//        TradeClient tradeClient = TradeClientFactory.create();
//        tradeClient.getData(TradeSymbol.BTC_USDT);

        BlockingQueue<TradeData> tradeDataQueue = new LinkedBlockingQueue<>();
        new BinanceWebSocketClient(tradeDataQueue).subscribeEvent();
        DataProcessorExecutor dataProcessorExecutor = DataProcessorExecutorFactory.instance.create(tradeDataQueue);
        dataProcessorExecutor.start();
    }
}
