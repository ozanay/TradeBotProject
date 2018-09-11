package com.trade.bot.command;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.trade.bot.configuration.IndicatorFeature;
import com.trade.bot.configuration.StartConfiguration;
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
public class CommandExecutor {
    private static final Logger logger = Logger.getLogger(CommandExecutor.class.getName());
    private static CommercialDecisionMakerRunner runner;
    
    void start(String configurationFilePath) throws IOException {
        String configurationContent = FileUtil.read(configurationFilePath);
        logger.log(Level.FINE, () -> "Configuration content is " + configurationContent);

        StartConfiguration startConfiguration = JsonUtil.parse(configurationContent, StartConfiguration.class);
        LoggerProvider.configureLoggerProvider(startConfiguration.getLogging().getPath());
    
        createRunner(startConfiguration);
        runner.start();
    }
    
    void stop() {
        if (runner != null) {
            runner.stop();
            System.exit(0);
        } else {
            logger.log(Level.SEVERE, () -> "RUNNER is empty.");
        }
    }
    
    private static void createRunner(StartConfiguration startConfiguration) throws IOException {
        TradeClient tradeClient = TradeClientFactory.create();
        IndicatorFeature indicatorFeature = startConfiguration.getIndicatorFeature();
        runner = CommercialDecisionMakerRunnerFactory
            .create(indicatorFeature.getIndicatorEnum(), indicatorFeature.getParameters().toString(), tradeClient,
                startConfiguration.getTradeSymbol(), startConfiguration.getCandleStickInterval());
    }
}
