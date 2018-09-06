package com.trade.bot;

import com.binance.api.client.domain.account.Trade;
import com.trade.bot.data.client.*;
import com.trade.bot.data.decisionmaker.CommercialDecisionMaker;
import com.trade.bot.data.decisionmaker.CommercialDecisionMakerFactory;
import com.trade.bot.data.indicator.Indicator;
import com.trade.bot.data.indicator.IndicatorEnum;
import com.trade.bot.data.indicator.IndicatorFactory;
import com.trade.bot.logging.LoggerProvider;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BotRunner {
    public static void main(String[] args) throws IOException {
        // int fmal = 3;
        // int smal = 5;
        int period = 16;
        String logPath = "C:\\Users\\OPC\\Desktop\\logs\\trade_bot.log";
        LoggerProvider.configureLoggerProvider(logPath);

        Indicator indicator = IndicatorFactory.getIndicator(IndicatorEnum.HULL_MOVING_AVERAGE, period);
        TradeClient tradeClient = TradeClientFactory.create();
        CommercialDecisionMaker commercialDecisionMaker = CommercialDecisionMakerFactory.create(IndicatorEnum.HULL_MOVING_AVERAGE, indicator, tradeClient,
                        TradeSymbol.BTC_USDT, TradeClientCandleStickInterval.FIFTEEN_MINUTES);
        commercialDecisionMaker.warmUp();
    
        BlockingQueue<TradeData> queue = new LinkedBlockingQueue<>();
        TradeWebSocketClient tradeWebSocketClient = TradeWebSocketClientFactory.create(queue);
        
        tradeWebSocketClient.subscribeEvent(TradeSymbol.BTC_USDT);
    }
}
