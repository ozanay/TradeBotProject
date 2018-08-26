package com.trade.bot;

import com.trade.bot.data.DataProcessorExecutor;
import com.trade.bot.data.DataProcessorExecutorFactory;
import com.trade.bot.data.client.TradeWebSocketClient;
import com.trade.bot.data.client.TradeWebSocketClientFactory;
import com.trade.bot.data.indicator.Indicator;
import com.trade.bot.data.indicator.IndicatorEnum;
import com.trade.bot.data.indicator.IndicatorFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BotRunner {
    public static void main(String[] args) {
        BlockingQueue<TradeData> tradeDataQueue = new LinkedBlockingQueue<>();
        TradeWebSocketClient tradeWebSocketClient = TradeWebSocketClientFactory.create(Market.BINANCE, tradeDataQueue);
        tradeWebSocketClient.subscribeEvent(TradeSymbol.BTC_USDT);
        Indicator indicator = IndicatorFactory.getIndicator(IndicatorEnum.MAVILIM);
        DataProcessorExecutor dataProcessorExecutor = DataProcessorExecutorFactory.instance.create(tradeDataQueue, indicator);
        dataProcessorExecutor.start();
    }
}
