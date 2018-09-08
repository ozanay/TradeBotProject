package com.trade.bot;

import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientCandleStickInterval;
import com.trade.bot.data.client.TradeClientFactory;
import com.trade.bot.data.decisionmaker.CommercialDecisionMakerRunner;
import com.trade.bot.data.decisionmaker.CommercialDecisionMakerRunnerFactory;
import com.trade.bot.data.indicator.IndicatorEnum;
import com.trade.bot.logging.LoggerProvider;

import java.io.IOException;

public class BotRunner {
    public static void main(String[] args) throws IOException {
        // int fmal = 3;
        // int smal = 5;
        String logPath = "C:\\Users\\z003u8xt\\Desktop\\logs\\trade_bot.log";
        LoggerProvider.configureLoggerProvider(logPath);
        String indicatorParameters = "{\n" +
            "\t\"period\": 16\n" +
            "}";
    
        TradeClient tradeClient = TradeClientFactory.create();
        CommercialDecisionMakerRunner runner = CommercialDecisionMakerRunnerFactory
            .create(IndicatorEnum.HULL_MOVING_AVERAGE, indicatorParameters, tradeClient,
                TradeSymbol.BTC_USDT, TradeClientCandleStickInterval.FIFTEEN_MINUTES);
        runner.start();
    }
}
