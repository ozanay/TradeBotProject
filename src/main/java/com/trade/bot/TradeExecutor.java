package com.trade.bot;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.trade.bot.configuration.IndicatorFeature;
import com.trade.bot.configuration.InitialConfiguration;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientFactory;
import com.trade.bot.data.decisionmaker.CommercialDecisionMakerRunner;
import com.trade.bot.data.decisionmaker.CommercialDecisionMakerRunnerFactory;
import com.trade.bot.logging.LoggerProvider;
import com.trade.bot.util.FileUtil;
import com.trade.bot.util.JsonUtil;

/**
 * @author Ozan Ay
 */
class TradeExecutor {
    private static final Logger logger = Logger.getLogger(TradeExecutor.class.getName());
    private static CommercialDecisionMakerRunner runner;
    
    void start(String configurationFilePath) throws IOException {
        String configurationContent = FileUtil.read(configurationFilePath);
        logger.log(Level.FINE, () -> "Configuration content is " + configurationContent);

        InitialConfiguration initialConfiguration = JsonUtil.parse(configurationContent, InitialConfiguration.class);
        LoggerProvider.configureLoggerProvider(initialConfiguration.getLogging().getPath());
    
        createRunner(initialConfiguration);
        runner.start();
        logger.info("Trader is running.");
    }
    
    private static void createRunner(InitialConfiguration initialConfiguration) throws IOException {
        TradeClient tradeClient = TradeClientFactory.create();
        IndicatorFeature indicatorFeature = initialConfiguration.getIndicatorFeature();
        runner = CommercialDecisionMakerRunnerFactory
            .create(indicatorFeature.getParameters().toString(), tradeClient,
                initialConfiguration.getTradeSymbol(), initialConfiguration.getCandleStickInterval());
    }
}
